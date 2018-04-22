package com.github.jannled.soundboard.window;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.github.jannled.lib.Print;
import com.github.jannled.soundboard.Main;
import com.github.jannled.window.Textfield;
import com.github.jannled.window.filedrop.FiledropEvent;
import com.github.jannled.window.filedrop.FiledropHandler;
import javax.swing.JScrollPane;

public class Frame extends JFrame implements FiledropEvent
{
	private static final long serialVersionUID = 565579054828392509L;
	private JFileChooser fileChooser;
	private JComboBox<String> cbAudioDevices;
	private FiledropHandler fdh = new FiledropHandler();
	
	/**
	 * Create the frame.
	 */
	public Frame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(ResourceBundle.getBundle("com.github.jannled.soundboard.window.messages").getString("Frame.this.title"));
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu(ResourceBundle.getBundle("com.github.jannled.soundboard.window.messages").getString("Frame.mnFile.text")); //$NON-NLS-1$ //$NON-NLS-2$
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenSoundlibrary = new JMenuItem(ResourceBundle.getBundle("com.github.jannled.soundboard.window.messages").getString("Frame.mntmOpenSoundlibrary.text"));
		mntmOpenSoundlibrary.setIcon(new ImageIcon(new ImageIcon(Frame.class.getResource("/com/github/jannled/window/assets/open.png")).getImage().getScaledInstance(16, 16, Image.SCALE_FAST)));
		mntmOpenSoundlibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
				int exitState = fileChooser.showOpenDialog(null);
				if(exitState == JFileChooser.APPROVE_OPTION)
				{
					Main.soundarchive.openArchive(fileChooser.getSelectedFile());
				}
			}
		});
		
		//The Filedrop Handler
		fdh.registerListener(this);
		
		//The File chooser
		fileChooser = new JFileChooser();
		FileFilter fileFilter = new FileNameExtensionFilter("Soundboard archive Files", "sba", "zip");
		fileChooser.setFileFilter(fileFilter);
		fileChooser.setCurrentDirectory(Main.soundarchive.getZipFile());
		Print.d(fileChooser.getCurrentDirectory().getAbsolutePath());
		
		mnFile.add(mntmOpenSoundlibrary);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pnlSounds = new JPanel();
		tabbedPane.addTab(ResourceBundle.getBundle("com.github.jannled.soundboard.window.messages").getString("Tabs.sounds"), null, pnlSounds, null);
		pnlSounds.setLayout(new BorderLayout(0, 0));
		
		Textfield searchBox = new Textfield(ResourceBundle.getBundle("com.github.jannled.soundboard.window.messages").getString("Frame.searchBox"));
		pnlSounds.add(searchBox, BorderLayout.NORTH);
		searchBox.setColumns(10);
		
		SoundList soundList = new SoundList(Main.soundarchive);
		soundList.setTransferHandler(fdh);
		JScrollPane soundScroll = new JScrollPane(soundList);
		pnlSounds.add(soundScroll);
		
		JPanel pnlConfig = new JPanel();
		tabbedPane.addTab(ResourceBundle.getBundle("com.github.jannled.soundboard.window.messages").getString("Tabs.config"), null, pnlConfig, null);
		pnlConfig.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlAudioSettings = new JPanel();
		pnlConfig.add(pnlAudioSettings, BorderLayout.NORTH);
		pnlAudioSettings.setLayout(new BorderLayout(0, 0));
		
		cbAudioDevices = new JComboBox<String>();
		pnlAudioSettings.add(cbAudioDevices);
		
		JPanel pnlDesign = new JPanel();
		pnlConfig.add(pnlDesign, BorderLayout.CENTER);
		pnlDesign.setLayout(new BorderLayout(0, 0));
		
		//Combo Box designs
		JComboBox<String> cbDesigns = new JComboBox<String>();
		
		cbDesigns.addItem("");
		
		cbDesigns.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				changeDesign(cbDesigns);
			}
		});
		pnlDesign.add(cbDesigns, BorderLayout.NORTH);
		
		setVisible(true);
	}
	
	public JComboBox<String> getAudioDevices()
	{
		return cbAudioDevices;
	}
	
	private void changeDesign(JComboBox<String> designs)
	{
		
	}

	@Override
	public void fileDropped(List<File> files)
	{
		Print.m("Files " + Arrays.toString(files.toArray()) + " has been dropped!");
		for(File f : files)
		{
			Main.soundarchive.addSound(f);
		}
	}
}
