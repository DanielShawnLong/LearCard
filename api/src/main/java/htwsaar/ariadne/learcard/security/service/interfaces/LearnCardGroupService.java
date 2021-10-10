package htwsaar.ariadne.learcard.security.service.interfaces;

import htwsaar.ariadne.learcard.entity.LearnCardGroup;

import java.util.List;
import java.util.Optional;

public interface LearnCardGroupService {

    /**
     * Sucht eine Learncardgroup anhand ihrer id und Namens.
     * @param id
     * @param name
     * @return Learncardgroup
     */

    LearnCardGroup findByIdAndUserName(long id, String name);

    /**
     *  Entfernt eine LearnCardGroup aus der Datenbank.
     * @param id
     * @param name
     */
    void deleteByIdAndUserName(long id, String name);

    /**
     * Gibt eine Liste aller Learncardgroups eines Nutzers aus.
     * @param username
     * @return List Learncardgroup
     */
    List<LearnCardGroup> findByUserName(String username);

    /**
     * Sucht eine Learncardgroup anhand ihrer ID.
     * @param id
     * @return
     */
    Optional<LearnCardGroup> findById(long id);

    /**
     * Legt eine Learncardgroup in der Datenbank an.
     * @param group
     * @return Learncardgroup
     */

    LearnCardGroup save(LearnCardGroup group);



}
