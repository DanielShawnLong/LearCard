package htwsaar.ariadne.learcard.controller;

import htwsaar.ariadne.learcard.entity.LearnCardGroup;
import htwsaar.ariadne.learcard.errorMsg.GroupNotFoundException;
import htwsaar.ariadne.learcard.repositorys.LearnCardGroupRepository;
import htwsaar.ariadne.learcard.security.config.JwtTokenUtil;
import htwsaar.ariadne.learcard.security.service.implementation.LearnCardGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LearnCardGroupController {

    @Autowired
    private LearnCardGroupServiceImpl service;
    //private LearnCardGroupRepository repository;
    private JwtTokenUtil jwtToken;

    LearnCardGroupController(LearnCardGroupServiceImpl service, JwtTokenUtil jwtToken) {

        this.service = service;
        this.jwtToken = jwtToken;
    }

    /**
     * Create group
     *
     * @param newLearnCardGroup
     * @return
     * @author Pamela Filipinski, Felix Werner
     */
    @PostMapping("/groups")
    LearnCardGroup newLearnCardGroup(@RequestBody LearnCardGroup newLearnCardGroup,
                                     @RequestHeader("authorization") String token) {

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));
        newLearnCardGroup.setUserName(name);

        return service.save(newLearnCardGroup);
    }

    /**
     * Get all groups
     *
     * @return
     * @author Pamela Filipinski, Felix Werner
     */
    @GetMapping("/groups")
    List<LearnCardGroup> all(@RequestHeader("authorization") String token) {
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return service.findByUserName(name);
    }

    /**
     * Get group by id
     *
     * @param id
     * @return
     * @author Pamela Filipinski, Felix Werner
     */
    @GetMapping("/groups/{id}")
    LearnCardGroup one(@PathVariable Long id,
                       @RequestHeader("authorization") String token) {
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        LearnCardGroup check = service.findByIdAndUserName(id, name);
        if (check == null) throw new GroupNotFoundException(id);
        else {
            return service.findByIdAndUserName(id, name);
        }
    }

    /**
     * Edit group
     *
     * @param changedCardGroup
     * @param id
     * @return
     * @author Pamela Filipinski, Felix Werner
     */
    @PutMapping("/groups/{id}")
    LearnCardGroup changedCardGroup(@RequestBody LearnCardGroup changedCardGroup,
                                    @PathVariable Long id,
                                    @RequestHeader("authorization") String token) {
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return service.findById(id)
                .map(learnCardGroup -> {
                    learnCardGroup.setLevel(changedCardGroup.getLevel());

                    return service.save(learnCardGroup);
                })
                .orElseGet(() -> {
                    throw new GroupNotFoundException(id);
                });
    }

    /**
     * Delete group
     *
     * @param id
     * @author Pamela Filipinski, Felix Werner
     */
    @DeleteMapping("groups/{id}")
    void deleteCardGroup(@PathVariable Long id,
                         @RequestHeader("authorization") String token) {

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        service.deleteByIdAndUserName(id, name);
    }


}