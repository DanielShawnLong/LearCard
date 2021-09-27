package htwsaar.ariadne.learcard.controller;

import htwsaar.ariadne.learcard.entity.LearnCardGroup;

import htwsaar.ariadne.learcard.repositorys.LearnCardGroupRepository;

import htwsaar.ariadne.learcard.security.config.JwtTokenUtil;

import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class LearnCardGroupController {

    private LearnCardGroupRepository repository;
    private  JwtTokenUtil jwtToken;

    LearnCardGroupController(LearnCardGroupRepository repository, JwtTokenUtil jwtToken){

        this.repository = repository;
        this.jwtToken = jwtToken;
    }

    /**
     * Create group
     * @param newLearnCardGroup
     * @return
     */
    @PostMapping("/groups")
    LearnCardGroup newLearnCardGroup (@RequestBody LearnCardGroup newLearnCardGroup,
                            @RequestHeader("authorization") String token){

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));
        newLearnCardGroup.setUserName(name);

        return repository.save(newLearnCardGroup);
    }

    /**
     * Get all groups
      * @return
     */
    @GetMapping("/groups")
    List<LearnCardGroup> all(@RequestHeader("authorization") String token){
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return repository.findByUserName(name);
    }

    /**
     * Get group by id
     * @param id
     * @return
     */
    @GetMapping("/groups/{id}")
    LearnCardGroup  one(@PathVariable Long id,
                   @RequestHeader("authorization") String token ) {
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));
        return repository.findByIdAndUserName(id,name);
               // .orElseThrow(() -> new CardNotFoundException(id)); // Supplier takes just one argument TODO
    }

    /**
     * Edit group
     * @param changedCardGroup
     * @param id
     * @return
     */
    @PutMapping("/groups/{id}")
    LearnCardGroup changedCardGroup(@RequestBody LearnCardGroup changedCardGroup,
                          @PathVariable Long id ,
                          @RequestHeader("authorization") String token){
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return repository.findById(id)   //return repository.findByIdAndUserName(id,name) TODO
                .map(learnCardGroup -> {
                    learnCardGroup.setGroupName(changedCardGroup.getGroupName());
                    
                    return repository.save(learnCardGroup);
                })
                .orElseGet(() -> {
                    changedCardGroup.setId(id);
                    return repository.save(changedCardGroup);
                });
    }

    /**
     * Delete group
     * @param id
     */
    @DeleteMapping("groups/{id}")
    void deleteCardGroup(@PathVariable Long id,
                    @RequestHeader("authorization") String token){

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        repository.deleteByIdAndUserName(id, name); //CardNotFoundException TODO
    }



}