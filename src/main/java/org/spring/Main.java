package org.spring;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        System.out.println("Hello world!");

        List<String> filesFrom=new ArrayList<>();
        List<String> filesTo=new ArrayList<>();
        List<File> files=new ArrayList<>();
        List<String[]> strFiles=new ArrayList<>();

        int n=8;
        for(int i=0;i<=n;i++){
            filesFrom.add("p0"+i);
            filesTo.add("p1"+i);
            filesFrom.forEach(f->files.add(new File(f)));
            files.forEach(f->strFiles.add(f.list()));

            moveFile(filesFrom.get(i),strFiles.get(i),filesTo.get(i));
        }

        long endTime=System.currentTimeMillis();
        long timeElapsed=endTime-startTime;
        System.out.println("Затраченное время = "+timeElapsed);
    }

    public static void moveFile(String fileFrom,String [] files,String newPath){
        for(String file:files){

            String fileName=new File(file).getName();
            String destinationName=newPath+"/"+fileName;

            try{
                Files.move(Path.of(fileFrom+"/"+file), Path.of(destinationName));
                System.out.println("Файл "+fileName+" успешно перемещен.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}