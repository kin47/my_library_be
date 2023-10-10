package com.example.my_library.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResp {
    private String username;
    private String token;
    // other properties
}
