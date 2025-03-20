package com.org.bytestore.service;

import com.org.bytestore.audit.logbuilder.UserLogBuilder;
import com.org.bytestore.mapper.request.AddUserRequestToUserMapper;
import com.org.bytestore.mapper.request.SignUpRequestToOrganisationMapper;
import com.org.bytestore.mapper.request.SignUpRequestToUserMapper;
import com.org.bytestore.mapper.request.UpdateUserInfoRequestToUserMapper;
import com.org.bytestore.mapper.response.UserToAddUserResponseMapper;
import com.org.bytestore.mapper.response.UserToGetAllUsersResponseMapper;
import com.org.bytestore.mapper.response.UserToUserDetailsResponseMapper;
import com.org.bytestore.mapper.response.UserToSignInResponseMapper;
import com.org.bytestore.mapper.response.UserToSignUpResponseMapper;
import com.org.bytestore.model.entity.Organisation;
import com.org.bytestore.model.entity.User;
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
import com.org.bytestore.model.validation.ValidationResult;
import com.org.bytestore.repository.OrganisationRepository;
import com.org.bytestore.repository.UserRepository;
import com.org.bytestore.utils.GsonUtils;
import com.org.bytestore.validator.request.RequestAuthValidator;
import com.org.bytestore.validator.request.SignUpRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    private final OrganisationRepository organisationRepo;

    private final SignUpRequestToUserMapper signUpRequestToUserMapper;

    private final SignUpRequestValidator signUpRequestValidator;

    private final RequestAuthValidator requestAuthValidator;

    private final UserToSignUpResponseMapper signUpResponseMapper;

    private final UserToSignInResponseMapper signInResponseMapper;

    private final UserToUserDetailsResponseMapper userDetailsToUserDetailsResponseMapper;

    private final AddUserRequestToUserMapper addUserRequestToUserMapper;

    private final UserToAddUserResponseMapper userToAddUserResponseMapper;

    private final UserToGetAllUsersResponseMapper userToGetAllUsersResponseMapper;

    private final UpdateUserInfoRequestToUserMapper updateUserInfoRequestToUserMapper;

    private final SignUpRequestToOrganisationMapper signUpRequestToOrganisationMapper;

    private final UserLogBuilder userLogBuilder;

    public Optional<GetAllUsersResponse> getAllUsers(@RequestBody String id) {
        log.info("Finding all users for id {}", id);
        Optional<List<User>> users = userRepo.findAllByOrgTokenId(id);
        if (users.isEmpty()) {
            return Optional.empty();
        }
        GetAllUsersResponse response = userToGetAllUsersResponseMapper.map(users.get());
        return Optional.of(response);
    }

    public Optional<UserDetailsResponse> getUserById(@RequestBody String userId) {
        log.info("Finding user by id {}", userId);
        Optional<User> userDetail = userRepo.findById(userId);
        log.info("Found user {}", userDetail);
        if (userDetail.isPresent()) {
            UserDetailsResponse response = userDetailsToUserDetailsResponseMapper.map(userDetail.get());
            return Optional.of(response);
        }
        return Optional.of(UserDetailsResponse.builder()
                .success(Boolean.FALSE)
                .message("User is not found...")
                .build());
    }

    public Optional<SignInResponse> getUserByMail(@RequestBody SignInRequest request) {
        Optional<User> user = userRepo.findByEmail(request.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            log.info("Found user {}", GsonUtils.toJson(user.get()));
            SignInResponse response = signInResponseMapper.map(user.get());
            return Optional.of(response);
        }
        return Optional.of(SignInResponse.builder()
                .isValid(false)
                .message("User with given credentials not found...")
                .build());
    }

    public Optional<SignUpResponse> addUser(@RequestBody SignUpRequest request) {
        try {
            ValidationResult validationResult = signUpRequestValidator.validate(request);
            if (validationResult.getIsValid()) {
                User user = signUpRequestToUserMapper.map(request);
                userRepo.save(user);
                Organisation organisation = signUpRequestToOrganisationMapper.map(request, user.getOrgTokenId());
                organisationRepo.save(organisation);

                userLogBuilder.buildAndPublish(user);
                SignUpResponse response = signUpResponseMapper.map(user);
                return Optional.of(response);
            }
            return Optional.of(SignUpResponse.builder()
                    .message(validationResult.getValidationFailureReason())
                    .success(Boolean.FALSE)
                    .build());
        } catch (final Exception err) {
            log.error("Unable to create user for {}, failed with exception", request, err);
        }
        return Optional.empty();
    }

    public Optional<AddUserResponse> addUser(@RequestBody AddUserRequest request) {
        try {
            ValidationResult validationResult = signUpRequestValidator.validate(request);
            ValidationResult authValidationResult = requestAuthValidator.validate(request);
            if (validationResult.getIsValid() && authValidationResult.getIsValid()) {
                User user = addUserRequestToUserMapper.map(request);
                userRepo.save(user);
                userLogBuilder.buildAndPublish(request);
                AddUserResponse response = userToAddUserResponseMapper.map(user);
                return Optional.of(response);
            }
            return Optional.of(AddUserResponse.builder()
                    .message(validationResult.getValidationFailureReason() + authValidationResult.getValidationFailureReason())
                    .success(Boolean.FALSE)
                    .build());
        } catch (final Exception err) {
            log.error("Unable to add user for {}, failed with exception", request, err);
        }
        return Optional.empty();
    }

    public Optional<UpdateUserInfoResponse> updateUserInfo(@RequestBody UpdateUserInfoRequest request) {
        try {
            final Optional<User> user = userRepo.findById(request.getUserId());
            if (user.isPresent()) {
                final User updatedUser = updateUserInfoRequestToUserMapper.map(request, user.get());
                userRepo.save(updatedUser);
                userLogBuilder.buildAndPublish(request);
                return Optional.of(UpdateUserInfoResponse.builder()
                        .success(Boolean.TRUE)
                        .message("User Info updated successfully...")
                        .orgTokenId(updatedUser.getOrgTokenId())
                        .updatedAt(updatedUser.getUpdatedAt())
                        .build());
            }
            return Optional.of(UpdateUserInfoResponse.builder()
                    .success(Boolean.FALSE)
                    .build());
        } catch (final Exception err) {
            log.error("Unable to update user for {}, failed with exception", request, err);
        }
        return Optional.empty();
    }

    public Optional<DeleteUserResponse> deleteUser(@RequestBody DeleteUserRequest request) {
        try {
            final Optional<User> user = userRepo.findById(request.getUserId());
            if (user.isPresent()) {
                final User deletedUser = user.get();
                userRepo.delete(deletedUser);
                userLogBuilder.buildAndPublish(request);
                return Optional.of(DeleteUserResponse.builder()
                        .message("User Deleted Successfully...")
                        .success(Boolean.TRUE)
                        .build());
            }
            return Optional.of(DeleteUserResponse.builder()
                    .success(Boolean.FALSE)
                    .build());
        } catch (final Exception err) {
            log.error("Unable to delete user for {}, failed with exception", request, err);
        }
        return Optional.empty();
    }
}
