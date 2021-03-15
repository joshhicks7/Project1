package project2package;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.awt.image.BufferedImage;
import java.beans.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class FrameContent extends JPanel
{	
	
	protected enum SelectionState{favGenre, favArtist, getName, inApp, settings};
	
	SelectionState myState = SelectionState.favGenre;
	
	JPanel content;
	Library library;
	JFrame parent;
	JTextField searchField;
	JPanel mainPanel = new JPanel();
	JPanel SongPanel;
	Timer timer;
	GetInfo getInfo;
	LibraryPanel libPanel;
	
	FavoritesScreen favorites;
	
	WelcomeScreen welcomeScreen;
	
	private JTextField textField;
	
	public FrameContent(JFrame jf)
	{
		Library b = new Library();
		this.parent = jf;
		library = new Library();		
		library.loadPlaylist();
		setLayout(new BorderLayout());
		
		welcomeScreen = new WelcomeScreen();
		add(welcomeScreen);		
		timer = new Timer(3000,new TitleScreen());		
		timer.start();
		
	}
	
	class TitleScreen implements ActionListener
	{	
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			remove(welcomeScreen);			
			revalidate();
			repaint();
			add(titlePanel(), BorderLayout.PAGE_START);
			parent.revalidate();
			CheckPreferences();
			timer.stop();		
			
		}		
	}
	
	void SetUp()
	{
		
		{
			JPanel panel_1 = new JPanel();
			add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(new GridLayout(0, 1));
			{
				{
					//panel_1.add(SongLabels());
				}
			}
			{
				SongPanel = new JPanel();
				panel_1.add(SongPanel, BorderLayout.CENTER);
				SongPanel.setLayout(new GridLayout(1, 0, 0, 0));
			}
		}
		{
			JPanel panel = new JPanel();
			add(panel, BorderLayout.SOUTH);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] {0};
			gbl_panel.rowHeights = new int[] {10};
			gbl_panel.columnWeights = new double[]{1.0};
			gbl_panel.rowWeights = new double[]{1.0};
			panel.setLayout(gbl_panel);
		}
		
		Library lib = new Library();
		for(String s : FavoriteHolder.favGenres)
		{
			lib.add(library.findSongsByGenre(s));
		}
		
		for(String s : FavoriteHolder.favArtists)
		{
			lib.add(library.findSongsByArtistName(s));
		}
		
		libPanel = new LibraryPanel(lib);		
		add(libPanel);
		revalidate();
	}
	
	void CheckPreferences()
	{
		if(Arrays.asList(getComponents()).contains(favorites))
		{
			remove(favorites);
			revalidate();
			repaint();
		}
		FavoriteHolder.LoadAll();
		
		if(FavoriteHolder.playerName == "")
		{
			myState = SelectionState.getName;
			getInfo = new GetInfo("Please Enter Your Name");
			add(getInfo);
			return;
		}
		
		if(FavoriteHolder.favGenres.size() == 0)
		{
			myState = SelectionState.favGenre;
			AddFavorites("genres", library.getGenres());
			return;
		}
		
		if(FavoriteHolder.favArtists.size() == 0)
		{
			myState = SelectionState.favArtist;
			AddFavorites("artists", library.getArtists());
			return;
		}	

		myState = SelectionState.inApp;
		SetUp();
	}
	
	void wait(int millis)
	{
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class GetInfo extends JPanel
	{
		JTextField field;
		public GetInfo(String data)
		{
			JPanel panel = new JPanel(new GridLayout(0,1));
			panel.setBackground(Color.white);
			JLabel lab = new JLabel(data);
			field = new JTextField();
			JButton b = new JButton("Done");
			b.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					Clicked();
				}
				
			});
			
			panel.add(lab);
			panel.add(field);
			panel.add(b);			
			this.add(panel);
		}	
		
		void Clicked()
		{
			if(field.getText() == "")
			{
				return;
			}
			else
			{
				switch(myState)
				{
					case getName:
						PlayerPrefs.setString(PlayerPrefs.PLAYER_NAME, field.getText());	
						this.setVisible(false);
						remove(getInfo);
						revalidate();
						repaint();
						CheckPreferences();
						break;
				}
			}
		}
		
	}
		
	void AddFavorites(String type, Set<String> faves)
	{
		favorites = new FavoritesScreen(type, faves);
		add(favorites);
		favorites.setVisible(true);
	}
	
	public JPanel getContent()
	{
		return content;
	}
	
	/*
	public class MainContentPanel extends JPanel
	{
		public MainContentPanel()
		{
			JLabel jt = new JLabel("Josh");		
			add(jt);
			setPreferredSize(new Dimension(100,100));
			
			JButton jb = new JButton("Load PlayList");
			add(jb);		
			jb.addActionListener(new loadPlayListAction());
			
			searchField = showTextInputs();
			add(searchField);
		}

	};
	*/
	
	/**
	 * This add the app title as well as welcoming the person back
	 * so yea
	 * @return
	 */
	public JPanel titlePanel()
	{
		JPanel TitleZone = new JPanel();
		TitleZone.setLayout(new GridLayout(0,1));
		JLabel lab = new JLabel("WELCOME BACK " + PlayerPrefs.getString(PlayerPrefs.PLAYER_NAME).toUpperCase());
		
		lab.setHorizontalAlignment(SwingConstants.CENTER);
		lab.setFont(new Font(lab.getFont().getName(), Font.BOLD, 20));
		TitleZone.add(lab);		
		{
			JPanel panel = new JPanel();
			TitleZone.add(panel);
			{
				JLabel lblLetsFindA = new JLabel("LET'S FIND A SONG");
				
				panel.add(lblLetsFindA);
			}
			{
				textField = new JTextField();
				panel.add(textField);
				textField.setColumns(20);
				
				
				
				textField.addKeyListener(new KeyListener()
						{

							@Override
							public void keyTyped(KeyEvent e) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void keyPressed(KeyEvent e) {
								
								
							}

							@Override
							public void keyReleased(KeyEvent e) 
							{
								System.out.print(textField.getText());
								ChangeLibrary(library.getByAll(textField.getText()));
							}
					
						});
			}
			{
				//JButton SearchButton = new JButton("Search");
				//SearchButton.addActionListener(new loadPlayListAction());
				//panel.add(SearchButton);
			}
		}
		{
			JPanel panel_2 = new JPanel();
			TitleZone.add(panel_2);
			panel_2.setLayout(new GridLayout(1, 1, 0, 0));
			{
				JCheckBox chckbxGenrea = new JCheckBox("Genre");
				panel_2.add(chckbxGenrea);
			}
			{
				JCheckBox chckbxSong = new JCheckBox("Song");
				panel_2.add(chckbxSong);
			}
			{
				JCheckBox chckbxArtistName = new JCheckBox("Artist Name");
				panel_2.add(chckbxArtistName);
			}
			{
				JCheckBox chckbxArtistName = new JCheckBox("Artist Name");
				panel_2.add(chckbxArtistName);
			}
		}
		
		return TitleZone;
	}
	
	/**
	 * Sets up the library
	 * @param lib
	 */
	public void ChangeLibrary(Library lib)
	{
		libPanel.SetUp(lib);
		libPanel.repaint();
		libPanel.revalidate();
		revalidate();
	}
	
	public JPanel SongLabels()
	{
		JPanel j = new JPanel();
		j.setLayout(new GridLayout());
		for(String s : Song.SONG_ATTRIBUTES)
		{
			JLabel lab = new JLabel(s);
			lab.setHorizontalAlignment(SwingConstants.CENTER);
			lab.setVisible(true);
			j.add(lab);			
		}
		return j;
	}
	
	public class LibraryPanel extends JScrollPane 
	{
		public LibraryPanel(Library lib)
		{
			SetUp(lib);
		}
		
		void SetUp(Library lib)
		{
			//this.removeAll();
			JPanel j = new JPanel();
			Iterator it = lib.iterator();
			j.setLayout(new GridLayout(0,1));
			int i = 0;
			while( it.hasNext())
			{
				
				Song s = (Song)it.next();				
				JPanel p = new JPanel(new GridLayout(1,0));
				p.setToolTipText(s.toString());
				
				JLabel lab;
				
				lab = new JLabel((++i) + "");
				String st = "";
				p.add(lab);	
				st = s.getArtist_name();				
				lab = new JLabel(st.substring(0, Math.min(20,  st.length())));
				lab.setHorizontalAlignment(SwingConstants.CENTER);
				lab.addMouseListener(listener("artist", st));
				p.add(lab);		
				
				st = s.getTrack_title();
				lab = new JLabel(st.substring(0,Math.min(20, st.length())));
				lab.setHorizontalAlignment(SwingConstants.CENTER);
				p.add(lab);
				
				st = s.getAlbum_title();
				lab = new JLabel(st.substring(0,Math.min(20, st.length())));
				lab.setHorizontalAlignment(SwingConstants.CENTER);
				lab.addMouseListener(listener("album", st));
				p.add(lab);			

				st = s.getGenre();
				lab = new JLabel(st.substring(0,Math.min(20, st.length())));
				lab.setHorizontalAlignment(SwingConstants.CENTER);
				lab.addMouseListener(listener("genre", st));
				p.add(lab);
				
				st = s.getYear_created() + "";
				lab = new JLabel(st.substring(0,Math.min(20, st.length())));
				lab.setHorizontalAlignment(SwingConstants.CENTER);
				lab.addMouseListener(listener("year", st));
				p.add(lab);				
				j.add(p);
			}
			
			
			((GridLayout)j.getLayout()).setVgap(20);
			j.setVisible(true);
			setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.setViewportView(j);
			this.revalidate();
			parent.revalidate();
		}
		
		class Menu extends JPopupMenu
		{
			public Menu()
			{
				JMenuItem item;
				item = new JMenuItem("Click Me");
				this.add(item);
				this.pack();
			}
		}
		
		MouseAdapter listener(String type, String text)
		{	
			return new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					if(e.getButton() == MouseEvent.BUTTON1)
					{
						switch(type)
						{
							case "artist":
								SetUp(library.findSongsByArtistName(text));
								break;
							case "genre":
								SetUp(library.findSongsByGenre(text));
								break;
							case "album":
								SetUp(library.findSongsByAlbum(text));
								break;
							case "track":
								SetUp(library.findSongsByArtistName(text));
								break;
							case "year":
								SetUp(library.findSongsByYear(text));
								break;
						}
					}
					else if(e.getButton() == MouseEvent.BUTTON3)
					{
						 new Menu().show(e.getComponent(),
			                       e.getX(), e.getY());
					}
				}
			};
		}

	}

	public class Settings extends JPanel
	{
		public Settings()
		{
			
		}
	}
	
	public JTextField showTextInputs()
	{	
		JTextField f = new JTextField("New ");
		f.setVisible(true);				
		return f;
	}
		
	public class loadPlayListAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{	
			
			 //remove(mainPanel);
			 SongPanel.removeAll();
			 SongPanel.add(new LibraryPanel(library));
	         parent.revalidate();
			
		}
				
		
	}
	
	public int matchFontSize(Dimension d)
	{
		int size = 0;
		
		
		
		return size;
	}
	
	public class FavoritesScreen extends JPanel
	{
		JPanel selectionPanel;
		JPanel t = new JPanel(new BorderLayout()); 
		JLabel lblPleaseSelectYou = new JLabel("PLEASE SELECT YOU FAVORITE ");
		JTextField field = new JTextField();
		
		
		public FavoritesScreen(String type, Set<String> lib)
		{
			t.add(field, BorderLayout.PAGE_END);
			setNew(type, lib);
			
			field.addKeyListener(new KeyListener() 
			{

				@Override
				public void keyTyped(KeyEvent e) {
					setNew(type, lib);
					
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
			
		}
		
		public void setNew(String type, Set<String> lib)
		{
			this.removeAll();
			setLayout(new BorderLayout(0, 0));
			lblPleaseSelectYou.setText("Please Select Your Favorite ".toUpperCase() + type.toUpperCase());
			

			lblPleaseSelectYou.setHorizontalAlignment(SwingConstants.CENTER);
			t.add(lblPleaseSelectYou, BorderLayout.NORTH);
			add(t, BorderLayout.NORTH);
			
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			add(scrollPane, BorderLayout.CENTER);
			
			selectionPanel = new JPanel();
			selectionPanel.setLayout(new GridLayout(0, 10, 0, 0));
			
			JPanel panel = new JPanel();
			add(panel, BorderLayout.SOUTH);
			panel.setLayout(new BorderLayout(0, 0));
			
			
			for(String val : lib)
			{
				JCheckBox check = new JCheckBox(val.substring(0,Math.min(10, val.length())));
				check.setBounds(0,0, 75, 50);
				check.setVisible(true);
				selectionPanel.add(check);
				check.setToolTipText(val);
			}
			
			scrollPane.setViewportView(selectionPanel);
			scrollPane.repaint();
			JButton Skip_Button = new JButton("SKIP");
			panel.add(Skip_Button, BorderLayout.WEST);
			
			JButton doneButton = new JButton("next");
			doneButton.addActionListener(new nextButtonListener());
			panel.add(doneButton, BorderLayout.EAST);					
			revalidate();
			this.repaint();
		}
		
		void SaveFavorites()
		{
		   Component[] comps = selectionPanel.getComponents();
		   System.out.print(comps.length);
		   String st = "";
		   for(Component c : comps)
		   {			   
			   JCheckBox jc = (JCheckBox)c;
			   
			   if(jc.isSelected())
			   {
				   st += jc.getToolTipText() + "\n";
				   
			   }
			  
		   }
		   
		   if(st == "")
		   {
			   return;
		   }
		   else
		   {
			   st = st.substring(0,st.length() - 1);
		   }
		   
		   switch(myState)
		   {
			   case favGenre:
				   PlayerPrefs.setString(PlayerPrefs.FAVORITE_GENRES, st);
				   break;
			   case favArtist:
				   PlayerPrefs.setString(PlayerPrefs.FAVORITE_ARTISTS, st);
				   break;
		   }
		  
		}
		
		class nextButtonListener implements ActionListener
		{			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				switch(myState)
				{
					case favGenre:								
						SaveFavorites();						
						CheckPreferences();
						break;
					case favArtist:
						SaveFavorites();
						CheckPreferences();
						break;
				}
			}
			
		}
	}
		
	public class WelcomeScreen extends JPanel
	{
		public WelcomeScreen()
		{
			
			this.setLayout(new BorderLayout());
			this.setBackground(Color.black);
			JLabel label = new JLabel("THE SPOT");
			label.setForeground(Color.white);
			label.setFont(new Font(label.getFont().getName(), Font.BOLD, 50));
			this.add(label,BorderLayout.NORTH);
			this.setVisible(true);			
		
			JLabel lab = new JLabel(new ImageIcon(FileHandler.readImage(FileHandler.IMAGE_STRING)));
			lab.setPreferredSize(new Dimension(100,100));
			this.add(lab, BorderLayout.CENTER);
			parent.revalidate();
		}
	}
}
