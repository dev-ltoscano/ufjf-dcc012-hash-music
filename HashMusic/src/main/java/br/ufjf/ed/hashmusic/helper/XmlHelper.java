package br.ufjf.ed.hashmusic.helper;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Luis Augusto
 */
public class XmlHelper 
{
    public static void readXml(String xmlPath)
    {
        
    }
    
    public static boolean saveXml(String xmlPath, Object xmlObj) throws FileNotFoundException, IOException
    {
        FileOutputStream outputFile = new FileOutputStream(new File(xmlPath));
        
        parseToXml(xmlObj, outputFile);
        
        outputFile.flush();
        outputFile.close();
        
        return true;
    }
    
    private static void parseToXml(Object xmlObj, OutputStream outStream)
    {
        XStream xStream = new XStream();
        xStream.toXML(xmlObj, outStream);
    }
}
