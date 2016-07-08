package countDown.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener{
	private JLabel title_Lb, version_Lb, pd_Lb;
	private JButton start_Btn, reverse_Btn;
	private Font font = new Font("Default", Font.BOLD, 20);
	
	public MainFrame(){
		this.setTitle("1to50 GAME");
		this.setBounds(new Rectangle(400, 100, 600, 600));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		init();
		start();
		
		this.setVisible(true);
	}
	
	public void init(){
		//프로그램 실행시 보이는 화면
		Container con = this.getContentPane();
		con.setLayout(null);

		start_Btn = new JButton("Start");
		start_Btn.setBounds(200, 400, 200, 40);
		start_Btn.setFont(font);
		
		reverse_Btn = new JButton("Reverse Mode");
		reverse_Btn.setBounds(200, 460, 200, 40);
		reverse_Btn.setFont(font);
			
		title_Lb = new JLabel("1 to 50");
		title_Lb.setBounds(220, 100, 300, 50);
		title_Lb.setFont(new Font("Default", Font.BOLD, 50));
		
		version_Lb = new JLabel("Ver. 1.0");
		version_Lb.setBounds(530, 0, 300, 30);
		version_Lb.setFont(new Font("Default", Font.PLAIN, 15));
		
		pd_Lb = new JLabel("제작 : 신의 한수");
		pd_Lb.setBounds(485, 540, 300, 30);
		pd_Lb.setFont(new Font("Default", Font.PLAIN, 15));
		
		con.add(start_Btn);
		con.add(reverse_Btn);
		con.add(title_Lb);
		con.add(version_Lb);
		con.add(pd_Lb);
	}
	
	public void start(){
		//프로그램 내 실행시킬 내용
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		start_Btn.addActionListener(this);
		reverse_Btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "Start":
			System.out.println("start"); break;
		case "Reverse Mode":
			System.out.println("reverse");	break;			
		}	
	}
}
