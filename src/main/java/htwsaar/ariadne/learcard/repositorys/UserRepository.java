package htwsaar.ariadne.learcard.repositorys;

import htwsaar.ariadne.learcard.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
