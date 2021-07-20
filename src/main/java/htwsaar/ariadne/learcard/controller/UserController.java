package htwsaar.ariadne.learcard.controller;


import htwsaar.ariadne.learcard.entity.User;
import htwsaar.ariadne.learcard.errorMsg.UserNotFoundException;
import htwsaar.ariadne.learcard.repositorys.UserRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class UserController {

  private final UserRepository repository;

  UserController(UserRepository repository){
      this.repository = repository;
  }

    /**
     * Create user
     * @param newUser
     * @return
     */
  @PostMapping("/users")
    User newUser(@RequestBody User newUser){
      return repository.save(newUser);
  }

  /**
   * Get all users
   * @return
   */
  @GetMapping("/users")
  List<User> all(){
      return repository.findAll();
  }

  /**
   * Get user by id
    * @param id
   * @return
   */
  @GetMapping("/users/{id}")
  User one(@PathVariable Long id){
    return repository.findById(id)
            .orElseThrow(()-> new UserNotFoundException(id));
  }

  /**
   * Edit user
   * @param newUser
   * @param id
   * @return
   */
  @PutMapping("/users/{id}")
  User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

    return repository.findById(id)
            .map(user -> {
              user.setUserName(newUser.getUserName());
              user.setUserEmail(newUser.getUserEmail());
              user.setUserPassword(newUser.getUserPassword());
              return repository.save(user);
            })
            .orElseGet(() -> {
              newUser.setId(id);
              return repository.save(newUser);
            });
  }

  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
  }

/*
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
            path = "/removeUser",
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

*/
}
