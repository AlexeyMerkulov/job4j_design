package ru.job4j.chapter001.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        int numberOfSameElements = 0;
        Info info = new Info();
        Map<Integer, String> map = new HashMap<>();
        for (User el : previous) {
            map.put(el.id, el.name);
        }
        int initialSize = map.size();
        for (User el : current) {
            if (!map.containsKey(el.id)) {
                info.added++;
            } else if (!map.remove(el.id, el.name)) {
                info.changed++;
            } else {
                numberOfSameElements++;
            }
        }
        info.deleted = initialSize - info.changed - numberOfSameElements;
        return info;
    }

    public static class User {
        int id;
        String name;
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
