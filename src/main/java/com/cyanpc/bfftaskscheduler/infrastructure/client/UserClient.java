package com.cyanpc.bfftaskscheduler.infrastructure.client;

import com.cyanpc.bfftaskscheduler.business.dto.AddressDTO;
import com.cyanpc.bfftaskscheduler.business.dto.PhoneDTO;
import com.cyanpc.bfftaskscheduler.business.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping
    UserDTO findUserByEmail(@RequestParam("email") String email,
                            @RequestHeader("Authorization") String token);

    @PostMapping
    UserDTO saveUser(@RequestBody UserDTO userDTO);

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO);

    @DeleteMapping("/{email}")
    void deleteUserByEmail(@PathVariable String email,
                           @RequestHeader("Authorization")String token);

    @PutMapping
    UserDTO updateUserData(@RequestBody UserDTO dto,
                                                  @RequestHeader("Authorization") String token);
    @PutMapping("/address")
    AddressDTO updateAddress(@RequestBody AddressDTO dto,
                                                    @RequestParam("id") Long id,
                                                    @RequestHeader("Authorization")String token);
    @PutMapping("/phone")
    PhoneDTO updatePhone(@RequestBody PhoneDTO dto,
                                                  @RequestParam("id") Long id,
                                                  @RequestHeader("Authorization")String token);
    @PostMapping("/address")
    AddressDTO insertAddress(@RequestBody AddressDTO dto,
                                                    @RequestHeader("Authorization") String token);
    @PostMapping("/phone")
    PhoneDTO insertPhone(@RequestBody PhoneDTO dto,
                                                @RequestHeader("Authorization") String token);

}
