package br.com.jeansemolini.cbfapi.api.exception;

import br.com.jeansemolini.cbfapi.domain.exception.RegistroJaExisteException;
import br.com.jeansemolini.cbfapi.domain.exception.RegistroNaoEncontradoException;
import br.com.jeansemolini.cbfapi.domain.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers,
                                                            HttpStatus status, WebRequest request) {
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<ApiErrors.Object> apiErroObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String mensagem = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return ApiErrors.Object.builder()
                            .name(name)
                            .userMessage(mensagem)
                            .build();
                })
                .collect(Collectors.toList());

        ApiErrors apiErrors = criaApiErroBuilder(status, "Dados inválidos", detail).objects(apiErroObjects).build();

        return handleExceptionInternal(ex, apiErrors, headers, status, request);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<?> handleRegraNegocio(RegraNegocioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String detail = ex.getMessage();

        ApiErrors erros = criaApiErroBuilder(status, "Regra de negócio", detail).build();

        return handleExceptionInternal(ex, erros, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<?> handleRegistroNaoEncontrado(RegistroNaoEncontradoException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();

        ApiErrors erros = criaApiErroBuilder(status, "Registro não encontrado", detail).build();

        return handleExceptionInternal(ex, erros, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(RegistroJaExisteException.class)
    public ResponseEntity<?> handleRegistroJaExistente(RegistroJaExisteException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String detail = ex.getMessage();

        ApiErrors erros = criaApiErroBuilder(status, "Registro duplicado", detail).build();

        return handleExceptionInternal(ex, erros, new HttpHeaders(), status, request);
    }

    private ApiErrors.ApiErrorsBuilder criaApiErroBuilder(HttpStatus status, String title, String detail) {

        return ApiErrors.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now().toString())
                .title(title)
                .detail(detail);
    }
}
