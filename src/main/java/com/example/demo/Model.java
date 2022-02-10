package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Model {
    @NotNull
    private Integer userId;
    private Integer levelId;
    private Integer result;
}
