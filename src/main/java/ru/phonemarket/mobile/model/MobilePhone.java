package ru.phonemarket.mobile.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MobilePhone {
    private Long id;
    @NotBlank(message = "Phone brand cannot be empty")
    private String brand;
    @NotBlank(message = "Phone model cannot be empty")
    private String model;
    @NotNull(message = "The phone's memory capacity cannot be empty")
    @Positive(message = "The phone's memory capacity should be positive number")
    private Integer memorySize;
    @NotNull(message = "The phone's accumulator capacity cannot be empty")
    @Positive(message = "The phone's accumulator capacity should be positive number")
    private Integer accumulatorCapacity;
    @PositiveOrZero(message = "The phone's accumulator capacity should be positive number or zero")
    @Builder.Default
    private Integer numberOfCameras = 0;
}
