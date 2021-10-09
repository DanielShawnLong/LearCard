package htwsaar.ariadne.learcard.repositorys;

import htwsaar.ariadne.learcard.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * USER repository
 * @author Pamela Filipinski
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
