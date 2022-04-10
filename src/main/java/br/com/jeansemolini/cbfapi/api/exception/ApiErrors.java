package br.com.jeansemolini.cbfapi.api.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ApiErrors {

    private Integer status;
    private String timestamp;
    private String title;
    private String detail;
    private List<Object> objects;

    @Getter
    @Builder
    public static class Object {
        private String name;
        private String userMessage;
    }
}
