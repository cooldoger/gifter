package com.cooldoger.gifter;

import java.nio.file.Path;
import java.nio.file.FileSystems;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GifterTest {
    @Test
    public void testLoadDataSuccess() throws Exception {
        Gifter app = new Gifter();
        Path inputPath = FileSystems.getDefault().getPath("tst/res", "example.txt");
        int num = app.loadData(inputPath);
        assertEquals(num, 3);
    }
}
