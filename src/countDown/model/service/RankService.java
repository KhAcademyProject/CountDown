package countDown.model.service;

import java.io.*;
import java.util.*;
import java.util.concurrent.CancellationException;

import countDown.controller.UserController;
import countDown.model.domain.AscRank;
import countDown.model.domain.User;

public class RankService {
	private User[] ur;

	// 랭킹에 등록// prop에 저장 user의 정보저장
	public void saveUserRank(User user, String time, String gameType) {
		Properties prop = new Properties();
		String key = String.valueOf(rankingLength(gameType));
		String value = user.getUserId() + "," + user.getNickName() + "," + time;

		prop.setProperty(key, value);
		if (gameType.equals("game")) {
			try (BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream("src/resource/usersRanking.properties", true))) {
				prop.store(bos, null);

			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (gameType.equals("reverse")) {
			try (BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream("src/resource/reverseRanking.properties", true))) {
				prop.store(bos, null);

			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return;
	}

	private int rankingLength(String gameType) {
		Properties prop = new Properties();
		if (gameType.equals("game")) {
			try {
				prop.load(new FileReader("src/resource/usersRanking.properties"));
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Set<String> keys = prop.stringPropertyNames();

			return keys.size() + 1;

		} else if (gameType.equals("reverse")) {
			try {
				prop.load(new FileReader("src/resource/reverseRanking.properties"));
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		Set<String> keys = prop.stringPropertyNames();

		return keys.size() + 1;

	}

	// 랭킹 보기// readRank 에서 순위대로 불러오기 //기록 오름차순
	public String readUserRank(String gameType) {
		Properties prop = new Properties();
		StringBuilder sb = new StringBuilder();
		ArrayList<User> list = new ArrayList<User>();
		String value = null;
		if(gameType.equals("game")){
		try {
			prop.load(new FileReader("src/resource/usersRanking.properties"));
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}else if(gameType.equals("reverse")){
			try {
				prop.load(new FileReader("src/resource/reverseRanking.properties"));
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Set<String> keys = prop.stringPropertyNames();
		User[] sr = new User[keys.size()];
		Iterator<String> keyIter = keys.iterator();

		for (int i = 0; keyIter.hasNext(); i++) {
			String key = keyIter.next();
			// System.out.println(key);
			value = prop.getProperty(key);
			String[] values = value.split(",");
			sr[i] = new User(values[0], values[1], values[2]);
			list.add(sr[i]);
		}

		list.sort(new AscRank());
		for (int j = 0; j < sr.length; j++) {
			// System.out.println(list.get(j));
			sb.append(j + 1 + "등 : " + list.get(j).getNickName() + " " + list.get(j).getTimeScore() + "\n");
		}
		return new String(sb);
	}
}
