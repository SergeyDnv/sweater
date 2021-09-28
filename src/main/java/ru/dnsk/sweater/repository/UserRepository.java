package ru.dnsk.sweater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dnsk.sweater.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);

    User findByActivationCode(String code);
}
