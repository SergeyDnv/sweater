package ru.dnsk.sweater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dnsk.sweater.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String name);
}
