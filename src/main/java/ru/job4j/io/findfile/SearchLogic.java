package ru.job4j.io.findfile;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchLogic extends SimpleFileVisitor<Path> {
    private Predicate<Path> predicate;
    private List<Path> resultList = new ArrayList<>();

    public SearchLogic(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    public List<Path> getPaths() {
        return resultList;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate == null) {
            throw new IllegalArgumentException("Задайте корректный тип поиска");
        }
        if (predicate.test(file)) {
            resultList.add(file);
        }
        return super.visitFile(file, attrs);
    }
}
