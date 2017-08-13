package com.github.jannled.soundboard;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.github.jannled.lib.Print;
import com.github.jannled.lib.archive.Zipfile;

public class Soundarchive
{
	File zipFile = null;
	Zipfile archive = null;
	
	public Soundarchive(File f)
	{
		openArchive(f);
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
	
	public void addSound(File f)
	{
		archive.addFile(f.toPath(), Paths.get("/" + f.getName()), 0, true);
	}
	
	public InputStream getSound(String name)
	{
		return archive.getFile(name);
	}
	
	public String[] getAllSounds()
	{
		ArrayList<String> names = new ArrayList<String>();
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(archive.getFilesystem().getPath("/")))
		{
			for(Path p : directoryStream)
			{
				names.add(p.getFileName().toString());
			}
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] s = new String[names.size()];
		return names.toArray(s);
	}

	public File getZipFile()
	{
		return zipFile;
	}
}
