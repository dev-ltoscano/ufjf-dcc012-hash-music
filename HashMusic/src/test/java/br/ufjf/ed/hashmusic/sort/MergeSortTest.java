/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.ed.hashmusic.sort;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author -Igor
 */
public class MergeSortTest {
    
    public MergeSortTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of sort method, of class MergeSort.
     */
    @Test
    public void testSort() {
        System.out.println("sort");
        List<String> strings = new ArrayList<String>();
        strings.add("xurupita");
        strings.add("abacate");
        strings.add("kaukai");
        strings.add("keli");
        strings.add("luis");
        strings.add("igor");
        MergeSort instance = new MergeSort();
        List<String> result = instance.sort(strings);
        
        for(String s : strings){
            System.out.println(s);
        }
    }
    
}
