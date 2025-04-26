package com.harshchauhan.irctc_core.modules.trainModule.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainDto implements Serializable {
    private String trainId;
    private String name;
}
