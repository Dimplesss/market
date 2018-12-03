/**
 * 
 */
package Market;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

/**
 * @author guoxi,16130120183,769747895@qq.com
 *
 * 
 */
public class manager extends JFrame {

	private JPanel contentPane;
	private JTextField marketnum;
	private JTextField workernum;
	private JTextField department;
	private JTextField telphone;
	private JTextField salary;
	private JTextField market;
	private JTextField name;
	private JTextField job;
	private JTextField departnum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public manager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		marketnum = new JTextField();
		marketnum.setColumns(10);
		marketnum.setBounds(106, 132, 100, 30);
		contentPane.add(marketnum);
		
		workernum = new JTextField();
		workernum.setColumns(10);
		workernum.setBounds(106, 172, 100, 30);
		contentPane.add(workernum);
		
		department = new JTextField();
		department.setColumns(10);
		department.setBounds(416, 212, 100, 30);
		contentPane.add(department);
		
		telphone = new JTextField();
		telphone.setColumns(10);
		telphone.setBounds(106, 292, 100, 30);
		contentPane.add(telphone);
		
		salary = new JTextField();
		salary.setColumns(10);
		salary.setBounds(416, 252, 100, 30);
		contentPane.add(salary);
		
		market = new JTextField();
		market.setColumns(10);
		market.setBounds(416, 132, 100, 30);
		contentPane.add(market);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(416, 172, 100, 30);
		contentPane.add(name);
		
		job = new JTextField();
		job.setColumns(10);
		job.setBounds(106, 252, 100, 30);
		contentPane.add(job);
		
		departnum = new JTextField();
		departnum.setColumns(10);
		departnum.setBounds(106, 212, 100, 30);
		contentPane.add(departnum);
		
		JTextArea txt = new JTextArea();//显示信息的区域
		txt.setFont(new Font("Monospaced", Font.PLAIN, 19));
		txt.setEditable(false);
		txt.setBounds(285, 299, 446, 213);
		contentPane.add(txt);
		
		JComboBox choose = new JComboBox();//选择查询（商场,部门，员工）
		choose.setModel(new DefaultComboBoxModel(new String[] {"\u5546\u573A\u4FE1\u606F", "\u90E8\u95E8\u4FE1\u606F", "\u5458\u5DE5\u4FE1\u606F"}));
		choose.setBounds(48, 91, 97, 31);
		contentPane.add(choose);
		
		
		JButton button_query = new JButton("\u67E5\u8BE2");//查询
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String market1=market.getText();
				String depart1=department.getText();
				String name1=name.getText();				
					Market.Sqlconnect con = new Sqlconnect();
					switch(choose.getSelectedItem().toString()) {
					case "商场信息":
						String[] markinfo=con.marketquery(market1);
						txt.setText(markinfo[0]+"\n"+markinfo[1]+"\n"+markinfo[2]+"\n"+markinfo[3]);
						break;
					case "部门信息":
						String[] depinfo=con.depquery(market1,depart1);
						txt.setText(depinfo[0]+"\n"+depinfo[1]+"\n"+depinfo[2]+"\n"+depinfo[3]);
						break;
					case "员工信息":
						String info[]=con.employeequery(name1,market1);
						txt.setText(info[0]+"\n"+info[1]+"\n"+info[2]+"\n"+info[3]+"\n"+info[4]+"\n"+info[5]);
					default:
						//不会选错
					}		
			}
		});
		button_query.setBounds(48, 44, 97, 23);
		contentPane.add(button_query);
		
		JButton button_add = new JButton("\u6DFB\u52A0");//添加
		button_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String marketnum1=marketnum.getText();
				String departnum1=departnum.getText();
				String name1=name.getText();
				String workernum1=workernum.getText();
				String job1=job.getText();
				String salary1=salary.getText();
				String telphone1=telphone.getText();	
				Market.Sqlconnect con = new Sqlconnect();
				String insertquery=con.insertinfo(marketnum1, departnum1, workernum1, name1, job1, telphone1, salary1);
				txt.setText(insertquery);
			}
		});
		button_add.setBounds(201, 44, 97, 23);
		contentPane.add(button_add);
		
		JButton button_mod = new JButton("\u4FEE\u6539");//修改
		button_mod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String marketnum1=marketnum.getText();
				String departnum1=departnum.getText();
				String name1=name.getText();
				String workernum1=workernum.getText();
				String job1=job.getText();
				String salary1=salary.getText();
				String telphone1=telphone.getText();
				Market.Sqlconnect con = new Sqlconnect();
				String mod=con.updateinfo(marketnum1, departnum1, workernum1, name1, job1, telphone1, salary1);
				txt.setText(mod);
				
				
			}
		});
		button_mod.setBounds(352, 44, 97, 23);
		contentPane.add(button_mod);
		
		JButton button_quit = new JButton("\u9000\u51FA");//退出
		button_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_quit.setBounds(495, 44, 97, 23);
		contentPane.add(button_quit);
		
		JLabel label_market = new JLabel("\u5546\u573A\u540D\u79F0\uFF1A");
		label_market.setBounds(336, 139, 79, 15);
		contentPane.add(label_market);
		
		JLabel label_name = new JLabel("\u59D3\u540D\uFF1A");
		label_name.setBounds(336, 179, 58, 15);
		contentPane.add(label_name);
		
		JLabel label_depart = new JLabel("\u90E8\u95E8\uFF1A");
		label_depart.setBounds(336, 219, 58, 15);
		contentPane.add(label_depart);
		
		JLabel label_tel = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_tel.setBounds(26, 299, 73, 15);
		contentPane.add(label_tel);
		
		JLabel label_workernum = new JLabel("*\u5458\u5DE5\u7F16\u53F7\uFF1A");
		label_workernum.setBounds(26, 179, 78, 15);
		contentPane.add(label_workernum);
		
		JLabel label_salary = new JLabel("\u5DE5\u8D44\uFF1A");
		label_salary.setBounds(336, 259, 58, 15);
		contentPane.add(label_salary);
		
		JLabel label_job = new JLabel("\u5C97\u4F4D\uFF1A");
		label_job.setBounds(26, 252, 58, 15);
		contentPane.add(label_job);
		
		JLabel label_marknum = new JLabel("*\u5546\u573A\u7F16\u53F7\uFF1A");
		label_marknum.setBounds(26, 139, 86, 15);
		contentPane.add(label_marknum);
		
		JLabel label_8 = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u5546\u573A\u7BA1\u7406\u7CFB\u7EDF");
		label_8.setVerticalAlignment(SwingConstants.TOP);
		label_8.setToolTipText("");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(219, 11, 196, 23);
		contentPane.add(label_8);

		
		JLabel label = new JLabel("*\u90E8\u95E8\u7F16\u53F7\uFF1A");
		label.setBounds(26, 212, 73, 15);
		contentPane.add(label);
		
		JTextPane txtpnhelp = new JTextPane();
		txtpnhelp.setText("  =======help======\r\n\u67E5\u8BE2\uFF1A\r\n\u8F93\u5165\u5546\u573A\u540D\u79F0\uFF0C\u53EF\u4EE5\u67E5\u8BE2\u5546\u573A\u4FE1\u606F\u53CA\u90E8\u95E8\u4FE1\u606F\r\n\u67E5\u8BE2\u5458\u5DE5\u4FE1\u606F\uFF0C\u9700\u8F93\u5165\u5458\u5DE5\u59D3\u540D\u53CA\u5546\u573A\u540D\u79F0\u3002\r\n\u6DFB\u52A0,\u4FEE\u6539\uFF1A\r\n\u6240\u6709\u4FE1\u606F\u4E0D\u80FD\u4E3A\u7A7A\u3002");
		txtpnhelp.setBounds(596, 91, 158, 185);
		contentPane.add(txtpnhelp);
		
		
	}
}
