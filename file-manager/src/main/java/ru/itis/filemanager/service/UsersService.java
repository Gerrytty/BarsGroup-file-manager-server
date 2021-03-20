package ru.itis.filemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.filemanager.dto.UserDto;
import ru.itis.filemanager.model.Userm;
import ru.itis.filemanager.repository.UsersRepository;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserDto getUserInfo(Long id) {

        Userm user = usersRepository.getOne(id);

        return UserDto.builder()
                .firstname(user.getName())
                .username(user.getLogin())
                .build();

    }
}
