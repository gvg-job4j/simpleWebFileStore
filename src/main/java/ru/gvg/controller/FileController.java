package ru.gvg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.gvg.model.User;
import ru.gvg.model.UserFile;
import ru.gvg.service.FileService;
import ru.gvg.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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

    private FileService fileService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("id") Long id
            , @RequestParam("file") MultipartFile file
            , HttpServletRequest request, Model model) throws IOException {
        String pageName = "redirect:/files";
        model.addAttribute("id", id);
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        String fileName = file.getOriginalFilename();
        if (fileName.lastIndexOf(File.separator) != -1) {
            fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
        }
        Path path = Paths.get(rootDirectory + fileDirectory + id.toString() + File.separator + fileName);
        try {
            file.transferTo(new File(path.toString()));
//            URL newUrl = new URL("file", "localhost", 8080, path.toString());
//            URL newUrl = request.getSession().getServletContext().getResource("/" + fileDirectory + fileName);
            URL newUrl = request.getSession().getServletContext()
                    .getResource("/" + fileDirectory + id.toString() + File.separator + fileName);
            UserFile userFile = new UserFile(fileName, path.toString(), newUrl, file.getSize());
            fileService.addFile(userFile, id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            pageName = "upload";
//            throw new RuntimeException("File saving failed", e);
        }
        return pageName;
    }

    @GetMapping("/files")
    public String getFilesPage(@RequestParam("id") Long id, Model model) {
        List<UserFile> files = fileService.getFilesByUserId(id);
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("files", files);
        return "filesPage";
    }

    @PostMapping("/deleteFile")
    public String deleteFile(@RequestParam("userId") Long userId, @RequestParam("fileId") Long fileId, Model model) {
        model.addAttribute("id", userId);
        fileService.deleteFile(fileId);
        return "redirect:/files";
    }

    @GetMapping("/upload")
    public String getUploadPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "upload";
    }
}
