package com.cooldoger.gifter;

import java.util.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.IOException;

public class Gifter {
    public Gifter() {
    }

    public void loadData(Path inputPath) throws IOException {
        // Assume the input file is encoded in UTF_8
        BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8);
        for (String line; (line = reader.readLine()) != null; ) {
            System.out.println(line);
        }
    }
}
