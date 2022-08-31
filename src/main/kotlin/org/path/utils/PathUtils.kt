package org.path.utils

import org.apache.commons.io.FilenameUtils
import java.io.File
import java.nio.file.LinkOption
import java.nio.file.Paths

/**
 * @author Mahdi Lavasani (darkDev8)
 * This class helps you find file and folder paths easily and modify them how you want to be.
 * @param checkPath Enable it if you want to check the file or folder path before getting path
 * if you enable this and specific path can't be found, it will return empty result.
 * @param dotEnabled This future prevent interference of dot(.) character with function parameters(paths) because
 * dot(.) means same place the program executed and sometimes this make invalid results.
 * @param pathNotFound It will show this text if you can't find the specific file or folder path.
 */
class PathUtils(var checkPath: Boolean = false, var dotEnabled: Boolean = false, var pathNotFound: String = "null") {
    private val currentPath: String = System.getProperty("user.dir"); /* current path where this program executed*/
    private val emptyString: String = ""

    /**
     * Gets the path of file or folder.
     * @param name The name of file or folder you want to get its path.
     * @return If path found it will return it and if not it will return empty result.
     */
    fun getPath(name: String): String {
        val input = File(name)

        if (name == ".") {
            getDotResult()
        }

        if (checkPath) {
            return when (input.exists()) {
                true -> input.absolutePath
                false -> pathNotFound
            }
        }

        return input.absolutePath
    }

    /**
     * Get the file or folder name from path.
     * @param path The full path of file or folder you want to fetch its name.
     * @return The name of the file or folder.
     */
    fun getName(path: String): String {
        val input = File(path)

        if (path == ".") {
            getDotResult()
        }

        if (checkPath) {
            return when (input.exists()) {
                true -> FilenameUtils.getName(path)
                false -> pathNotFound
            }
        }

        return FilenameUtils.getName(path)
    }

    /**
     * Get the base name of file or folder.
     * The base name is only the name of file without extension and for folders, its name is base name.
     * @param path The full path of file or folder you want to fetch its base name.
     * @return The base name of file or folder.
     */
    fun getBaseName(path: String): String {
        val input = File(path)

        if (path == ".") {
            getDotResult()
        }

        if (checkPath) {
            return when (input.exists()) {
                true -> FilenameUtils.getBaseName(path)
                false -> pathNotFound
            }
        }

        return FilenameUtils.getBaseName(path)
    }

    /**
     * Get the extension of file or folder.
     * The extension of files, but folders don't have extension.
     * @param path The full path of file or folder you want to fetch its extension.
     * @return The extension of file or folder.
     */
    fun getExtension(path: String): String {
        val input = File(path)

        if (path == ".") {
            getDotResult()
        }

        if (checkPath) {
            return when (input.exists()) {
                true -> FilenameUtils.getExtension(path)
                false -> pathNotFound
            }
        }

        return FilenameUtils.getExtension(path)
    }

    /**
     * Get the folder name that this file or folder is in it.
     * @param path The full path of file or folder you want to fetch its parent name.
     * @return The parent folder name this file or folder is in it.
     */
    fun getParentName(path: String): String {
        val input = File(path)

        if (path == ".") {
            getDotResult()
        }

        if (checkPath) {
            return when (input.exists()) {
                true -> Paths.get(input.absolutePath).parent.fileName.toString()
                false -> pathNotFound
            }
        }

        return Paths.get(input.absolutePath).parent.fileName.toString();
    }

    /**
     * Get the folder full path that this file or folder is in it.
     * @param path The full path of file or folder you want to fetch its parent folder full path.
     * @return The parent folder full path this file or folder is in it.
     */

    fun getParentPath(path: String): String {
        val input = File(path)

        if (path == ".") {
            getDotResult()
        }

        if (checkPath) {
            return when (input.exists()) {
                true -> Paths.get(input.absolutePath).getParent().toRealPath(LinkOption.NOFOLLOW_LINKS).toString()
                false -> pathNotFound
            }
        }

        return Paths.get(input.absolutePath).getParent().toRealPath(LinkOption.NOFOLLOW_LINKS).toString()
    }

    /**
     * It will normalize the path and get you clean ready path to find files and folders.
     * It will process every folder in and out characters like (.) and (..)
     * @param path The full path of file or folder you want to fetch its parent folder full path.
     * @return After normalize it will return the new path.
     */
    fun normalize(path: String): String {
        val os = System.getProperty("os.name").lowercase()
        val input = if (os.contains("win")) File(FilenameUtils.normalize(File(path).absolutePath, false))
        else File(FilenameUtils.normalize(File(path).absolutePath, true))

        if (path == ".") {
            getDotResult()
        }

        if (checkPath) {
            return when (input.exists()) {
                true -> input.absolutePath
                false -> pathNotFound
            }
        }

        return input.absolutePath
    }

    /**
     * Check to see method input is dot(.) or not.
     * If dot future was enabled it will return current path the program executed
     * and if not enabled it will return not path found variable.
     * @return True path for dot character to prevent invalid path to create.
     */
    private fun getDotResult(): String {
        return when (dotEnabled) {
            true -> currentPath
            false -> pathNotFound
        }
    }
}