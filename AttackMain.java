// 2020-09-28 Yoo Jaein
// Monoalphabetic 암호문 공격 프로그램

import java.io.*;
import java.util.*;

public class AttackMain {

	/*
	 *  English Letter Frequency (based on a sample of 40,000 words)
	 *  http://pi.math.cornell.edu/~mec/2003-2004/cryptography/subs/frequencies.html
	 *  private final double[] avgFreqs = {
	 *  		8.12, 1.49, 2.71, 4.32, 12.02, 2.30, 2.03, 5.92, 7.31, 0.10, 0.69, 3.98, 2.61,
	 *  		6.95, 7.68, 1.82, 0.11, 6.02, 6.28, 9.10, 2.88, 1.11, 2.09, 0.17, 2.11, 0.07
	 *  	};
	 */
	
	private static HashMap<Integer, Double> map = new HashMap<>();
	private static int cnt = 0;
	
	public static void frequencyAnalysis() {
		try {
			// 암호문 텍스트 파일 읽기
			File keyFile = new File("C:/project/Ciphertext.txt");
			FileReader file_reader = new FileReader(keyFile);

			StringBuilder sb = new StringBuilder();

			int cur = 0;
			while((cur = file_reader.read())!=-1){
				sb.append((char)cur);
			}


			// 알파벳 빈도 수 출력
			String cipherText = sb.toString();
			calculation(cipherText);

			for(int i=0; i<26; i++) {
				if(map.containsKey(i))
					System.out.println(Character.valueOf((char) ('A'+i))+": "+map.get(i)+" ");
				else
					System.out.println(Character.valueOf((char) ('A'+i))+": "+0+" ");
			}
			
			file_reader.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public static void calculation(String cipherText) {
		for(int i=0; i<cipherText.length(); i++) {
			if(Character.isAlphabetic(cipherText.charAt(i))) {
				map.put(cipherText.charAt(i)-'A', map.getOrDefault(cipherText.charAt(i)-'A', (double) 0)+1);
				cnt++;
			}
		}
	}
	
	public static void attack() {
		try {
			// 암호문 텍스트 파일 읽기
			File keyFile = new File("C:/project/Ciphertext.txt");
			FileReader file_reader = new FileReader(keyFile);

			StringBuilder sb = new StringBuilder();

			int cur = 0;
			while((cur = file_reader.read())!=-1){
				sb.append((char)cur);
			}


			// 알파벳 빈도 수 계산
			String cipherText = sb.toString();
			calculation(cipherText);

			for(int i=0; i<26; i++) {
				if(!map.containsKey(i))
					map.put(i, 0.0);
			}

			// 암호문의 알파벳 frequency 계산
			for(int k : map.keySet()) {
				map.replace(k, map.get(k)/(double)cnt*100);
			}


			// avgFreqs을 이용한
			// key와 원문 텍스트 파일 예측
			OutputStream output = new FileOutputStream("C:/project/Key1.txt");
			OutputStream output2 = new FileOutputStream("C:/project/Plaintext1.txt");

			// map value의 내림차순으로 정렬
			List<Integer> keySetList = new ArrayList<>(map.keySet());
			Collections.sort(keySetList, (o1, o2) -> (map.get(o2).compareTo(map.get(o1))));

			String eta = "ETAOINSRHDLUCMFYWGPBVKXQJZ";
			char[] result = new char[26];

			int idx = 0;
			for(int k : keySetList) {
				result[eta.charAt(idx)-'A'] = Character.valueOf((char) ('A'+k));
				idx++;
			}


			// Key 생성
			StringBuilder sb2 = new StringBuilder();

			for(int i=0; i<26; i++)
				sb2.append(result[i]);

			String key = sb2.toString();


			// 원문 텍스트 파일 생성
			String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			StringBuilder sb3 = new StringBuilder();

			for(int i=0; i<cipherText.length(); i++) {
				char c = cipherText.charAt(i);
				if(Character.isAlphabetic(c))
					sb3.append(abc.charAt(key.indexOf(c)));
				else
					sb3.append(c);
			}


			byte[] by = key.getBytes();
			output.write(by);

			String plainText = sb3.toString();
			by = plainText.getBytes();
			output2.write(by);

			output2.close();
			output.close();
			file_reader.close();
			System.out.println("Key와 원문 예측이 실행되었습니다.");
			System.out.println();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			System.out.println("---------------------");
			System.out.println("        암호문 공격 프로그램");
			System.out.println("---------------------");
			System.out.println("1. 암호문의 알파벳 빈도 수 출력");
			System.out.println("2. key와 원문 예측");
			System.out.println("q를 입력하면 종료합니다.");
			System.out.println("---------------------");
			
			String s = br.readLine();
			if(s.equals("q")) break;
			
			if(s.equals("1")) {
				frequencyAnalysis();
			} else if(s.equals("2")) {
				attack();
			}
		}
		
		br.close();
    	return;
    }
}
