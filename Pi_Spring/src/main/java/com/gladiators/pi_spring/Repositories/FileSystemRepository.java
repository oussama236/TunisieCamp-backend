package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Image;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Repository
public interface FileSystemRepository extends JpaRepository<Image, Long> {



    public default String save(MultipartFile content) throws Exception {
        Path newFile = Paths.get(System.getProperty("user.dir")+"/assets/springfever-" + new Date().getTime() + "-" + content.getOriginalFilename()); // to change
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content.getBytes());
        return newFile.toAbsolutePath()
                .toString();
    }

    public default FileSystemResource findInFileSystem(String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            // Handle access or file not found problems.
            throw new RuntimeException();
        }
    }
}