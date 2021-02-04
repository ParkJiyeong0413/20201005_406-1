package com.java.main;

import java.util.Scanner;

public class MainJava {

	public static void main(String[] args) {
		
		System.out.println("안녕하세요");
		
		//입력
		Scanner scan = new Scanner(System.in);
		int k = Integer.parseInt(scan.nextLine());
		int y = Integer.parseInt(scan.nextLine());
		
		//처리
		int sum = k+y;
		
		
		//출력
		System.out.println(k+" + "+y+" = "+sum);
	}

}









