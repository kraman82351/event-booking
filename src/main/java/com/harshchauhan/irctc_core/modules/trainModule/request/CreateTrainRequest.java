package com.harshchauhan.irctc_core.modules.trainModule.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTrainRequest {
    @NotNull
    @NotBlank
    private String name;

    @Positive
    private int numberOfSeats;
}
