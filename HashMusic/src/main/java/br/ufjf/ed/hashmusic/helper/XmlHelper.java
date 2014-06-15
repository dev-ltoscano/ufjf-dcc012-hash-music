package br.ufjf.ed.hashmusic.helper;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Luis Augusto
 */
public class XmlHelper 
{
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
    
    public static boolean saveXml(String xmlPath, Object xmlObj) throws FileNotFoundException, IOException
    {
        try (FileOutputStream outStream = new FileOutputStream(new File(xmlPath))) 
        {
            XStream xStream = new XStream();
            xStream.toXML(xmlObj, outStream);
            outStream.flush();
        }
        
        return true;
    }
}
