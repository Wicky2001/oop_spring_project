package group6.group6.services;

import group6.group6.entity.User;
import group6.group6.model.LoginData;
import group6.group6.model.RegistrationData;
import group6.group6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Object> RegisterUser(RegistrationData data){
        User newUser = new User(data.getName(), data.getEmail(), data.getContactNumber(), data.getPassword());

        User registeredUser = userRepository.save(newUser);

        Map<String, Object> response = new HashMap<>();

        if (registeredUser != null && registeredUser.getId() != null) {
            response.put("registration", true);
            response.put("user", registeredUser.getName()); // Replace with any other user details you want to return
        } else {
            response.put("registration", false);
        }

        return response;


    }

    public Map<String, Object> LogInUser(LoginData data){
      Optional<User> userOptional = userRepository.findByEmail(data.getEmail());
        Map<String, Object> response = new HashMap<>();
      if(userOptional.isPresent()){
          User registerdUser = userOptional.get();
          if(registerdUser.getPassword().equals(data.getPassword())){
              response.put("message","login successfull");
              response.put("successfull",true);
          }else {
              response.put("message","login faild. Incorrect Password");
              response.put("successfull",false);
          }
      }else{
          response.put("message","You are not registered. Please register before login");
          response.put("successfull",false);
      }

      return response;
    }
}
