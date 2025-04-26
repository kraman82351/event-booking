package com.harshchauhan.irctc_core.modules.trainSeatModule.request;

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
public class BookTrainSeatRequest {
    @NotNull
    @NotBlank
    private String trainId;

    @Positive
    private int seatNumber;
}
