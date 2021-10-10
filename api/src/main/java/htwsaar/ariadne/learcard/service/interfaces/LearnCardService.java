package htwsaar.ariadne.learcard.service.interfaces;

import htwsaar.ariadne.learcard.entity.LearnCard;

import java.util.List;
import java.util.Optional;


public interface LearnCardService {

    /**
     * Returns a List of every Learncard.
     * @return List Learncard
     */
    List<LearnCard> getAll();

    /**
     * Saves a Learncard into the Database and returns it.
     * @param card
     * @return Learncard
     */
    LearnCard insertCard(LearnCard card);

    /**
     * Finds a Learncard based on it's Id and Name.
     * @param id
     * @param name
     * @return Learncard
     */

    LearnCard findCardByIdandName(long id, String name);

    /**
     * Find a Learncard by it's Id.
     * @param id
     * @return Learncard
     */

    Optional<LearnCard> findCardById(long id);

    /**
     * Finds all Learncards from a User.
     * @param name
     * @return List Learncard
     */
    List<LearnCard> findAllByName(String name);

    /**
     * Removes a Learncard from the Database.
     * @param id
     * @param name
     */
    void removeCard(long id,String name);

    /**
     * Creates a List of Learncards from a Group and a User.
     * @param group
     * @param username
     * @return List Learncard
     */
    List<LearnCard> findByGroupIdAndUserName(Long group, String username);


    /**
     * Returns a List of Learncards from a Group that have been answered correctly.
     * @param group
     * @param username
     * @param answer
     * @return List Learncard
     */
    List<LearnCard> findByGroupIdAndUserNameAndRightAnswer(Long group,
                                                           String username,
                                                           Boolean answer);

    /**
     * Returns a List of Learncards from a Group that have been answered correctly.
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
