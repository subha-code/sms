package com.student.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data

public class StudentDto {

    private Long id;

@Size(min=2, max=20, message = "Should be more than 2 characters")
    private String name;

@Email(message = "Invalid email format")
    private String email;
@Size(min = 10, max = 10, message = "Should be 10 digits")

    private String mobile;

    private String message; // customize the dto
    // If add any members that should be saved as a column name in the database, but in DTO
    // if we add any variables that is not going to impacts in the database base table, use for test purpose
    // for testing purpose, if send the response as custimize way then we use dto
    //Don't expose your entity class directly
    //For signup , don't return only name,mail without giving a message like sign up succesfully so that we can use dto to use a custom memeber in the dto class
    private int totalPages;
    private boolean last;
    private boolean first;
    private int pageSize;
    private int pageNumber;
}