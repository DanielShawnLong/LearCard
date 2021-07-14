package htwsaar.ariadne.Backend.entity.interfaces;

import htwsaar.ariadne.Backend.entity.Lernkartei;
import htwsaar.ariadne.Backend.enums.Modules;

import java.util.List;

public interface FlashcardDeckEntity {


     /**
      * Fügt eine Lernkartei dem Deck hinzu.
      * @param lernkartei Die Lernkartei, welche hinzugefügt wird.
      */
     void addFlashcard(Lernkartei lernkartei);

     /**
      * Entfernt eine Lernkartei aus dem Deck
      * @param lernkartei Die Lernkartei, welche entfernt werden soll.
      */
     void removeFlashcard(Lernkartei lernkartei);

     void renameFlashcardDeck(String deckname);

     /*
      * getter und setter
      *
      */
     int getFlashcardDeckId();
     //void setLernkarteiDeckId(int lernkarteiDeckId);
     String getDeckName();
     //void setDeckName(String deckName);
     Modules getModul();
     //void setModul(Module modul);
     List<Lernkartei> getFlashcardDeck();
     //void setLernkarteiDeck(List<Lernkartei> lernkarteiDeck);

}
