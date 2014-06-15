package br.ufjf.ed.hashmusic.hash;

/**
 *
 * @author Luis Augusto
 */
public class HashTest implements IHash 
{
    private final int TABLE_LENGTH = 99;
    
    @Override
    public String hashDirectory(String key) 
    {
        int sum = 0;
        char ch[] = key.toCharArray();

        for (int i = 0; i < key.length(); i++) 
        {
            sum += ch[i];
        }
        
        return String.valueOf(sum % TABLE_LENGTH);
    }

    @Override
    public String hashFile(String key)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
