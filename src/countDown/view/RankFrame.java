package countDown.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import countDown.controller.MainController;
import countDown.model.service.RankService;

public class RankFrame extends JFrame implements ActionListener{

	private JButton rankUpBtn, rankViewBtn, toMainBtn, replayBtn, modeBtn;
	private JLabel timeLb;
	private Font font = new Font("돋움체", Font.BOLD, 20);
	private JTextArea tarea = new JTextArea();
	//private Container cPane;
	private String time, gameType;
	private JLabel ImgBox1, ImgBox2, ImgBox3, ImgBox4;
	private ImageIcon img1, img2, img3, img4;
	private int saveSwitch = 0;
	
	public RankFrame(){
		this.setTitle("1 TO 50 GAME");
		this.setBounds(new Rectangle(400, 100, 800, 600));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		img(); //화면꾸미기
		
		this.setVisible(true);
	}

	private void rankStart() {
		//실행
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		rankUpBtn.addActionListener(this);
		rankViewBtn.addActionListener(this);
		toMainBtn.addActionListener(this);
		replayBtn.addActionListener(this);
		modeBtn.addActionListener(this);
	}

	public void rankInit(String time, String gameType) {
		// 화면
		this.time = time;
		this.gameType = gameType;
		Container con = this.getContentPane();
		con.setLayout(null);
		
		rankUpBtn = new JButton("랭킹에 등록");
		rankViewBtn = new JButton("랭킹 보기");
		toMainBtn = new JButton("처음으로 가기");
		replayBtn = new JButton("다시하기");
		modeBtn = new JButton("모드 선택하기");
		timeLb = new JLabel(time);
		timeLb.setFont(new Font("Default", Font.BOLD, 40));
		//버튼 꾸미기
		rankUpBtn.setBounds(200, 270, 180, 40);
		rankUpBtn.setFont(font);
		rankUpBtn.setBackground(new Color(102, 255, 153));
		
		rankViewBtn.setBounds(410, 270, 180, 40);
		rankViewBtn.setFont(font);
		rankViewBtn.setBackground(new Color(204, 255, 153));
		
		toMainBtn.setBounds(100, 330, 180, 40);
		toMainBtn.setFont(font);
		toMainBtn.setBackground(new Color(204, 255, 102));
		
		replayBtn.setBounds(510, 330, 180, 40);
		replayBtn.setFont(font);
		replayBtn.setBackground(new Color(153, 250, 204));
		
		modeBtn.setBounds(310, 330, 180, 40);
		modeBtn.setFont(font);
		modeBtn.setBackground(new Color(153, 255, 175));
		
		timeLb.setBounds(310, 170, 200, 50);
		
		con.add(rankUpBtn);
		con.add(rankViewBtn);
		con.add(toMainBtn);
		con.add(replayBtn);
		con.add(modeBtn);
		con.add(timeLb);
		
		rankStart();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println(e.getActionCommand());
		switch(e.getActionCommand()){
		case "랭킹에 등록":
			if(saveSwitch == 0){
				int result = JOptionPane.showConfirmDialog(this, "등록하시겠습니까?", "랭킹 등록", JOptionPane.OK_CANCEL_OPTION);
					if(result == 0){
						//랭킹등록 메소드 실행
						new RankService().saveUserRank(new LoginFrame().userInfo, time, gameType);
						JOptionPane.showMessageDialog(this, "등록되었습니다.");
						saveSwitch = 1;
					}
					
			}else if (saveSwitch == 1){
				JOptionPane.showMessageDialog(this, "이미 등록하였습니다.");
			}
			/*System.out.println(result);*/	break;
		case "랭킹 보기": 
			//랭킹 보는 메소드 실행 
			Frame fs = new Frame("랭킹 확인");
//			System.out.println();
			
			tarea.setText((new RankService().readUserRank(gameType)));
			tarea.setEditable(false);
			tarea.setFont(new Font("Default", Font.BOLD, 15));
			fs.add(tarea, new BorderLayout().CENTER);
			fs.setBounds(200, 100, 300, 700);	
			fs.setLocationRelativeTo(null);
			fs.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					fs.setVisible(false);
					fs.dispose();
				}
			});
			
			fs.setVisible(true);
			new RankService().readUserRank(gameType);	
			break;	
		case "처음으로 가기":
			new MainController();
			this.dispose();
//			System.out.println("메인");	
			break;	
		case "다시하기":	
//			System.out.println(gameType +"--------");
			if (gameType.equals("game"))
				new GameFrame();
			else if (gameType.equals("reverse"))
				new ReverseGameFrame();
			this.dispose();
//			System.out.println("다시");	
			break;	
		case "모드 선택하기":
			new MainFrame();
			this.dispose();
	//		System.out.println("AA");
			break;
		}	
		
	}
	
	public void img(){
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.PINK);
//		cPane = getContentPane();
		contentPane.setLayout(null);
		
		img1 = new ImageIcon("src/images/rank1.gif");
		ImgBox1 = new JLabel(img1);
		ImgBox1.setBounds(25, 80, img1.getIconWidth(), img1.getIconHeight());
		ImgBox1.setSize(300, 200);
		contentPane.add(ImgBox1);
		
		img2 = new ImageIcon("src/images/rank2.gif");
		ImgBox2 = new JLabel(img2);
		ImgBox2.setBounds(470, 80, img2.getIconWidth(), img2.getIconHeight());
		ImgBox2.setSize(300, 200);
		contentPane.add(ImgBox2);
		
		img3 = new ImageIcon("src/images/together.png");
		ImgBox3 = new JLabel(img3);
		ImgBox3.setBounds(428, 372, img3.getIconWidth(), img3.getIconHeight());
		ImgBox3.setSize(500, 200);
		contentPane.add(ImgBox3);
		
		img4 = new ImageIcon("src/images/duck.png");
		ImgBox4 = new JLabel(img4);
		ImgBox4.setBounds(0, 372, img4.getIconWidth(), img4.getIconHeight());
		ImgBox4.setSize(300, 200);
		contentPane.add(ImgBox4);
	}
	
/*	public static void main(String[] args){
		new RankFrame().rankInit("1:00:00", "reverse");;
	}*/

}
