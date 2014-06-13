package br.ufjf.ed.hashmusic.model;

/**
 *
 * @author Luis Augusto
 */
public class MusicInfo 
{
    private String artist;
    private String album;
    private String title;
    
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
}
