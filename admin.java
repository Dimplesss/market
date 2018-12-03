/**
 * 
 */
package Market;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author guoxi,16130120183,769747895@qq.com
 *
 * 
 */
public class admin {

	private JFrame admin;
	private JTextField username;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin window = new admin();
					window.admin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		admin = new JFrame();
		admin.setTitle("\u5546\u573A\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
		admin.setBounds(100, 100, 664, 501);
		admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("    \u6B22\u8FCE\u4F7F\u7528\u5546\u573A\u7BA1\u7406\u7CFB\u7EDF");
		label.setBounds(207, 88, 189, 37);
		admin.getContentPane().add(label);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(211, 149, 122, 37);
		admin.getContentPane().add(username);
		
		JLabel label_user = new JLabel("    \u7528\u6237");
		label_user.setBounds(113, 149, 89, 37);
		admin.getContentPane().add(label_user);
		
		JLabel label_password = new JLabel("   \u5BC6\u7801");
		label_password.setBounds(123, 230, 58, 15);
		admin.getContentPane().add(label_password);
		
		JButton button_manager = new JButton(" \u7BA1\u7406\u5458\u767B\u5F55");//管理员登录
		button_manager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=username.getText();
				String password=new String(passwordField.getPassword());
				if(name.equals(""))//弹出警告窗口
				{//用户名不能为空
					JOptionPane.showMessageDialog(null, "用户名不能为空", "警告", JOptionPane.PLAIN_MESSAGE);
					System.out.println("error");
				}else if(password.equals("")){//密码不能为空
					JOptionPane.showMessageDialog(null, "密码不能为空", "警告", JOptionPane.PLAIN_MESSAGE);
					System.out.println("error");
				}else
				{
					Market.Sqlconnect con = new Sqlconnect();
					int count=con.connect(name,password);//连接数据库
					if(count==-1)
					{//如果在表中找不到对应的用户，提示出错
						JOptionPane.showMessageDialog(null, "用户名或密码错误", "警告", JOptionPane.PLAIN_MESSAGE);
					}else
					{//在登录成功后关闭登录界面
						admin.setVisible(false);
					}
				}

			}
		});
		button_manager.setBounds(113, 294, 122, 37);
		admin.getContentPane().add(button_manager);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(211, 230, 122, 37);
		admin.getContentPane().add(passwordField);
		
		JButton button_worker = new JButton(" \u5458\u5DE5\u767B\u5F55");//员工登录
		button_worker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=username.getText();
				String password=new String(passwordField.getPassword());
				if(name.equals(""))//弹出警告窗口
				{//用户名不能为空
					JOptionPane.showMessageDialog(null, "用户名不能为空", "警告", JOptionPane.PLAIN_MESSAGE);
					System.out.println("error");
				}else if(password.equals("")){//密码不能为空
					JOptionPane.showMessageDialog(null, "密码不能为空", "警告", JOptionPane.PLAIN_MESSAGE);
					System.out.println("error");
				}else
				{
				Market.Sqlconnect con = new Sqlconnect();
				int count=con.empolyeelogin(name,password);	//连接数据库
				if(count==-1)
				{//如果在表中找不到对应的用户，提示出错
					JOptionPane.showMessageDialog(null, "用户名或密码错误", "警告", JOptionPane.PLAIN_MESSAGE);
				}else
				{//在登录成功后关闭登录界面
					admin.setVisible(false);
				}
				}
			}
		});
		button_worker.setBounds(347, 294, 122, 37);
		admin.getContentPane().add(button_worker);
	}
}
