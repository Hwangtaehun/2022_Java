package split;

import java.sql.ResultSet;
import java.time.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.*;

class book_symbol{
	private String[] cho = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
	private String[] joong = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
	private String[] jong = {"", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
	
	private String author_array[], author_symbol;
	private char author_char_array[];
	private boolean lastauthor_exist;
	//boolean failure = false;
	
	public book_symbol(String author, boolean bool) {
		lastauthor_exist = bool;
		author_array = author.split(" ");
		
		for(int i = 0; i < author_array.length; i++) {
			System.out.println(author_array[i]);
		}
		
		inital();
	}
	
	private void inital() {
		int num = 0;
		
		while(english_check(author_array[num])) {
			if(author_array.length - 1 == num) {
				break;
			}
			num++;
		}
		
		if(num > 0) {
			if(korean_check(author_array[num])) {
				author_char_array = stringTochar(author_array[num]);
			}
			else {
				if(lastauthor_exist) {
					author_array = sequence_change(author_array);
				}
				author_array = englishTokorean(author_array[0]);
				author_char_array = first_word(author_array);
			}
		}
		else if(author_array.length > 1) {
			if(lastauthor_exist) {
				author_array = sequence_change(author_array);
			}
			author_char_array = stringTochar(author_array[0]);
		}
		else {
			if(korean_check(author_array[0])) {
				author_char_array = stringTochar(author_array[0]);
			}
		}
		
		if(author_char_array != null) {
			finish_symbol();
		}
	}
	
	private boolean korean_check(String str) {
		return Pattern.matches("^[ㄱ-ㅎ가-힣]*$", str);
	}
	
	private boolean english_check(String str) {
		String character[];
		
		character = str.split(".");
		
		if(character.length > 0) {
			return Pattern.matches("^[a-zA-Z]*$", character[0]);
		}
		else {
			return Pattern.matches("^[a-zA-Z]*$", str);
		}
	}
	
	private String[] sequence_change(String[] str) {
		String temp;
		
		temp = str[str.length-1];
		str[str.length-1] = str[0];
		str[0] = temp;
		
		return str;
	}
	
	private String[] englishTokorean(String str) { //여기서 잘못되었음 확인 바람
		String result = "";
		String[] result_array;
		
		for(int i = 0; i < str.length(); i++) {
			System.out.println("str[" + i + "]: " + str.charAt(i));
		}
		
		for(int i = 0; i < str.length(); i++) {
			switch(str.charAt(i)) {
			case 'a':
			case 'A':
				if(i == str.length() - 1) {
					result += "ㅏ";
				}
				else {
					i++;
					if(str.charAt(i) == 'e') {
						result += "ㅐ";
					}
					else {
						i--;
						result += "ㅏ";
					}
				}
				break;
			case 'b':
			case 'B':
				result += "ㅂ";
				break;
			case 'c':
			case 'C':
				if(i == str.length() - 1) {
					result += "ㅋ";
				}
				else {
					i++;
					if(str.charAt(i) == 'h') {
						result += "ㅊ";
					}
					else {
						i--;
						result += "ㅋ";
					}
				}
				break;
			case 'd':
			case 'D':
				result += "ㄷ";
				break;
			case 'e':
			case 'E':
				if(i == str.length() - 1) {
					result += "ㅔ";
				}
				else {
					i++;
					if(str.charAt(i) == 'o') {
						result += "ㅓ";
					}
					else if(str.charAt(i) == 'u') {
						result += "ㅡ";
					}
					else if(str.charAt(i) == 'e') {
						result += "ㅣ";
					}
					else {
						i--;
						result += "ㅔ";
					}
				}
				break;
			case 'f':
			case 'F':
				result += "ㅍ";
				break;
			case 'G':
			case 'g':
				result += "ㄱ";
				break;
			case 'H':
			case 'h':
				result += "ㅎ";
				break;
			case 'I':
			case 'i':
				result += "ㅣ";
				break;
			case 'J':
			case 'j':
				if(i == str.length() - 1) {
					result += "ㅈ";
				}
				else {
					i++;
					if(str.charAt(i) == 'j') {
						result += "ㅉ";
					}
					else {
						i--;
						result += "ㅈ";
					}
				}
				break;
			case 'K':
			case 'k':
				if(i == str.length() - 1) {
					result += "ㅋ";
				}
				else {
					i++;
					if(str.charAt(i) == 'k') {
						result += "ㄲ";
					}
					else {
						i--;
						result += "ㅋ";
					}
				}
				break;
			case 'L':
			case 'l':
				result += "ㄹ";
				break;
			case 'M':
			case 'm':
				result += "ㅁ";
				break;
			case 'N':
			case 'n':
				if(i == str.length() - 1) {
					result += "ㄴ";
				}
				else {
					i++;
					if(str.charAt(i) == 'g') {
						result += "ㅇ";
					}
					else {
						i--;
						result += "ㄴ";
					}
				}
				break;
			case 'O':
			case 'o':
				if(i == str.length() - 1) {
					result += "ㅗ";
				}
				else {
					i++;
					if(str.charAt(i) == 'e') {
						result += "ㅚ";
					}
					else if(str.charAt(i) == 'o') {
						result += "ㅜ";
					}
					else {
						i--;
						result += "ㅗ";
					}
				}
				break;
			case 'P':
			case 'p':
				if(i == str.length() - 1) {
					result += "ㅍ";
				}
				else {
					i++;
					if(str.charAt(i) == 'p') {
						result += "ㅃ";
					}
					else {
						i--;
						result += "ㅍ";
					}
				}
				break;
			case 'Q':
			case 'q':
				result += "ㅋ";
				break;
			case 'R':
			case 'r':
				result += "ㄹ";
				break;
			case 'S':
			case 's':
				if(i == str.length() - 1) {
					result += "ㅅ";
				}
				else {
					i++;
					if(str.charAt(i) == 's') {
						result += "ㅆ";
					}
					else {
						i--;
						result += "ㅅ";
					}
				}
				break;
			case 'T':
			case 't':
				if(i == str.length() - 1) {
					result += "ㅌ";
				}
				else {
					i++;
					if(str.charAt(i) == 't') {
						result += "ㄸ";
					}
					else {
						i--;
						result += "ㅌ";
					}
				}
				break;
			case 'U':
			case 'u':
				if(i == str.length() - 1) {
					result += "ㅜ";
				}
				else {
					i++;
					if(str.charAt(i) == 'i') {
						result += "ㅢ";
					}
					else {
						i--;
						result += "ㅜ";
					}
				}
				break;
			case 'V':
			case 'v':
				result += "ㅂ";
				break;
			case 'W':
			case 'w':
				if(i == str.length() - 1) {
					result += "ㅝ";
				}
				else {
					i++;
					if(str.charAt(i) == 'a') {
						if(i == str.length() - 1) {
							result += "ㅘ";
						}
						else {
							i++;
							if(str.charAt(i) == 'e') {
								result += "ㅙ";
							}
							else {
								i--;
								result += "ㅘ";
							}
						}
					}
					else if(str.charAt(i) == 'e') {
						result += "ㅞ";
					}
					else if(str.charAt(i) == 'i') {
						result += "ㅟ";
					}
					else if(str.charAt(i) == 'o') {
						result += "ㅝ";
					}
					else {
						i--;
						result += "ㅝ";
					}
				}
				break;
			case 'X':
			case 'x':
				result += "ㅋ";
				result += "ㅡ";
				result += "ㅅ";
				result += "ㅡ";
				break;
			case 'Y':
			case 'y':
				if(i == str.length() - 1) {
					result += "ㅏ";
					result += "ㅇ";
					result += "ㅣ";
				}
				else {
					i++;
					if(str.charAt(i) == 'a') {
						if(i == str.length() - 1) {
							result += "ㅒ";
						}
						else {
							i++;
							if(str.charAt(i) == 'e') {
								result += "ㅑ";
							}
							else {
								i--;
								result += "ㅒ";
							}
						}
					}
					else if(str.charAt(i) == 'e') {
						if(i == str.length() - 1) {
							result += "ㅖ";
						}
						else {
							i++;
							if(str.charAt(i) == 'o') {
								result += "ㅕ";
							}
							else {
								i--;
								result += "ㅖ";
							}
						}
					}
					else if(str.charAt(i) == 'o') {
						result += "ㅛ";
					}
					else if(str.charAt(i) == 'u') {
						result += "ㅠ";
					}
					else {
						i--;
						result += "ㅣ";
					}
				}
				break;
			case 'Z':
			case 'z':
				result += "ㅅ";
				break;
			}
		}
		System.out.println("englishTokorean함수의 result: " + result);
		result_array = result.split("");
		
		return result_array;
	}
	
	private char[] first_word(String[] str) {
		int num = 0, cho_num = 99, joong_num = 99, jong_num = 0;
		String result;
		char unicode, result_array[] = null;
		
		for(int i = 0; i < cho.length; i++) {
			if(str[num].equals(cho[i])) {
				cho_num = i;
			}
		}
		
		if(cho_num == 99) {
			cho_num = 11;
		}
		
		System.out.println("cho_num: " + cho_num);
		
		num++;
		
		for(int i = 0; i < joong.length; i++) {
			if(str[num].equals(joong[i])) {
				joong_num = i;
			}
		}
		
		System.out.println("joong_num: " + joong_num);
		
		num++;
		
		for(int i = 0; i < jong.length; i++) {
			if(str[num].equals(jong[i])) {
				jong_num = i;
			}
		}
		
		System.out.println("jong_num: " + jong_num);
		
		if(jong_num == 0) {
			num--;
		}
		
		if(joong_num == 99) {
			return result_array;
		}
		
		num++;
		
		unicode = (char)((cho_num * 21 + joong_num) * 28 + jong_num + 0xAC00);
		System.out.println("unicode = " + unicode);
		
		result = String.valueOf(unicode);
		for(int i = num; i < str.length; i++) {
			result += str[i];
		}
		
		result_array = new char[result.length()];
		
		for(int i = 0; i < result.length(); i++) {
			result_array[i] = result.charAt(i);
		}
		
		return result_array;
	}
	
	private char[] stringTochar(String str) {
		String str_result;
		char uniVal, result_array[];
		
		//System.out.println("stringTochar의 값: " + str);
		//System.out.println("str.charAt(0)의 값: " + str.charAt(0));
		str_result = String.valueOf(str.charAt(0));
		uniVal = str.charAt(1);
		
		str_result += separate_character(uniVal);
		
		result_array = new char[str_result.length()];
		
		for(int i = 0; i < str_result.length(); i++) {
			result_array[i] = str_result.charAt(i);
		}
		
		return result_array;
	}
	
	private void finish_symbol() {
		int num = 0;
		
		author_symbol = String.valueOf(author_char_array[num]);
		
		num++;
		
		switch(author_char_array[num]) {
		case 'ㄱ':
		case 'ㄲ':
			author_symbol += "1";
			break;
		case 'ㄴ':
			author_symbol += "19";
			break;
		case 'ㄷ':
		case 'ㄸ':
			author_symbol += "2";
			break;
		case 'ㄹ':
			author_symbol += "29";
			break;
		case 'ㅁ':
			author_symbol += "3";
			break;
		case 'ㅂ':
		case 'ㅃ':
			author_symbol += "4";
			break;
		case 'ㅅ':
		case 'ㅆ':
			author_symbol += "5";
			break;
		case 'ㅇ':
			author_symbol += "6";
			break;
		case 'ㅈ':
		case 'ㅉ':
			author_symbol += "7";
			break;
		case 'ㅊ':
			author_symbol += "8";
			break;
		case 'ㅋ':
			author_symbol += "87";
			break;
		case 'ㅌ':
			author_symbol += "88";
			break;
		case 'ㅍ':
			author_symbol += "89";
			break;
		case 'ㅎ':
			author_symbol += "9";
			break;
		}
		
		if(author_symbol.equals(String.valueOf(author_char_array[0]))) {
			author_symbol += "6";
		}
		else {
			num++;
		}
		
		if(author_char_array[num-1] == 'ㅊ') {
			switch(author_char_array[num]) {
			case 'ㅏ':
			case 'ㅐ':
			case 'ㅑ':
			case 'ㅒ':
				author_symbol += "2";
				break;
			case 'ㅓ':
			case 'ㅔ':
			case 'ㅕ':
			case 'ㅖ':
				author_symbol += "3";
				break;
			case 'ㅗ':
			case 'ㅘ':
			case 'ㅙ':
			case 'ㅚ':
			case 'ㅛ':
				author_symbol += "4";
				break;
			case 'ㅜ':
			case 'ㅝ':
			case 'ㅞ':
			case 'ㅟ':
			case 'ㅠ':
			case 'ㅡ':
			case 'ㅢ':
				author_symbol += "5";
				break;
			case 'ㅣ':
				author_symbol += "6";
				break;
			}
		}
		else {
			switch(author_char_array[num]) {
			case 'ㅏ':
				author_symbol += "2";
				break;
			case 'ㅐ':
			case 'ㅑ':
			case 'ㅒ':
				author_symbol += "3";
				break;
			case 'ㅓ':
			case 'ㅔ':
			case 'ㅕ':
			case 'ㅖ':
				author_symbol += "4";
				break;
			case 'ㅗ':
			case 'ㅘ':
			case 'ㅙ':
			case 'ㅚ':
			case 'ㅛ':
				author_symbol += "5";
				break;
			case 'ㅜ':
			case 'ㅝ':
			case 'ㅞ':
			case 'ㅟ':
			case 'ㅠ':
				author_symbol += "6";
				break;
			case 'ㅡ':
			case 'ㅢ':
				author_symbol += "7";
				break;	
			case 'ㅣ':
				author_symbol += "8";
				break;
			}
		}
	}
	
	public String separate_character(char uniVal) {
		String result = "";
		int num_cho, num_joong, num_jong;
		
		num_cho = (uniVal-0xAC00)/28/21;
		num_joong = (uniVal - 0xAC00)/28%21;
		num_jong = (uniVal - 0xAC00)%28;
		
		result += cho[num_cho];
		result += joong[num_joong];
		result += jong[num_jong];
		result.trim();
		
		return result;
	}
	
	public String call_symbol() {
		return author_symbol;
	}
}

public class main {	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
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
		*/
		String str_kor, str_engkor, str_eng, sym_kor, sym_engkor, sym_eng, str_error, sym_error;
		book_symbol kor, engkor, eng, error;
		
		str_kor = "김정준";
		str_engkor = "J.K. 롤칭";
		str_eng = "howard phillips lovecraft";
		str_error = "abcd efgh";
		
		kor = new book_symbol(str_kor, false);
		engkor = new book_symbol(str_engkor, true);
		eng = new book_symbol(str_eng, true);
		error = new book_symbol(str_error , false);
		
		sym_kor = kor.call_symbol();
		sym_engkor = engkor.call_symbol();
		sym_eng = eng.call_symbol();
		sym_error = error.call_symbol();
		
		System.out.println("kor: " + sym_kor + ", engkor: " + sym_engkor + ", eng: " + sym_eng + ", error: " + sym_error);
	}
}
