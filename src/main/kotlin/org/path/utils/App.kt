package org.path.utils

fun main() {
    val pathUtils = PathUtils(
        true,
        false,
        "Invalid path detected"
    )

    val path = "file.txt"

    println("path: ${pathUtils.getPath(path)}")
    println("name: ${pathUtils.getName(path)}")
    println("base name: ${pathUtils.getBaseName(path)}")
    println("extension: ${pathUtils.getExtension(path)}")
    println("parent name: ${pathUtils.getParentName(path)}")
    println("parent path: ${pathUtils.getParentPath(path)}")
    println("normalize: ${pathUtils.normalize(path)}")
}