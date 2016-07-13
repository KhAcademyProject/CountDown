package countDown.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainFrame extends JFrame implements ActionListener{

	private JButton gameStart;
	private JButton Reverse;
	private Font font = new Font("Default", Font.BOLD, 20);
	private JLabel title, version, pd, ImgBox, ImgBox1;
	private Container cPane;
	private ImageIcon img, img1;
	
	public MainFrame(){
		super("1 TO 50 GAME"); 	//제목
		this.setBounds(new Rectangle(560, 215, 800, 600)); 		// 화면크기
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 		//닫기
		this.setLocationRelativeTo(null);
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.ORANGE);
	
		contentPane.setLayout(new FlowLayout());
		this.setResizable(false);
		
		cPane = getContentPane();
		cPane.setLayout(null);
		
		img = new ImageIcon("src/images/움1.gif");
		ImgBox = new JLabel(img);
		ImgBox.setBounds(500, 80, img.getIconWidth(), img.getIconHeight());
		ImgBox.setSize(300, 370);				
		cPane.add(ImgBox);
		
		img1 = new ImageIcon("src/images/움3.gif");
		ImgBox1 = new JLabel(img1);
		ImgBox1.setBounds(20, 80, img.getIconWidth(), img1.getIconHeight());
		ImgBox1.setSize(275, 370);
		
		cPane.add(ImgBox1);
		
		Container con = this.getContentPane();
		con.setLayout(null);
		

		gameStart = new JButton("Game START");
		gameStart.setBounds(300, 250, 180, 50);
		gameStart.setBackground(Color.ORANGE);
		gameStart.setFont(font);

		Reverse = new JButton("Reverse mode");
		Reverse.setBounds(300, 350, 180, 50);
		Reverse.setBackground(Color.orange);
		Reverse.setFont(font);
		
		title = new JLabel("1 to 50");
		title.setBounds(330, 100, 300, 50);
		title.setFont(new Font("Default", Font.BOLD, 50));
		
		version = new JLabel("Ver. 1.0");
		version.setBounds(730, 20, 300, 30);
		version.setFont(new Font("Default", Font.BOLD, 15));
		
		pd = new JLabel("제작 : 신의 한수");
		pd.setBounds(650, 500, 200, 50);
		pd.setFont(new Font("Default", Font.BOLD, 16));
		

		con.add(gameStart);
		con.add(Reverse);
		con.add(title);
		con.add(version);
		con.add(pd);

		
		start();

		this.setVisible(true);
	}
	
	
		public void start(){
			//프로그램 내 실행시킬 내용
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			gameStart.addActionListener(this);
			Reverse.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()){
			case "Game START":
				new GameFrame();
				this.dispose();
				break;
			case "Reverse mode":
				new ReverseGameFrame();
				this.dispose();
				break;	
			}	

		}
	}