package countDown.model.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import countDown.model.domain.User;

public class UserService {

	public HashMap getUserList(){
		HashMap<String, User> users = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("src/resource/user.properties"));
			
			
			if(prop.size() > 0){
				users = new HashMap<String, User>();
				Iterator entryIter = prop.entrySet().iterator();
				
				while(entryIter.hasNext()){
					Map.Entry entry = (Map.Entry)entryIter.next();
					String key = (String)entry.getKey();
					String value = (String)entry.getValue();
					String[] values = value.split(",");
					
					users.put(key, new User(values[0],values[1],values[2]));					
				}
			}
		}catch (Exception e) {
			
		}
		return users;
	}

	public boolean saveUserList(HashMap<String, User> users) {
		boolean result = false;
		Properties prop = new Properties();
		
		Iterator<Map.Entry<String, User>> entryIter = users.entrySet().iterator();
		while(entryIter.hasNext()){
			Map.Entry<String, User> entry = entryIter.next();
			prop.setProperty(entry.getKey(), entry.getValue().toString());
		}
		
		try {
			prop.store(new FileWriter("src/resource/user.properties"), null);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
