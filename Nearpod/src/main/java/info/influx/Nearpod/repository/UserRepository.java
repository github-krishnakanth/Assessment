package info.influx.Nearpod.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.influx.Nearpod.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findOneByUsername(String username);
	
	Optional<User> findOneByUsernameAndPassword(String username, String password);
}
