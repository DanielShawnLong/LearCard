package htwsaar.ariadne.learcard.controller;

import htwsaar.ariadne.learcard.entity.LearnCard;
import htwsaar.ariadne.learcard.entity.LearnCardDeck;
import htwsaar.ariadne.learcard.repositorys.LearnCardDeckRepository;
import htwsaar.ariadne.learcard.repositorys.LearnCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
public class LearnCardDeckController {

    @Autowired
    private LearnCardDeckRepository learnCardDeckRepository;

    @Autowired
    private LearnCardRepository learnCardRepository;

    /**
     *
     * @param name Name des Decks
     * @param module Modulname
     * @return LearnCardDeck
     */
    @RequestMapping(method = RequestMethod.GET,
    path = "/createDeck",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public LearnCardDeck createDeck(@RequestParam String name,
                                    @RequestParam String module){
        LearnCardDeck learnCardDeck = new LearnCardDeck(name,module);
        learnCardDeckRepository.save(learnCardDeck);

        return learnCardDeck;
    }


    /**
     *
     * @param deckId ID des Deck, welches erweitert werden soll
     * @param cardId Id der Karte, die hinzugefügt werden soll
     * @return LearnCardDeck
     */
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/addCard",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public LearnCardDeck addCard(@RequestParam int deckId, @RequestParam int cardId){
        Optional<LearnCardDeck> learnCardDeck = learnCardDeckRepository.findById(deckId);
        Optional<LearnCard> learnCard = learnCardRepository.findById(cardId);
        if(learnCardDeck.isPresent() && learnCard.isPresent()){
            LearnCardDeck addDeck = learnCardDeck.get();
            LearnCard addCard = learnCard.get();
            addDeck.addCard(addCard);
            learnCardDeckRepository.save(addDeck);
            return addDeck;
        }
        // Platzhalter
        return null;
    }


    /**
     *
     * @param id des veränderten Decks
     * @param name neuer Name des Decks
     * @param module neues Modul des Decks
     */
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/editDeck",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void editDeck(         @RequestParam int id,
                                  @RequestParam String name,
                                  @RequestParam String module){
        Optional<LearnCardDeck> learnCardDeckList = learnCardDeckRepository.findById(id);

        if(learnCardDeckList.isPresent()) {
            LearnCardDeck newDeck = learnCardDeckList.get();
            newDeck.setDeckName(name);
            newDeck.setModule(module);
            learnCardDeckRepository.save(newDeck);
        }

    }

    /**
     *
     * @param id id des zu Entfernenden Decks
     * @return LearnCardDeck welches entfernt wurde
     */
    @RequestMapping(method = RequestMethod.GET,
    path =  "/removeDeck",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public LearnCardDeck removeDeck(@RequestParam int id){
        Optional<LearnCardDeck> deckList = learnCardDeckRepository.findById(id);
        if(deckList.isPresent()){
            learnCardDeckRepository.deleteById(id);
            return deckList.get();
        }
        //Platzhalter
        return null;
    }

    /**
     *
     * @param id id des gesuchten Decks
     * @return LearnCardDeck
     */
    @RequestMapping(method = RequestMethod.GET,
    path = "/getDeck",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public LearnCardDeck getDeck(@RequestParam int id){
        Optional<LearnCardDeck> deckList = learnCardDeckRepository.findById(id);
        if(deckList.isPresent()){
            return deckList.get();
        }
        //Platzhalter
        return null;
    }
}
