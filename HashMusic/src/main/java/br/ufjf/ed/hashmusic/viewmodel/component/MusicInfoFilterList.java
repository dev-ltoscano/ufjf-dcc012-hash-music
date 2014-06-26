package br.ufjf.ed.hashmusic.viewmodel.component;

import br.ufjf.ed.hashmusic.model.MusicInfo;
import java.util.ArrayList;

/**
 *
 * @author Luis Augusto
 */
public class MusicInfoFilterList extends ArrayList<MusicInfo>
{
    public int firstIndex(Object obj)
    {
        if(obj != null)
        {
            for(int i = 0; i < this.size(); i++)
            {
                if(this.get(i).similar(obj))
                    return i;
            }
        }
        
        return -1;
    }
    
    public int lastIndex(int firstIndex, Object obj)
    {
        int endIndex = firstIndex;
        
        if(obj != null)
        {
            for(int i = firstIndex; (i != -1) && (i < this.size()) && this.get(i).similar(obj); i++)
            {
                endIndex = i;
            }
        }
        
        return endIndex;
    }
}
