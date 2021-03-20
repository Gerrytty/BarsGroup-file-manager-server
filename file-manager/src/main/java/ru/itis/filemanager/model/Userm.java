package ru.itis.filemanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.filemanager.dto.Role;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Userm {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String login;

    private String pass;
    private Role role;


}
