package htwsaar.ariadne.learcard.controller;


import htwsaar.ariadne.learcard.entity.User;
import htwsaar.ariadne.learcard.repositorys.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(
            method = RequestMethod.POST, //Why get? not post?
            path = "/createUser",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User createUser(@RequestBody String userName,
                           @RequestBody String userEmail,
                           @RequestBody String userPassword) {
        // Create new user
        User user = new User(userName, userEmail, userPassword);
        userRepository.save(user);

        // return user
        return user;
    }

    @RequestMapping (
            method = RequestMethod.PUT,
            path = "/editUser",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User editUser(@RequestParam int id,
                         @RequestParam String userName,
                         @RequestParam String userEmail,
                         @RequestParam String userPassword) {
        // search user by id
        Optional<User> userList = userRepository.findById(id);

        // Edit user
        User updatedUser = userList.get();
        updatedUser.setUserName(userName);
        updatedUser.setUserEmail(userEmail);
        updatedUser.setUserPassword(userPassword);

        // replace user data
        userRepository.save(updatedUser);

        // return updated user
        return updatedUser;
    }
}
