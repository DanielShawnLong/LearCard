package htwsaar.ariadne.learcard.repositorys;

import htwsaar.ariadne.learcard.entity.LearnCard;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LearnCardRepository extends JpaRepository<LearnCard, Long> {
}
