package htwsaar.ariadne.learcard.controller;

import htwsaar.ariadne.learcard.entity.LearnCard;
import htwsaar.ariadne.learcard.entity.LearnCardGroup;
import htwsaar.ariadne.learcard.errorMsg.CardNotFoundException;
import htwsaar.ariadne.learcard.repositorys.LearnCardGroupRepository;
import htwsaar.ariadne.learcard.repositorys.LearnCardRepository;
import htwsaar.ariadne.learcard.security.config.JwtTokenUtil;

import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class LearnCardController {

    private LearnCardRepository repository;
    private  JwtTokenUtil jwtToken;

    LearnCardController(LearnCardRepository repository, JwtTokenUtil jwtToken){

        this.repository = repository;
        this.jwtToken = jwtToken;
    }

    /**
     * Create card
     * @param newLearnCard
     * @return
     */
    @PostMapping("/cards")
    LearnCard newLearnCard (@RequestBody LearnCard newLearnCard,
                            @RequestHeader("authorization") String token){

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));
        newLearnCard.setUserName(name);

        System.out.println("");

        return repository.save(newLearnCard);
    }

    /**
     * Get all cards
      * @return
     */
    @GetMapping("/cards")
    List<LearnCard> all(@RequestHeader("authorization") String token){
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return repository.findByUserName(name);
    }

    /**
     * Get card by id
     * @param id
     * @return
     */
    @GetMapping("/cards/{id}")
    LearnCard  one(@PathVariable Long id,
                   @RequestHeader("authorization") String token ) {

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));
        LearnCard check = repository.findByIdAndUserName(id,name);
        if(check == null) throw new CardNotFoundException(id);
        else { return repository.findByIdAndUserName(id,name);}

    }

    /**
     * Edit card
     * @param changedCard
     * @param id
     * @return
     */
    @PutMapping("/cardsAnswer/{id}")
    LearnCard changedCardAnswer(@RequestBody LearnCard changedCard,
                          @PathVariable Long id ,
                          @RequestHeader("authorization") String token){
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return repository.findById(id)
                .map(learnCard -> {
                    learnCard.setIsSolved(changedCard.getIsSolved());
                    learnCard.setRightAnswer(changedCard.getRightAnswer());
                    return repository.save(learnCard);
                })
                .orElseGet(() -> {
                    throw new CardNotFoundException(id);
                });
    }

    /**
     * Delete card
     * @param id
     */
    @DeleteMapping("cards/{id}")
    void deleteCard(@PathVariable Long id,
                    @RequestHeader("authorization") String token){

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        repository.deleteByIdAndUserName(id, name); //CardNotFoundException TODO
    }


    /**
     * Get all cards from group
     * @return
     */

    @GetMapping("/cardsByGroup/{id}")
    List<LearnCard> all(@RequestHeader("authorization") String token , @PathVariable Long id) {

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return repository.findByGroupIdAndUserName(id, name);

    }

    /**
     * Get all cards from group where rightAnswer is false
     * @return
     */

    @GetMapping("/cards-wrong/{id}")
    List<LearnCard> allWrong(@RequestHeader("authorization") String token , @PathVariable Long id) {

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return repository.findByGroupIdAndUserNameAndRightAnswer(id, name, false);

    }
    /**
     * Get all cards from group where rightAnswer is false and isSolved is false
     * @return
     */

    @GetMapping("/cards-session/{id}")
    List<LearnCard> startSession(@RequestHeader("authorization") String token , @PathVariable Long id) {

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return repository.findByGroupIdAndUserNameAndRightAnswerAndIsSolved(id, name, false, false);

    }


}
