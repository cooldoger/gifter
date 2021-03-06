package com.cooldoger.gifter;

import java.util.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.IllegalArgumentException;

/**
 * Gifter application to shuffle the participants but avoid the gifter from the
 * same family.
 *
 * It group the participants into families. And shuffle the family list, then
 * shuffle the participants inside of each family. Then the gifter receiver
 * index is: (currentUserIdx + maxFamilySize) % numUser
 */
public class NonFamilyGifter extends Gifter {
    protected List<Family> familyList;
    private int maxFamilySize;

    public NonFamilyGifter() {
        super();
        familyList = new ArrayList<>();
    }

    @Override
    public int loadData(Path inputPath) throws IOException {
        int num = super.loadData(inputPath);
        Map<String, Family> familyHash = new HashMap<>();
        maxFamilySize = 0;
        for (Person p : this.userList) {
            // Assume the participants share the same last name are family.
            String lastName = p.getLastName();
            Family f = familyHash.get(lastName);
            if (f == null) {
                f = new Family(lastName);
                familyHash.put(lastName, f);
                this.familyList.add(f);
            }
            f.addMember(p);
            maxFamilySize = Math.max(maxFamilySize, f.size());
        }
        if (maxFamilySize > num / 2) {
            throw new IllegalArgumentException(
                    "More than half of the participants are from the same family.");
        }
        return num;
    }

    @Override
    public List<Person> shuffleList() {
        Collections.shuffle(familyList);
        this.userList = new ArrayList<>();
        for (Family f : familyList) {
            Collections.shuffle(f.getMembers());
            this.userList.addAll(f.getMembers());
        }

        int n = userList.size();
        for (int i = 0; i < n; i++) {
            int recUserIdx = (i + this.maxFamilySize) % n;
            Person curUser = userList.get(i);
            Person recUser = userList.get(recUserIdx);
            curUser.setRecipient(recUser);
            recUser.setGiver(curUser);
        }
        return this.userList;
    }

    public List<Family> getFamilyList() {
        return this.familyList;
    }
}
