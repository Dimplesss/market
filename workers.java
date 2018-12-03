/**
 * 
 */
package Market;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

/**
 * @author guoxi,16130120183,769747895@qq.com
 *
 * 完成所有按钮的事件监听（2018/12/1）
 * 
 * 
 */
public class workers extends JFrame {

	private JPanel contentPane;
	private JTextField textField_marketname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					workers frame = new workers("HH0001");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public workers(String employeeid) {//获取员工编号，用于查询员工个人信息
		setTitle("\u5546\u573A\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u4FE1\u606F\u67E5\u8BE2");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(204, 49, 266, 38);
		contentPane.add(label);
		
		JTextArea textArea = new JTextArea();//显示信息区域
		textArea.setEditable(false);
		textArea.setBounds(82, 264, 583, 156);
		contentPane.add(textArea);
		JButton button_personinfo = new JButton("\u4E2A\u4EBA\u4FE1\u606F\u67E5\u8BE2");//个人信息查询
		button_personinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//个人信息查询的实现
				Market.Sqlconnect con = new Sqlconnect();
				String[] info=con.personquery(employeeid);//获取存储个人信息的数组
				textArea.setText(info[0]+"\n"+info[1]+"\n"+info[2]+"\n"+info[3]+"\n"+info[4]+"\n"+info[5]);
			}
		});
		button_personinfo.setBounds(309, 191, 156, 38);
		contentPane.add(button_personinfo);
		
		JButton button_1 = new JButton("New button");
		button_1.setBounds(175, 383, 48, -14);
		contentPane.add(button_1);
		
		JButton button_markinfo = new JButton("\u5546\u573A\u4FE1\u606F\u67E5\u8BE2");//商场信息查询
		button_markinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String markname=textField_marketname.getText();
				Market.Sqlconnect con = new Sqlconnect();
				String[] info=con.marketquery(markname);//获取存储商场信息的数组
				textArea.setText(info[0]+"\n"+info[1]+"\n"+info[2]+"\n"+info[3]);
			}
		});
		button_markinfo.setBounds(97, 191, 170, 38);
		contentPane.add(button_markinfo);
		
		JLabel label_marketname = new JLabel("\u5546\u573A\u540D\u79F0\uFF1A");
		label_marketname.setBounds(45, 127, 92, 15);
		contentPane.add(label_marketname);
		
		textField_marketname = new JTextField();
		textField_marketname.setColumns(10);
		textField_marketname.setBounds(145, 128, 100, 30);
		contentPane.add(textField_marketname);
		
		
		
		JButton button_quit = new JButton("\u9000\u51FA");//退出
		button_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_quit.setBounds(518, 191, 125, 38);
		contentPane.add(button_quit);
		
		JTextPane txtpnhelp = new JTextPane();
		txtpnhelp.setEditable(false);
		txtpnhelp.setText("   =====help=====\r\n\u8F93\u5165\u5546\u573A\u540D\u79F0\u67E5\u8BE2\u5546\u573A\u4FE1\u606F\u3002\r\n\u70B9\u51FB\u4E2A\u4EBA\u4FE1\u606F\u67E5\u8BE2\u53EF\u67E5\u8BE2\u4E2A\u4EBA\u4FE1\u606F\u3002");
		txtpnhelp.setBounds(451, 49, 136, 125);
		contentPane.add(txtpnhelp);
	}

}
