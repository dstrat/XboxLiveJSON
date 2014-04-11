package LiveData;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Sets up the graphical user interface for the application
 * @author danielstratton
 *
 */
public class View{
	/**
	 * Declares all needed parts for the JFrame.
	 */
	JTextField txf1,txf2,txf3,txf4;
	xboxData xD = new xboxData();
	JTextArea txa1;
	JTextField friendTXF;
	JButton fTXFB;
	JFrame frame2;
	private ImageIcon logo;
	private JLabel logoLabel;
	private JLabel avatar,boxart;
	/**
	 * Creates the JFrame with an awesome layout.
	 */
	public void showDisplay(){
		JFrame frame = new JFrame("XBOX DATA");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(499, 399);
		frame.setLayout(null);
		frame.setBackground(Color.black);
		JLabel l1 = new JLabel("Please enter your gamer tag below:");
		txf1 = new JTextField(25);
		l1.setBounds(25, 10, 250, 30);
		l1.setForeground(Color.yellow);
		l1.setFont(new Font("Broadway",Font.ITALIC,14));
		txf1.setBounds(25,50,150, 30);
		txf1.setForeground(Color.red);
		txf1.setFont(new Font("SpeedyMarker",Font.BOLD,14));
		JLabel l2 = new JLabel("Want to know your record of a game? Please enter the name below!");
		txf2 = new JTextField(25);
		l2.setBounds(25, 90, 475, 30);
		l2.setForeground(Color.red);
		l2.setFont(new Font("Broadway",Font.ITALIC,14));
		txf2.setBounds(25, 130, 150, 30);
		txf2.setForeground(Color.red);
		txf2.setFont(new Font("SpeepyMarker",Font.BOLD,14));
		JLabel l4 = new JLabel("Lets take a look at your friends list!");
		txf4 = new JTextField(25);
		l4.setBounds(25, 160, 250, 30);
		l4.setForeground(Color.green);
		l4.setFont(new Font("Broadway",Font.ITALIC,14));
		txf4.setBounds(25, 185, 150, 30);
		txf4.setForeground(Color.green);
		txf4.setFont(new Font("SpeepyMarker",Font.BOLD,14));
		JButton gbgi = new JButton("Get Basic Gamer Info");
		gbgi.addActionListener(new resultHandler());
		gbgi.setBounds(190,55,200,25);
		gbgi.setForeground(Color.orange);
		JButton gameInfo = new JButton("Get Game Info");
		gameInfo.addActionListener(new gameInfoHandler());
		gameInfo.setBounds(190,130,200,25);
		gameInfo.setForeground(Color.red);
		JButton friendInfo = new JButton("Friends!");
		friendInfo.addActionListener(new friendInfoHandler());
		friendInfo.setBounds(190,190,200,25);
		friendInfo.setForeground(Color.blue);
		JButton quit = new JButton("Quit!");
		quit.addActionListener(new quitHandler());
		quit.setBounds(125,250,250,25);
		quit.setForeground(Color.blue);
		logo = new ImageIcon("images/imageLogo.png");
		logoLabel = new JLabel(logo);
		logoLabel.setBounds(50,275,350,219);
		frame.add(l1);
		frame.add(txf1);
		frame.add(l2);
		frame.add(txf2);
		frame.add(l4);
		frame.add(txf4);
		frame.add(gbgi);
		frame.add(gameInfo);
		frame.add(friendInfo);
		frame.add(quit);
		frame.add(logoLabel);
		frame.setSize(500,525);
	}
	/**
	 * Private inner class for the Basic Gamer Info button
	 * @author danielstratton
	 *
	 */
	private class resultHandler implements ActionListener{
		/**
		 * Standard method for action listeners.
		 * Sets up a new JFrame to display basic gamer information.
		 */
		public void actionPerformed(ActionEvent e){
			try{
			frame2 = new JFrame("YOUR RESULTS!");
			frame2.setVisible(true);
			frame2.pack();
			frame2.setSize(700,400);
			frame2.setResizable(true);
			frame2.setLayout(null);
			txa1 = new JTextArea(5,20);
			txa1.setLineWrap(true);
			String temp = txf1.getText();
			if(temp.contains(" ")){
				temp = temp.replace(" ","+");
				setText(temp);
			}else{
				setText(temp);
			}
			ImageIcon avatarIcon = new ImageIcon(xD.avatarBodyImage(temp));
			avatar = new JLabel(avatarIcon);
			JLabel title = new JLabel("CURRENT XBOX LIVE INFORMATION!");
			avatar.setBounds(50, 75, 150, 300);
			txa1.setBounds(300, 200, 390, 100);
			title.setBounds(15,15,650,150);
			txa1.setBackground(Color.black);
			txa1.setForeground(Color.green);
			txa1.setFont(new Font("Bazooka",Font.BOLD,20));
			title.setForeground(Color.red);
			title.setFont(new Font("Microramma",Font.BOLD,20));
			frame2.setBackground(Color.black);
			frame2.add(title);
			frame2.add(txa1);
			frame2.add(avatar);
			setText(temp);
			}catch (Exception e1){
				txf1.setText("Please enter a valid gamer tag");
			}
		}
		/**
		 * Sets the text for the JTextArea inside the second JFrame
		 * @param p
		 * @throws Exception
		 */
		public void setText(String p) throws Exception{
				txa1.setText(xD.profileData(p));
		}
	}
	/**
	 * A second private inner class to handle the game info button
	 * @author danielstratton
	 *
	 */
	private class gameInfoHandler implements ActionListener{
		/**
		 * Standard method for actionListeners.
		 * Creates another JFrame when the button is pressed.
		 * Displays game info with an image of the game.
		 */
		public void actionPerformed(ActionEvent arg0) {
			try{
			frame2 = new JFrame("YOUR RESULTS!");
			frame2.setVisible(true);
			frame2.pack();
			frame2.setResizable(true);
			frame2.setSize(800,500);
			frame2.setLayout(null);
			txa1 = new JTextArea(5,20);
			txa1.setLineWrap(true);
			String temp = txf1.getText();
			String game = txf2.getText();
			if(temp.contains(" ")){
				temp = temp.replace(" ","+");
				setGameText(temp,game);
			}else{
				setGameText(temp,game);
			}
			ImageIcon boxartIcon = new ImageIcon(xD.getBoxArt(temp, game));
			boxart = new JLabel(boxartIcon);
			JLabel gamesTitle = new JLabel(temp + "'s" + " Game Information!");
			gamesTitle.setBounds(300, 25, 650, 100);
			gamesTitle.setForeground(Color.green);
			gamesTitle.setFont(new Font("CopperPlate",Font.BOLD,24));
			txa1.setForeground(Color.red);
			txa1.setBackground(Color.black);
			txa1.setBounds(425, 200, 350, 100);
			txa1.setFont(new Font("Dynamo",Font.BOLD,18));
			boxart.setBounds(50, 75, 219, 300);
			frame2.setBackground(Color.black);
			frame2.add(gamesTitle);
			frame2.add(txa1);
			frame2.add(boxart);
			}catch(Exception e){
				txf2.setText("Please enter a valid game name.");
			}
		}
		/**
		 * Sets the text for the JTextArea
		 * @param p
		 * @param g
		 * @throws Exception
		 */
		public void setGameText(String p,String g) throws Exception{
			txa1.setText(xD.gameData(p,g));
		}
		
	}
	/**
	 * A third inner class to handle the friend info button.
	 * @author danielstratton
	 *
	 */
	private class friendInfoHandler implements ActionListener{
		/** A global String*/
		private String friends;
		/**
		 * Displays basic friend info in a second JFrame.
		 */
		public void actionPerformed(ActionEvent arg1){
			try{
				setUpFrame2Info();
				friends = txf4.getText();
				if(friends.contains(" ")){
					friends = friends.replace(" ", "+");
					setfriendtext(friends);
				}else
					setfriendtext(friends);
			}catch(Exception e){
				txf4.setText("Please enter a valid gamer tag");
			}
		}
		/**
		 * Instantiates the JFrame and awesome esthetics 
		 */
		public void setUpFrame2Info(){
			try{
				frame2 = new JFrame("Friends!");
				frame2.setVisible(true);
				frame2.pack();
				frame2.setResizable(false);
				frame2.setLayout(null);
				frame2.setSize(899,399);
				txa1 = new JTextArea(5,15);
				txa1.setLineWrap(true);
				friendTXF = new JTextField(7);
				fTXFB = new JButton("Please enter a gamer tag");
				fTXFB.addActionListener(new frame2friendinfohandler());
				txa1.setBounds(350,100,550,150);
				txa1.setBackground(Color.black);
				txa1.setForeground(Color.blue);
				txa1.setFont(new Font("Bedrock",Font.BOLD,18));
				friendTXF.setBackground(Color.white);
				friendTXF.setForeground(Color.red);
				friendTXF.setBounds(25,100,200,30);
				fTXFB.setForeground(Color.blue);
				fTXFB.setBackground(Color.gray);
				fTXFB.setBounds(25, 150, 200, 40);
				frame2.setBackground(Color.black);
				frame2.add(friendTXF);
				frame2.add(fTXFB);
				frame2.add(txa1);
				//frame2.setSize(910,410);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		/**
		 * Sets the text for the JTextArea
		 * @param f
		 * @throws Exception
		 */
		public void setfriendtext(String f) throws Exception{
			txa1.setText(xD.friendsData(f));
		}
	}
	/**
	 * A fourth private inner class to handle the information of the second JFrame for the friends information.
	 * @author danielstratton
	 *
	 */
	private class frame2friendinfohandler implements ActionListener{
		/**
		 * Normal action Listener method, changes the information of the second JFrame to a certain friend's data.
		 */
		public void actionPerformed(ActionEvent g){
			try {
				String profile = txf4.getText();
				String gamertag = friendTXF.getText();
				if(gamertag.contains(" ")){
					gamertag = gamertag.replace(" ", "+");
					gamertag = friendTXF.getText();
					setFTXFText(profile,gamertag);
				}else{
					setFTXFText(profile,gamertag);
				}
				ImageIcon friendsAvatar = new ImageIcon(xD.friendBoxArt(profile,gamertag));
				JLabel friendLabel = new JLabel(friendsAvatar);
				friendLabel.setVisible(true);
				friendLabel.setBounds(225,5,150,300);
				frame2.add(friendLabel);
				setFTXFText(profile,gamertag);
				frame2.setSize(900,400);
			}catch (Exception e) {
				txf4.setText("Invalid");
			}
		}
		/**
		 * Sets the text for the JTextArea.
		 * @param profile
		 * @param gamertag
		 * @throws Exception
		 */
		public void setFTXFText(String profile,String gamertag) throws Exception{
			txa1.setText(xD.friendGTData(profile,gamertag));
		}
	}
	/**
	 * Last private inner class for the quit button
	 * @author danielstratton
	 *
	 */
	private class quitHandler implements ActionListener{
		/**
		 * Quits the application.
		 */
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
}
