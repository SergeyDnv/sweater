package ru.dnsk.sweater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dnsk.sweater.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByTag(String tag);
}
