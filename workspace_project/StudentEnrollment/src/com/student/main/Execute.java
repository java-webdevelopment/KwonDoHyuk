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
			System.out.println("�޴��� �������ּ��� ");
			System.out.println("1.�л����");
			System.out.println("2.�ڽ��v��");
			System.out.println("3.����Ȯ��");
			System.out.println("4.IDã��");
			System.out.println("5.����");
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
					System.out.println("1~4���� ���ڸ� �Է��ϼ���");
					break;
			
			}
		}
		

	}

}
