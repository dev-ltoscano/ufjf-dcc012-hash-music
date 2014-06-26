package br.ufjf.ed.hashmusic.viewmodel.component.comparator;

import br.ufjf.ed.hashmusic.model.MusicInfo;
import java.util.Comparator;

/**
 *
 * @author Igor Pires
 */
public class MusicInfoAlbumComparator implements Comparator<MusicInfo> 
{
    @Override
    public int compare(MusicInfo info1, MusicInfo info2) 
    {
        return info1.getAlbum().compareToIgnoreCase(info2.getAlbum());
    }
}