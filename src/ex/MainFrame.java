package ex;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class MainFrame extends JFrame implements MouseListener, Runnable {
	// 메인프레임클래스
	private JLabel lb_title = new JLabel();
	// 게임타이틀표시용라벨
	private JLabel lb_time = new JLabel();
	// 시간표시용라벨
	private JButton bt_start = new JButton("START");
	// 게임시작버튼
	private JButton bt_reset = new JButton("RESET");
	// 게임리셋버튼
	SimpleDateFormat time_format;
	// 시간값을변환하기위한포맷
	String show_time;
	// 진행시간값을받아들일문자열
	long start_time, current_time, actual_time;
	// 게임시작시간, 컴퓨터시간, 실제게임진행시간
	boolean time_run = false;
	Thread th;
	// 스레드
	ImagePanel sc;

	// 게임화면을표시할패널클래스접근키
	MainFrame() {
		// 화면에띄울프레임정의
		init();
		start();
		setTitle("1 to 50 Game");
		setSize(600, 600);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2 - getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - getHeight() / 2);
		setLocation(xpos, ypos);
		setResizable(false);
		setVisible(true);
	}

	public void init() {
		// 게임라벨, 버튼및게임화면용패널설정
		Container con = this.getContentPane();
		con.setLayout(null);
		lb_title.setBounds(250, 10, 300, 30);
		lb_title.setFont(new Font("Default", Font.BOLD, 20));
		con.add(lb_title);
		lb_time.setBounds(400, 50, 150, 30);
		lb_time.setFont(new Font("Default", Font.BOLD, 20));
		con.add(lb_time);
		// 프레임에표시할텍스트라벨
		bt_start.setBounds(100, 520, 100, 30);
		bt_start.setFont(new Font("Default", Font.BOLD, 20));
		con.add(bt_start);
		bt_reset.setBounds(400, 520, 100, 30);
		bt_reset.setFont(new Font("Default", Font.BOLD, 20));
		con.add(bt_reset);
		// 프레임에표시할게임시작및리셋버튼
		sc = new ImagePanel();
		sc.setBounds(100, 100, 410, 410);
		con.add(sc);
		// 게임화면용패널
	}

	public void start() {
		// 프레임내실행시킬기본내용
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 프레임종료용X버튼활성화
		this.addMouseListener(this);
		// 프레임내마우스동작활성화
		bt_start.addMouseListener(this);
		// start 버튼에마우스동작활성화
		bt_reset.addMouseListener(this);
		// reset 버튼에마우스동작활성화
		th = new Thread(this);
		th.start();
		// 스레드시작
		time_format = new SimpleDateFormat("HH:mm:ss.SSS");
		// 시간포맷설정
		lb_time.setText("00:00:00.000");
		lb_title.setText("1 to 50 Game");
		// 라벨텍스트내용설정
		sc.gameStart(false);
		// 게임은대기상태로
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
			sc.countDown((int) actual_time / 1000);
			// 카운트다운표시용시간값전송
			if (!sc.game_start && sc.check <= 50) {
				// 게임세팅완료후게임시작되면게임진행시간갱신
				show_time = time_format.format(actual_time - 32403000);
				lb_time.setText(show_time);
			}
		}
		if (sc.check > 50) {
			sc.ClearTime(lb_time.getText());
			// 숫자50까지클릭이끝나면게임클리어메세지띄울준비
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == bt_start) {
			// 게임시작버튼
			if (!time_run && !sc.game_start) {
				start_time = System.currentTimeMillis();
				// 시작버튼눌렀을시시간값받기
				sc.rect_select.clear();
				time_run = true;
				sc.gameStart(true);
				// 게임및시간세팅
				// System.out.println("start");
			}
		} else if (e.getSource() == bt_reset) {
			// 게임초기화버튼
			start_time = 0;
			lb_time.setText("00:00:00.000");
			sc.rect_select.clear();
			sc.countDown(0);
			time_run = false;
			sc.gameStart(false);
			sc.check = 0;
			// 게임초기화
			// System.out.println("reset");
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
