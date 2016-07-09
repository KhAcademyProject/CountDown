package countDown.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import countDown.controller.UserController;

public class JoinFrame extends JFrame implements ActionListener {

	private Font font = new Font("Default", Font.BOLD, 20);
	private JLabel title, id, pw, join, nickName, pd;
	private JTextField idField, nickField;
	private JPasswordField pwField;
	private JButton joinBtn, cancelBtn, checkBtn ;
	private JFrame frame;
	private Container con;
	private UserController userController;
	private boolean btnChk =false;
	boolean idChk = true;
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
		
		
		frame.setVisible(true);
		start();
	}

	private void contentLocation() {
		title = new JLabel("1 to 50");
		title.setBounds(260, 60, 300, 100);
		title.setFont(new Font("Default", Font.BOLD, 80));

		id = new JLabel("ID");
		id.setBounds(180, 200, 100, 60);
		id.setFont(new Font("Default", Font.BOLD, 25));

		idField = new JTextField();
		idField.setBounds(id.getBounds().x+60, id.getBounds().y+15, 360, 40);
		idField.setFont(new Font("Default", Font.BOLD, 25));

		pw = new JLabel("PW");
		pw.setBounds(id.getBounds().x-5,id.getBounds().y+60, 100, 60);
		pw.setFont(new Font("Default", Font.BOLD, 25));

		pwField = new JPasswordField();
		pwField.setBounds(idField.getBounds().x, pw.getBounds().y+15, idField.getBounds().width, 40);
		pwField.setFont(new Font("Default", Font.BOLD, 25));
		
		nickName = new JLabel("NickName");
		nickName.setBounds(pw.getBounds().x-80,pw.getBounds().y+60, 150, 60);
		nickName.setFont(new Font("Default", Font.BOLD, 25));
		
		nickField= new JTextField();
		nickField.setBounds(pwField.getBounds().x, nickName.getBounds().y+15, idField.getBounds().width, 40);
		nickField.setFont(new Font("Default", Font.BOLD, 25));

		joinBtn = new JButton("가입");
		joinBtn.setBounds(nickField.getBounds().x+20, nickField.getBounds().y+100, 120, 40);
		joinBtn.setFont(new Font("Default", Font.BOLD, 20));
		joinBtn.setBackground(Color.WHITE);
		
		cancelBtn = new JButton("취소");
		cancelBtn.setBounds(joinBtn.getBounds().x+210, joinBtn.getBounds().y, 120, 40);
		cancelBtn.setFont(new Font("Default", Font.BOLD, 20));
		cancelBtn.setBackground(Color.WHITE);

		checkBtn = new JButton("중복체크");
		checkBtn.setBounds(idField.getBounds().x+idField.getBounds().width+20, 
							idField.getBounds().y, 90, 40);
		checkBtn.setFont(new Font("Default", Font.BOLD, 12));
		checkBtn.setBackground(Color.WHITE);

		pd = new JLabel("제작 : 신의 한수");
		pd.setBounds(670, 520, 200, 50);
		pd.setFont(new Font("Default", Font.BOLD, 15));
		
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
		
		switch(e.getActionCommand()){
			case "취소":	
				frame.dispose();	
				new LoginFrame();
			break;
			case "중복체크":
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
				
			case "가입":
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
