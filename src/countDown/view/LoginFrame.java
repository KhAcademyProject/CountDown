package countDown.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import countDown.controller.UserController;
import countDown.model.domain.User;

public class LoginFrame extends JFrame implements ActionListener {
	public static User userInfo;
	private Font font = new Font("Default", Font.BOLD, 20);
	private JLabel title, id, pw,join, pd, imgBox, version;
	private JTextField idField;
	private JPasswordField pwField;
	private JButton loginBtn;
	private ImageIcon img;
	private JFrame frame;
	private Container con;
	
	public LoginFrame() {
	
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
		con.add(imgBox);
		con.add(version);

		start();
		frame.setVisible(true);
	}

	private void contentLocation() {
		
		con.setBackground(Color.WHITE);
		
		version = new JLabel("Ver. 1.0");
		version.setBounds(730, 0, 300, 30);
		version.setFont(new Font("Default", Font.BOLD, 15));
		
		
		img = new ImageIcon("src/images/23-4.gif");
		imgBox = new JLabel(img);
		imgBox.setBounds(450, 180, img.getIconWidth(), img.getIconHeight());

		id = new JLabel("ID");
		id.setBounds(200, 220, 100, 60);
		id.setFont(new Font("Default", Font.BOLD, 25));
		
		idField = new JTextField();
		idField.setBounds(id.getBounds().x+50, id.getBounds().y+15, 300, 40);
		idField.setFont(new Font("Default", Font.BOLD, 25));

		pw = new JLabel("PW");
		pw.setBounds(id.getBounds().x-5,id.getBounds().y+60, 100, 60);
		pw.setFont(new Font("Default", Font.BOLD, 25));

		pwField = new JPasswordField();
		pwField.setBounds(idField.getBounds().x, pw.getBounds().y+15, idField.getBounds().width, 40);
		pwField.setFont(new Font("Default", Font.BOLD, 25));

		loginBtn = new JButton("Login");
		loginBtn.setBounds(pwField.getBounds().x,pwField.getBounds().y+pwField.getBounds().height+60,
				pwField.getBounds().width,pwField.getBounds().height);
		loginBtn.setFont(new Font("Default", Font.BOLD, 24));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBackground( new Color(000, 000, 255));
		
		join = new JLabel("Hey, get the only your ID!",SwingConstants.CENTER);
		join.setBounds(loginBtn.getBounds().x,
				pwField.getBounds().y+pwField.getBounds().height+10,
				pwField.getBounds().width,pwField.getBounds().height);
		join.setFont(new Font("Default", Font.BOLD, 16));
		join.setForeground(Color.GRAY);
		join.setBackground( new Color(255, 102, 102));

		title = new JLabel("1 to 50",SwingConstants.CENTER);
		title.setBounds(loginBtn.getBounds().x,
				idField.getBounds().y-idField.getBounds().height-90,
				idField.getBounds().width,90);
		title.setFont(new Font("Century Gothic", Font.BOLD, 90));
		title.setForeground(new Color(255,102,051));
		
		pd = new JLabel("신의 한수");
		pd.setBounds(720, 520, 200, 50);
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
			public void mouseEntered(MouseEvent e) {
				font = e.getComponent().getFont();
			    Map attributes = font.getAttributes();
			    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			    e.getComponent().setFont(font.deriveFont(attributes));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				 e.getComponent().setFont(font);
			}

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
						 userInfo = userController.userInfo(idField.getText());
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
