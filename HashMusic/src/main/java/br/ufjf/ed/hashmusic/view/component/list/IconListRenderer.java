package br.ufjf.ed.hashmusic.view.component.list;

import java.awt.Component;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Luis Augusto
 */
public class IconListRenderer extends DefaultListCellRenderer
{
	private Map<Object, Icon> icons = null;
	
	public IconListRenderer(Map<Object, Icon> icons) 
        {
		this.icons = icons;
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
        {
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Icon icon = icons.get(value);
		label.setIcon(icon);
		return label;
	}
}

