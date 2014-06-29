package br.ufjf.ed.hashmusic.viewmodel.component;

import br.ufjf.ed.hashmusic.viewmodel.component.event.ICallBack;

/**
 *
 * @author Luis Augusto
 */
public class TableMusicInfoComparatorChange implements ICallBack
{   
    @Override
    public void call(Object... params) 
    {
        System.out.println("Called!");
    }
}
