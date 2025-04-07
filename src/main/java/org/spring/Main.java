package org.spring;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        long startTime = System.currentTimeMillis();
        System.out.println("Hello world!");

        String from = "p1";
        String to = "p0";

        File filePath = new File(from);
        String[] strFiles = filePath.list();

        moveFile(from, strFiles, to);
        //deleteFile(strFiles, to);

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Затраченное время = " + timeElapsed);
    }

    public static void moveFile(String fileFrom, String[] files, String newPath) throws InterruptedException {

        for (int i = 0; i < files.length; i += Math.min(4, files.length-i-1)) {

            String[] part=new String[Math.min(4, files.length)];
            for(int t=0;t<part.length;t++){

                part[t]=files[i+t];
            }

            for (int j = 0; j < part.length; j++) {

                int finalJ = j;
                Runnable task = ()->{
                        String fileName = part[finalJ];
                        String destinationName = newPath + "/" + fileName;

                        try {
                            Files.copy(Path.of(fileFrom + "/" + fileName), Path.of(destinationName));
                            System.out.println("Файл " + fileName + " успешно скопирован.");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    };

                Thread thread1=new Thread(task);
                thread1.start();
            }
        }
    }

    public static void deleteFile(String[] files, String newPath) throws InterruptedException {

        for (int i = 0; i < files.length - 4; i += Math.min(4, files.length)) {

            String[] part=new String[Math.min(4, files.length)];
            for(int t=0;t<part.length;t++){

                part[t]=files[i+t];
            }

            for (int j = 0; j < part.length; j++) {

                int finalJ = j;
                Runnable task=()->{
                        String fileName = part[finalJ];
                    String destinationName = newPath + "/" + fileName;

                        try {
                            Files.delete(Path.of(destinationName));
                            System.out.println("Файл " + fileName + " успешно удален.");
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    };

                Thread thread1=new Thread(task);
                thread1.start();
            }
        }
    }
}