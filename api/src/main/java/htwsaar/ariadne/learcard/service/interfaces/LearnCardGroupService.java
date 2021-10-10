package htwsaar.ariadne.learcard.service.interfaces;

import htwsaar.ariadne.learcard.entity.LearnCardGroup;

import java.util.List;
import java.util.Optional;

public interface LearnCardGroupService {

    /**
     * Finds Learncardgroup by it's Id and Name.
     * @param id
     * @param name
     * @return Learncardgroup
     * @author Felix Werner
     */

    LearnCardGroup findByIdAndUserName(long id, String name);

    /**
     *  Removes a LearnCardGroup from the Database
     * @param id
     * @param name
     * @author Felix Werner
     */
    void deleteByIdAndUserName(long id, String name);

    /**
     * Returns List of every Learncardgroup of a User.
     * @param username
     * @return List Learncardgroup
     * @author Felix Wernerr
     */
    List<LearnCardGroup> findByUserName(String username);

    /**
     * Searches LearnCardGroup by it's Id
     * @param id
     * @return
     * @author Felix Werner
     */
    Optional<LearnCardGroup> findById(long id);

    /**
     * Saves a LearncardGroup into the Database.
     * @param group
     * @return Learncardgroup
     * @author Felix Werner
     */

    LearnCardGroup save(LearnCardGroup group);



}
