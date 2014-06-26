package br.ufjf.ed.hashmusic.model;

import java.util.Objects;

/**
 * Classe de objetos que guardam as informações de uma música
 * @author Luis Augusto
 */
public class MusicInfo
{
    private String artist;
    private String album;
    private String title;
    
    public MusicInfo()
    {
        this.artist = "";
        this.album = "";
        this.title = "";
    }
    
    public MusicInfo(String artist, String album, String title)
    {
        this.artist = artist;
        this.album = album;
        this.title = title;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(String album) {
        this.album = album;
    }
    
    public boolean similar(Object obj)
    {
        if(obj instanceof MusicInfo)
        {
            MusicInfo info = (MusicInfo)obj;
            
            if (info.getArtist().equalsIgnoreCase(this.artist) || info.getAlbum().equalsIgnoreCase(this.album) || info.getTitle().equalsIgnoreCase(this.title)) 
            {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof MusicInfo)
        {
            MusicInfo info = (MusicInfo)obj;
            
            if (info.getArtist().equalsIgnoreCase(this.artist) && info.getAlbum().equalsIgnoreCase(this.album) && info.getTitle().equalsIgnoreCase(this.title)) 
            {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        
        hash = 53 * hash + Objects.hashCode(this.artist);
        hash = 53 * hash + Objects.hashCode(this.album);
        hash = 53 * hash + Objects.hashCode(this.title);
        
        return hash;
    }
}
