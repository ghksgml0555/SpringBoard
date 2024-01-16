package com.board.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

}
