package br.ufjf.ed.hashmusic.hash;

/**
 *  Interface para funções de Hash que alocam o diretório
 * @author Luis Augusto
 */
public interface IHashDirectory
{
    /**
     * Função que retorna o hash da chave
     * @param key Chave 
     * @return Hash da chave, que neste caso é o diretório que deve ser alocado
     * @throws IllegalArgumentException 
     */
    String getHash(String key) throws IllegalArgumentException;
}
