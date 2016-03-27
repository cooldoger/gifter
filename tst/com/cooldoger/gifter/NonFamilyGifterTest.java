package com.cooldoger.gifter;

import java.util.*;
import java.nio.file.*;
import org.junit.Test;
import org.junit.Before;
import java.lang.IllegalArgumentException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NonFamilyGifterTest extends GifterTest {

    @Before
    public void SetupGifterApp() {
        this.app = new NonFamilyGifter();
    }

    @Override
    @Test
    public void testShuffleExample10() throws Exception {
        Path inputPath = FileSystems.getDefault().getPath("tst/res", "10Lines.txt");
        int num = app.loadData(inputPath);
        assertEquals(num, 10);
    }

    @Override
    @Test
    public void testShuffleExample100() throws Exception {
    }

}
