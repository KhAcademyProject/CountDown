package countDown.model.service;

import java.io.*;
import java.util.Properties;

import countDown.model.domain.User;

public class RankService {
	//랭킹에 등록// prop에 저장
	public void saveUserRank(User[] users){
		Properties prop = new Properties();
		
		for(User user : users){
			String key = user.getNickName();
			String value = user.getNickName() + "," + user.getUserName() + "," + user.getTimeScore();	
			prop.setProperty(key, value);
		}
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("usersRanking.properties"))){
			prop.load(bis);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("저장");
	}
	
	//랭킹 보기// prop에서 순위대로 불러오기
	public User[] readUserRank(Properties prop){
		try {
			prop.load(new FileReader("usersRanking.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("불러오기");
		return null;
	}
}
