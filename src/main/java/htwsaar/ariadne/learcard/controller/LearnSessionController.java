package htwsaar.ariadne.learcard.controller;

import htwsaar.ariadne.learcard.entity.LearnSession;
import htwsaar.ariadne.learcard.repositorys.LearnSessionRepository;

import htwsaar.ariadne.learcard.security.config.JwtTokenUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LearnSessionController {

    private LearnSessionRepository repository;
    private  JwtTokenUtil jwtToken;

    LearnSessionController(LearnSessionRepository repository, JwtTokenUtil jwtToken){

        this.repository = repository;
        this.jwtToken = jwtToken;
    }

    /**
     * Create session
     * @param newLearnSession
     * @return
     */
    @PostMapping("/session")
    LearnSession newSession (@RequestBody LearnSession newLearnSession,
                            @RequestHeader("authorization") String token){

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));
        newLearnSession.setUserName(name);


        return repository.save(newLearnSession);
    }

    /**
     * Get all sessions
     * @return
     */
    @GetMapping("/session")
    List<LearnSession> all(@RequestHeader("authorization") String token){
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return repository.findByUserName(name);
    }

    /**
     * Get session by id
     * @param id
     * @return
     */
    @GetMapping("/session/{id}")
    LearnSession  one(@PathVariable Long id,
                        @RequestHeader("authorization") String token ) {
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));
        return repository.findByIdAndUserName(id,name);
        // .orElseThrow(() -> new CardNotFoundException(id)); // Supplier takes just one argument TODO
    }

    /**
     * Edit session
     * @param changedLearnSession
     * @param id
     * @return
     */
    @PutMapping("/session/{id}")
    LearnSession changedLearnSession(@RequestBody LearnSession changedLearnSession,
                                    @PathVariable Long id ,
                                    @RequestHeader("authorization") String token){
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return repository.findById(id)   //return repository.findByIdAndUserName(id,name) TODO
                .map(learnSession -> {
                    learnSession.setLevel(changedLearnSession.getLevel());

                    return repository.save(learnSession);
                })
                .orElseGet(() -> {
                    changedLearnSession.setId(id);
                    return repository.save(changedLearnSession);
                });
    }

    /**
     * Delete session
     * @param id
     */
    @DeleteMapping("session/{id}")
    void deleteSession(@PathVariable Long id,
                         @RequestHeader("authorization") String token){

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        repository.deleteByIdAndUserName(id, name); //CardNotFoundException TODO
    }
}
