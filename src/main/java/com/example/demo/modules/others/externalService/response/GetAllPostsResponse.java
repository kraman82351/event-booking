package com.example.demo.modules.others.externalService.response;

import java.util.List;

import com.example.demo.modules.others.externalService.dto.PostsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPostsResponse {
    private List<PostsDto> posts;
}