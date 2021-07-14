
package htwsaar.ariadne.Backend.entity.implementations;

import htwsaar.ariadne.Backend.entity.interfaces.LernkarteiDeck;
import htwsaar.ariadne.Backend.entity.Lernkartei;
import htwsaar.ariadne.Backend.enums.Module;

import java.util.List;

public class LernkarteiDeckImpl implements LernkarteiDeck {

    private int lernkarteiDeckId;
    private String deckName;
    private Module modul;
    private List<Lernkartei> lernkarteiDeck;

    public LernkarteiDeckImpl(int lernkarteiDeckId,
                              String deckName,
                              Module modul,
                              List<Lernkartei> lernkarteiDeck){

        this.lernkarteiDeckId = lernkarteiDeckId;
        this.deckName = deckName;
        this.modul = modul;
        this.lernkarteiDeck = lernkarteiDeck;

    }

    @Override
    public void addLernkartei(Lernkartei lernkartei) {
        this.lernkarteiDeck.add(lernkartei);
    }

    @Override
    public void removeLernkartei(Lernkartei lernkartei) {
        this.lernkarteiDeck.remove(lernkartei);
    }


    public int getLernkarteiDeckId() {
        return lernkarteiDeckId;
    }

    public void setLernkarteiDeckId(int lernkarteiDeckId) {
        this.lernkarteiDeckId = lernkarteiDeckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public Module getModul() {
        return modul;
    }

    public void setModul(Module modul) {
        this.modul = modul;
    }

    public List<Lernkartei> getLernkarteiDeck() {
        return lernkarteiDeck;
    }

    public void setLernkarteiDeck(List<Lernkartei> lernkarteiDeck) {
        this.lernkarteiDeck = lernkarteiDeck;
    }

}
