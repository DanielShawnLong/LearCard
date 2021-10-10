package htwsaar.ariadne.learcard.repositorys;


import htwsaar.ariadne.learcard.entity.LearnCard;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * CARD repository
 * @author Pamela Filipinski, Daniel-Shawn Long
 */
public interface LearnCardRepository extends JpaRepository<LearnCard, Long> {
    List<LearnCard> findByUserName(String username);


    LearnCard findByIdAndUserName(Long id,
                                  String username);

    List<LearnCard> findByGroupIdAndUserName(Long group,
                                             String username);

    List<LearnCard> findByGroupIdAndUserNameAndRightAnswer(Long group,
                                                           String username,
                                                           Boolean answer);

    List<LearnCard> findByGroupIdAndUserNameAndRightAnswerAndIsSolved(Long group,
                                                                      String username,
                                                                      Boolean rightAnswer,
                                                                      Boolean isSolved);

    @Transactional
    void deleteByIdAndUserName(Long id,
                               String username);
}
