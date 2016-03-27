package com.cooldoger.gifter;

import java.util.*;
import java.nio.file.Path;
import java.nio.file.FileSystems;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("loading file: " + args[0]);
        boolean isNonFamilyGifter = false;
        if (args.length > 1 && args[1].toLowerCase().equals("-nonfamilygifter")) {
            isNonFamilyGifter = true;
        }

        Gifter gifterApp;
        if (isNonFamilyGifter) {
            gifterApp = new NonFamilyGifter();
        } else {
            gifterApp = new Gifter();
        }
        Path inputPath = FileSystems.getDefault().getPath(args[0]);
        int num = gifterApp.loadData(inputPath);
        gifterApp.shuffleList();
        gifterApp.printResult();
    }
}
