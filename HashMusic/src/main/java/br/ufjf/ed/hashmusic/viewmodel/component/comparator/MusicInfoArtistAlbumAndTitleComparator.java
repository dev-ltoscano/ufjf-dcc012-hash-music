package br.ufjf.ed.hashmusic.viewmodel.component.comparator;

import br.ufjf.ed.hashmusic.model.MusicInfo;
import java.util.Comparator;

/**
 *
 * @author Luis Augusto
 */
public class MusicInfoArtistAlbumAndTitleComparator implements Comparator<MusicInfo>
{
    @Override
    public int compare(MusicInfo info1, MusicInfo info2) 
    {
        int compareArtist = info1.getArtist().compareToIgnoreCase(info2.getArtist());
        
        if (compareArtist == 0) 
        {
            int compareAlbum = info1.getAlbum().compareToIgnoreCase(info2.getAlbum());
            
            if(compareAlbum == 0)
            {
                return info1.getTitle().compareToIgnoreCase(info2.getTitle());
            }
            
            return compareAlbum;
        }

        return compareArtist;
    }
}