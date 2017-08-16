package filemanager;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private String path;
    private List<String> list;
    private File sourceFile;
    private String name;

    public FileManager(String name, String path) {
        this.name = name;
        this.path = path;
        this.sourceFile = setFile(path);
        this.list = makeList();
    }
    
//    
//    File Manager simply opens manages the reading and saving of text files used 
//            to store data within the various classes used in the program. 
//   
            
    public File setFile(String path) {
        this.path = path;
        File file = new File(path);
        try {
            file.createNewFile();
            while (!file.canRead() || !file.canWrite()) {
                if (!file.canRead()) {
                    System.out.println("Unable to read or find file: " + path + "\nPease enter new path.");
                    path = getCommand();
                    System.out.println("Trying new path " + path);
                    file = new File(path);
                } else if (!file.canWrite()) {
                    System.out.println("Unable to write to file: " + path + "\nPease enter new path.");
                    path = getCommand();
                    System.out.println("Trying new path " + path);
                    file = new File(path);
                }
            }
        } catch (Exception e) {
            System.out.println("File does not exist and can not be created at location:");
            System.out.println(path);
        }
        //System.out.println(this.name + " is read/writeable at location:\n" + path);
        return file;
    }

    private List<String> makeList() {
        List freshList = new ArrayList<>();
        try {
            Scanner reader = new Scanner(sourceFile);
            while (reader.hasNext()) {
                String line = reader.nextLine().toLowerCase().trim();
                if (!line.isEmpty()) {
                    freshList.add(line);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("FileManager makeList error: " + e);
            return null;
        }
        return freshList;
    }

    public List<String> getList() {
        return list;
    }

    public String getCommand() {
        Scanner reader = new Scanner(System.in);
        String command = reader.next();
        return command;
    }

    public String getName() {
        return this.name;
    }

    public void printProof() {
        for (String each : list) {
            System.out.println(each);
        }
    }

    public void save(List<String> newList) {
        try {
            //FileWriter writer = new FileWriter(sourceFile);
            FileWriter writer = new FileWriter(path);
            for (String each : newList) {
                writer.write(each + System.getProperty("line.separator"));
            }
            System.out.println("Saved \"" + this.name + "\" to " + this.path);
            writer.close();

        } catch (Exception e) {
            System.out.println("Exception in file writer.");
        }
    }

    public void save(List<String> newList, String newPath) {
        try {
            //FileWriter writer = new FileWriter(sourceFile);
            FileWriter writer = new FileWriter(newPath);
            for (String each : newList) {
                writer.write(each + System.getProperty("line.separator"));
            }
            System.out.println("Saved \"" + this.name + "\" to " + newPath);
            writer.close();

        } catch (Exception e) {
            System.out.println("Exception in file writer.");
        }
    }

}
