package com.github.jannled.soundboard.window;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JScrollPane;

import com.github.jannled.soundboard.Main;

public class SoundList extends JScrollPane
{
	private static final long serialVersionUID = -7284446314243551075L;
	
	ArrayList<Soundbutton> soundbuttons = new ArrayList<Soundbutton>();
	
	public SoundList()
	{
		System.out.println(Arrays.toString(Main.soundarchive.getAllSounds()));
	}
	
	
}
