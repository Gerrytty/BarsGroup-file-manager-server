package ru.itis.filemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.filemanager.model.Userm;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Userm, Long> {

    Optional<Userm> findUsermByLogin(String login);

}
