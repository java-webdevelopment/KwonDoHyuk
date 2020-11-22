package com.student.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.student.DTOImple.DTOImple;
import com.student.VO.S_VO;

public class Execute {

	public static void main(String[] args) {
		
		
		
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("메뉴를 선택해주세요 ");
			System.out.println("1.학생등록");
			System.out.println("2.코스틍록");
			System.out.println("3.상태확인");
			System.out.println("4.ID찾기");
			System.out.println("5.종료");
			int input = Integer.parseInt(scan.nextLine());
			switch(input) {
			case 1:
				DTOImple student = new DTOImple();
				student.enrollStudent();
				break;
			case 2:
				DTOImple student2 = new DTOImple();
				student2.enrollCourse();
				break;
			case 3:
				DTOImple student3 = new DTOImple();
				student3.getStudentTable();
				break;
			case 4:
				DTOImple student4 = new DTOImple();
				student4.getStudentId();
				
				break;
			case 5:
				System.exit(0);
				default :
					System.out.println("1~4사이 숫자만 입력하세요");
					break;
			
			}
		}
		

	}

}
