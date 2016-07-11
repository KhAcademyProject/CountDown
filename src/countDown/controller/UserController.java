package countDown.controller;

import java.util.HashMap;

import javax.swing.JOptionPane;

import countDown.model.domain.User;
import countDown.model.service.UserService;
import countDown.view.JoinFrame;

public class UserController {
	
	public void join() {
		JoinFrame joinView = new JoinFrame();
		joinView.makeComponents();
	}

	public boolean addMember(HashMap map) {
		UserService usersevice = new UserService();
		String id = (String)map.get("id");
		String pw = (String)map.get("pw");
		String nickName = (String)map.get("nickName");
		
		HashMap<String, User> users = userListCont();
		users.put(id,new User(id,pw,nickName));
		
		return usersevice.saveUserList(users);
	}
	
	public boolean checkId(String id) {
		return userListCont().containsKey(id);   
	}

	public boolean checkMember(String id, String password) {
//		System.out.println(id + " " + password);
		boolean result = false;
		if(checkId(id)){
			result= userInfo(id).getUserId().equals(id) && 
					userInfo(id).getUserPass().equals(password);
		}
		return result;
	}
	
	

	private User userInfo(String id) {
		User user = (User) userListCont().get(id);
		return user;
	}
	
	public HashMap userListCont() {
		UserService usersevice = new UserService();
		HashMap<String, User> users = usersevice.getUserList();
		return users;
	}

		
		

}

