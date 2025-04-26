package com.harshchauhan.irctc_core.utility.responseHandler.responseClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataResponse<T> {
    @Getter(onMethod_ = { @JsonProperty("isSuccess") })
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private final boolean isSuccess = true;

    private T data;
    private int responseCode;
}
