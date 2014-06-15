package br.ufjf.ed.hashmusic.hash;

/**
 *
 * @author Luis Augusto
 */
public interface IHash 
{
    String hashDirectory(String key);
    String hashFile(String key);
}