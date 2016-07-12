package countDown.controller;

import countDown.view.LoginFrame;
import countDown.view.MainFrame;

public class MainController {
	// 첫 화면 설정
	public MainController(){
		LoginView();
	};
	
	//로그인 화면 보여주기
	public void LoginView(){
		LoginFrame loginView = new LoginFrame();
		loginView.makeFrame();
	}
	
	
	
}
