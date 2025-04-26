package com.harshchauhan.irctc_core.modules.userModule.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshchauhan.irctc_core.common.constants.AppConstants;
import com.harshchauhan.irctc_core.modules.userModule.core.UserAuthDetails;
import com.harshchauhan.irctc_core.modules.userModule.request.CreateUserApiRequest;
import com.harshchauhan.irctc_core.modules.userModule.request.UserLoginRequest;
import com.harshchauhan.irctc_core.modules.userModule.service.UserDetailsServiceImpl;
import com.harshchauhan.irctc_core.modules.userModule.service.UserService;
import com.harshchauhan.irctc_core.modules.userModule.service.UserServiceImpl;
import com.harshchauhan.irctc_core.modules.userModule.utils.AuthCookieUtil;
import com.harshchauhan.irctc_core.utility.jwtUtils.JWTUtil;
import com.harshchauhan.irctc_core.utility.responseHandler.ResponseHandler;
import com.harshchauhan.irctc_core.utility.responseHandler.responseClasses.DataResponse;
import com.harshchauhan.irctc_core.utility.responseHandler.responseClasses.SuccessResponse;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("user-auth")
public class UserAuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JWTUtil jwtUtil;

    public UserAuthController(UserServiceImpl userService, AuthenticationManager authenticationManager,
            UserDetailsServiceImpl userDetailsService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/generate-tokens")
    public void logIn() {
        // This method is intentionally left blank. The actual authentication logic is
        // handled in the JWTAuthenticationFilter.
        // The filter will intercept the request and generate tokens if authentication
        // is successful.
        // No need to implement any logic here.
    }

    @PostMapping("/login")
    public ResponseEntity<DataResponse<Map<String, String>>> logIn(
            @Valid @RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword()));

        UserAuthDetails userDetails = (UserAuthDetails) authentication.getPrincipal();

        Map<String, String> tokens = AuthCookieUtil.generateAndAddAuthCookies(userDetails, jwtUtil, response,
                AppConstants.TOKEN_EXPIRATION_TIME_MINUTES,
                AppConstants.REFRESH_TOKEN_EXPIRATION_TIME_MINUTES);

        return ResponseHandler.<Map<String, String>>data(tokens, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SuccessResponse> signUp(@Valid @RequestBody CreateUserApiRequest createUserApiRequest) {
        this.userService.createUser(createUserApiRequest.getName(), createUserApiRequest.getEmail(),
                createUserApiRequest.getPassword());

        return ResponseHandler.success("Success", HttpStatus.OK);
    }
}
