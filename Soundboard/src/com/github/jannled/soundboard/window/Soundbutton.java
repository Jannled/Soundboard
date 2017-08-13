package com.github.jannled.soundboard.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.github.jannled.soundboard.Main;

public class Soundbutton extends JPanel implements MouseListener
{
	private static final long serialVersionUID = -2175629959942208865L;
	
	private String name;

	public Soundbutton(String name)
	{
		this.name = name;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		Main.audioplayer.play(Main.soundarchive.getSound(name));
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
}
