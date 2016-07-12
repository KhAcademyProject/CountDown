package countDown.view;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.event.*;
import countDown.model.service.ReverseMainService;

public class ReverseGameFrame extends JFrame implements MouseListener, Runnable  {
	private JLabel time = new JLabel();
	private JButton start = new JButton("START");
	private JButton reset = new JButton("Reset");
	private JButton pause = new JButton("Pause");
	private JLabel graphic = new JLabel();
	private int on = 0;

	SimpleDateFormat time_format;
	String show_time;
	long start_time, current_time, actual_time;
	boolean time_run = false;
	Thread th;
	ReverseMainService ms;
	
	ReverseGameFrame() {
		super("1 TO 50 GAME"); 	//제목
		this.setBounds(new Rectangle(500, 300, 800, 600)); 		// 화면크기
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 		//닫기
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		Container con = this.getContentPane();
		con.setLayout(null);
		time.setBounds(520, 150, 150, 30);
		time.setFont(new Font("Default", Font.BOLD, 30));
		con.add(time);
		// 프레임에표시할텍스트라벨
		pause.setBounds(510, 320, 150, 50);
		pause.setFont(new Font("Default", Font.BOLD, 25));
		pause.setBackground(new Color(204,204,255));
		con.add(pause);
		
		reset.setBounds(510, 380, 150, 50);
		reset.setFont(new Font("Default", Font.BOLD, 25));
		reset.setBackground(new Color(0,153,51));
		con.add(reset);
		
		start.setBounds(510, 260, 150, 50);
		start.setFont(new Font("Default", Font.BOLD, 25));
		start.setBackground(new Color(255,155,000));
		con.add(start);
		
		//게임화면 크기
		ms = new ReverseMainService();
		ms.setBounds(70, 70, 640, 400);
		con.add(ms);
		start();
		setVisible(true);
	}

public void start() {
	// 프레임내실행시킬기본내용
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// 프레임종료용X버튼활성화
	this.addMouseListener(this);
	// 프레임내마우스동작활성화
	start.addMouseListener(this);
	// start 버튼에마우스동작활성화
	reset.addMouseListener(this);
	// reset 버튼에마우스동작활성화
	pause.addMouseListener(this);
	
	th = new Thread(this);
	th.start();
	// 스레드시작
	time_format = new SimpleDateFormat("mm:ss.SSS");
	// 시간포맷설정
	time.setText("00:00.000");

	ms.gameStart(false);
	}

public void run() {
	while (true) {
		try {
			repaint();
			TimeCheck();
			Thread.sleep(15);
			// 무한스레드돌리기
		} catch (Exception e) {
		}
	}
}

public void TimeCheck() {
	if (time_run) {
		current_time = System.currentTimeMillis();
		actual_time = current_time - start_time;
		// 게임시작버튼을눌렀을때의시간값과
		// 실제시간값으로게임진행시간계산.
		ms.countDown((int) actual_time / 1000);
		// 카운트다운표시용시간값전송
		if (!ms.game_start && ms.check >= 1) {
			// 게임세팅완료후게임시작되면게임진행시간갱신
			show_time = time_format.format(actual_time - 32403000);
			time.setText(show_time);
		}
	}
	if (ms.check < 1) {
		ms.ClearTime(time.getText());
		// 숫자 1까지클릭이끝나면게임클리어메세지띄울준비
		this.dispose();
	}
}
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == start) {
				
				if (!time_run && !ms.game_start) {
					start_time = System.currentTimeMillis();
					// 시작버튼눌렀을시 시간값받기
					ms.rect_select.clear();
					time_run = true;
					ms.gameStart(true);
					// 게임및시간세팅
				}
			} else if (e.getSource() == reset) {
				// 게임초기화버튼
				start_time = 0;
				time.setText("00:00.000");
				ms.rect_select.clear();
				ms.countDown(0);
				time_run = false;
				ms.gameStart(false);
				ms.check = 50;
			} 
			// 일시정지
			else if(e.getSource() == pause){
			
				if(on == 0)	{	 
					on = 1;
					th.suspend();
				}else  if(on == 1){ 
						on = 0;
						th.resume();
					}
			}
			}
		
		
		

public void mousePressed(MouseEvent e) {
}

public void mouseReleased(MouseEvent e) {
}

public void mouseEntered(MouseEvent e) {
}

public void mouseExited(MouseEvent e) {
}

}