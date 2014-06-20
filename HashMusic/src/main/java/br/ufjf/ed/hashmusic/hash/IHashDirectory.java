package br.ufjf.ed.hashmusic.hash;

/**
 *
 * @author Luis Augusto
 */
public interface IHashDirectory
{
    String getHash(String key) throws IllegalArgumentException;
}
