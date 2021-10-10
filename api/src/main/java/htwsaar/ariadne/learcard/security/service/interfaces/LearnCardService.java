package htwsaar.ariadne.learcard.security.service.interfaces;

import htwsaar.ariadne.learcard.entity.LearnCard;

import java.util.List;
import java.util.Optional;


public interface LearnCardService {

    /**
     * Gibt eine Liste aller Lernkarten zurück.
     * @return List Learncard
     */
    List<LearnCard> getAll();

    /**
     * Trägt eine Learncard in die Datenbank ein und gibt sie zurück.
     * @param card
     * @return Learncard
     */
    LearnCard insertCard(LearnCard card);

    /**
     * Sucht eine Learncard anhand ihres namens und ihrer Id
     * @param id
     * @param name
     * @return Learncard
     */

    LearnCard findCardByIdandName(long id, String name);

    /**
     * Sucht eine Learncard anhand ihrer id
     * @param id
     * @return Learncard
     */

    Optional<LearnCard> findCardById(long id);

    /**
     * Sucht alle Learncards eines Users.
     * @param name
     * @return List Learncard
     */
    List<LearnCard> findAllByName(String name);

    /**
     * Entfernt eine Learncard aus der Datenbank.
     * @param id
     * @param name
     */
    void removeCard(long id,String name);

    /**
     * Erstellt eine Liste aller Learncards eines Nutzer und einer LearncardGroup
     * @param group
     * @param username
     * @return List Learncard
     */
    List<LearnCard> findByGroupIdAndUserName(Long group, String username);


    /**
     * Gibt eine Liste aller Learncards eines Nutzers und einer Gruppe, welche richtig beantwortet wurden.
     * @param group
     * @param username
     * @param answer
     * @return List Learncard
     */
    List<LearnCard> findByGroupIdAndUserNameAndRightAnswer(Long group,
                                                           String username,
                                                           Boolean answer);

    /**
     * Gibt eine Liste aller Learncards eines Nutzers und einer Gruppe, welche richtig beantwortet wurden.
     * @param group
     * @param username
     * @param rightAnswer
     * @param isSolved
     * @return List Learncard
     */
    List<LearnCard> findByGroupIdAndUserNameAndRightAnswerAndIsSolved(Long group,
                                                                      String username,
                                                                      Boolean rightAnswer,
                                                                      Boolean isSolved);





}
