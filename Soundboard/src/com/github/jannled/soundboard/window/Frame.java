package com.github.jannled.soundboard.window;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Frame extends JFrame
{
	private static final long serialVersionUID = 565579054828392509L;
	private JFileChooser fileChooser;
	private JComboBox<String> cbAudioDevices;
	
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
		
		SoundList soundList = new SoundList();
		pnlSounds.add(soundList);
		
		JPanel pnlConfig = new JPanel();
		tabbedPane.addTab(ResourceBundle.getBundle("com.github.jannled.soundboard.window.messages").getString("Tabs.config"), null, pnlConfig, null);
		pnlConfig.setLayout(new BorderLayout(0, 0));
		
		cbAudioDevices = new JComboBox<String>();
		pnlConfig.add(cbAudioDevices, BorderLayout.NORTH);
		
		setVisible(true);
	}
	
	public JComboBox<String> getAudioDevices()
	{
		return cbAudioDevices;
	}
}
