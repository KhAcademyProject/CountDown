package countDown.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import countDown.controller.UserController;

public class JoinFrame extends JFrame implements ActionListener {

	private Font font = new Font("Default", Font.BOLD, 20);
	private JLabel title, id, pw, join, nickName, pd, imgBox, imgBox2, imgBox3, version;
	private JTextField idField, nickField;
	private JPasswordField pwField;
	private JButton joinBtn, cancelBtn, checkBtn ;
	private JFrame frame;
	private Container con;
	private UserController userController;
	private boolean btnChk =false;
	boolean idChk = true;
	private ImageIcon image,img,img2,img3;
	public JoinFrame() {}
	
	public void makeComponents() {
		userController = new UserController();
		frame = this;
		con = frame.getContentPane();
		con.setLayout(null);
		frame.setBounds(new Rectangle(500, 300, 800, 600)); // 화면크기
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 닫기
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);	
		contentLocation(); // 콘텐츠 위치정보
	
		
		con.add(title);
		con.add(id);
		con.add(idField);
		con.add(idField);
		con.add(pwField);
		con.add(pw);
		con.add(nickName);
		con.add(nickField);
		con.add(joinBtn);
		con.add(cancelBtn);
		con.add(checkBtn);
		con.add(pd);
		con.add(imgBox);
		con.add(imgBox2);
		con.add(version);
//		con.add(imgBox3);
		
		frame.setVisible(true);
		start();
	}

	private void contentLocation() {
		con.setBackground(Color.WHITE);
		
		
		img = new ImageIcon("src/images/15-4.gif");
		imgBox = new JLabel(img);
		imgBox.setBounds(650, 350, img.getIconWidth(), img.getIconHeight());
		
		img2 = new ImageIcon("src/images/lion.gif");
		imgBox2 = new JLabel(img2);
		imgBox2.setBounds(350, 20, img2.getIconWidth(), img2.getIconHeight());
		
//		img3 = new ImageIcon("src/images/lion2.gif");
//		imgBox3 = new JLabel(img3);
//		imgBox3.setBounds(0, 420, img3.getIconWidth(), img3.getIconHeight());

		version = new JLabel("Ver. 1.0");
		version.setBounds(730, 0, 300, 30);
		version.setFont(new Font("Default", Font.BOLD, 15));
		
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
		pwField.setBounds(idField.getBounds().x, pw.getBounds().y+15, idField.getBounds().width+40, 40);
		pwField.setFont(new Font("Default", Font.BOLD, 25));
		
		nickName = new JLabel("NickName");
		nickName.setBounds(pw.getBounds().x-80,pw.getBounds().y+60, 150, 60);
		nickName.setFont(new Font("Default", Font.BOLD, 25));
		
		nickField= new JTextField();
		nickField.setBounds(pwField.getBounds().x, nickName.getBounds().y+15, pwField.getBounds().width, 40);
		nickField.setFont(new Font("Default", Font.BOLD, 25));

		joinBtn = new JButton("Sign Up");
		joinBtn.setBounds(nickField.getBounds().x, nickField.getBounds().y+nickField.getBounds().height+20, 
				nickField.getBounds().width/3, 40);
		joinBtn.setFont(new Font("Default", Font.BOLD, 20));
		joinBtn.setForeground(Color.WHITE);
		joinBtn.setBackground( new Color(000, 000, 255));;
		joinBtn.setBorder(new LineBorder(new Color(000, 000, 255)));
		
		cancelBtn = new JButton("Back");
		cancelBtn.setBounds(joinBtn.getBounds().x+joinBtn.getBounds().width*2, joinBtn.getBounds().y, 
							nickField.getBounds().width/3, 40);
		cancelBtn.setFont(new Font("Default", Font.BOLD, 20));
		cancelBtn.setForeground(Color.WHITE);
		cancelBtn.setBackground(new Color(204,204,204));
		cancelBtn.setBorder(new LineBorder(new Color(204,204,204)));
		
		checkBtn = new JButton(new ImageIcon("src/images/check.gif"));
		checkBtn.setBounds(idField.getBounds().x+idField.getBounds().width-2, 
							idField.getBounds().y, 40, 39);
		checkBtn.setBackground(Color.WHITE);
		

		pd = new JLabel("신의 한수");
		pd.setBounds(725, 520, 200, 50);
		pd.setFont(new Font("Default", Font.BOLD, 15));
		
		title = new JLabel("1 to 50",SwingConstants.CENTER);
		title.setBounds(idField.getBounds().x,
				idField.getBounds().y-idField.getBounds().height-90,
				idField.getBounds().width,90);
		title.setFont(new Font("Century Gothic", Font.BOLD, 90));
		title.setForeground(new Color(255,102,051));
		
	}

	public void start() {
		// 프로그램 내 실행시킬 내용
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		joinBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		checkBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		HashMap<String, String> map = new HashMap();
		map.put("id", idField.getText());
		map.put("pw", String.valueOf(pwField.getPassword()));
		map.put("nickName",nickField.getText());
		  //(클래스)e.getSource(); 해당 객체의 정보를 넘긴다.
       
		switch(e.getActionCommand()){
			case "Back":	
				frame.dispose();	
				new LoginFrame().makeFrame();
			break;
			case "":
				idChk = userController.checkId(idField.getText());
				
				if(!idField.getText().equals("")){
					if(idChk) 
						JOptionPane.showMessageDialog(this, "이미 존재하는 ID입니다","중복확인",JOptionPane.ERROR_MESSAGE);
					else if(!idChk){
						JOptionPane.showMessageDialog(this, "사용할 수 있는 ID입니다","중복확인",JOptionPane.INFORMATION_MESSAGE);
						btnChk = true;
					}
				}else{
					JOptionPane.showMessageDialog(this, "아이디를 입력해주세요","중복확인",JOptionPane.WARNING_MESSAGE);
				}
				
				break;
				
			case "Sign Up":
				if(!idField.getText().equals("") &&
					!pwField.getPassword().equals("") &&
					!nickField.getText().equals(""))	{
					
					if(!idChk && btnChk){
						if(userController.addMember(map)){
							JOptionPane.showMessageDialog(this, "저장되었습니다","알림창",JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
							new LoginFrame();
						}
						else
							JOptionPane.showMessageDialog(this, "저장에 실패하였습니다","알림창",JOptionPane.WARNING_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(this, "중복체크로 아이디 중복여부를 확인하세요","경고메세지", JOptionPane.WARNING_MESSAGE);
					}
				
				}else{
					JOptionPane.showMessageDialog(this, "빈칸을 모두 채워주세요","중복확인",JOptionPane.WARNING_MESSAGE);
				}
			break;
		}
	}

	
	
	
}
