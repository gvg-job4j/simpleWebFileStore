package ru.gvg.model;

import java.time.LocalDateTime;

/**
 * @author Valeriy Gyrievskikh
 * @since 12.08.2020
 */
public class File {
    /**
     * File name.
     */
    private String name;
    /**
     * Path to the file on the user's machine.
     */
    private String localPath;
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
    private String userId;

    public File() {
    }

    public String getName() {
        return name;
    }

    public String getLocalPath() {
        return localPath;
    }

    public String getServerPath() {
        return serverPath;
    }

    public long getSize() {
        return size;
    }

    public String getUserId() {
        return userId;
    }
}
