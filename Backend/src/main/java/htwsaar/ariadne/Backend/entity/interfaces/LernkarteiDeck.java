package htwsaar.ariadne.Backend.entity.interfaces;

import htwsaar.ariadne.Backend.entity.Lernkartei;
import htwsaar.ariadne.Backend.enums.Module;

import java.util.List;

public interface LernkarteiDeck {


     /**
      * Fügt eine Lernkartei dem Deck hinzu.
      * @param lernkartei Die Lernkartei, welche hinzugefügt wird.
      */
     void addLernkartei(Lernkartei lernkartei);

     void removeLernkartei(Lernkartei lernkartei);

     int getLernkarteiDeckId();
     void setLernkarteiDeckId(int lernkarteiDeckId);
     String getDeckName();
     void setDeckName(String deckName);
     Module getModul();
     void setModul(Module modul);
     List<Lernkartei> getLernkarteiDeck();
     void setLernkarteiDeck(List<Lernkartei> lernkarteiDeck);

}
