package com.LockersPvt;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileOperations {
    public static void createMainFolderIfNotPresent(String folderName) {
        File file = new File(folderName);

        // If file doesn't exist, create the main folder
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void displayAllFiles(String path) {
        FileOperations.createMainFolderIfNotPresent("main");
        // All required files and folders inside "main" folder relative to current
        // folder
        System.out.println("Displaying all files with directory structure in ascending order\n");

        // listFilesInDirectory displays files along with folder structure
        List<String> filesListNames = FileOperations.listFilesInDirectory(path, 0, new ArrayList<String>());

        System.out.println("Displaying all files in ascending order\n");
        Collections.sort(filesListNames);

        // a single thread to process the file names
        filesListNames.stream().forEach(System.out::println);
    }

    public static List<String> listFilesInDirectory(String path, int indentationCount, List<String> fileListNames) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        List<File> filesList = Arrays.asList(files); // returns this list after its sorted in ascending order

        // Sorts the specified list into ascending order by default
        Collections.sort(filesList);

        if (files != null && files.length > 0) {
            // look deeper in to the folder stucture if there is one
            for (File file : filesList) {
                System.out.print(" ".repeat(indentationCount * 2));

                if (file.isDirectory()) {
                    System.out.println("`-- " + file.getName());

                    // Recursively indent and display the files
                    fileListNames.add(file.getName());
                    listFilesInDirectory(file.getAbsolutePath(), indentationCount + 1, fileListNames);
                } else {
                    System.out.println("|-- " + file.getName());
                    fileListNames.add(file.getName());
                }
            }
        } else {
            System.out.print(" ".repeat(indentationCount * 2));
            System.out.println("|-- Empty Directory");
        }
        System.out.println();
        return fileListNames;
    }

}
