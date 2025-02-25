package org.example.bookslibrary.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, String> {
    Optional<User> findByOauth2Id(String oauth2Id);
}
