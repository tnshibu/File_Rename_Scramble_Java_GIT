import java.io.*;
import java.util.*;

public class File_Scramble_Recursive {
    //----------------------------------------------------------------------------------------------------------------
    private static String SOURCE_BASE_FOLDER = "."; //process current directory
    private static List<String> sourceFileList = new ArrayList<String>();
    private static List<String> sourceFolderList = new ArrayList<String>();
    //----------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) throws Exception {
        if(args != null && args.length > 0) {
            SOURCE_BASE_FOLDER = args[0];
        }
        System.out.println("SOURCE_BASE_FOLDER ="+SOURCE_BASE_FOLDER);
        File sourceFolder = new File(SOURCE_BASE_FOLDER);
        if(!sourceFolder.exists()) {
            System.out.println("Folder "+SOURCE_BASE_FOLDER + " does not exist. Exiting");
            System.exit(-1);
        }
        sourceFileList   = FileUtil.getFileListFromFolder(SOURCE_BASE_FOLDER);
		sourceFolderList = FileUtil.getFolderListRecursive(SOURCE_BASE_FOLDER);
		sourceFileList.addAll(sourceFolderList); // Add folder list also, so that folders too get scrambled
        for(int i=0;i<sourceFileList.size();i++) {
            String oldName = sourceFileList.get(i);
            String fileNameAlone = new File(oldName).getName();
            String dirNameAlone  = new File(oldName).getParent();
            String newName = rename(fileNameAlone);
            File f = new File(oldName);
            f.renameTo(new File(dirNameAlone + "/" + newName));
            System.out.println(fileNameAlone+"-->"+newName);
        }
    }
    //----------------------------------------------------------------------------------------------------------------
    public static String rename (String oldName) {
        /*
             From https://introcs.cs.princeton.edu/java/31datatype/Rot13.java.html
        */
        StringBuilder newName = new StringBuilder();
        for (int i = 0; i < oldName.length(); i++) {
            char c = oldName.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            newName.append(c);
        }
        return newName.toString();
    }
    //----------------------------------------------------------------------------------------------------------------
}
