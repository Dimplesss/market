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
	            statement.execute("USE `market`");//数据库名字
	            ResultSet resultSet = statement.executeQuery("select* from 员工信息表\r\n" + 
	            		"	where  (select 商场编号 from 商场信息表\r\n" + 
	            		"				where 商场名称='长安商场')=员工信息表.商场编号\r\n" + 
	            		"                and 姓名='王时';");
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
	public int connect(String name,String password) {//管理员登录
		USER_NAME=name;
		USER_PASSWORD=password;
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            Statement statement = connection.createStatement();
	            statement.execute("USE `market`");//数据库名字
	            ResultSet resultSet = statement.executeQuery("  SELECT 密码 FROM 管理员用户表\r\n" + 
	            		"	        	where 用户名 ='"+name+"';\r\n");
	            while(resultSet.next()) {
	            	if(resultSet.getString("密码").equals(password))
	            		{
	            		System.out.println("登录成功");
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
	            			System.out.println("密码错误");
	            			
	            }     
		 }catch (Exception e) {
	            e.printStackTrace();
	        }		
		 return -1;
	}
	public int empolyeelogin(String employeeid,String password) {//员工登录
		USER_NAME=employeeid;
		USER_PASSWORD=password;
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            Statement statement = connection.createStatement();
	            statement.execute("USE `market`");//数据库名字
	            ResultSet resultSet = statement.executeQuery(" SELECT 密码 FROM 员工用户表\r\n" + 
	            		"	where 员工编号 ='" +employeeid+"';\r\n");
	            while(resultSet.next()) {
	            	if(resultSet.getString("密码").equals(password))
	            		{
	            		System.out.println("登录成功");      		
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
	            			System.out.println("密码错误");
	            }


	            
		 }catch (Exception e) {
	            e.printStackTrace();
	        }		 
		 	return -1;
	}
	public String[] personquery(String employeeid) {//个人信息查询
		String[] personinfo=new String[10];//存储个人信息的数组
		personinfo[0]="查无此人！";
		personinfo[1]="";
		personinfo[2]="";
		personinfo[3]="";
		personinfo[4]="";
		personinfo[5]="";
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            Statement statement = connection.createStatement();
	            statement.execute("USE `market`");//数据库名字
	            ResultSet resultSet = statement.executeQuery(" SELECT *FROM 员工信息表\r\n" + 
	            		"	where 员工编号 ='" +employeeid+"';\r\n");
	            while(resultSet.next()) {
	            		personinfo[0]="员工编号："+resultSet.getString(3);
	            		personinfo[1]="姓名："+resultSet.getString(4);
	            		personinfo[2]="岗位："+resultSet.getString(5);
	            		personinfo[3]="联系电话："+resultSet.getString(6);
	            		personinfo[4]="工资："+resultSet.getString(7);
	            		personinfo[5]="实习期："+resultSet.getString(8);
	            		
	            }
	            System.out.println("查询成功！");

	            
		 }catch (Exception e) {
	            e.printStackTrace();
	        }
		return personinfo;		 
	}
	public String[] employeequery(String employeename,String marketname) {//用于管理员的个人信息查询
		String[] personinfo=new String[10];//存储个人信息的数组
		personinfo[0]="查无此人！";
		personinfo[1]="";
		personinfo[2]="";
		personinfo[3]="";
		personinfo[4]="";
		personinfo[5]="";
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            Statement statement = connection.createStatement();
	            statement.execute("USE `market`");//数据库名字
	            ResultSet resultSet = statement.executeQuery(" SELECT *FROM 员工信息表\r\n" + 
	            		"	where 姓名 ='" +employeename+"'and 商场编号=(select 商场编号 from 商场信息表 where 商场名称='"+marketname+"');\r\n");
	            while(resultSet.next()) {
	            		personinfo[0]="员工编号："+resultSet.getString(3);
	            		personinfo[1]="姓名："+resultSet.getString(4);
	            		personinfo[2]="岗位："+resultSet.getString(5);
	            		personinfo[3]="联系电话："+resultSet.getString(6);
	            		personinfo[4]="工资："+resultSet.getString(7);
	            		personinfo[5]="实习期："+resultSet.getString(8);
	            		
	            }
	            System.out.println("查询成功！");

	            
		 }catch (Exception e) {
	            e.printStackTrace();
	        }
		return personinfo;		 
	}
	public String[] marketquery(String mkname) {//商场信息查询
		String[] marketinfo=new String[10];
		marketinfo[0]="查无此商场";
		marketinfo[1]=" ";
		marketinfo[2]=" ";
		marketinfo[3]=" ";
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            Statement statement = connection.createStatement();
	            statement.execute("USE `market`");//数据库名字
	            ResultSet resultSet = statement.executeQuery(" SELECT *FROM 商场信息表\r\n" + 
	            		"	where 商场名称 ='" +mkname+"';\r\n");

	            while(resultSet.next()) {
	            		marketinfo[0]="商场编号："+resultSet.getString(1);
	            		marketinfo[1]="商场名称："+resultSet.getString(2);
	            		marketinfo[2]="地址："+resultSet.getString(3);
	            		marketinfo[3]="联系电话："+resultSet.getString(4);
	            }
	            System.out.println("查询成功！");

	            
		 }catch (Exception e) {
	            e.printStackTrace();
	        }
		return marketinfo;		 
	}
	public String[] depquery(String mkname,String depname) {//管理员部门信息查询
		String[] marketinfo=new String[10];
		marketinfo[0]="查无此部门";
		marketinfo[1]=" ";
		marketinfo[2]=" ";
		marketinfo[3]=" ";
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	   
	            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
	            statement.execute("USE `market`");//数据库名字
	            ResultSet resultSet = statement.executeQuery("select *from 部门信息表 \r\n" + 
	            		"	where (select 商场编号 from 商场信息表\r\n" + 
	            		"				where 商场名称='"+mkname+"') =部门信息表.商场编号\r\n" + 
	            		"    and 部门名称='"+depname+"';\r\n" + 
	            		"");
	            while(resultSet.next()) {
	            	marketinfo[0]="商场编号："+resultSet.getString(1);
	            		marketinfo[1]="部门编号："+resultSet.getString(2);
	            		marketinfo[2]="部门名称："+resultSet.getString(3);
	            		marketinfo[3]="位置分布："+resultSet.getString(4);
	            	marketinfo[4]="联系电话："+resultSet.getString(5);            		          
	            	}     	         
		 }catch (Exception e) {
	            e.printStackTrace();
	        }
		return marketinfo;		 
	}
	
	
	public String insertinfo(String mknum,String depnum,String empnum,String name,String job,String tel,String salary) {//插入员工信息	
		 try {
	            Class.forName(DB_DRIVER);
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);	   
	            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                     ResultSet.CONCUR_READ_ONLY);
	            statement.execute("USE `market`");//数据库名字
	            statement.execute("insert into 员工信息表 \r\n" + 
	            		"values('"+mknum+"','"+depnum+"','"+empnum+"','"+name+"','"+job+"','"+tel+"','"+salary+"',null);\r\n");       
		 }catch (Exception e) {
	            e.printStackTrace();
	            return "请将信息补全！！";
	        }
		
		return "插入成功";
	}
	public String updateinfo(String mknum,String depnum,String empnum,String name,String job,String tel,String salary) {//修改员工信息
		try {
            Class.forName(DB_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);	   
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                 ResultSet.CONCUR_READ_ONLY);
            statement.execute("USE `market`");//数据库名字
            statement.execute("UPDATE 员工信息表\r\n" + 
            		"set 姓名='"+name+"'\r\n" +",商场编号='"+mknum+"',部门编号='"+depnum+"',岗位='"+job+"',电话号码='"+tel+"',工资='"+salary+"'"+
            		"where 员工编号='"+empnum+"';\r\n" + 
            		"");
		
		
		}catch (Exception e) {
            e.printStackTrace();
            return "请将信息补全！！";
        }
		
		return "修改成功!!";
	}
	
	
	
//	测试用的主方法
//	public static void main(String[] args) {
//		Market.Sqlconnect con = new Sqlconnect();
//		con.employeequery("王时","长安商场");
//}
}