package com.sistema_expedientes.exception_handler.message;

import java.util.Map;

public class FieldErrorRequestFormat {

    private final String message;
    private final Map<String, String> causes;

    public FieldErrorRequestFormat(String message, Map<String, String> causes) {
        this.message = message;
        this.causes = causes;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getCauses() {
        return causes;
    }
}
