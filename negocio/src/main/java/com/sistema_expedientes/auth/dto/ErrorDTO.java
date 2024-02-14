package com.sistema_expedientes.auth.dto;

public record ErrorDTO(
        String message
) {
    public static ErrorDTOBuilder builder() {
        return new ErrorDTOBuilder();
    }

    public static class ErrorDTOBuilder {
        private String message;

        public ErrorDTOBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorDTO build() {
            return new ErrorDTO(message);
        }
    }
}
