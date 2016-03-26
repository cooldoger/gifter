package com.cooldoger.gifter;

import java.util.*;
import java.nio.file.Path;
import java.nio.file.FileSystems;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello JJJJJ");
        Gifter gifterApp = new Gifter();
        Path inputPath = FileSystems.getDefault().getPath("tst/res", "example.txt");
        gifterApp.loadData(inputPath);
    }
}
