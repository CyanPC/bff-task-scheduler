package com.cyanpc.bfftaskscheduler.business;

import com.cyanpc.bfftaskscheduler.business.dto.AddressDTO;
import com.cyanpc.bfftaskscheduler.business.dto.PhoneDTO;
import com.cyanpc.bfftaskscheduler.business.dto.UserDTO;
import com.cyanpc.bfftaskscheduler.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient client;


    public UserDTO saveUser(UserDTO userDTO){
        return client.saveUser(userDTO);
    }

    public String login(UserDTO userDTO){
        return client.login(userDTO);
    }

    public UserDTO findUserByEmail(String email, String token){
        return client.findUserByEmail(email, token);
    }

    public void deleteUserByEmail(String email, String token){
       client.deleteUserByEmail(email, token);
    }

    public UserDTO updateUserData(String token,UserDTO dto){
        return client.updateUserData(dto, token);
    }

    public AddressDTO updateAddress(Long id, AddressDTO addressDTO, String token){
        return client.updateAddress(addressDTO, id, token);
    }

    public PhoneDTO updatePhone(Long id, PhoneDTO phoneDTO, String token){
        return client.updatePhone(phoneDTO, id, token);
    }

    public AddressDTO insertAddress(String token, AddressDTO dto){
        return client.insertAddress(dto, token);
    }

    public PhoneDTO insertPhone(String token, PhoneDTO dto){
        return client.insertPhone(dto, token);
    }
}
