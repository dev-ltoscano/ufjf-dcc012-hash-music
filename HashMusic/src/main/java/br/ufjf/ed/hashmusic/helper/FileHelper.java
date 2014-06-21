package br.ufjf.ed.hashmusic.helper;

import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;

/**
 *  Implementação da classe que auxilia em operações com arquivos
 * @author Luis Augusto
 */
public class FileHelper 
{
    /**
     * Função que cria um diretório
     * @param dirPath Caminho do diretório a ser criado
     * @return true Se o diretório foi criado; false caso contrário
     */
    public static boolean createDirectory(String dirPath)
    {
        return (new File(dirPath).mkdirs());
    }
    
    /**
     * Função que verifica se um caminho existe
     * @param path Caminho
     * @return (true) Se o caminho existe; (false) caso contrário
     */
    public static boolean checkPathExists(String path)
    {
        return (new File(path).exists());
    }
    
    /**
     * Função que pega todos os arquivos de um caminho de acordo com a extensão do filtro
     * @param dirPath Caminho do diretório
     * @param fileFilter Filtro da extensão
     * @return Lista com todos os arquivos filtrados pela extensão do diretório e subdiretórios
     */
    public static ArrayList<File> getAllFilesList(File dirPath, String fileFilter)
    {
        ArrayList<File> allFileList = new ArrayList<>();
        
        File[] files = dirPath.listFiles();
        
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
    
    /**
     * Função que formata um caminho concatenando o diretório raiz com subdiretórios
     * @param rootPath Diretório raíz
     * @param subPathList Lista de subdiretórios
     * @return O caminho completo
     */
    public static String formatSubDirectory(String rootPath, String... subPathList)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(rootPath);
        
        for(String subPath : subPathList)
        {
            // File.separator leva em consideração a diferença entre caminhos entre os sistemas Windows/Linux
            builder.append(String.format("%s%s", File.separator, subPath));
        }
         
        return builder.toString();
    }
    
    /**
     * Função que remove caracteres inválidos em nomes de arquivos
     * @param fileName Nome do arquivo
     * @return Nome do arquivo válido
     */
    public static String replaceInvalidCharacters(String fileName)
    {
        // Regex que retira caracteres inválidos no nome do arquivo, mantendo apenas números e letras
        return fileName.replaceAll("[^a-zA-Z0-9]", "_");
    }
}