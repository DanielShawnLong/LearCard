package htwsaar.ariadne.learcard.repositorys;

import htwsaar.ariadne.learcard.entity.LearnCard;

import htwsaar.ariadne.learcard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;
import java.util.List;


public interface LearnCardRepository extends JpaRepository<LearnCard, Long> {
    List <LearnCard>  findByUserName(String username);
    LearnCard findByIdAndUserName (Long id, String username);
    @Transactional
    void deleteByIdAndUserName(Long id, String username);
}
