package htwsaar.ariadne.learcard.security.service.interfaces;

import htwsaar.ariadne.learcard.entity.LearnCard;

import java.util.List;
import java.util.Optional;


public interface LearnCardService {

    List<LearnCard> getAll();

    LearnCard insertCard(LearnCard card);

    LearnCard findCardByIdandName(long id, String name);

    Optional<LearnCard> findCardById(long id);

    List<LearnCard> findAllByName(String name);

    void removeCard(long id,String name);

    List<LearnCard> findByGroupIdAndUserName(Long group, String username);

    List<LearnCard> findByGroupIdAndUserNameAndRightAnswer(Long group,
                                                           String username,
                                                           Boolean answer);

    List<LearnCard> findByGroupIdAndUserNameAndRightAnswerAndIsSolved(Long group,
                                                                      String username,
                                                                      Boolean rightAnswer,
                                                                      Boolean isSolved);





}
