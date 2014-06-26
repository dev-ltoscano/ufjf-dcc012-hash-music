package br.ufjf.ed.hashmusic.hash.directory;

import br.ufjf.ed.hashmusic.hash.IHash;
import br.ufjf.ed.hashmusic.hash.IHashDirectory;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Luis Augusto
 */
public class HashMd5 implements IHashDirectory
{
    @Override
    public String getHash(String key) throws IllegalArgumentException 
    {
        if((key == null) || key.isEmpty())
                throw new IllegalArgumentException();
        
        try 
        {
            MessageDigest msgDigest = MessageDigest.getInstance("MD5");  
            BigInteger bigIntDigest = new BigInteger(1, msgDigest.digest(key.getBytes()));
            
            int sum = 0;
            char charList[] = bigIntDigest.toString().toCharArray();

            for (char c : charList) 
            {
                sum += c;
            }
            
            return String.valueOf(sum % IHash.TSIZE);
        } 
        catch (NoSuchAlgorithmException ex) 
        {
            return new HashMeanSquare().getHash(key);
        }
    }
}
