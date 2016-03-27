package com.cooldoger.gifter;

import java.util.*;
import java.nio.file.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GifterTest {
    @Test
    public void testLoadDataSuccess() throws Exception {
        Gifter app = new Gifter();
        Path inputPath = FileSystems.getDefault().getPath("tst/res", "example.txt");
        int num = app.loadData(inputPath);
        assertEquals(num, 3);
    }

    @Test(expected=NoSuchFileException.class)
    public void testLoadDataFailed() throws Exception {
        Gifter app = new Gifter();
        Path inputPath = FileSystems.getDefault().getPath("tst/res", "missing.txt");
        int num = app.loadData(inputPath);
    }

    @Test
    public void testShuffle() throws Exception {
        Gifter app = new Gifter();
        Path inputPath = FileSystems.getDefault().getPath("tst/res", "example.txt");
        int num = app.loadData(inputPath);
        assertEquals(num, 3);
        int result[][] = new int[num][num];
        Map<String, Integer> mapping = new HashMap<>();
        List<Person> userList = app.getUserList();
        int idx = 0;
        for (Person p : userList) {
            mapping.put(p.getName(), idx++);
        }

        int runNum = 10000;
        for (int i = 0; i < runNum; i++) {
            userList = app.shuffleList();
            for (Person cur : userList) {
                int curIdx = mapping.get(cur.getName());
                int giverIdx = mapping.get(cur.getGiver().getName());
                result[curIdx][giverIdx]++;
            }
        }

        int avgHit = runNum / (num - 1);
        double epsilon = avgHit * 0.01;

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (i == j) {
                    assertEquals(result[i][j], 0);
                } else {
                    assertTrue(Math.abs(result[i][j] - avgHit) < epsilon);
                }
            }
        }
        //assertEquals(num, 4);
    }
}
