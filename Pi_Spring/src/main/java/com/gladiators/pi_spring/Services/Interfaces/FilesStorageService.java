package com.gladiators.pi_spring.Services.Interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
    public void init();

    public String fileName(MultipartFile file);

    public void save(MultipartFile file, String newName);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();


}
