package htwsaar.ariadne.learcard.repositorys;

import com.sun.org.apache.xpath.internal.operations.Bool;
import htwsaar.ariadne.learcard.entity.LearnCard;

import htwsaar.ariadne.learcard.entity.LearnCardGroup;
import htwsaar.ariadne.learcard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;
import java.util.List;


public interface LearnCardRepository extends JpaRepository<LearnCard, Long> {
    List <LearnCard>  findByUserName(String username);
    LearnCard findByIdAndUserName (Long id, String username);
    List <LearnCard> findByGroupIdAndUserName(Long group, String username);

    List <LearnCard> findByGroupIdAndUserNameAndRightAnswer(Long group, String username, Boolean answer);
    List <LearnCard> findByGroupIdAndUserNameAndRightAnswerAndIsSolved(Long group, String username, Boolean rightAnswer, Boolean isSolved);

    @Transactional
    void deleteByIdAndUserName(Long id, String username);
}
