package br.ufjf.ed.hashmusic.viewmodel.component.comparator;

import br.ufjf.ed.hashmusic.model.MusicInfo;
import java.util.Comparator;

/**
 *
 * @author Igor Pires
 */
public class MusicInfoArtistAndAlbumComparator implements Comparator<MusicInfo>, IComparatorCompose<MusicInfo>
{
    @Override
    public int[] compareCompose(MusicInfo info1, MusicInfo info2)
    {
        int compare = info1.getArtist().compareToIgnoreCase(info2.getArtist());
        
        if (compare == 0) 
        {
            int compare2 = info1.getAlbum().compareToIgnoreCase(info2.getAlbum());
            
            return new int[] { 0, compare2 };
        } 
        
        return new int[] { compare, -1 };
    }
    
    @Override
    public int compare(MusicInfo info1, MusicInfo info2) 
    {
        int compareArtist = info1.getArtist().compareToIgnoreCase(info2.getArtist());
        
        if (compareArtist == 0) 
        {
            int compareAlbum = info1.getAlbum().compareToIgnoreCase(info2.getAlbum());
            return compareAlbum;
        }

        return compareArtist;
    }
}