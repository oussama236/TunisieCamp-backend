package com.gladiators.pi_spring.Services.Implementations;

import com.gladiators.pi_spring.Entities.Image;
import com.gladiators.pi_spring.Repositories.FileSystemRepository;
import com.gladiators.pi_spring.Repositories.ImageRepository;
import com.gladiators.pi_spring.Services.Interfaces.IFileLocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class FileLocationService implements IFileLocationService {
    @Autowired
    FileSystemRepository fileSystemRepository;

    @Autowired
    ImageRepository imageDataRepository;

    @Override
    public Image save(MultipartFile file) throws Exception {
        String location = fileSystemRepository.save (file);
        return imageDataRepository.save (new Image (file.getOriginalFilename (), location));
    }

    @Override
    public FileSystemResource find(Long imageId) {

        Image image = imageDataRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException (HttpStatus.NOT_FOUND));
        return fileSystemRepository.findInFileSystem(image.getLocation());

    }
}
