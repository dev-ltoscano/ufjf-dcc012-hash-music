package br.ufjf.ed.hashmusic.hash;

/** 
 *  Interface que define constantes usadas nas funções de Hash
 * @author Luis Augusto
 */
public interface IHash 
{
    // Tamanho da tabela de Hash
    public int TSIZE = 100; 
    
    // Número primo para dispersão das chaves na tabela
    public int PRIME_NUMBER = 3571; // Fonte: http://en.wikipedia.org/wiki/List_of_prime_numbers
}