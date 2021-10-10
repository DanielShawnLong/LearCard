package htwsaar.ariadne.learcard.security.service.interfaces;

import htwsaar.ariadne.learcard.entity.LearnCardGroup;

import java.util.List;
import java.util.Optional;

public interface LearnCardGroupService {

    LearnCardGroup findByIdAndUserName(long id, String name);

    void deleteByIdAndUserName(long id, String name);

    List<LearnCardGroup> findByUserName(String username);

    Optional<LearnCardGroup> findById(long id);

    LearnCardGroup save(LearnCardGroup group);



}
