package htwsaar.ariadne.learcard.entity;

import com.sun.istack.NotNull;
import htwsaar.ariadne.learcard.enums.Modules;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "learncarddeck")
public class LearnCardDeck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   // @Enumerated
    private String module;
    @NotNull
    private String deckName;

    @ManyToMany(targetEntity = htwsaar.ariadne.learcard.entity.LearnCard.class)
    private List cards;

    public LearnCardDeck(String module, String deckName){
        this.module = module;
        this.deckName = deckName;
        this.cards = new ArrayList();

    }

    public void addCard(LearnCard learnCard){
        cards.add(learnCard);
    }
    public void removeCard(LearnCard learnCard){
        cards.remove(learnCard);
    }

    public LearnCardDeck() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public List getDeck() {
        return cards;
    }

    public void setDeck(List deck) {
        this.cards = deck;
    }
}
