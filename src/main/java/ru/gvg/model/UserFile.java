package ru.gvg.model;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDateTime;

/**
 * @author Valeriy Gyrievskikh
 * @since 12.08.2020
 */
@Entity
@Table(name="files")
public class UserFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private URL fileUrl;
    /**
     * File name.
     */
    private String name;
    /**
     * Path to the file on the server.
     */
    private String serverPath;
    /**
     * File size.
     */
    private long size;
    /**
     * User id.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public UserFile() {
    }

    public UserFile(String name, String serverPath, URL fileUrl, long size) {
        this.name = name;
        this.serverPath = serverPath;
        this.size = size;
        this.fileUrl = fileUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public URL getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(URL fileUrl) {
        this.fileUrl = fileUrl;
    }
}
