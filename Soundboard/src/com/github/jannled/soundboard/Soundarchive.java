package com.github.jannled.soundboard;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;

import com.github.jannled.lib.Print;
import com.github.jannled.lib.archive.Zipfile;
import com.github.jannled.soundboard.sound.Sound;

public class Soundarchive
{
	File zipFile = null;
	Zipfile archive = null;
	HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	
	public Soundarchive(File f)
	{
		openArchive(f);
		refresh();
	}
	
	/**
	 * Open a sound library
	 * @param f The zip archive containing the sounds
	 * @return If the file could be successfully opened
	 */
	public boolean openArchive(File f)
	{
		Print.m("Opening file " + f.getAbsolutePath());
		zipFile = f;
		archive = new Zipfile(f, true);
		return true;
	}
	
	public void refresh()
	{
		sounds.clear();
		String[] soundNames = archive.getFiles("/");
		
		for(String s : soundNames)
		{
			sounds.put(s, new Sound(s, archive.getFile("/" + s), -1));
		}
	}
	
	public void addSound(File f)
	{
		archive.addFile(f.toPath(), Paths.get("/" + f.getName()), 0, true);
	}
	
	public Sound getSound(String name)
	{
		return sounds.get(name);
	}
	
	public String[] getAllSounds()
	{
		return archive.getFiles("/");
	}

	public File getZipFile()
	{
		return zipFile;
	}
}
