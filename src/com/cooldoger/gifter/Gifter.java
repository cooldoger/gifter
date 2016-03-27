package com.cooldoger.gifter;

import java.util.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.IllegalArgumentException;

/**
 * Gifter application to shuffle the participants.
 *
 * The algorithm is shuffling the users and having the next user as secret gift
 * receiver. Have the user list as a cycle list, if next user is beyong the
 * user list, go back to the first one.
 */
public class Gifter {
    protected List<Person> userList;

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
        int num = userList.size();
        if (num <= 1) {
            throw new IllegalArgumentException("At least 2 users. Provided: " + num);
        }
        return num;
    }

    public List<Person> shuffleList() {
        Collections.shuffle(userList);
        int n = userList.size();
        for (int i = 0; i < n; i++) {
            int recUserIdx = i + 1;
            if (recUserIdx == n) {
                recUserIdx = 0;
            }
            Person curUser = userList.get(i);
            Person recUser = userList.get(recUserIdx);
            curUser.setRecipient(recUser);
            recUser.setGiver(curUser);
        }
        return this.userList;
    }

    public void printResult() {
        for (Person user : userList) {
            System.out.println(user.getName() + " - " +
                               user.getGiver().getName()); }
    }

    public List<Person> getUserList() {
        return this.userList;
    }
}
