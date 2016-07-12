package countDown.model.service;

import java.io.*;
import java.util.*;

import countDown.model.domain.AscRank;
import countDown.model.domain.User;

public class RankService {
	private User[] ur;
	//랭킹에 등록// prop에 저장  user의 정보저장
	public void saveUserRank(User user){
		Properties prop = new Properties();
	
			String key = user.getCode();
			String value = user.getUserId() + " : " + user.getNickName()+ ", 걸린 시간 : " + user.getTimeScore();	

			prop.setProperty(key, value);

		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("src/resource/usersRanking.properties",true))){
			prop.store(bos, null);

		} catch (NullPointerException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	//랭킹 보기// readRank 에서 순위대로 불러오기 //기록 오름차순
	public String readUserRank(){
		Properties prop = new Properties();
		StringBuilder sb = new StringBuilder();
		ArrayList<Object> list = new ArrayList<Object>();
		
		String value = null;

		try {
			prop.load(new FileReader("src/resource/usersRanking.properties"));
		} catch (NullPointerException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		Set<String> keys = prop.stringPropertyNames();
		User[] sr = new User[keys.size()];
		Iterator<String> keyIter = keys.iterator();
		
		for(int i = 0; keyIter.hasNext(); i++){
			String key = keyIter.next();
			value = prop.getProperty(key);
			String[] values = value.split(",");
			sr[i]= new User(values[0],values[1],values[2]);
			list.add(sr[i]);
			
		}
	
		list.sort(new AscRank());
	
		for (int j = 0; j < sr.length; j++) {
			sb.append(list.get(j)+ "\n");
		}
		
		
		return new String(sb);
		
	}
}
