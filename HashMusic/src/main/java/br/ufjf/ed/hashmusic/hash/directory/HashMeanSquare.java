package br.ufjf.ed.hashmusic.hash.directory;

import br.ufjf.ed.hashmusic.hash.IHash;
import br.ufjf.ed.hashmusic.hash.IHashDirectory;

/**
 *
 * @author Luis Augusto
 */
public class HashMeanSquare implements IHashDirectory
{
    @Override
    public String getHash(String key) throws IllegalArgumentException
    {
        if((key == null) || key.isEmpty())
            throw new IllegalArgumentException();
        
        int sum = 0;
        char charList[] = key.toCharArray();

        for (int i = (charList.length / 2); i < charList.length; i++)
        {
            if((i % 2) == 0)
                sum += Math.pow(charList[i], 2);
            
            sum += charList[i];
        }
        
        return String.valueOf(sum % IHash.TSIZE);
    }
}
