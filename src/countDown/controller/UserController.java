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

	//회원추가 
	public boolean addMember(HashMap map) {
		UserService usersevice = new UserService();
		HashMap<String, User> users = userListCont();
		String lastindex = usersevice.lastIndex(users);
		String id = (String)map.get("id");
		String pw = (String)map.get("pw");
		String nickName = (String)map.get("nickName");
		users.put(lastindex,new User(lastindex,id,pw,nickName));
		
		return usersevice.saveUserList(users);
	}
	
	//아이디 중복체크
	public boolean checkId(String id) {
		UserService usersevice = new UserService();
		return usersevice.compareId(userListCont(),id);
	}

	//로그인 아이디 패스워드 체크
	public boolean checkMember(String id, String password) {
		UserService usersevice = new UserService();
		boolean result = false;
		if(checkId(id)){
			String key = usersevice.getKey(usersevice.getUserList(),id);
			result= userInfo(key).getUserId().equals(id) && 
					userInfo(key).getUserPass().equals(password);
		}
		return result;
	}
	
	

	public User userInfo(String id) {
		UserService usersevice = new UserService();
		String key = usersevice.getKey(usersevice.getUserList(),id);
		User user = (User) userListCont().get(key);
		return user;
	}
	
	public HashMap userListCont() {
		UserService usersevice = new UserService();
		HashMap<String, User> users = usersevice.getUserList();
		return users;
	}

	

		
		

}

