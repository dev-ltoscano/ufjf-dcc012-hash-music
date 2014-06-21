package br.ufjf.ed.hashmusic.hash;

/** 
 *  Interface para funções de Hash que renomeiam os arquivos
 * @author Luis Augusto
 */
public interface IHashName
{
    // Comprimento máximo dos nomes dos arquivos
    public int NAME_LENGTH = 10;
    
    /**
     * Função que retorna o hash da chave
     * @param key Chave 
     * @return Hash da chave, que neste caso é o nome do arquivo
     * @throws IllegalArgumentException 
     */
    String getHash(String key) throws IllegalArgumentException;
}
