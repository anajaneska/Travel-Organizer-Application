package mk.ukim.finki.backendtravelorganizer.service.impl;

import mk.ukim.finki.backendtravelorganizer.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    private final String uploadDirectory = "uploads/tickets/";

    public FileServiceImpl() {
        // Create the directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDirectory);
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize upload directory.", e);
        }
    }

    public String saveFile(MultipartFile file) {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDirectory, fileName);

        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }

        return filePath.toString();
    }
}
