package countDown.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import countDown.model.domain.User;
import countDown.model.service.RankService;

public class RankFrame extends JFrame implements ActionListener{

	private JButton rankUpBtn, rankViewBtn, toMainBtn, replayBtn;
	private JLabel timeLb;
	private Font font = new Font("Default", Font.BOLD, 20);
	private JTextArea tarea = new JTextArea();
	private JPanel rankPane = new JPanel();
	private MainFrame frame;
	
	public RankFrame(){
//		super(frame);
		this.setTitle("1 TO 50 GAME");
		this.setBounds(new Rectangle(400, 100, 800, 600));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);

		rankInit();
		rankStart();
		
		this.setVisible(true);
	}

	private void rankStart() {
		//실행
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		rankUpBtn.addActionListener(this);
		rankViewBtn.addActionListener(this);
		toMainBtn.addActionListener(this);
		replayBtn.addActionListener(this);
	}

	private void rankInit() {
		// 화면
		
		Container con = this.getContentPane();
		con.setLayout(null);
		
		rankUpBtn = new JButton("랭킹에 등록");
		rankViewBtn = new JButton("랭킹 보기");
		toMainBtn = new JButton("메인으로 가기");
		replayBtn = new JButton("다시하기");
		timeLb = new JLabel("기록");
		timeLb.setFont(font);
		
		rankUpBtn.setBounds(220, 320, 150, 40);
		rankViewBtn.setBounds(400, 320, 150, 40);
		toMainBtn.setBounds(220, 380, 150, 40);
		replayBtn.setBounds(400, 380, 150, 40);
		timeLb.setBounds(360, 150, 150, 40);
		
		con.add(rankUpBtn);
		con.add(rankViewBtn);
		con.add(toMainBtn);
		con.add(replayBtn);
		con.add(timeLb);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "랭킹에 등록":
			int result = JOptionPane.showConfirmDialog(this, "등록하시겠습니까?", "랭킹 등록", JOptionPane.OK_CANCEL_OPTION);
			if(result == 0){
				//랭킹등록 메소드 실행
				new RankService().saveUserRank(new LoginFrame().userInfo);
				JOptionPane.showMessageDialog(this, "등록되었습니다.");
			}
			/*System.out.println(result);*/	break;
		case "랭킹 보기": 
			//랭킹 보는 메소드 실행 
			Frame fs = new Frame("랭킹 확인");
			
			tarea.setText((new RankService().readUserRank()));
			tarea.setEditable(false);
			tarea.setFont(new Font("Default", Font.BOLD, 15));
			fs.add(tarea, new BorderLayout().CENTER);
//			rankPane.add(tarea);
			fs.setBounds(200, 100, 300, 400);
//			fs.add(rankPane);
			
			fs.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					fs.setVisible(false);
					fs.dispose();
				}
			});
			
			fs.setVisible(true);

//			Properties prop = new Properties();
			new RankService().readUserRank();	
			break;	
		case "메인으로 가기":
			new MainFrame();
			this.dispose();
//			System.out.println("메인");	
			break;	
		case "다시하기":
			new GameFrame();
			this.dispose();
//			System.out.println("다시");	
			break;	
		}	
		
	}
	
/*	public String showRank(String userRank){
//		tarea.setText(userRank.toString());
		return userRank.toString();
	}*/
	
	public static void main(String[] args){
		new RankFrame();
	}

}
