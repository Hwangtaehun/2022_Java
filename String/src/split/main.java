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
				word_array = sequence_change(word_array);
			}
		}
		else if(word_array.length > 1) {
			if(lastname_exist) {
				word_array = sequence_change(word_array);
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
	
	private String englishToKorean(String str) {
		String result = "";
		String[] array_str;
		
		array_str = str.split("");
		
		for(int i = 0; i < array_str.length; i++) {
			switch(array_str[i]) {
			case "a":
			case "A":
				if(i == array_str.length - 1) {
					result += "ㅏ";
				}
				else {
					i++;
					if(array_str[i].equals("e")) {
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
				if(i == array_str.length - 1) {
					result += "ㅋ";
				}
				else {
					i++;
					if(array_str[i].equals("h")) {
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
				if(i == array_str.length - 1) {
					result += "ㅔ";
				}
				else {
					i++;
					if(array_str[i].equals("o")) {
						result += "ㅓ";
					}
					else if(array_str[i].equals("u")) {
						result += "ㅡ";
					}
					else if(array_str[i].equals("e")) {
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
				if(i == array_str.length - 1) {
					result += "ㅈ";
				}
				else {
					i++;
					if(array_str[i].equals("j")) {
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
				if(i == array_str.length - 1) {
					result += "ㅋ";
				}
				else {
					i++;
					if(array_str[i].equals("k")) {
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
				if(i == array_str.length - 1) {
					result += "ㄴ";
				}
				else {
					i++;
					if(array_str[i].equals("g")) {
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
				if(i == array_str.length - 1) {
					result += "ㅗ";
				}
				else {
					i++;
					if(array_str[i].equals("e")) {
						result += "ㅚ";
					}
					else {
						i--;
						result += "ㅗ";
					}
				}
				break;
			case "P":
			case "p":
				if(i == array_str.length - 1) {
					result += "ㅍ";
				}
				else {
					i++;
					if(array_str[i].equals("p")) {
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
				if(i == array_str.length - 1) {
					result += "ㅅ";
				}
				else {
					i++;
					if(array_str[i].equals("s")) {
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
				if(i == array_str.length - 1) {
					result += "ㅌ";
				}
				else {
					i++;
					if(array_str[i].equals("t")) {
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
				if(i == array_str.length - 1) {
					result += "ㅜ";
				}
				else {
					i++;
					if(array_str[i].equals("i")) {
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
				if(i == array_str.length - 1) {
					result += "ㅝ";
				}
				else {
					i++;
					if(array_str[i].equals("a")) {
						if(i == array_str.length - 1) {
							result += "ㅘ";
						}
						else {
							i++;
							if(array_str[i].equals("e")) {
								result += "ㅙ";
							}
							else {
								i--;
								result += "ㅘ";
							}
						}
					}
					else if(array_str[i].equals("e")) {
						result += "ㅞ";
					}
					else if(array_str[i].equals("i")) {
						result += "ㅟ";
					}
					else if(array_str[i].equals("o")) {
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
				if(i == array_str.length - 1) {
					result += "ㅏ";
					result += "ㅇ";
					result += "ㅣ";
				}
				else {
					i++;
					if(array_str[i].equals("a")) {
						if(i == array_str.length - 1) {
							result += "ㅒ";
						}
						else {
							i++;
							if(array_str[i].equals("e")) {
								result += "ㅑ";
							}
							else {
								i--;
								result += "ㅒ";
							}
						}
					}
					else if(array_str[i].equals("e")) {
						if(i == array_str.length - 1) {
							result += "ㅖ";
						}
						else {
							i++;
							if(array_str[i].equals("o")) {
								result += "ㅕ";
							}
							else {
								i--;
								result += "ㅖ";
							}
						}
					}
					else if(array_str[i].equals("o")) {
						result += "ㅛ";
					}
					else if(array_str[i].equals("u")) {
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
