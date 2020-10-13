package ru.gvg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.gvg.model.User;
import ru.gvg.model.UserFile;
import ru.gvg.service.FileService;
import sun.net.www.http.HttpClient;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 11.10.2020
 */
@Controller
@PropertySource("classpath:application.properties")
public class FileController {

    @Value("${file.directory}")
    private String fileDirectory;

    private Long userId;

    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) throws IOException {
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        String fileName = file.getOriginalFilename();
        if (fileName.lastIndexOf(File.separator) != -1){
            fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
        }
        Path path = Paths.get(rootDirectory + fileDirectory + fileName);
        model.addAttribute("id", userId);
        try {
            file.transferTo(new File(path.toString()));
//            URL newUrl = new URL("file", "localhost", 8080, path.toString());
            URL newUrl = request.getSession().getServletContext().getResource("/" + fileDirectory + fileName);
            UserFile userFile = new UserFile(fileName, path.toString(), newUrl, file.getSize());
            fileService.addFile(userFile, userId);
            model.addAttribute("filename", fileName);
        } catch (Exception e) {
            model.addAttribute("fileSaveError", e.getMessage());
            throw new RuntimeException("File saving failed", e);
        }
        return "redirect:/files";
//        return "success";
    }

    @GetMapping("/files")
    public String getFilesPage(@RequestParam("id") Long id, Model model) {
        List<UserFile> files = fileService.getFilesByUserId(id);
        model.addAttribute("files", files);
        userId = id;
        return "filesPage";
    }

    @PostMapping("/deleteFile")
    public String deleteFile(@RequestParam("fileId") Long fileId, Model model){
        model.addAttribute("id", userId);
        fileService.deleteFile(fileId);
        return "redirect:/files";
    }

    @GetMapping("/upload")
    public String getUploadPage() {
        return "upload";
    }
}
