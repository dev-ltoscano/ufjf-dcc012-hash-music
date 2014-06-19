package br.ufjf.ed.hashmusic.hash.directory;

import br.ufjf.ed.hashmusic.hash.IHash;
import br.ufjf.ed.hashmusic.hash.IHashDirectory;

/**
 *
 * @author Luis Augusto
 */
public class HashDivision implements IHash, IHashDirectory
{
    @Override
    public String getHash(String key) 
    {
        if((key == null) || key.isEmpty())
            throw new IllegalArgumentException();
        
        int sum = 0;
        char charList[] = key.toCharArray();

        for (char c : charList) 
        {
            sum += c;
        }

        return String.valueOf((sum % IHash.PRIME_NUMBER) % IHash.TSIZE);
    }
}
