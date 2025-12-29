package com.user.dto;

import java.time.LocalDateTime;

public record OrderResponseDto(Long id, String product, double amount, long customerId, LocalDateTime createdAt) {
}