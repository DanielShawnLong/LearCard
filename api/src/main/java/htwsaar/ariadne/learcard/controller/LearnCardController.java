package htwsaar.ariadne.learcard.controller;

import htwsaar.ariadne.learcard.entity.LearnCard;
import htwsaar.ariadne.learcard.errorMsg.CardNotFoundException;
import htwsaar.ariadne.learcard.repositorys.LearnCardRepository;
import htwsaar.ariadne.learcard.security.config.JwtTokenUtil;

import htwsaar.ariadne.learcard.security.service.implementation.LearnCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LearnCardController {

    @Autowired
    private LearnCardServiceImpl service;
    //private LearnCardRepository repository;
    private JwtTokenUtil jwtToken;


    LearnCardController(LearnCardServiceImpl service, JwtTokenUtil jwtToken) {

        this.service = service;
        this.jwtToken = jwtToken;
    }

    /**
     * Create card
     *
     * @param newLearnCard
     * @return
     * @author Pamela Filipinski, Daniel-Shawn Long
     *
     */
    @PostMapping("/cards")
    LearnCard newLearnCard(@RequestBody LearnCard newLearnCard,
                           @RequestHeader("authorization") String token) {

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));
        newLearnCard.setUserName(name);

        return service.insertCard(newLearnCard);
    }

    /**
     * Get all cards
     *
     * @return
     * @author Pamela Filipinski, Daniel-Shawn Long
     */
    @GetMapping("/cards")
    List<LearnCard> all(@RequestHeader("authorization") String token) {
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return service.findAllByName(name);
    }

    /**
     * Get card by id
     *
     * @param id
     * @return
     * @author Pamela Filipinski, Daniel-Shawn Long
     */
    @GetMapping("/cards/{id}")
    LearnCard one(@PathVariable Long id,
                  @RequestHeader("authorization") String token) {

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));
        LearnCard check = service.findCardByIdandName(id, name);
        if (check == null) throw new CardNotFoundException(id);
        else {
            return service.findCardByIdandName(id, name);
        }

    }

    /**
     * Edit card
     *
     * @param changedCard
     * @param id
     * @return
     * @author Pamela Filipinski, Daniel-Shawn Long
     */
    @PutMapping("/cardsAnswer/{id}")
    LearnCard changedCardAnswer(@RequestBody LearnCard changedCard,
                                @PathVariable Long id,
                                @RequestHeader("authorization") String token) {
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return service.findCardById(id)
                .map(learnCard -> {
                    learnCard.setIsSolved(changedCard.getIsSolved());
                    learnCard.setRightAnswer(changedCard.getRightAnswer());
                    return service.insertCard(learnCard);
                })
                .orElseGet(() -> {
                    throw new CardNotFoundException(id);
                });
    }

    /**
     * Delete card
     *
     * @param id
     * @author Pamela Filipinski, Daniel-Shawn Long
     */
    @DeleteMapping("cards/{id}")
    void deleteCard(@PathVariable Long id,
                    @RequestHeader("authorization") String token) {

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        service.removeCard(id, name);
    }


    /**
     * Get all cards from group
     *
     * @return
     * @author Pamela Filipinski
     */

    @GetMapping("/cardsByGroup/{id}")
    List<LearnCard> all(@RequestHeader("authorization") String token,
                        @PathVariable Long id) {

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return service.findByGroupIdAndUserName(id, name);

    }

    /**
     * Get all cards from group where rightAnswer is false
     *
     * @return
     * @author Pamela Filipinski
     */

    @GetMapping("/cards-wrong/{id}")
    List<LearnCard> allWrong(@RequestHeader("authorization") String token,
                             @PathVariable Long id) {

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return service.findByGroupIdAndUserNameAndRightAnswer(id, name, false);

    }

    /**
     * Get all cards from group where rightAnswer is false and isSolved is false
     *
     * @return
     * @author Pamela Filipinski
     */

    @GetMapping("/cards-session/{id}")
    List<LearnCard> startSession(@RequestHeader("authorization") String token,
                                 @PathVariable Long id) {

        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return service.findByGroupIdAndUserNameAndRightAnswerAndIsSolved(id, name, false, false);

    }

    /**
     * reset all cards from Group
     *
     * @param id
     * @return
     * @author Pamela Filipinski
     */
    @PutMapping("/resetCards/{id}")
    List<LearnCard> resetCards(@RequestHeader("authorization") String token,
                               @PathVariable Long id) {
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        List<LearnCard> newList = service.findByGroupIdAndUserName(id, name);

        for (int i = 0; i < newList.size(); i++) {
            newList.get(i).setRightAnswer(false);
            service.insertCard(newList.get(i));
        }

        return null;
    }

}
