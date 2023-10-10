package com.example.my_library.api;

import com.example.my_library.config.JwtUtil;
import com.example.my_library.dto.LoginDTO;
import com.example.my_library.dto.resp.LoginResp;
import com.example.my_library.model.ResponseObject;
import com.example.my_library.model.Users;
import com.example.my_library.repo.UserRepo;
import com.example.my_library.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    public static Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final Date date = new Date();
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UsersService service;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepo userRepo;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Users users = service.findByUsername(loginDTO.getUsername()).orElse(null);
        if(users ==null){
            return new ResponseEntity<>("Lỗi đăng nhập", HttpStatus.UNAUTHORIZED);
        }
        Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDTO.getUsername(), loginDTO.getPassword()
                        )
                );
        Users user = (Users) authenticate.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user.getUid());
        map.put("phone", user.getPhone());
        map.put("mail", user.getEmail());
        String token = jwtUtil.generateToken(user.getUid(), map);
        users.setToken(token);
        userRepo.save(users);
        LoginResp resp = LoginResp.builder()
                .username(user.getUsername())
                .token(token)
                .build();
        return new ResponseEntity<>(resp,  HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseObject> createNewUsers(@Validated @RequestBody Users user) {
        if(service.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(400, "Username existed", null));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreate_at(date.getTime());
        user.setRole(2);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject(201, "Register successfully", service.save(user)));
    }
}