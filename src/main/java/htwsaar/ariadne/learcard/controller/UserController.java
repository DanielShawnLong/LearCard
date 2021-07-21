package htwsaar.ariadne.learcard.controller;

import htwsaar.ariadne.learcard.entity.User;
import htwsaar.ariadne.learcard.errorMsg.UserNotFoundException;
import htwsaar.ariadne.learcard.repositorys.UserRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Create user
     *
     * @param newUser
     * @return
     * @author Pamela Filipinski
     */
    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    /**
     * Get all users
     *
     * @return
     * @author Pamela Filipinski
     */
    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    /**
     * Get user by id
     *
     * @param id
     * @return
     * @author Pamela Filipinski
     */
    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Edit user
     *
     * @param newUser
     * @param id
     * @return
     * @author Pamela Filipinski
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

    /**
     * Delete user
     *
     * @param id
     * @author Pamela Filipinski
     */
    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {

        repository.deleteById(id);
    }

}
