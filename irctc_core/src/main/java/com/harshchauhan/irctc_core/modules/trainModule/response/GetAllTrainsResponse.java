package com.harshchauhan.irctc_core.modules.trainModule.response;

import java.io.Serializable;
import java.util.List;

import com.harshchauhan.irctc_core.modules.trainModule.dto.TrainDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class GetAllTrainsResponse implements Serializable {
    List<TrainDto> trains;
}
