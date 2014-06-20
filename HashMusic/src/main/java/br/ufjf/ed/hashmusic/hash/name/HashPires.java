/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.ed.hashmusic.hash.name;

import br.ufjf.ed.hashmusic.hash.IHash;
import br.ufjf.ed.hashmusic.hash.IHashDirectory;

/**
 *
 * @author -Igor
 */
public class HashPires implements IHash, IHashDirectory
{
    
    private final int TAMANHO = 10;
    
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
        
        String hash = String.valueOf(Integer.toString((int)Math.pow(sum, 2), 20));
        
        if(hash.length() < TAMANHO)
            return hash;
        
        int metade = hash.length()/2;
        
        int inicio = metade - (TAMANHO/2);
        int fim = metade + (TAMANHO/2);
        
        return hash.substring(inicio, fim);
    }
}
