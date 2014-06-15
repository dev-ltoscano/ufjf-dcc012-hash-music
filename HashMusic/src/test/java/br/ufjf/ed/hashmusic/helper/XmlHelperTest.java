/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.ed.hashmusic.helper;

import br.ufjf.ed.hashmusic.model.MusicInfo;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Luis Augusto
 */
public class XmlHelperTest {
    
    public XmlHelperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readXml method, of class XmlHelper.
     */
    @Test
    @Ignore
    public void testReadXml() throws Exception 
    {
        System.out.println("========== Read Xml ==========");
        String xmlPath = "";
        
        ArrayList<MusicInfo> result = new ArrayList<>();
        result = (ArrayList<MusicInfo>)XmlHelper.readXml(xmlPath, result);
        
        for(MusicInfo info : result)
        {
            System.out.println("Música: " + info.getTitle());
        }
    }

    /**
     * Test of saveXml method, of class XmlHelper.
     */
    @Test
    @Ignore
    public void testSaveXml() throws Exception {
        System.out.println("saveXml");
        String xmlPath = "";
        Object xmlObj = null;
        boolean expResult = false;
        boolean result = XmlHelper.saveXml(xmlPath, xmlObj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
