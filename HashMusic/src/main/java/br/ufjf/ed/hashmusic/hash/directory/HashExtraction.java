package br.ufjf.ed.hashmusic.hash.directory;

import br.ufjf.ed.hashmusic.hash.IHash;
import br.ufjf.ed.hashmusic.hash.IHashDirectory;

/**
 * Implementação do Hash da Extração
 * @author Luis Augusto
 */
public class HashExtraction implements IHash, IHashDirectory
{
    @Override
    public String getHash(String key) throws IllegalArgumentException 
    {
        // Verifica se a chave é inválida, se for, lança exceção
        if((key == null) || key.isEmpty())
            throw new IllegalArgumentException();
        
        // Pega a primeira metade da chave
        String firstPart = key.substring(0, (key.length() / 2) - 1);
        
        // Pega a segunda metade da chave
        String secondPart = key.substring((key.length() / 2), key.length());
        
        // Concatena invertido
        String newKey = secondPart.concat(firstPart);
        
        // Extrai os 5 caracteres do meio se a chave for maior que 5 caracteres, caso contrário, usa a chave completa
        String partKey = (newKey.length() < 5) ? newKey : newKey.substring((newKey.length() / 2) - 2, (newKey.length() / 2) + 3);
        
        int sum = 0;
        char charList[] = partKey.toCharArray();

        //Faz a soma de cada caracter de parte da chave
        for (char c : charList)
        {
            sum += c;
        }
        
        // (sum % IHash.TSIZE) encaixa o valor na tabela
        return String.valueOf(sum % IHash.TSIZE);
    }
}
