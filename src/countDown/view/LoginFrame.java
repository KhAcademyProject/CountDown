package countDown.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.synth.SynthSeparatorUI;

import countDown.controller.UserController;

public class LoginFrame extends JFrame implements ActionListener {

	private Font font = new Font("Default", Font.BOLD, 20);
	private JLabel title, id, pw, join, pd;
	private JTextField idField;
	private JPasswordField pwField;
	private JButton loginBtn;
	private JFrame frame;
	private Container con;
	
	public LoginFrame() {
		super("1 TO 50 GAME"); // 제목
		makeFrame();
	}
	
	public void makeFrame() {
		frame = this;
		frame.setBounds(new Rectangle(500, 300, 800, 600)); // 화면크기
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 닫기
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);	
		makeComponents();	// 구성요소 추가	
		frame.setVisible(true);
	}

	private void makeComponents() {
		con = frame.getContentPane();
		con.setLayout(null);
		contentLocation(); // 콘텐츠 위치정보
		con.add(title);
		con.add(id);
		con.add(idField);
		con.add(idField);
		con.add(pwField);
		con.add(pw);
		con.add(loginBtn);
		con.add(join);
		con.add(pd);
		start();
		frame.setVisible(true);
	}

	private void contentLocation() {
		title = new JLabel("1 to 50");
		title.setBounds(260, 80, 300, 100);
		title.setFont(new Font("Default", Font.BOLD, 80));

		id = new JLabel("ID");
		id.setBounds(150, 220, 100, 60);
		id.setFont(new Font("Default", Font.BOLD, 25));

		idField = new JTextField();
		idField.setBounds(id.getBounds().x+50, id.getBounds().y+15, 340, 40);
		idField.setFont(new Font("Default", Font.BOLD, 25));

		pw = new JLabel("PW");
		pw.setBounds(id.getBounds().x-5,id.getBounds().y+60, 100, 60);
		pw.setFont(new Font("Default", Font.BOLD, 25));

		pwField = new JPasswordField();
		pwField.setBounds(idField.getBounds().x, pw.getBounds().y+15, idField.getBounds().width, 40);
		pwField.setFont(new Font("Default", Font.BOLD, 25));

		loginBtn = new JButton("Login");
		loginBtn.setBounds((idField.getBounds().x + idField.getBounds().width)+20, idField.getBounds().y,
			(pwField.getBounds().y+pwField.getBounds().height)-idField.getBounds().y,
			(pwField.getBounds().y+pwField.getBounds().height)-idField.getBounds().y);
		loginBtn.setFont(new Font("Default", Font.BOLD, 20));
		loginBtn.setBackground(Color.WHITE);
			
		join = new JLabel("회원가입");
		join.setBounds(loginBtn.getBounds().x+25, 340, 100, 60);
		join.setFont(new Font("Default", Font.BOLD, 18));

		pd = new JLabel("제작 : 신의 한수");
		pd.setBounds(670, 520, 200, 50);
		pd.setFont(new Font("Default", Font.BOLD, 15));
	}

	public void start() {
		// 프로그램 내 실행시킬 내용
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginBtn.addActionListener(this);
		
		join.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new UserController().join();
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case "Login" :
				if(!idField.getText().equals("") && pwField.getPassword().length !=0){
					UserController userController = new UserController();
					if(userController.checkMember(idField.getText(), String.valueOf(pwField.getPassword()))){
						this.dispose();
						new MainFrame();
					}else{
						JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 틀립니다","알림창",JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(this, "빈칸을 모두 채워주세요","알림창",JOptionPane.WARNING_MESSAGE);
				}
				break;
		}
	}
	
	
}
