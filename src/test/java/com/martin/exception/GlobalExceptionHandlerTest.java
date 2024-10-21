package com.martin.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleMethodArgumentNotValidException() {
        // Given
        String fieldName = "testField";
        String errorCode = "NotNull";
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        FieldError fieldError = mock(FieldError.class);
        when(ex.getFieldError()).thenReturn(fieldError);
        when(fieldError.getField()).thenReturn(fieldName);
        when(fieldError.getCode()).thenReturn(errorCode);
        when(ex.getFieldError()).thenReturn(fieldError);

        // When
        ResponseEntity<String> response = exceptionHandler.handleMethodArgumentNotValidException(ex);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Field: 'testField' - Problem: 'NotNull'", response.getBody());
    }
}
