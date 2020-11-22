package com.student.DTOImple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.student.VO.S_VO;

public class DTOImple {
	String driver ="oracle.jdbc.OracleDriver";
	String url ="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String id = "week7";
	String pwd = "week7";
	
	Connection con=null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = null;
	
	public DTOImple() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,id,pwd);
			
		}catch(Exception e) {e.printStackTrace();}
		
	}
	
	public void enrollStudent(){
		List<S_VO> list = new ArrayList<>();
		System.out.println("�� ���� ����Ͻðڽ��ϱ�?");
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		while(input-->0) {
			scan.nextLine();// ���ۺ���,ť����
			S_VO s_vo = new S_VO();
			System.out.print("������ ���ּ��� :");
			s_vo.setName(scan.nextLine());
			System.out.print("�ֹι�ȣ13�ڸ��� ���ּ��� :");
			s_vo.setYear(scan.nextLine());
			System.out.print("�г��� ���ּ��� :");
			s_vo.setGrade(scan.nextInt());
			System.out.print("�ܾ��� �����ϼ��� :");
			s_vo.setBalance(scan.nextInt());
			
			try {
				
				sql ="select * from student where year='"+s_vo.getYear()+"'";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				
				if(!rs.next())//�ֹι�ȣ�� ���� Ʃ���� ���ٸ� ����Ѵ�.
				{
					while(true) {
						s_vo.setId(idMaker(s_vo.getGrade()));
						sql = "select * from student where id ='"+s_vo.getId()+"'";
						if(ps.executeQuery(sql).next())// �÷��� ������ �ݺ������� ���̵� �缳��
							continue;
						else//�ߺ����̵� �����Ƿ� Ż��
							break;
					}
					sql="insert into student values(?,?,?,?,?)";
					ps =con.prepareStatement(sql);
					ps.setString(1, s_vo.getName());
					ps.setString(2, s_vo.getYear());
					ps.setString(3, s_vo.getId());
					ps.setInt(4, s_vo.getGrade());
					ps.setInt(5, s_vo.getBalance());
					int result = ps.executeUpdate();
					if(result==1) {
						System.out.println("student���̺� ���� ����!");
					}
					list.add(s_vo);
				}
				else {
					System.out.println("����Ͻ� ������ �ֽ��ϴ�. IDã�⸦ ���� Ȯ�����ּ���");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					
					if(rs!=null) rs.close();
					if(rs!=null) rs.close();
					if(rs!=null) rs.close();
				}catch(Exception e) {e.printStackTrace();}
			}
			
		}
		System.out.println("����Ͻ� �����Դϴ�.");
		System.out.println("name\tid\tyear\t\tgrade\tbalance");
		for(int i=0;i<list.size();i++) {
			System.out.println(
					list.get(i).getName()+"\t"+
					list.get(i).getId()+"\t"+
					list.get(i).getYear()+"\t"+
					list.get(i).getGrade()+"\t"+
					list.get(i).getBalance()+"\t");
		}
	}
	//��ü���
	
	//���̵� 5�ڸ� ����
	public String idMaker(int grade) {
		String id;
		int backNumber;
		while(true) {
			backNumber = (int)(Math.random()*10000);
			if(backNumber>=1000&&backNumber<=9999)
				break;
		}
		id = Integer.toString(grade) +Integer.toString(backNumber);
		
		return id;
	}
	//���� �����ֱ�, ���̺� �����ֱ�
	public void getStudentTable() {
		try {
			sql="select * from student";
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			System.out.println("name\tid\tyear\t\tgrade\tbalance");
			while(rs.next()) {
				System.out.println(
				rs.getString("name")+"\t"+
				rs.getString("id")+"\t"+
				rs.getString("year")+"\t"+
				rs.getInt("grade")+"\t"+
				rs.getInt("balance")
						);
				
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {		
				if(rs!=null) rs.close();
				if(rs!=null) rs.close();
				if(rs!=null) rs.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		
	}
	//Idã��
	public void getStudentId() {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.print("�ֹι�ȣ�� �Է��ϼ���(13�ڸ�): ");
			String input = scan.nextLine();
			sql="select * from student where year=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, input);
			rs = ps.executeQuery();
			if(!rs.next()) {//ã�� id�� ���� ��
				System.out.println("��ϵ��� ���� �ֹι�ȣ�Դϴ�. ���� ����ϼ���");
			}else {
				System.out.println(rs.getString("id"));
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {		
				if(rs!=null) rs.close();
				if(rs!=null) rs.close();
				if(rs!=null) rs.close();
			}catch(Exception e) {e.printStackTrace();}
		}
	}
	//���� ���
	public void enrollCourse() {
		try {
			Scanner scan = new Scanner(System.in);
			String course="";
			System.out.print("���̵� �Է��ϼ��� :");
			String id = scan.nextLine();
			int balance=0;
			
			sql="select * from student where id= ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(!rs.next()) {//��ġ�ϴ� ���̵� ���� ��
				System.out.println("�Է��Ͻ� id�� �����ϴ�. id�� Ȯ���غ����� ");
			}else {
				
					balance =rs.getInt("balance");
					
					System.out.print("����Ͻ� �ڽ��� ����ּ��� :");
					int input =Integer.parseInt(scan.nextLine());
			
					
					switch(input) {
					case 1:course ="History 101";break;
					case 2:course="Mathematics 101";break;
					case 3:course="English 101";break;
					case 4:course="Chemistry 101";break;
					case 5:course="Computer Science 101";break;
					}
					
					//id�� �������� Ʃ�� �˻�
					sql="select * from course where s_id=? and course_name=?";
					ps = con.prepareStatement(sql);
					ps.setString(1, id);
					ps.setString(2, course);
					ResultSet rsStudent = ps.executeQuery();
					
					
					
					//Ʃ���� �����Ƿ� ������ �̹� ��ϵǾ�����
					if(1<=rsStudent.getRow()) {
						System.out.println(course+"�� �̹� ����Ͻ� �����Դϴ�.");
					}else {
					
						//Ʃ���� �����Ƿ� ��� ������.
						//���� �˻�
						sql="select * from course_price where c_name=?";
						ps =con.prepareStatement(sql);
						ps.setString(1,course);
						ResultSet rsCourse_Price = ps.executeQuery();
						int price=0;
						while(rsCourse_Price.next()) {
							price = rsCourse_Price.getInt("c_price");
							System.out.println("������ "+price+"�Դϴ�.");
						}
						System.out.print("�����Ͻðڽ��ϱ�? (y/n) ");
						String pay =scan.nextLine();
						
						//�ܾ��� �ִ��� ������ ����
						if(pay.equals("n")||pay.equals("n")) {
							System.out.println("����� ��ҵƽ��ϴ�.");
							
						}else if(pay.equals("y")||pay.equals("Y")){
							if(balance<price) {
								System.out.println(balance);
								System.out.println("�ܾ��� �����Ͽ� ����� ��ҵƽ��ϴ�.");
							}else {
								sql="insert into course(s_id,course_name) values(?,?)";
								ps = con.prepareStatement(sql);
								ps.setString(1, id);
								ps.setString(2, course);
								int result =ps.executeUpdate();
								if(result ==1) {
									System.out.println("���������� ��ϵƽ��ϴ�.");
									sql="update student set balance = ?";
									ps = con.prepareStatement(sql);
									ps.setInt(1, balance - price);
									ps.executeUpdate();
								}
								
							}
						}else {
							System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�.");
							System.out.println("�ٽ� �������ּ���");
						}
						if(rsCourse_Price!=null) rsCourse_Price.close();
					
					if(rsStudent !=null) rsStudent.close();
				}
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {		
				if(rs!=null) rs.close();
				if(rs!=null) rs.close();
				if(rs!=null) rs.close();
				
			}catch(Exception e) {e.printStackTrace();}
		}
		
	}
}