package br.ufjf.ed.hashmusic.helper;

import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Luis Augusto
 */
public class FileHelper 
{
    public static boolean createDirectory(String dirPath)
    {
        return (new File(dirPath).mkdirs());
    }
    
    public static boolean checkPathExists(String path)
    {
        return (new File(path).exists());
    }
    
    public static ArrayList<File> getAllFilesList(File dir, String fileFilter)
    {
        ArrayList<File> allFileList = new ArrayList<>();
        
        File[] files = dir.listFiles();
        
        for (File file : files) 
        {
            if (file.isDirectory())
                allFileList.addAll(getAllFilesList(file, fileFilter));
            else
            {
                if(FilenameUtils.getExtension(file.getName()).equals(fileFilter.toLowerCase()))
                    allFileList.add(file);
            }
        }
        
        return allFileList;
    }
    
    public static String formatSubDirectory(String rootPath, String... subPathList)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(rootPath);
        
        for(String subPath : subPathList)
        {
            builder.append(String.format("%s%s", File.separator, subPath));
        }
         
        return builder.toString();
    }
    
    public static String replaceInvalidCharacters(String fileName)
    {
//        return fileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
        return fileName.replaceAll("[^a-zA-Z0-9]", "_");
    }
}