package com.cyanpc.bfftaskscheduler.controller;


import com.cyanpc.bfftaskscheduler.business.UserService;
import com.cyanpc.bfftaskscheduler.business.dto.AddressDTO;
import com.cyanpc.bfftaskscheduler.business.dto.PhoneDTO;
import com.cyanpc.bfftaskscheduler.business.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User login and register")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Save User", description = "Creates a new user")
    @ApiResponse(responseCode = "200", description = "User saved successfully")
    @ApiResponse(responseCode = "400", description = "User already registered")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }
    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Logs the user")
    @ApiResponse(responseCode = "200", description = "Access granted")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Server error")
    public String login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    @GetMapping
    @Operation(summary = "Find User by Email", description = "Finds user by email")
    @ApiResponse(responseCode = "200", description = "User found successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UserDTO> findUserByEmail(@RequestParam("email")String email,
                                                   @RequestHeader("Authorization")String token){
        return  ResponseEntity.ok(userService.findUserByEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete User", description = "Deletes the user")
    @ApiResponse(responseCode = "200", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email,
                                                    @RequestHeader("Authorization")String token){
        userService.deleteUserByEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Update User", description = "Updates the user")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UserDTO> updateUserData(@RequestBody UserDTO dto,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.updateUserData(token, dto));
    }

    @PutMapping("/address")
    @Operation(summary = "Update Address", description = "Updates the address")
    @ApiResponse(responseCode = "200", description = "Address updated successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO dto,
                                                    @RequestParam("id") Long id,
                                                    @RequestHeader("Authorization")String token) {
        return ResponseEntity.ok(userService.updateAddress(id, dto,token));
    }
    @PutMapping("/phone")
    @Operation(summary = "Update Phone", description = "Updates the phone")
    @ApiResponse(responseCode = "200", description = "Phone updated successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<PhoneDTO> updatePhone(@RequestBody PhoneDTO dto,
                                                @RequestParam("id") Long id,
                                                @RequestHeader("Authorization")String token) {
        return ResponseEntity.ok(userService.updatePhone(id, dto, token));
    }
    @PostMapping("/address")
    @Operation(summary = "Add Address", description = "Adds an address")
    @ApiResponse(responseCode = "200", description = "Address added successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<AddressDTO> insertAddress(@RequestBody AddressDTO dto,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.insertAddress(token, dto));
    }
    @PostMapping("/phone")
    @Operation(summary = "Add Phone", description = "Adds a phone")
    @ApiResponse(responseCode = "200", description = "Phone added successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<PhoneDTO> insertPhone(@RequestBody PhoneDTO dto,
                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.insertPhone(token, dto));
    }
}

