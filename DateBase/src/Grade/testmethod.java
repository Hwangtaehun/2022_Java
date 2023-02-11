package Grade;

public class testmethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 12345;
		Test test = new Test();
		String result = test.FindSuperTitle(num);
		System.out.println(result);
	}

}

class Array{
	public Array() {}
	
	public void ArraySort1(String data[]) {
		int num = 1;
		for(int i = 0; i < 5; i++)
		{
			data[i] = Integer.toString(num);
			num++;
		}
	}
	
	public void ArraySort2(String data[]) {
		int num = 5;
		for(int i = 0; i < 5; i++)
		{
			data[i] = Integer.toString(num);
			num--;
		}
	}
	
	public void Print(String data[]) {
		for(int i = 0; i < 5; i++)
		{
			System.out.print(data[i] + " ");
		}
		System.out.print("\n");
	}
}

class Test{
	String string;
	
	public Test() {}
	
	public void CheckDate(String date) {
		String year = null, month = null, day = null, fianl = null;
		String[] array_word;
		boolean number = false;
		int count = date.length();
		array_word = date.split("");
		
		number = CheckNumber(date);
		if(number) {
			if(count == 8)
			{
				for(int i = 0; i < 4; i++) {
					if(i == 0)
						year = array_word[i];
					else
						year += array_word[i];
				}
				for(int i = 4 ; i < 6; i++) {
					if(i == 4)
						month = array_word[i];
					else
						month += array_word[i];
				}
				for(int i = 6; i < 8; i++) {
					if(i == 6)
						day = array_word[i];
					else
						day += array_word[i];
				}
			}
			else 
			{
				year = "20";
				for(int i = 0; i < 2; i++) {
					year += array_word[i];
				}
				for(int i = 2 ; i < 4; i++) {
					if(i == 2)
						month = array_word[i];
					else
						month += array_word[i];
				}
				for(int i = 4; i < 6; i++) {
					if(i == 4)
						day = array_word[i];
					else
						day += array_word[i];
				}
			}
		}
		else {
			if(count == 8 ) 
			{
				year = "20";
				for(int i = 0; i < 2; i++) {
					year += array_word[i];
				}
				for(int i = 3 ; i < 5; i++) {
					if(i == 3)
						month = array_word[i];
					else
						month += array_word[i];
				}
				for(int i = 6; i < 8; i++) {
					if(i == 6)
						day = array_word[i];
					else
						day += array_word[i];
				}
			}
			else 
			{
				for(int i = 0; i < 4; i++) {
					if(i == 0)
						year = array_word[i];
					else
						year += array_word[i];
				}
				for(int i = 5; i < 7; i++) {
					if(i == 5)
						month = array_word[i];
					else
						month += array_word[i];
				}
				for(int i = 8; i < 10; i++) {
					if(i == 8)
						day = array_word[i];
					else
						day += array_word[i];
				}
			}
		}
		fianl = year + "-" + month + "-" + day;
		System.out.println(fianl);
	}
	
	private boolean CheckNumber(String date) {
		try {
		      Integer.parseInt(date);
		      return true;
		    } catch (NumberFormatException ex) {
		      return false;
		    }
	}
	
	public String FindSuperTitle(int id)
	{
		
		int temp = id/10000;
		String number = Integer.toString(temp);
		
		temp = id%10000/1000;
		number += Integer.toString(temp);
		
		temp = id%10000%1000/100;
		number += Integer.toString(temp);
		
		number += "00";
		
		
		return number;
	}
}