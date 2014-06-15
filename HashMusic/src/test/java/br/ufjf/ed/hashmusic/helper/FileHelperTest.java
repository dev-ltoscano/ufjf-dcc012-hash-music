/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.ed.hashmusic.helper;

import java.io.File;
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
public class FileHelperTest {
    
    public FileHelperTest() {
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
     * Test of createDirectory method, of class FileHelper.
     */
    @Test
    @Ignore
    public void testCreateDirectory() {
        System.out.println("createDirectory");
        String dirPath = "";
        boolean expResult = false;
        boolean result = FileHelper.createDirectory(dirPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPathExists method, of class FileHelper.
     */
    @Test
    @Ignore
    public void testCheckPathExists() {
        System.out.println("checkPathExists");
        String path = "";
        boolean expResult = false;
        boolean result = FileHelper.checkPathExists(path);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllFilesList method, of class FileHelper.
     */
    @Test
    @Ignore
    public void testGetAllFilesList() {
        System.out.println("getAllFilesList");
        File dir = null;
        String fileFilter = "";
        ArrayList<File> expResult = null;
        ArrayList<File> result = FileHelper.getAllFilesList(dir, fileFilter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatSubDirectory method, of class FileHelper.
     */
    @Test
    @Ignore
    public void testFormatSubDirectory() 
    {
        System.out.println("========== formatSubDirectory ==========");
        String rootPath = "C:\\Users\\Luis Augusto\\Desktop";
        String[] subPathList = new String[] { "HashMusic", "Music.xml" };
        String result = FileHelper.formatSubDirectory(rootPath, subPathList);
        System.out.println(result);
    }
    
}
