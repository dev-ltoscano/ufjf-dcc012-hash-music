package br.ufjf.ed.hashmusic.helper;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  Implementação da classe que utiliza a biblioteca XStream para gerar arquivos XML
 * @author Luis Augusto
 */
public class XmlHelper 
{
    /**
     * Função que lê um arquivo XML
     * @param xmlPath Caminho do arquivo XML
     * @param xmlObj Objeto do XML
     * @return Objeto do XML lido
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static Object readXml(String xmlPath, Object xmlObj) throws FileNotFoundException, IOException
    {
        Object returnObj;
        
        try (FileInputStream inStream = new FileInputStream(new File(xmlPath))) 
        {
            XStream xStream = new XStream();
            returnObj = xStream.fromXML(inStream, xmlObj);
        }
        
        return returnObj;
    }
    
    /**
     * Função que salva um objeto em um XML
     * @param xmlPath Caminho do arquivo XML
     * @param xmlObj Objeto do XML
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void saveXml(String xmlPath, Object xmlObj) throws FileNotFoundException, IOException
    {
        try (FileOutputStream outStream = new FileOutputStream(new File(xmlPath))) 
        {
            XStream xStream = new XStream();
            xStream.toXML(xmlObj, outStream);
            outStream.flush();
        }
    }
}
