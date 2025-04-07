package org.spring;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

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

    public static void moveFile(String fileFrom, String[] files, String newPath) {

        for (int i = 0; i < files.length - 4; i += Math.min(4, files.length)) {
            List<String> part = new ArrayList<>();
            part.add(files[i]);
            part.add(files[i + 1]);
            part.add(files[i + 2]);
            part.add(files[i + 3]);

            for (int j = 0; j < part.size(); j++) {

                int finalJ = j;
                Thread t = new Thread(() -> {
                    String fileName = part.get(finalJ);
                    String destinationName = newPath + "/" + fileName;

                    try {
                        Files.copy(Path.of(fileFrom + "/" + fileName), Path.of(destinationName));
                        System.out.println("Файл " + fileName + " успешно скопирован.");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                t.run();
            }
        }
//        for(String file:files){
//
//            String fileName=new File(file).getName();
//            String destinationName=newPath+"/"+fileName;
//
//            try{
//                Files.copy(Path.of(fileFrom+"/"+file), Path.of(destinationName));
//                System.out.println("Файл "+fileName+" успешно скопирован.");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    public static void deleteFile(String[] files, String newPath) {

        for (int i = 0; i < files.length - 4; i += Math.min(4, files.length)) {
            List<String> part = new ArrayList<>();
            part.add(files[i]);
            part.add(files[i + 1]);
            part.add(files[i + 2]);
            part.add(files[i + 3]);

            for (int j = 0; j < part.size(); j++) {

                int finalJ = j;
                Thread t = new Thread(() -> {
                    String fileName = part.get(finalJ);
                    String destinationName = newPath + "/" + fileName;

                    try {
                        Files.delete(Path.of(destinationName));
                        System.out.println("Файл " + fileName + " успешно удален.");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                });
                t.run();
            }
//        for(String file:files){
//
//            String fileName=new File(file).getName();
//            String destinationPath=newPath+"/"+fileName;
//
//            try{
//                Files.delete(Path.of(destinationPath));
//                System.out.println("Файл "+fileName+" успешно удален.");
//            } catch (IOException e){
//                System.out.println(e.getMessage());
//            }
//        }
        }
    }
}