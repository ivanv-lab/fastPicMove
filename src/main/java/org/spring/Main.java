package org.spring;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class Main {

    public static void main(String[] args) {

        long startTime=System.currentTimeMillis();
        System.out.println("Hello world!");

        String from="p1";
        String to="p0";

        List<String> filesFrom=new ArrayList<>();
        List<String> filesTo=new ArrayList<>();
        List<File> files=new ArrayList<>();
        List<String[]> strFiles=new ArrayList<>();



        filesFrom.add(from);
        filesTo.add(to);
        filesFrom.forEach(f->files.add(new File(f)));
        files.forEach(f->strFiles.add(f.list()));

        moveFile(filesFrom.get(0),strFiles.get(0),filesTo.get(0));

        deleteFile(strFiles.get(0),filesTo.get(0));

        long endTime=System.currentTimeMillis();
        long timeElapsed=endTime-startTime;
        System.out.println("Затраченное время = "+timeElapsed);
    }

    public static void moveFile(String fileFrom,String [] files,String newPath){

        for(String file:files){
            new Thread(moving(fileFrom,file,newPath)).run();
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

    static void moving(String fileFrom,String file,String newPath){

        String fileName=new File(file).getName();
        String destinationName=newPath+"/"+fileName;

        try{
                Files.copy(Path.of(fileFrom+"/"+file), Path.of(destinationName));
                System.out.println("Файл "+fileName+" успешно скопирован.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public static void deleteFile(String []files,String newPath){
        for(String file:files){

            String fileName=new File(file).getName();
            String destinationPath=newPath+"/"+fileName;

            try{
                Files.delete(Path.of(destinationPath));
                System.out.println("Файл "+fileName+" успешно удален.");
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}