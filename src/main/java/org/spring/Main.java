package org.spring;

import javax.naming.spi.DirectoryManager;
import java.awt.desktop.FilesEvent;
import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class Main {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        System.out.println("Hello world!");

        String fileFrom1="/src/main/resources/p01";
        String fileFrom2="/src/main/resources/p02";
        String fileFrom3="/src/main/resources/p03";
        String fileFrom4="/src/main/resources/p04";

        String fileTo1="/src/main/resources/p11";
        String fileTo2="/src/main/resources/p12";
        String fileTo3="/src/main/resources/p13";
        String fileTo4="/src/main/resources/p14";

        String[]files=

        long endTime=System.currentTimeMillis();
        long timeElapsed=endTime-startTime;
        System.out.println("Затраченное время = "+timeElapsed);
    }
}