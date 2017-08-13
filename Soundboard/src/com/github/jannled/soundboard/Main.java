package com.github.jannled.soundboard;

import java.io.File;

import com.github.jannled.lib.Print;
import com.github.jannled.soundboard.sound.Audioplayer;
import com.github.jannled.soundboard.window.Frame;

public class Main
{
	public static Soundarchive soundarchive;
	public static Audioplayer audioplayer;
	public static Frame window;

	public static void main(String[] args)
	{
		Print.setOutputLevel(Print.ALL);
		soundarchive = new Soundarchive(new File("soundboard.sba"));
		window = new Frame();
		audioplayer = new Audioplayer();
	}
}
