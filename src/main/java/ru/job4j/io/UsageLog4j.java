package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte first = 120;
        short second = 20000;
        int third = 40000;
        long forth = 50000L;
        float fifth = 4.35F;
        double sixth = 11.11;
        char c = 'c';
        boolean check = true;

        LOG.debug("byte {} short {} int {} long {} float {} double {} char {} boolean {}",
                first, second, third, forth, fifth, sixth, c, check);

    }
}