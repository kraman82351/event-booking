package com.harshchauhan.irctc_core.modules.others.externalService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostsDto {
    private int userId;
    private int id;
    private String title;
    private String body;
}