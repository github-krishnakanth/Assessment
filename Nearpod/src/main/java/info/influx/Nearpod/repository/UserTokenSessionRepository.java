package info.influx.Nearpod.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import info.influx.Nearpod.model.user.UserTokenSession;

@Repository
public interface UserTokenSessionRepository extends CrudRepository<UserTokenSession, Long>{
	
	Optional<UserTokenSession> findOneByUsername(String username);
}
