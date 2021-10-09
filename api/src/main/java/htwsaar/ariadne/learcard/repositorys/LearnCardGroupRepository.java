
package htwsaar.ariadne.learcard.repositorys;

import htwsaar.ariadne.learcard.entity.LearnCardGroup;

import org.springframework.data.jpa.repository.JpaRepository;


import javax.transaction.Transactional;
import java.util.List;

/**
 * GROUP repository
 * @author Pamela Filipinski, Felix Werner
 */
public interface LearnCardGroupRepository extends JpaRepository<LearnCardGroup, Long> {
    List<LearnCardGroup> findByUserName(String username);

    LearnCardGroup findByIdAndUserName(Long id,
                                       String username);

    @Transactional
    void deleteByIdAndUserName(Long id,
                               String username);
}
