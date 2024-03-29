package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Set<FileProperty> duplicateSet = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileWrapper = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (duplicateSet.contains(fileWrapper)) {
            System.out.println(file.toAbsolutePath());
        } else {
            duplicateSet.add(fileWrapper);
        }
        return super.visitFile(file, attrs);
    }
}
