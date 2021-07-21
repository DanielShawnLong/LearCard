package htwsaar.ariadne.learcard.repositorys;

import htwsaar.ariadne.learcard.entity.LearnCard;

import htwsaar.ariadne.learcard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;


public interface LearnCardRepository extends JpaRepository<LearnCard, Long> {
    List <LearnCard>  findByUserName(String username);
}
