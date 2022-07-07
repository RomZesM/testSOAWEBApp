package main.java.com.reverseString;
//Ќаписать метод, который разворачивает строку в обратном порЯдке и
//		замерить времЯ работы этого метода на 1000, 10 000, 100 000 повторений.
//		оформить надо в виде stand alone java приложениЯ с консольным вводом строки.
//		результатом работы должны быть строка, развернутаЯ строка и 3 цифры (времЯ работы).


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringReverse {
	
	public static void main(String[] args) {
		String str = null;
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			str = br.readLine();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		if(str.equals(""))
		{
			System.out.println("String is empty");
			return;
		}
		if(str != null){
			System.out.println(str);
			System.out.println(reverseStr(str));
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < 1000; i++){
				reverseStr(str);
			}
			long endTime = System.currentTimeMillis();
			System.out.println(endTime - startTime);
			
			startTime = System.currentTimeMillis();
			for (int i = 0; i < 10000; i++){
				reverseStr(str);
			}
			endTime = System.currentTimeMillis();
			System.out.println(endTime - startTime);
			
			startTime = System.currentTimeMillis();
			for (int i = 0; i < 100000; i++){
				reverseStr(str);
			}
			endTime = System.currentTimeMillis();
			System.out.println(endTime - startTime);
			
		}
		
	}
	
	public static String reverseStr(String string){
		char[] charArr = string.toCharArray();
		char[] revStr = new char[charArr.length];
		
		for(int i = charArr.length - 1, k = 0; i >= 0; i--, k++){
			revStr[k] = charArr[i];
		}
		String res = new String(revStr);
		return(res);
	}
	
}
