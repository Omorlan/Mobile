package ru.phonemarket.mobile.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

/**
 * Represents a mobile phone entity.
 */
@Builder
@Data
public class MobilePhone {
    /**
     * The ID of the mobile phone in storage.
     */
    private Long id;
    @NotBlank(message = "Phone brand cannot be empty")
    /**
     * The brand of the mobile phone.
     */ private String brand;
    /**
     * The model of the mobile phone.
     */
    @NotBlank(message = "Phone model cannot be empty")
    private String model;
    /**
     * The memory capacity of the mobile phone.
     * Must be a positive number.
     */
    @NotNull(message = "The phone's memory capacity cannot be empty")
    @Positive(message = "The phone's memory capacity should be positive number")
    private Integer memorySize;
    /**
     * The accumulator capacity of the mobile phone.
     * Must be a positive number.
     */
    @NotNull(message = "The phone's accumulator capacity cannot be empty")
    @Positive(message = "The phone's accumulator capacity should be positive number")
    private Integer accumulatorCapacity;
    /**
     * The number of cameras of the mobile phone.
     * Must be a positive number or zero.
     * Default value is 0.
     */
    @PositiveOrZero(message = "The phone's accumulator capacity should be positive number or zero")
    @Builder.Default
    private Integer numberOfCameras = 0;
}
