package ru.gvg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gvg.model.UserFile;
import ru.gvg.repository.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 11.10.2020
 */
@Service
public class FileService {
    private FileRepository fileRepository;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFileRepository(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public List<UserFile> getFilesByUserId(Long userId) {
        return fileRepository.getByUserId(userId);
    }

    @Transactional
    public void addFile(UserFile userFile, Long userId) throws Exception {
        try {
            userFile.setUser(userService.findUserById(userId));
            fileRepository.addFile(userFile);
        } catch (Exception e) {
            throw new RuntimeException("File saving failed", e);
        }
    }

    @Transactional
    public void deleteFile(Long id) {
        fileRepository.deleteFile(id);
    }

    @Transactional
    public void deleteUserFiles(Long userId) {
        List<UserFile> files = fileRepository.getByUserId(userId);
        for (UserFile file : files) {
            fileRepository.deleteFile(file.getId());
        }
    }

    public void createDirectory(Path path) {
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
