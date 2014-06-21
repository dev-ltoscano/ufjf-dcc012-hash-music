package br.ufjf.ed.hashmusic.hash.directory;

import br.ufjf.ed.hashmusic.hash.IHash;
import br.ufjf.ed.hashmusic.hash.IHashDirectory;

/**
 *  Implementação do Hash usado na função hashCode() da linguagem Java
 *  Fonte: http://en.wikipedia.org/wiki/Java_hashCode()
 * @author Luis Augusto
 */
public class HashJava implements IHash, IHashDirectory
{
    @Override
    public String getHash(String key) throws IllegalArgumentException 
    {
        // Verifica se a chave é inválida, se for, lança exceção
        if((key == null) || key.isEmpty())
            throw new IllegalArgumentException();
        
        double sum = 0;
        char[] charList = key.toCharArray();
        
        // Somatório de k[i] * 31 ^ [(key.length() - 1) - i]
        for(int i = 0; i < charList.length; i++)
        {
            sum += charList[i] * Math.pow(31, (key.length() - 1) - i);
        }
        
        // (sum % IHash.TSIZE) encaixa o valor na tabela
        return String.valueOf((int)(sum % IHash.TSIZE));
    }
}
