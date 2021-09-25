package htwsaar.ariadne.learcard.repositorys;


import htwsaar.ariadne.learcard.entity.LearnCard;
import htwsaar.ariadne.learcard.entity.LearnCardGroup;
import htwsaar.ariadne.learcard.entity.LearnSession;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface LearnSessionRepository extends JpaRepository<LearnSession, Long> {
    List<LearnSession> findByUserName(String username);
    LearnSession findByIdAndUserName (Long id, String username);
    @Transactional
    void deleteByIdAndUserName(Long id, String username);
}
