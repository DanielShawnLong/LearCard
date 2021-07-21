package htwsaar.ariadne.learcard.controller;

import htwsaar.ariadne.learcard.entity.LearnCard;
import htwsaar.ariadne.learcard.errorMsg.CardNotFoundException;
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
        return repository.findByIdAndUserName(id,name);
               // .orElseThrow(() -> new CardNotFoundException(id)); // Supplier takes just one argument TODO
    }

    /**
     * Edit card
     * @param changedCard
     * @param id
     * @return
     */
    @PutMapping("/cards/{id}")
    LearnCard changedCard(@RequestBody LearnCard changedCard,
                          @PathVariable Long id ,
                          @RequestHeader("authorization") String token){
        String name = jwtToken.getUsernameFromToken(token.replace("Bearer ", ""));

        return repository.findById(id)   //return repository.findByIdAndUserName(id,name) TODO
                .map(learnCard -> {
                    learnCard.setFrontText(changedCard.getFrontText());
                    learnCard.setBackText(changedCard.getBackText());
                    learnCard.setSolved(changedCard.isSolved());
                    return repository.save(learnCard);
                })
                .orElseGet(() -> {
                    changedCard.setId(id);
                    return repository.save(changedCard);
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
/*
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/createLearnCard",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public LearnCard createLearnCard(@RequestParam String frontText,
                                      @RequestParam String backText) {
        // Erzeuge neue Lernkarteikarte
        LearnCard learnCard = new LearnCard(frontText, backText);
        learnCardRepository.save(learnCard);

        // Erzeugte Lernkarteikarte zurückgeben
        return learnCard;
    }

    @RequestMapping (
            method = RequestMethod.GET,
            path = "/editLearnCard",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public LearnCard editLearnCard(@RequestParam int id, @RequestParam String frontText, @RequestParam String backText) {
        // Suche Lernkartekartei anhand der Id
        Optional<LearnCard> learnCardList = learnCardRepository.findById(id);

        // Lernkarteikarten-Texte bearbeiten
        LearnCard newLearnCard = learnCardList.get();
        newLearnCard.setFrontText(frontText);
        newLearnCard.setBackText(backText);

        // Ersetze alte Lernkarteikarte
        learnCardRepository.save(newLearnCard);

        // Neue Lernkarteikarte zurückgeben
        return newLearnCard;
    }

    @RequestMapping (
            method = RequestMethod.GET,
            path = "/removeLearnCard",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public LearnCard removeLearnCard(@RequestParam int id) {
        // Suche Lernkarteikartei anhand der Id
        Optional<LearnCard> learnCardList = learnCardRepository.findById(id);

        // Lösche die gefundene Lernkarteikarte
        learnCardRepository.deleteById(id);

        // Gebe die gelöschte Karteikarte zurück
        return learnCardList.get();
    }

    @RequestMapping (
            method = RequestMethod.GET,
            path = "/getLearnCard",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public LearnCard getLearnCard(@RequestParam int id) {
        // Suche Lernkarteikartei anhand der Id
        Optional<LearnCard> learnCardList = learnCardRepository.findById(id);

        // Gebe die gefundene Lernkarteikarte zurück
        return learnCardList.get();
    }
*/
}
