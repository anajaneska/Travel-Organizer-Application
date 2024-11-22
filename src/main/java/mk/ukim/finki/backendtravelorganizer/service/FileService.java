package mk.ukim.finki.backendtravelorganizer.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String saveFile(MultipartFile file);
}
