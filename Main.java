// 2020-09-24 Yoo Jaein
// Monoalphabetic 암호화 및 복호화 프로그램

import java.io.*;
import java.util.*;

public class Main {
	
	public static void makeKey() {
		try {
			// 임의의 key 생성
			OutputStream output = new FileOutputStream("C:/project/Key.txt");
			StringBuilder sb = new StringBuilder();
			
			Random r = new Random();
			String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

			for(int i=0; i<26; i++) {
				char c = abc.charAt(r.nextInt(abc.length()));

				if(sb.toString().contains(String.valueOf(c)))
					i--;
				else
					sb.append(c);
			}

			// 파일로 저장
			byte[] by = sb.toString().getBytes();
			output.write(by);
			output.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public static void encryption() {
		try{
			// key 파일 읽기
			File keyFile = new File("C:/project/Key.txt");
			FileReader file_reader = new FileReader(keyFile);

			StringBuilder sb = new StringBuilder();

			int cur = 0;
			while((cur = file_reader.read())!=-1){
				sb.append((char)cur);
			}

			String key = sb.toString();
			String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


			// 원문 텍스트 파일 읽기
			File plainFile = new File("C:/project/Plaintext.txt");
			FileReader file_reader2 = new FileReader(plainFile);

			StringBuilder sb2 = new StringBuilder();

			int cur2 = 0;
			while((cur2 = file_reader2.read())!=-1){
				sb2.append(Character.toUpperCase((char)cur2));
			}

			String plainText = sb2.toString();


			// 암호문 텍스트 파일 생성 
			OutputStream output = new FileOutputStream("C:/project/Ciphertext.txt");
			StringBuilder sb3 = new StringBuilder();

			for(int i=0; i<plainText.length(); i++) {
				char c = plainText.charAt(i);
				if(Character.isAlphabetic(c))
					sb3.append(key.charAt(abc.indexOf(c)));
				else
					sb3.append(c);
			}


			// 파일로 저장
			byte[] by = sb3.toString().getBytes();
			output.write(by);	

			file_reader.close();
			file_reader2.close();
			output.close();
			System.out.println("암호화 알고리즘이 실행되었습니다.");
			System.out.println();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("암호화 알고리즘이 실행되지 않았습니다.");
			System.out.println();
		}
	}
	
	public static void decryption() {
		try{
			// key 파일 읽기
			File keyFile = new File("C:/project/Key.txt");
			FileReader file_reader = new FileReader(keyFile);

			StringBuilder sb = new StringBuilder();

			int cur = 0;
			while((cur = file_reader.read())!=-1){
				sb.append((char)cur);
			}

			String key = sb.toString();
			String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


			// 암호문 텍스트 파일 읽기
			File cipherFile = new File("C:/project/Ciphertext.txt");
			FileReader file_reader2 = new FileReader(cipherFile);

			StringBuilder sb2 = new StringBuilder();

			int cur2 = 0;
			while((cur2 = file_reader2.read())!=-1){
				sb2.append(Character.toUpperCase((char)cur2));
			}

			String cipherText = sb2.toString();


			// 원문 텍스트 파일 생성
			OutputStream output = new FileOutputStream("C:/project/Plaintext2.txt");
			StringBuilder sb3 = new StringBuilder();

			for(int i=0; i<cipherText.length(); i++) {
				char c = cipherText.charAt(i);
				if(Character.isAlphabetic(c))
					sb3.append(abc.charAt(key.indexOf(c)));
				else
					sb3.append(c);
			}


			// 파일로 저장
			byte[] by = sb3.toString().getBytes();
			output.write(by);

			file_reader.close();
			file_reader2.close();
			output.close();
			System.out.println("복호화 알고리즘이 실행되었습니다.");
			System.out.println();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("복호화 알고리즘이 실행되지 않았습니다.");
			System.out.println();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			System.out.println("----------------");
			System.out.println("암호화&복호화 프로그램");
			System.out.println("----------------");
			System.out.println("1. key 생성");
			System.out.println("2. 암호화");
			System.out.println("3. 복호화");
			System.out.println("q를 입력하면 종료합니다.");
			System.out.println("----------------");
			
			String s = br.readLine();
			if(s.equals("q")) break;
			
			if(s.equals("1")) {
				makeKey();
			} else if(s.equals("2")) {
				encryption();
			} else if(s.equals("3")) {
				decryption();
			}
		}
		
		br.close();
    	return;
    }
}
