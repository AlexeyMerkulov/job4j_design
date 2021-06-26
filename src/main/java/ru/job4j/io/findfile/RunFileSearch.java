package ru.job4j.io.findfile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunFileSearch {

    private static Predicate<Path> predicateWithMatcher(Pattern pattern) {
        return p -> {
            Matcher matcher = pattern.matcher(p.toFile().getName());
            return matcher.matches();
        };
    }

    public static Predicate<Path> selectSearchType(String typeOfSearch, String value) {
        Predicate<Path> predicate;
        switch (typeOfSearch) {
            case "mask":
                Pattern pattern = Pattern.compile(value.replace(".", "\\.").
                                replace("?", ".?").replace("*", ".*"));
                predicate = predicateWithMatcher(pattern);
                break;
            case "name":
                predicate = p -> p.toFile().getName().equals(value);
                break;
            case "regex":
                pattern = Pattern.compile(value);
                predicate = predicateWithMatcher(pattern);
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
