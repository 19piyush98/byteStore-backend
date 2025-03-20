package com.org.bytestore.controller;

import com.org.bytestore.model.request.AddUserRequest;
import com.org.bytestore.model.request.DeleteUserRequest;
import com.org.bytestore.model.request.SignInRequest;
import com.org.bytestore.model.request.SignUpRequest;
import com.org.bytestore.model.request.UpdateUserInfoRequest;
import com.org.bytestore.model.response.AddUserResponse;
import com.org.bytestore.model.response.DeleteUserResponse;
import com.org.bytestore.model.response.GetAllUsersResponse;
import com.org.bytestore.model.response.SignInResponse;
import com.org.bytestore.model.response.SignUpResponse;
import com.org.bytestore.model.response.UpdateUserInfoResponse;
import com.org.bytestore.model.response.UserDetailsResponse;
import com.org.bytestore.service.UserService;
import com.org.bytestore.utils.GsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/byteStore/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    //get all users
    @GetMapping("/getAll/{id}")
    public ResponseEntity<Optional<GetAllUsersResponse>> getAllUsers(@PathVariable("id") String orgTokenId){
        Optional<GetAllUsersResponse> users = userService.getAllUsers(orgTokenId);
        return ResponseEntity.ok(users);
    }

    //get a user by id
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<UserDetailsResponse>> getUserById(@PathVariable("id") String userId){
        Optional<UserDetailsResponse> user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    //create a new user
    @PostMapping("/signUp")
    public ResponseEntity<Optional<SignUpResponse>> createUser(@RequestBody SignUpRequest user){
        Optional<SignUpResponse> createdUser = userService.addUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/signIn")
    public ResponseEntity<Optional<SignInResponse>> getUserByMail(@RequestBody SignInRequest request){
        log.info("Received request {}", GsonUtils.toJson(request));
        Optional<SignInResponse> signInResponse = userService.getUserByMail(request);
        return ResponseEntity.ok(signInResponse);
    }

    @PostMapping("/addUser")
    public ResponseEntity<Optional<AddUserResponse>> addUser(@RequestBody AddUserRequest request){
        Optional<AddUserResponse> response = userService.addUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/updateUserInfo")
    public ResponseEntity<Optional<UpdateUserInfoResponse>> updateUserInfo(@RequestBody UpdateUserInfoRequest request){
        Optional<UpdateUserInfoResponse> response = userService.updateUserInfo(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<Optional<DeleteUserResponse>> deleteUser(@RequestBody DeleteUserRequest request){
        Optional<DeleteUserResponse> response = userService.deleteUser(request);
        return ResponseEntity.ok(response);
    }
}

