/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.ed.hashmusic.hash.name;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author -Igor
 */
public class HashHalfSquareTest {
    /**
     * Test of getHash method, of class HashHalfSquare.
     */
    @Test
    public void testGetHash() {
        System.out.println("getHash");
        String key = "afvyagfoajhsdgiuovdsdvdsvagasdvasdvgasdvyuasfvhioasfviofasdfuaiog";
        HashHalfSquare instance = new HashHalfSquare();
        String result = instance.getHash(key);
        System.out.println(result);
    }
    
}
