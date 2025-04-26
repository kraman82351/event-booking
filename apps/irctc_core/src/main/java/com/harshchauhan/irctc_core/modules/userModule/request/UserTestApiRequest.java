package com.harshchauhan.irctc_core.modules.userModule.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTestApiRequest {

    @NotNull
    @Positive(message = "Project ID must be positive")
    int projectId;

    @Positive
    @NotNull
    int respondentFileId;

    String wave;

    @NotEmpty
    List<@NotNull @Positive Integer> respondentIds;

    @NotBlank
    String username;

}
