package com.github.jannled.soundboard.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.jannled.lib.Print;
import com.github.jannled.soundboard.Main;
import com.github.jannled.soundboard.sound.Sound;

public class Soundbutton extends JPanel implements MouseListener
{
	private static final long serialVersionUID = -2175629959942208865L;
	
	private Sound sound;
	
	public Soundbutton(Sound sound)
	{
		this.sound = sound;
		JTextField name = new JTextField("" + sound.getName());
		name.setEditable(false);
		
		add(name);
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(sound != null)
		{
			Main.audioplayer.play(sound);
		}
		else
		{
			Print.e("The InputStream for sound \"" + sound.toString() + "\" is null!");
		}
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
