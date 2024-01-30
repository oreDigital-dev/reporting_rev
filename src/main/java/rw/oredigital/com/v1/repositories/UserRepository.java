package rw.oredigital.com.v1.repositories;

import org.springframework.stereotype.Repository;
import rw.oredigital.com.v1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
