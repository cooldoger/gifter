package com.cooldoger.gifter;

import java.util.*;
import java.nio.file.*;
import org.junit.Test;
import java.lang.IllegalArgumentException;
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

    @Test(expected=IllegalArgumentException.class)
    public void testLoadDataEmpty() throws Exception {
        Gifter app = new Gifter();
        Path inputPath = FileSystems.getDefault().getPath("tst/res", "empty.txt");
        int num = app.loadData(inputPath);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLoadDataOneLine() throws Exception {
        Gifter app = new Gifter();
        Path inputPath = FileSystems.getDefault().getPath("tst/res", "oneLine.txt");
        int num = app.loadData(inputPath);
    }

    private void shuffleTest(Gifter app, int runTimes, double epsilon) {
        List<Person> userList = app.getUserList();
        int num = userList.size();

        int result[][] = new int[num][num];
        Map<String, Integer> mapping = new HashMap<>();
        int idx = 0;
        for (Person p : userList) {
            mapping.put(p.getName(), idx++);
        }

        for (int i = 0; i < runTimes; i++) {
            userList = app.shuffleList();
            for (Person cur : userList) {
                int curIdx = mapping.get(cur.getName());
                int giverIdx = mapping.get(cur.getGiver().getName());
                result[curIdx][giverIdx]++;
            }
        }

        int avgHit = runTimes / (num - 1);
        double epsilonVal = avgHit * epsilon;

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (i == j) {
                    assertEquals(result[i][j], 0);
                } else {
                    assertTrue("result : " + result[i][j] + " !~= " + avgHit +
                            " ~ " + epsilonVal,
                            Math.abs(result[i][j] - avgHit) < epsilonVal);
                }
            }
        }
    }

    @Test
    public void testShuffleExample() throws Exception {
        Gifter app = new Gifter();
        Path inputPath = FileSystems.getDefault().getPath("tst/res", "example.txt");
        int num = app.loadData(inputPath);
        assertEquals(num, 3);
        shuffleTest(app, 100000, 0.01);
    }

    @Test
    public void testShuffleExample10() throws Exception {
        Gifter app = new Gifter();
        Path inputPath = FileSystems.getDefault().getPath("tst/res", "10Lines.txt");
        int num = app.loadData(inputPath);
        assertEquals(num, 10);
        shuffleTest(app, 100000, 0.05);
    }

    @Test
    public void testShuffleExample100() throws Exception {
        Gifter app = new Gifter();
        Path inputPath = FileSystems.getDefault().getPath("tst/res", "100Lines.txt");
        int num = app.loadData(inputPath);
        assertEquals(num, 100);
        shuffleTest(app, 100000, 0.15);
    }
}
