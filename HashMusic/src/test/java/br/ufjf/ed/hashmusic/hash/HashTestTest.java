/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.ed.hashmusic.hash;

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
public class HashTestTest {
    
    public HashTestTest() {
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
     * Test of hashDirectory method, of class HashTest.
     */
    @Test
    public void testHashDirectory() 
    {
        System.out.println("=========== Hash Directory ===========");
        String key = String.format("%s%s", "RIP By LiMaO", "+ Velozes e + Furiosos");
        
        HashTest instance = new HashTest();
        
        System.out.println("Hash ====> " + instance.hashDirectory(key.trim()));
    }

    /**
     * Test of hashFile method, of class HashTest.
     */
    @Test
    @Ignore
    public void testHashFile() {
        System.out.println("hashFile");
        String key = "";
        HashTest instance = new HashTest();
        String expResult = "";
        String result = instance.hashFile(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
