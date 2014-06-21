package br.ufjf.ed.hashmusic.hash.directory;

import br.ufjf.ed.hashmusic.hash.IHash;
import br.ufjf.ed.hashmusic.hash.IHashDirectory;

/**
 * Implementação do Hash de Divisão
 * @author Luis Augusto
 */
public class HashDivision implements IHash, IHashDirectory
{
    @Override
    public String getHash(String key)
    {
        // Verifica se a chave é inválida, se for, lança exceção
        if((key == null) || key.isEmpty())
            throw new IllegalArgumentException();
        
        int sum = 0;
        char charList[] = key.toCharArray(); 
        
        //Faz a soma de cada caracter da chave
        for (char c : charList) 
        {
            sum += c;
        }
        
        // (sum % IHash.PRIME_NUMBER) dispersa e (sum % IHash.PRIME_NUMBER) % IHash.TSIZE encaixa o valor na tabela
        return String.valueOf((sum % IHash.PRIME_NUMBER) % IHash.TSIZE);
    }
}
