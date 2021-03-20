package ru.itis.filemanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    boolean deleted;

    String actualName;

    String mimeType;

    Long size;

    @Lob
    private Blob data;

}