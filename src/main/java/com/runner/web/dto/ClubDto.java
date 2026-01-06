package com.runner.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClubDto{
    private int id;
    @NotEmpty(message = "Title field should not be empty")
    private String title;
    @NotEmpty(message = "Photo link field should not be empty")
    private String photoUrl;
    @NotEmpty(message = "Content field should not be empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
