package ru.gvg.repository;

import ru.gvg.model.UserFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 11.10.2020
 */
public interface FileRepository {
    List<UserFile> getByUserId(Long userId);
    public void addFile(UserFile userFile);
    UserFile getFile(Long id);
    void deleteFile(Long id);
}
