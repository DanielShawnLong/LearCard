package htwsaar.ariadne.learcard.repositorys;

import htwsaar.ariadne.learcard.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
