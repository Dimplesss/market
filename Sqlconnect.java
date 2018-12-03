/**
 * 
 */
package Market;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author guoxi,16130120183,769747895@qq.com
 *
 * 
 */
public class Sqlconnect {
	String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost:3306?useSSL=false&serverTimezone=Asia/Shanghai";
    String DB_USERNAME = "root";
    String DB_PASSWORD = "1234";
    String USER_NAME="";
    String USER_PASSWORD="";
    /**
	 * 
	 */
    
	public void connect(){
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            Statement statement = connection.createStatement();
	            statement.execute("USE `market`");//���ݿ�����
	            ResultSet resultSet = statement.executeQuery("select* from Ա����Ϣ��\r\n" + 
	            		"	where  (select �̳���� from �̳���Ϣ��\r\n" + 
	            		"				where �̳�����='�����̳�')=Ա����Ϣ��.�̳����\r\n" + 
	            		"                and ����='��ʱ';");
	            while(resultSet.next()) {
	                System.out.println(resultSet.getNString(8)+" "+resultSet.getString(2));
	            }
	            resultSet.close();
	            statement.close();
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	public int connect(String name,String password) {//����Ա��¼
		USER_NAME=name;
		USER_PASSWORD=password;
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            Statement statement = connection.createStatement();
	            statement.execute("USE `market`");//���ݿ�����
	            ResultSet resultSet = statement.executeQuery("  SELECT ���� FROM ����Ա�û���\r\n" + 
	            		"	        	where �û��� ='"+name+"';\r\n");
	            while(resultSet.next()) {
	            	if(resultSet.getString("����").equals(password))
	            		{
	            		System.out.println("��¼�ɹ�");
	            		EventQueue.invokeLater(new Runnable() {
	            			public void run() {
	            				try {
	            					manager frame = new manager();
	            					frame.setVisible(true);
	            				} catch (Exception e) {
	            					e.printStackTrace();
	            				}
	            			}
	            		});
	            		return 0;
	            		}else
	            			System.out.println("�������");
	            			
	            }     
		 }catch (Exception e) {
	            e.printStackTrace();
	        }		
		 return -1;
	}
	public int empolyeelogin(String employeeid,String password) {//Ա����¼
		USER_NAME=employeeid;
		USER_PASSWORD=password;
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            Statement statement = connection.createStatement();
	            statement.execute("USE `market`");//���ݿ�����
	            ResultSet resultSet = statement.executeQuery(" SELECT ���� FROM Ա���û���\r\n" + 
	            		"	where Ա����� ='" +employeeid+"';\r\n");
	            while(resultSet.next()) {
	            	if(resultSet.getString("����").equals(password))
	            		{
	            		System.out.println("��¼�ɹ�");      		
	            		EventQueue.invokeLater(new Runnable() {
	            			public void run() {
	            				try {
	            					workers frame = new workers(employeeid);
	            					frame.setVisible(true);
	            				} catch (Exception e) {
	            					e.printStackTrace();
	            				}
	            			}
	            		});
	            		return 0;
	            		}else
	            			System.out.println("�������");
	            }


	            
		 }catch (Exception e) {
	            e.printStackTrace();
	        }		 
		 	return -1;
	}
	public String[] personquery(String employeeid) {//������Ϣ��ѯ
		String[] personinfo=new String[10];//�洢������Ϣ������
		personinfo[0]="���޴��ˣ�";
		personinfo[1]="";
		personinfo[2]="";
		personinfo[3]="";
		personinfo[4]="";
		personinfo[5]="";
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            Statement statement = connection.createStatement();
	            statement.execute("USE `market`");//���ݿ�����
	            ResultSet resultSet = statement.executeQuery(" SELECT *FROM Ա����Ϣ��\r\n" + 
	            		"	where Ա����� ='" +employeeid+"';\r\n");
	            while(resultSet.next()) {
	            		personinfo[0]="Ա����ţ�"+resultSet.getString(3);
	            		personinfo[1]="������"+resultSet.getString(4);
	            		personinfo[2]="��λ��"+resultSet.getString(5);
	            		personinfo[3]="��ϵ�绰��"+resultSet.getString(6);
	            		personinfo[4]="���ʣ�"+resultSet.getString(7);
	            		personinfo[5]="ʵϰ�ڣ�"+resultSet.getString(8);
	            		
	            }
	            System.out.println("��ѯ�ɹ���");

	            
		 }catch (Exception e) {
	            e.printStackTrace();
	        }
		return personinfo;		 
	}
	public String[] employeequery(String employeename,String marketname) {//���ڹ���Ա�ĸ�����Ϣ��ѯ
		String[] personinfo=new String[10];//�洢������Ϣ������
		personinfo[0]="���޴��ˣ�";
		personinfo[1]="";
		personinfo[2]="";
		personinfo[3]="";
		personinfo[4]="";
		personinfo[5]="";
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            Statement statement = connection.createStatement();
	            statement.execute("USE `market`");//���ݿ�����
	            ResultSet resultSet = statement.executeQuery(" SELECT *FROM Ա����Ϣ��\r\n" + 
	            		"	where ���� ='" +employeename+"'and �̳����=(select �̳���� from �̳���Ϣ�� where �̳�����='"+marketname+"');\r\n");
	            while(resultSet.next()) {
	            		personinfo[0]="Ա����ţ�"+resultSet.getString(3);
	            		personinfo[1]="������"+resultSet.getString(4);
	            		personinfo[2]="��λ��"+resultSet.getString(5);
	            		personinfo[3]="��ϵ�绰��"+resultSet.getString(6);
	            		personinfo[4]="���ʣ�"+resultSet.getString(7);
	            		personinfo[5]="ʵϰ�ڣ�"+resultSet.getString(8);
	            		
	            }
	            System.out.println("��ѯ�ɹ���");

	            
		 }catch (Exception e) {
	            e.printStackTrace();
	        }
		return personinfo;		 
	}
	public String[] marketquery(String mkname) {//�̳���Ϣ��ѯ
		String[] marketinfo=new String[10];
		marketinfo[0]="���޴��̳�";
		marketinfo[1]=" ";
		marketinfo[2]=" ";
		marketinfo[3]=" ";
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            Statement statement = connection.createStatement();
	            statement.execute("USE `market`");//���ݿ�����
	            ResultSet resultSet = statement.executeQuery(" SELECT *FROM �̳���Ϣ��\r\n" + 
	            		"	where �̳����� ='" +mkname+"';\r\n");

	            while(resultSet.next()) {
	            		marketinfo[0]="�̳���ţ�"+resultSet.getString(1);
	            		marketinfo[1]="�̳����ƣ�"+resultSet.getString(2);
	            		marketinfo[2]="��ַ��"+resultSet.getString(3);
	            		marketinfo[3]="��ϵ�绰��"+resultSet.getString(4);
	            }
	            System.out.println("��ѯ�ɹ���");

	            
		 }catch (Exception e) {
	            e.printStackTrace();
	        }
		return marketinfo;		 
	}
	public String[] depquery(String mkname,String depname) {//����Ա������Ϣ��ѯ
		String[] marketinfo=new String[10];
		marketinfo[0]="���޴˲���";
		marketinfo[1]=" ";
		marketinfo[2]=" ";
		marketinfo[3]=" ";
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	   
	            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
	            statement.execute("USE `market`");//���ݿ�����
	            ResultSet resultSet = statement.executeQuery("select *from ������Ϣ�� \r\n" + 
	            		"	where (select �̳���� from �̳���Ϣ��\r\n" + 
	            		"				where �̳�����='"+mkname+"') =������Ϣ��.�̳����\r\n" + 
	            		"    and ��������='"+depname+"';\r\n" + 
	            		"");
	            while(resultSet.next()) {
	            	marketinfo[0]="�̳���ţ�"+resultSet.getString(1);
	            		marketinfo[1]="���ű�ţ�"+resultSet.getString(2);
	            		marketinfo[2]="�������ƣ�"+resultSet.getString(3);
	            		marketinfo[3]="λ�÷ֲ���"+resultSet.getString(4);
	            	marketinfo[4]="��ϵ�绰��"+resultSet.getString(5);            		          
	            	}     	         
		 }catch (Exception e) {
	            e.printStackTrace();
	        }
		return marketinfo;		 
	}
	
	
	public String insertinfo(String mknum,String depnum,String empnum,String name,String job,String tel,String salary) {//����Ա����Ϣ	
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);	   
	            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                     ResultSet.CONCUR_READ_ONLY);
	            statement.execute("USE `market`");//���ݿ�����
	            statement.execute("insert into Ա����Ϣ�� \r\n" + 
	            		"values('"+mknum+"','"+depnum+"','"+empnum+"','"+name+"','"+job+"','"+tel+"','"+salary+"',null);\r\n");       
		 }catch (Exception e) {
	            e.printStackTrace();
	            return "�뽫��Ϣ��ȫ����";
	        }
		
		return "����ɹ�";
	}
	public String updateinfo(String mknum,String depnum,String empnum,String name,String job,String tel,String salary) {//�޸�Ա����Ϣ
		try {
            Class.forName(DB_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);	   
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                 ResultSet.CONCUR_READ_ONLY);
            statement.execute("USE `market`");//���ݿ�����
            statement.execute("UPDATE Ա����Ϣ��\r\n" + 
            		"set ����='"+name+"'\r\n" +",�̳����='"+mknum+"',���ű��='"+depnum+"',��λ='"+job+"',�绰����='"+tel+"',����='"+salary+"'"+
            		"where Ա�����='"+empnum+"';\r\n" + 
            		"");
		
		
		}catch (Exception e) {
            e.printStackTrace();
            return "�뽫��Ϣ��ȫ����";
        }
		
		return "�޸ĳɹ�!!";
	}
	
	
	
//	�����õ�������
//	public static void main(String[] args) {
//		Market.Sqlconnect con = new Sqlconnect();
//		con.employeequery("��ʱ","�����̳�");
//}
}