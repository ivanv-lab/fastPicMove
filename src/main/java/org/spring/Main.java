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
    private static final int CORE_COUNT=10;

    public static void main(String[] args) throws IOException, InterruptedException {

        long startTime = System.currentTimeMillis();
        System.out.println("Hello world!");

        String from = "p1";
        String to = "p0";

        File filePath = new File(from);
        String[] strFiles = filePath.list();

        moveFile(from, strFiles, to);
        deleteFile(strFiles, to);

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Затраченное время = " + timeElapsed);
    }

    public static void moveFile(String fileFrom, String[] files, String newPath) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();
        List<List<String>> fileGroups=partition(files,CORE_COUNT);

        for (List<String> fileGroup:fileGroups) {
            Runnable task = () -> {
                for(String file:fileGroup) {
                    String fileName = fileFrom + "/" + file;
                    String destinationName = newPath + "/" + file;

                    try {
                        Files.copy(Path.of(fileName), Path.of(destinationName));
                        System.out.println("Файл " + fileName + " успешно скопирован.");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            };

            Thread thread=new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for(Thread thread:threads){
            thread.join();
        }
    }

    public static void deleteFile(String[] files, String newPath) throws InterruptedException {

        List<Thread> threads=new ArrayList<>();

        for(String file:files){
            Runnable task=()->{
                String destinationName=newPath+"/"+file;

                try{
                    Files.delete(Path.of(destinationName));
                    System.out.println("Файл "+destinationName+" успешно удален.");
                } catch (IOException e){
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
    }

    public static <T> List<List<T>> partition(T[] list, int partitionSize){

        List<List<T>> partitions=new ArrayList<>();
        for(int i=0;i< list.length;i+=partitionSize){

            int end=Math.min(list.length, i+partitionSize);
            partitions.add(new ArrayList<>(List.of(list).subList(i, end)));
        }
        return partitions;
    }
}