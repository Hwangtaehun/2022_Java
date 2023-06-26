package split;

import java.sql.ResultSet;
import java.time.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.*;

class book_symbol{
	String[] cho = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
	String[] joong = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
	String[] jong = {"", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
	
	String author_array[], author_symbol;
	char author_char_array[];
	boolean lastauthor_exist;
	boolean failure = false;
	
	public book_symbol(String author, boolean bool) {
		lastauthor_exist = bool;
		author_array = author.split(" ");
		
		inital();
	}
	
	private void inital() {
		int num = 0;
		
		while(english_check(author_array[num])) {
			num++;
		}
		
		if(num > 0) {
			if(korean_check(author_array[num])) {
				author_char_array = stringTochar(author_array[num]);
			}
			else {
				author_array = sequence_change(author_array);
				author_array = englishToKorean(author_array);
				author_char_array = first_word(author_array);
			}
		}
		else if(author_array.length > 1) {
			if(lastauthor_exist) {
				author_array = sequence_change(author_array);
				author_char_array = stringTochar(author_array[0]);
			}
		}
	}
	
	private boolean korean_check(String str) {
		return Pattern.matches("^[ㄱ-ㅎ가-힣]*$", str);
	}
	
	private boolean english_check(String str) {
		String character[];
		
		character = str.split(".");
		
		return Pattern.matches("^[a-zA-Z]*$", character[0]);
	}
	
	private String[] sequence_change(String[] str) {
		String temp;
		
		temp = str[str.length-1];
		str[str.length-1] = str[0];
		str[0] = temp;
		
		return str;
	}
	
	private String[] englishToKorean(String[] str) {
		String result = "";
		String[] result_array;
		
		for(int i = 0; i < str.length; i++) {
			switch(str[i]) {
			case "a":
			case "A":
				if(i == str.length - 1) {
					result += "ㅏ";
				}
				else {
					i++;
					if(str[i].equals("e")) {
						result += "ㅐ";
					}
					else {
						i--;
						result += "ㅏ";
					}
				}
				break;
			case "b":
			case "B":
				result += "ㅂ";
				break;
			case "c":
			case "C":
				if(i == str.length - 1) {
					result += "ㅋ";
				}
				else {
					i++;
					if(str[i].equals("h")) {
						result += "ㅊ";
					}
					else {
						i--;
						result += "ㅋ";
					}
				}
				break;
			case "d":
			case "D":
				result += "ㄷ";
				break;
			case "e":
			case "E":
				if(i == str.length - 1) {
					result += "ㅔ";
				}
				else {
					i++;
					if(str[i].equals("o")) {
						result += "ㅓ";
					}
					else if(str[i].equals("u")) {
						result += "ㅡ";
					}
					else if(str[i].equals("e")) {
						result += "ㅣ";
					}
					else {
						i--;
						result += "ㅔ";
					}
				}
				break;
			case "f":
			case "F":
				result += "ㅍ";
				break;
			case "G":
			case "g":
				result += "ㄱ";
				break;
			case "H":
			case "h":
				result += "ㅎ";
				break;
			case "I":
			case "i":
				result += "ㅣ";
				break;
			case "J":
			case "j":
				if(i == str.length - 1) {
					result += "ㅈ";
				}
				else {
					i++;
					if(str[i].equals("j")) {
						result += "ㅉ";
					}
					else {
						i--;
						result += "ㅈ";
					}
				}
				break;
			case "K":
			case "k":
				if(i == str.length - 1) {
					result += "ㅋ";
				}
				else {
					i++;
					if(str[i].equals("k")) {
						result += "ㄲ";
					}
					else {
						i--;
						result += "ㅋ";
					}
				}
				break;
			case "L":
			case "l":
				result += "ㄹ";
				break;
			case "M":
			case "m":
				result += "ㅁ";
				break;
			case "N":
			case "n":
				if(i == str.length - 1) {
					result += "ㄴ";
				}
				else {
					i++;
					if(str[i].equals("g")) {
						result += "ㅇ";
					}
					else {
						i--;
						result += "ㄴ";
					}
				}
				break;
			case "O":
			case "o":
				if(i == str.length - 1) {
					result += "ㅗ";
				}
				else {
					i++;
					if(str[i].equals("e")) {
						result += "ㅚ";
					}
					else if(str[i].equals("o")) {
						result += "ㅜ";
					}
					else {
						i--;
						result += "ㅗ";
					}
				}
				break;
			case "P":
			case "p":
				if(i == str.length - 1) {
					result += "ㅍ";
				}
				else {
					i++;
					if(str[i].equals("p")) {
						result += "ㅃ";
					}
					else {
						i--;
						result += "ㅍ";
					}
				}
				break;
			case "Q":
			case "q":
				result += "ㅋ";
				break;
			case "R":
			case "r":
				result += "ㄹ";
				break;
			case "S":
			case "s":
				if(i == str.length - 1) {
					result += "ㅅ";
				}
				else {
					i++;
					if(str[i].equals("s")) {
						result += "ㅆ";
					}
					else {
						i--;
						result += "ㅅ";
					}
				}
				break;
			case "T":
			case "t":
				if(i == str.length - 1) {
					result += "ㅌ";
				}
				else {
					i++;
					if(str[i].equals("t")) {
						result += "ㄸ";
					}
					else {
						i--;
						result += "ㅌ";
					}
				}
				break;
			case "U":
			case "u":
				if(i == str.length - 1) {
					result += "ㅜ";
				}
				else {
					i++;
					if(str[i].equals("i")) {
						result += "ㅢ";
					}
					else {
						i--;
						result += "ㅜ";
					}
				}
				break;
			case "V":
			case "v":
				result += "ㅂ";
				break;
			case "W":
			case "w":
				if(i == str.length - 1) {
					result += "ㅝ";
				}
				else {
					i++;
					if(str[i].equals("a")) {
						if(i == str.length - 1) {
							result += "ㅘ";
						}
						else {
							i++;
							if(str[i].equals("e")) {
								result += "ㅙ";
							}
							else {
								i--;
								result += "ㅘ";
							}
						}
					}
					else if(str[i].equals("e")) {
						result += "ㅞ";
					}
					else if(str[i].equals("i")) {
						result += "ㅟ";
					}
					else if(str[i].equals("o")) {
						result += "ㅝ";
					}
					else {
						i--;
						result += "ㅝ";
					}
				}
				break;
			case "X":
			case "x":
				result += "ㅋ";
				result += "ㅡ";
				result += "ㅅ";
				result += "ㅡ";
				break;
			case "Y":
			case "y":
				if(i == str.length - 1) {
					result += "ㅏ";
					result += "ㅇ";
					result += "ㅣ";
				}
				else {
					i++;
					if(str[i].equals("a")) {
						if(i == str.length - 1) {
							result += "ㅒ";
						}
						else {
							i++;
							if(str[i].equals("e")) {
								result += "ㅑ";
							}
							else {
								i--;
								result += "ㅒ";
							}
						}
					}
					else if(str[i].equals("e")) {
						if(i == str.length - 1) {
							result += "ㅖ";
						}
						else {
							i++;
							if(str[i].equals("o")) {
								result += "ㅕ";
							}
							else {
								i--;
								result += "ㅖ";
							}
						}
					}
					else if(str[i].equals("o")) {
						result += "ㅛ";
					}
					else if(str[i].equals("u")) {
						result += "ㅠ";
					}
					else {
						i--;
						result += "ㅣ";
					}
				}
				break;
			case "Z":
			case "z":
				result += "ㅅ";
				break;
			}
		}
		result_array = result.split("");
		
		return result_array;
	}
	
	private char[] first_word(String[] str) {
		int num = 0, cho_num = 99, joong_num = 99, jong_num = 0;
		String result;
		char unicode, result_array[] = null;
		
		for(int i = 0; i < cho.length; i++) {
			if(str[num] == cho[i]) {
				cho_num = i;
			}
		}
		
		if(cho_num == 99) {
			cho_num = 11;
		}
		
		num++;
		
		for(int i = 0; i < joong.length; i++) {
			if(str[num] == joong[i]) {
				joong_num = i;
			}
		}
		
		num++;
		
		for(int i = 0; i < jong.length; i++) {
			if(str[num] == jong[i]) {
				jong_num = i;
			}
		}
		
		if(jong_num == 0) {
			num--;
		}
		
		if(joong_num == 99) {
			failure = false;
			return result_array;
		}
		
		unicode = (char)((cho_num * 21 + joong_num) * 28 + jong_num + 0xAC00);
		
		result = String.valueOf(unicode);
		
		for(int i = num; i < str.length; i++) {
			result += str[i];
		}
		
		for(int i = 0; i < result.length(); i++) {
			result_array[i] = result.charAt(i);
		}
		
		return result_array;
	}
	
	private char[] stringTochar(String str) {
		String result_str;
		char uniVal, result_array[] = null;
		int num_cho, num_joong, num_jong;
		
		result_array[0] = str.charAt(0);
		uniVal = str.charAt(1);
		
		num_cho = (uniVal-0xAC00)/28/21;
		num_joong = (uniVal - 0xAC00)/28%21;
		num_jong = (uniVal - 0xAC00)%28;
		
		result_array[1] = cho[num_cho].charAt(0);
		result_array[2] = joong[num_joong].charAt(0);
		result_array[3] = jong[num_jong].charAt(0);
		
		return result_array;
	}
	
	public String separate_charater(char uniVal) {
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
