package org.spring;

import javax.naming.spi.DirectoryManager;
import java.awt.desktop.FilesEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        System.out.println("Hello world!");

        String fileFrom1="src/main/resources/p01";
        String fileFrom2="src/main/resources/p02";
        String fileFrom3="src/main/resources/p03";
        String fileFrom4="src/main/resources/p04";

        String fileTo1="src/main/resources/p11";
        String fileTo2="src/main/resources/p12";
        String fileTo3="src/main/resources/p13";
        String fileTo4="src/main/resources/p14";

        File f1=new File(fileFrom1);
        File f2=new File(fileFrom2);
        File f3=new File(fileFrom3);
        File f4=new File(fileFrom4);

        String[]files1=f1.list();
        String[]files2=f2.list();
        String[]files3=f3.list();
        String[]files4=f4.list();

        moveFile(fileFrom1,files1,fileTo1);
        moveFile(fileFrom2,files2,fileTo2);
        moveFile(fileFrom3,files3,fileTo3);
        moveFile(fileFrom4,files4,fileTo4);

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