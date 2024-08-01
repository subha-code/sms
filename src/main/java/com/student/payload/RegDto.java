package com.student.payload;

import lombok.Data;

import java.util.List;

@Data
public class RegDto {

    private List<StudentDto> dto;
    private int pageNo;
}
