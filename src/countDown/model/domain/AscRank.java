package countDown.model.domain;

import java.util.Comparator;

public class AscRank implements Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		int result = 0;
		
		if(o1 instanceof User && o2 instanceof User){
			User u1 = (User)o1;
			User u2 = (User)o2;
		
		result = ((u1.getTimeScore().compareTo(u2.getTimeScore()) > 0? 1 : u1.getTimeScore().compareTo(u2.getTimeScore()) == 0 ? 0 : -1));
		}
		return result;
	}

}
