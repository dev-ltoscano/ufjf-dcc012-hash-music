package br.ufjf.ed.hashmusic.hash.directory;

import br.ufjf.ed.hashmusic.hash.IHash;
import br.ufjf.ed.hashmusic.hash.IHashDirectory;

/**
 *
 * @author Luis Augusto
 */
public class HashExtraction implements IHashDirectory
{
    @Override
    public String getHash(String key) throws IllegalArgumentException 
    {
        if((key == null) || key.isEmpty())
            throw new IllegalArgumentException();
        
        String firstPart = key.substring(0, (key.length() / 2) - 1);
        String secondPart = key.substring((key.length() / 2), key.length());
        String newKey = secondPart.concat(firstPart);
        
        int sum = 0;
        char charList[] = newKey.toCharArray();

        for (char c : charList)
        {
            sum += c;
        }
        
        return String.valueOf(sum % IHash.TSIZE);
    }
}
