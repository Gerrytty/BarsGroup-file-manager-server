package ru.itis.filemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.filemanager.model.Userm;

@Repository
public interface UsersRepository extends JpaRepository<Userm, Long> {

    Userm findUsermByLogin(String login);

}
