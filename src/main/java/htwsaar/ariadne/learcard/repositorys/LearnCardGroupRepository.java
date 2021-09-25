
package htwsaar.ariadne.learcard.repositorys;

import htwsaar.ariadne.learcard.entity.LearnCardGroup;

import htwsaar.ariadne.learcard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;
import java.util.List;


public interface LearnCardGroupRepository extends JpaRepository<LearnCardGroup, Long> {
    List <LearnCardGroup>  findByUserName(String username);
    LearnCardGroup findByIdAndUserName (Long id, String username);
    @Transactional
    void deleteByIdAndUserName(Long id, String username);
}
