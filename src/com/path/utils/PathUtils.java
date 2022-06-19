package com.path.utils;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
@Setter
public class PathUtils {
    private final String EMPTY_STRING;
    //Null String can be anything.
    private final String NULL_STRING;

    //Check to see if the generated path from methods exist in the computer or not, if not it will return empty String value.
    private boolean checkPath;
    //Enable to check if the input paths from methods are null or not, if it's true if will throw NullPointerException
    //and if it is false, it will return an empty String value for the path.
    private boolean throwNullPointerException;
    //If Dot(.) character detected in method input if true it will return the current path,application is executing
    //and if false it will return empty String value.
    private boolean disableDotCharacter;

    public PathUtils(boolean checkPath, boolean throwNullPointerException) {
        EMPTY_STRING = "";
        NULL_STRING = "null";

        this.checkPath = checkPath;
        this.throwNullPointerException = throwNullPointerException;
        this.disableDotCharacter = false;
    }

    /**
     * Returns the full path of a file or folder by its name.
     *
     * @param name The file or folder name.
     * @return The path of file or folder.
     */
    public String getPath(String name) {
        if (checkInput(name).equals(NULL_STRING)) {
            return NULL_STRING;
        }

        if (checkPath) {
            return FilenameUtils.getPath(name);
        }

        if (disableDotCharacter && name.equals(".")) {
            return NULL_STRING;
        }

        return new File(name).getAbsolutePath();
    }

    /**
     * Returns only the name of a file or folder with its extension .
     *
     * @param path The file or folder path.
     * @return For the files it returns the full name with extension and for folders it returns just the name.
     */
    public String getName(String path) {
        if (checkInput(path).equals(NULL_STRING)) {
            return NULL_STRING;
        }

        return FilenameUtils.getName(path);
    }

    /**
     * Returns the file or folder name without extension.
     *
     * @param path The file or folder path.
     * @return For the files it returns only the file name without extension and for folders it returns just the name.
     */
    public String getBaseName(String path) {
        if (checkInput(path).equals(NULL_STRING)) {
            return NULL_STRING;
        }

        return FilenameUtils.getBaseName(path);
    }

    /**
     * Returns the extension of a file or folder.
     *
     * @param path The file or folder path.
     * @return For the files it returns only the extension of file without its name and for folders it returns just the name.
     */
    public String getExtension(String path) {
        if (checkInput(path).equals(NULL_STRING)) {
            return NULL_STRING;
        }

        return FilenameUtils.getExtension(path);
    }

    /**
     * Returns the parent folder name of specific path.
     *
     * @param path The file or folder path.
     * @return The parent name of file or folder.
     */
    public String getParentName(String path) {
        if (checkInput(path).equals(NULL_STRING)) {
            return NULL_STRING;
        }

        if (disableDotCharacter && path.equals(".")) {
            return NULL_STRING;
        }

        if (checkPath && !new File(path).exists()) {
            return NULL_STRING;
        }

        return Paths.get(path).getParent().getFileName().toString();
    }

    /**
     * Returns the parent folder path of specific path.
     *
     * @param path The file or folder path.
     * @return The parent full path of file or folder.
     */
    public String getParentPath(String path) throws IOException {
        if (checkInput(path).equals(NULL_STRING)) {
            return NULL_STRING;
        }

        if (disableDotCharacter && path.equals(".")) {
            return NULL_STRING;
        }

        if (checkPath && !new File(path).exists()) {
            return NULL_STRING;
        }

        return Paths.get(path).getParent().toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
    }

    /**
     * Convert the specific path to normal path using separators and dots(.).
     *
     * @param path          The file or folder path.
     * @param unixSeparator The unix separator is used when you are using unix based os like mac or linux,for windows disable it.
     * @return The normalized path that can easily use.
     */
    public String normalize(String path, boolean unixSeparator) {
        if (checkInput(path).equals(NULL_STRING)) {
            return NULL_STRING;
        }

        String normalizedPath = FilenameUtils.normalize(path, unixSeparator);
        if (checkPath && !new File(normalizedPath).exists()) {
            return NULL_STRING;
        }

        return normalizedPath;
    }

    /**
     * Returns the file or folder new name without extension.
     *
     * @param path The path of file or folder.
     * @return For the files it will remove its extension and return the name of file
     * and for folders it doesn't do anything just returns the name of it.
     */
    public String removeExtension(String path) {
        if (checkInput(path).equals(NULL_STRING)) {
            return NULL_STRING;
        }

        return FilenameUtils.removeExtension(path);
    }

    /**
     * Check the methods input by specific arguments in the class, if NullPointerException is
     * set to true it will check for null inputs and throw exception if one of them is null otherwise it will return null string
     * or check to see if the generated path or name from methods actually exists in the computer or not, if true it will return it
     * and if false it will return null;
     *
     * @param input The input from methods arguments.
     * @return The filtered method inputs.
     */
    public String checkInput(String input) {
        if (input == null) {
            if (throwNullPointerException) {
                throw new NullPointerException("The input is null.");
            } else {
                return NULL_STRING;
            }
        }

        return input;
    }
}
