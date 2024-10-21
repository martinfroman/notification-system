package com.martin.dto;

import com.martin.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationRequest {
    @NotNull
    private Category category;

    @NotEmpty
    private String message;

    public NotificationRequest withCategory(Category value) {
        setCategory(value);
        return this;
    }

    public NotificationRequest withMessage(String value) {
        setMessage(value);
        return this;
    }

}
