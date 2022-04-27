package ru.job4j.chapter004.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(cachingDir + "\\" + key))) {
            in.lines().forEach(sj::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sj.toString();
    }
}