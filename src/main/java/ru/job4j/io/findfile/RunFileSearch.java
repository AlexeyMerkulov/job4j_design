package ru.job4j.io.findfile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class RunFileSearch {

    public static Predicate<Path> selectSearchType(String typeOfSearch, String value) {
        Predicate<Path> predicate;
        switch (typeOfSearch) {
            case "mask":
                predicate = p -> p.toFile().getName().endsWith(value.substring(1));
                break;
            case "name":
                predicate = p -> p.toFile().getName().equals(value);
                break;
            case "regex":
                predicate = p -> p.toFile().getName().matches(value);
                break;
            default:
                predicate = null;
        }
        return predicate;
    }

    public static void writeInFile(String filePath, List<Path> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            list.forEach(pw::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Validation validation = Validation.of(args);
        SearchLogic search = new SearchLogic(selectSearchType(validation.get("t"), validation.get("n")));
        Files.walkFileTree(Paths.get(validation.get("d")), search);
        List<Path> list = search.getPaths();
        writeInFile(validation.get("o"), list);
    }
}
