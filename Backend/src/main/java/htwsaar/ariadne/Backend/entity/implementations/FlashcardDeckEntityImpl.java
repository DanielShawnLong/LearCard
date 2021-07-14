
package htwsaar.ariadne.Backend.entity.implementations;

import htwsaar.ariadne.Backend.entity.interfaces.FlashcardDeckEntity;
import htwsaar.ariadne.Backend.entity.Lernkartei;
import htwsaar.ariadne.Backend.enums.Modules;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class FlashcardDeckEntityImpl implements FlashcardDeckEntity {

    @Min(value = 0, message = "ID >= 0")
    private int flashcardDeckId;
    @NotEmpty(message = "Deck muss benannt werden")
    private String deckName;
    @NotEmpty(message = "Deck muss einem Modul zugeordnet werden")
    private Modules modul;
    private List<Lernkartei> flashcardDeck;

    public FlashcardDeckEntityImpl(int flashcardDeckId,
                                   String deckName,
                                   Modules modul,
                                   List<Lernkartei> flashcardDeck){

        this.flashcardDeckId = flashcardDeckId;
        this.deckName = deckName;
        this.modul = modul;
        this.flashcardDeck = flashcardDeck;

    }

    @Override
    public void addFlashcard(Lernkartei lernkartei) {
        this.flashcardDeck.add(lernkartei);
    }

    @Override
    public void removeFlashcard(Lernkartei lernkartei) {
        this.flashcardDeck.remove(lernkartei);
    }

    @Override
    public void renameFlashcardDeck(String deckname) {
        setDeckName(deckname);
    }


    public int getFlashcardDeckId() {
        return flashcardDeckId;
    }

    private void setFlashcardDeckId(int flashcardDeckId) {
        this.flashcardDeckId = flashcardDeckId;
    }

    public String getDeckName() {
        return deckName;
    }

    private void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public Modules getModul() {
        return modul;
    }

    private void setModul(Modules modul) {
        this.modul = modul;
    }

    public List<Lernkartei> getFlashcardDeck() {
        return flashcardDeck;
    }

    private void setFlashcardDeck(List<Lernkartei> flashcardDeck) {
        this.flashcardDeck = flashcardDeck;
    }

}
