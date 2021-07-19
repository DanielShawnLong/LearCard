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
    public User createUser(@RequestParam String userName,
                           @RequestParam String userEmail,
                           @RequestParam String userPassword) {
        // create new user
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

        // edit user
        User updatedUser = userList.get();
        updatedUser.setUserName(userName);
        updatedUser.setUserEmail(userEmail);
        updatedUser.setUserPassword(userPassword);

        // replace user data
        userRepository.save(updatedUser);

        // return updated user
        return updatedUser;
    }
    @RequestMapping (
            method = RequestMethod.DELETE,
            path = "/removeLearnCard",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User removeUser(@RequestParam int id) {
        // Search user by id
        Optional<User> userList = userRepository.findById(id);

        // delete user
        userRepository.deleteById(id);

        // return deleted user
        return userList.get();
    }


}
