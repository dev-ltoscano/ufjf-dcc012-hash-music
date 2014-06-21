/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.ed.hashmusic.hash.name;

import br.ufjf.ed.hashmusic.hash.IHash;
import br.ufjf.ed.hashmusic.hash.IHashName;

/**
 *
 * @author -Igor
 */
public class HashMultiplication implements IHash, IHashName 
{
    @Override
    public String getHash(String key) throws IllegalArgumentException 
    {
        if((key == null) || key.isEmpty())
            throw new IllegalArgumentException();
        
        int sum = 0;
        char charList[] = key.toCharArray();
        
        for (char c : charList) 
        {
            sum += c;
        }
        
        String hash = String.valueOf(sum * ((float)key.length() / IHash.PRIME_NUMBER));
        
        if(hash.length() < IHashName.NAME_LENGTH)
            return hash;
        
        return hash.substring(hash.length() - IHashName.NAME_LENGTH);
    }
}
