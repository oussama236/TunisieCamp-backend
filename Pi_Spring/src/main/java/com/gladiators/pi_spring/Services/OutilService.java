package com.gladiators.pi_spring.Services;

import com.gladiators.pi_spring.Entities.ImageData;
import com.gladiators.pi_spring.Entities.Outils;
import com.gladiators.pi_spring.Repositories.OutilRepository;
import com.gladiators.pi_spring.Repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class OutilService {
    private static OutilRepository outilRepository;
    @Autowired
    private OutilRepository outilsRepository;

    public Outils getOutilById(long id) {
        return outilRepository.findById(id).orElseThrow(() -> new RuntimeException("Outil introuvable"));
    }

    public List<Outils> getAllOutils() {
        return outilRepository.findAll();
    }

    public Outils addOutil(Outils outils) {
        return outilRepository.save(outils);
    }

    public Outils updateOutil(long id, Outils outils) {
        Outils existingOutil = getOutilById(id);
        existingOutil.setName(outils.getName());
        existingOutil.setPoids(outils.getPoids());
        existingOutil.setPrice(outils.getPrice());
        existingOutil.setCathegorie(outils.getCathegorie());
        return outilRepository.save(existingOutil);
    }

    public void deleteOutil(long id) {
        outilRepository.deleteById(id);
    }

    public static List<Outils> getTopVisitedOutils(int limit) {
        Pageable topN = (Pageable) PageRequest.of(0, limit, Sort.by("nombreVisites").descending());
        Page<Outils> topOutils = outilRepository.findAll((org.springframework.data.domain.Pageable) topN);
        return topOutils.getContent();
    }




    // CompressImage
    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }


    // decompressImage

    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }

    @Autowired
    private StorageRepository repository;
    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        System.err.println("*****0*******");
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        System.err.println("*****1*******");
        byte[] images=decompressImage(dbImageData.get().getImageData());
        System.err.println("*****2*******");

        return images;
    }

}
