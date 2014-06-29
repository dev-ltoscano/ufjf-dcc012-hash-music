package br.ufjf.ed.hashmusic.viewmodel.component.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 
 * @author Luis Augusto
 */
public class TableColumnClickListener implements MouseListener
{
    private ICallBack callBack;

    public TableColumnClickListener(ICallBack callBack)
    {
        this.callBack = callBack;
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        this.callBack.call(e);
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        
    }
}