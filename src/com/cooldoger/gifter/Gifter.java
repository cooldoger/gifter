package com.cooldoger.gifter;

import java.util.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.IOException;

public class Gifter {
    private List<Person> userList;

    public Gifter() {
        userList = new ArrayList<>();
    }

    public int loadData(Path inputPath) throws IOException {
        // Assume the input file is encoded in UTF_8
        BufferedReader reader = Files.newBufferedReader(inputPath,
                                                        StandardCharsets.UTF_8);
        for (String line; (line = reader.readLine()) != null; ) {
            String[] userData = line.split(" ", 3);
            Person p = new Person(userData[0], userData[1], userData[2]);
            userList.add(p);
        }
        return userList.size();
    }

    public void shuffleList() {
        Collections.shuffle(userList);
    }

    public void printResult() {
        int n = userList.size();
        for (int i = 0; i < n; i++) {
            int nextUser = i + 1;
            if (nextUser == n) {
                nextUser = 0;
            }
            System.out.println(userList.get(i).getName() + " - " +
                    userList.get(nextUser).getName());
        }
    }
}
