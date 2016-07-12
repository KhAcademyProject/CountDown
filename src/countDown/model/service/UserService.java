package countDown.model.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import countDown.model.domain.User;

public class UserService {

	private static final Object[] User = null;

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
					
					users.put(key, new User(values[0],values[1],values[2],values[3]));					
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

	public String lastIndex(HashMap<String, User> users) {
		
		ArrayList<Integer> keys = new ArrayList<Integer>();
		Set key = users.keySet();
		for (Iterator iterator = key.iterator(); iterator.hasNext();) {
			String keyName = (String) iterator.next();
			keys.add(Integer.parseInt(keyName));
		}
		
		int lastIndex =0;
		
		for (int i = 0; i < key.size(); i++) {
			if(keys.get(i) > lastIndex ){
				lastIndex = keys.get(i);
			}
		}
		return String.valueOf(lastIndex+1);
	}

	public boolean compareId(HashMap<String, User> users, String id) {
		boolean result = false;
		Set set = users.keySet();
		Iterator it = set.iterator();
		for(int i =0; it.hasNext(); i++){
			String key = (String) it.next();
			User value = users.get(key);
			if(value.getUserId().equals(id)){
				result = true;
			}
		}
		
		return result;
	}
	
	
	public int userSize(HashMap<String, User> users) {
		return users.size();
	}


	public String getKey(HashMap<String, User> users, String id) {
		
		String getkey = null;
		Set key = users.keySet();
		for (Iterator iterator = key.iterator(); iterator.hasNext();) {
			String keyName = (String) iterator.next();
			User valueName = users.get(keyName);
			if(valueName.getUserId().equals(id)){
				getkey = valueName.getCode();
			}
		
		}
		
		return getkey;
	
	
	}

	
	
	
}
