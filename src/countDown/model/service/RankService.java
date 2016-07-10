package countDown.model.service;

import java.io.*;
import java.util.*;

import countDown.model.domain.AscRank;
import countDown.model.domain.User;

public class RankService {
	//랭킹에 등록// prop에 저장  user의 정보저장
	public void saveUserRank(User[] users){
		Properties prop = new Properties();
		
		for(User user : users){
			String key = user.getUserId();
			String value = user.getUserId() + "," + user.getNickName()+ "," + user.getTimeScore();	
			prop.setProperty(key, value);
		}
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("resource/usersRanking.properties"))){
			prop.store(bos, null);
			System.out.println("저장");
		} catch (NullPointerException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	//랭킹 보기// prop에서 순위대로 불러오기 //기록 오름차순
	public String readUserRank(){
		Properties prop = new Properties();
		StringBuilder sb = new StringBuilder();
		ArrayList<User> list = new ArrayList<User>();
//		AscRank ar = new AscRank().compare(o1, o2);
		
		try {
			prop.load(new FileReader("resource/usersRanking.properties"));
		} catch (NullPointerException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		list.sort(new AscRank());
		User[] uArray = new User[list.size()];
		list.toArray(uArray);
//		Iterator<User> iter = list.iterator();
		for (User user : uArray) {
			sb.append(user + "\n");
		}
//		Set<Object> keyset = prop.keySet();
//		Iterator<Object> keyList = keyset.iterator();
//		 Vector<String> v = new Vector<String>();
		 
//		 while (iter.hasNext()) {
//			 sb.append(iter.next() + "\n");
//		        User key = iter.next();
//		        v.add(key);
//		 }
//		        Collections.sort(v);
		        //1등 : 아이디(key), 닉네임 , 기록 순으로 출력되게  기록을 기준으로 정렬
//		    for (String key : v) {
//		        sb.append("1등 : " + key + " = " + prop.getProperty(key) + "\n");
		    	
//		    }
//		    System.out.println(sb);
		    return new String(sb);
	}
}
