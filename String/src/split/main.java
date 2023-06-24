package split;

import java.sql.ResultSet;
import java.time.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.*;

class separate_charater{
	String[] cho = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
	String[] joong = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
	String[] jong = {"", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
	
	
	String word;
	String word_array[];
	char char_array[];
	boolean lastname_exist;
	
	public separate_charater(String str, boolean bool) {
		lastname_exist = bool;
		word = str;
		word_array = str.split(" ");
		
		inital(word_array);
	}
	
	//올영어, 동양 이름, 서양 이름
	private void inital(String[] str) {
		int num = 0;
		boolean bool = false;
		
		while(english_check(str[num])) {
			num++;
		}
		
		if(num > 0) {
			if(korean_check(str[num])) {
				for(int i = 0; i < str[num].length() ; i++) {
					char_array[i] = str[num].charAt(i);
				}
			}
			else {
				
			}
		}
		else if(word_array.length > 1) {
			if(lastname_exist) {
				String temp = "";
				
			}
		}
	}
	
	private boolean korean_check(String str) {
		boolean bool = Pattern.matches("^[ㄱ-ㅎ가-힣]*$", str);
		
		return bool;
	}
	
	private boolean english_check(String str) {
		boolean bool;
		String character[];
		
		character = str.split(".");
		bool =  Pattern.matches("^[a-zA-Z]*$", character[0]);
		
		return bool;
	}
}

public class main {	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LbDB_DAO db = new LbDB_DAO();
		String sql = "SELECT * " + "FROM library, book, material LEFT JOIN lent ON material.mat_no = lent.mat_no " + 
				     "WHERE library.lib_no = material.lib_no AND book.book_no = material.book_no AND " +
				     "len_no = 2";
		ResultSet result = db.getResultSet(sql);
		String state = null;
		
		try {
			while(result.next()) {
				state = result.getString("lent.len_re_st");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(state);
		
		LocalDate Now = LocalDate.now();
		System.out.println(Now);
	}
}
