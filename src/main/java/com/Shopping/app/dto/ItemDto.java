package com.Shopping.app.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
