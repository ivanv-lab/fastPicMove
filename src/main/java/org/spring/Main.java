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

        List<Thread> threads = new ArrayList<>();

        for (String file : files) {
            Runnable task = () -> {
                String fileName = fileFrom + "/" + file;
                String destinationName = newPath + "/" + file;

                try {
                    Files.copy(Path.of(fileFrom + "/" + fileName), Path.of(destinationName));
                    System.out.println("Файл " + fileName + " успешно скопирован.");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            };

            Thread thread=new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for(Thread thread:threads){
            thread.join();
        }
//        Runnable task = () -> {
//            String fileName = part[finalJ];
//            String destinationName = newPath + "/" + fileName;
//
//            try {
//                Files.copy(Path.of(fileFrom + "/" + fileName), Path.of(destinationName));
//                System.out.println("Файл " + fileName + " успешно скопирован.");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        };
    }

    public static void deleteFile(String[] files, String newPath) throws InterruptedException {

//        Runnable task = () -> {
//            String fileName = part[finalJ];
//            String destinationName = newPath + "/" + fileName;
//
//            try {
//                Files.delete(Path.of(destinationName));
//                System.out.println("Файл " + fileName + " успешно удален.");
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        };
    }
}