package htwsaar.ariadne.learcard.controller;

import htwsaar.ariadne.learcard.entity.LearnCard;
import htwsaar.ariadne.learcard.repositorys.LearnCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LearnCardController {

    @Autowired
    private LearnCardRepository learnCardRepository;

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

}
