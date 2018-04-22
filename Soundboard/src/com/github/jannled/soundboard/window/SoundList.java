package com.github.jannled.soundboard.window;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import com.github.jannled.soundboard.Main;
import com.github.jannled.soundboard.Soundarchive;

public class SoundList extends JPanel
{
	private static final long serialVersionUID = -7284446314243551075L;
	
	ArrayList<Soundbutton> soundbuttons = new ArrayList<Soundbutton>();
	Soundarchive sounds;
	
	public SoundList(Soundarchive sounds)
	{
		setLayout(new GridLayout(0, 2));
		this.sounds = sounds;
		System.out.println(Arrays.toString(sounds.getAllSounds()));
		refresh();
	}
	
	public void refresh()
	{
		soundbuttons.clear();
		String[] sounds = Main.soundarchive.getAllSounds();
		
		for(String s : sounds)
		{
			Soundbutton sb = new Soundbutton(Main.soundarchive.getSound(s));
			soundbuttons.add(sb);
			add(sb);
		}
	}
	
	public void updateList(String searchFilter)
	{
		removeAll();
		for(Soundbutton s : soundbuttons)
		{
			add(s);
		}
	}
}
