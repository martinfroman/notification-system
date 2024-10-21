package com.martin.dto;

import com.martin.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotificationRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        // Create a Validator instance for validation tests
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testValidNotificationRequest() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        request.setCategory(Category.SPORTS); // Assuming Category is an enum with SPORTS, FINANCE, etc.
        request.setMessage("This is a valid message");

        // Act
        Set<ConstraintViolation<NotificationRequest>> violations = validator.validate(request);

        // Assert
        assertTrue(violations.isEmpty(), "There should be no validation errors for a valid NotificationRequest");
    }

    @Test
    void testInvalidNotificationRequest_NullCategory() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        request.setCategory(null); // Invalid: Category is null
        request.setMessage("This is a valid message");

        // Act
        Set<ConstraintViolation<NotificationRequest>> violations = validator.validate(request);

        // Assert
        assertEquals(1, violations.size(), "There should be one validation error due to null category");
        ConstraintViolation<NotificationRequest> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage(), "Expected validation message for null category");
        assertEquals("category", violation.getPropertyPath().toString(), "Invalid property should be 'category'");
    }

    @Test
    void testInvalidNotificationRequest_EmptyMessage() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        request.setCategory(Category.FINANCE); // Valid category
        request.setMessage(""); // Invalid: message is empty

        // Act
        Set<ConstraintViolation<NotificationRequest>> violations = validator.validate(request);

        // Assert
        assertEquals(1, violations.size(), "There should be one validation error due to empty message");
        ConstraintViolation<NotificationRequest> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage(), "Expected validation message for empty message");
        assertEquals("message", violation.getPropertyPath().toString(), "Invalid property should be 'message'");
    }

    @Test
    void testInvalidNotificationRequest_NullMessage() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        request.setCategory(Category.MOVIES); // Valid category
        request.setMessage(null); // Invalid: message is null

        // Act
        Set<ConstraintViolation<NotificationRequest>> violations = validator.validate(request);

        // Assert
        assertEquals(1, violations.size(), "There should be one validation error due to null message");
        ConstraintViolation<NotificationRequest> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage(), "Expected validation message for null message");
        assertEquals("message", violation.getPropertyPath().toString(), "Invalid property should be 'message'");
    }
}
