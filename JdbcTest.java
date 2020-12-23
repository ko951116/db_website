package test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class JdbdTest extends Frame implements ActionListener{

	TextArea display;
	TextField ID, name, birth, phone;
	TextField change_ID, change_name, change_birth, change_phone;
	TextField rend_return_ID, rend_or_return, rend_return_CD, rend_return_date;
	TextField charge_ID, charge_money, charge_date, charge_person;
	TextField check_ID;
	TextField check_ITdept;
	TextField delete_ID;
	TextField print_ID;

	Button mem_add, mem_change, rend_return, charge;
	Button check, check_IT, delete, print;
	
	Connection conn;
	Statement stat;
	int cmd;
	
	public JdbdTest() {
		super("ITdept");
		setLayout(new BorderLayout());

		display = new TextArea();
		display.setEditable(false);

		////////메뉴/////////////////////////
		Panel title = new Panel();
		title.add(new Label("*회원가입*"));
		Font f1 = new Font("굴림", Font.BOLD, 15);
		title.setFont(f1);
		title.setForeground(Color.pink);
		Panel title2 = new Panel();
		title2.add(new Label("*회원정보수정*"));
		title2.setFont(f1);
		title2.setForeground(Color.pink);
		Panel title3 = new Panel();
		title3.add(new Label("*CD대여/반납하기*"));
		title3.setFont(f1);
		title3.setForeground(Color.blue);
		Panel title4 = new Panel();
		title4.add(new Label("*프린트비 잔액충전*"));
		title4.setFont(f1);
		title4.setForeground(Color.magenta);
		Panel title5 = new Panel();
		title5.add(new Label("*자신의 CD대여/반납 내역확인*"));
		title5.setFont(f1);
		title5.setForeground(Color.red);
		Panel title6 = new Panel();
		title6.add(new Label("*과사무실의 CD별 대여상태 확인*"));
		title6.setFont(f1);
		title6.setForeground(Color.red);
		Panel title7 = new Panel();
		title7.add(new Label("*자신의 CD대여/반납 내역 지우기*"));
		title7.setFont(f1);
		title7.setForeground(Color.red);
		Panel title8 = new Panel();
		title8.add(new Label("*프린트비 충전내역 확인하기*"));
		title8.setFont(f1);
		title8.setForeground(Color.red);


		///////////////라벨
		Panel p_ID = new Panel();
		p_ID.add(new Label("학번"));
		p_ID.add(ID = new TextField(10));
		Panel p_name = new Panel();
		p_name.add(new Label("이 름"));
		p_name.add(name = new TextField(10));
		Panel p_birth = new Panel();
		p_birth.add(new Label("생년월일"));
		p_birth.add(birth = new TextField(10));
		Panel p_phone = new Panel();
		p_phone.add(new Label("휴대폰 번호"));
		p_phone.add(phone = new TextField(11));
		////////////////////////////////////////////////////////
		Panel p_change_ID = new Panel();
		p_change_ID.add(new Label("학번"));
		p_change_ID.add(change_ID = new TextField(10));
		Panel p_change_name = new Panel();
		p_change_name.add(new Label("이 름"));
		p_change_name.add(change_name = new TextField(10));
		Panel p_change_birth = new Panel();
		p_change_birth.add(new Label("생년월일"));
		p_change_birth.add(change_birth = new TextField(10));
		Panel p_change_phone = new Panel();
		p_change_phone.add(new Label("휴대폰 번호"));
		p_change_phone.add(change_phone = new TextField(11));
/////////////////////////////////////////////////////////////////
		Panel p_rend_return_ID = new Panel();
		p_rend_return_ID.add(new Label("학번"));
		p_rend_return_ID.add(rend_return_ID= new TextField(10));
		Panel p_rend_or_return = new Panel();
		p_rend_or_return.add(new Label("대여/반납"));
		p_rend_or_return.add(rend_or_return= new TextField(10));
		Panel p_rend_return_CD = new Panel();
		p_rend_return_CD.add(new Label("CD종류"));
		p_rend_return_CD.add(rend_return_CD = new TextField(10));
		Panel p_rend_day = new Panel();
		p_rend_day.add(new Label("날짜"));
		p_rend_day.add(rend_return_date = new TextField(10));

/////////////////////////////////////////////////////////////////
		Panel p_charge_ID = new Panel();
		p_charge_ID.add(new Label("학번"));
		p_charge_ID.add(charge_ID = new TextField(10));
		Panel p_charge_money = new Panel();
		p_charge_money.add(new Label("충전할 금액"));
		p_charge_money.add(charge_money = new TextField(10));
		Panel p_charge_day = new Panel();
		p_charge_day.add(new Label("충전 일자"));
		p_charge_day.add(charge_date = new TextField(10));
		Panel p_charge_person = new Panel();
		p_charge_person.add(new Label("충전하는 사람"));
		p_charge_person.add(charge_person = new TextField(10));
	////////////////////////////////////////////////////////////////
		
		//////////////////////////////////////////////////////////
		Panel p_check_ID = new Panel();
		p_check_ID.add(new Label("학번"));
		p_check_ID.add(check_ID = new TextField(10));
		
		Panel p_check_ITdept = new Panel();
		p_check_ITdept.add(new Label("CD이름"));
		p_check_ITdept.add(check_ITdept = new TextField(10));
		
		Panel p_delete_ID = new Panel();
		p_delete_ID.add(new Label("학번"));
		p_delete_ID.add(delete_ID = new TextField(10));
		
		Panel p_print_ID = new Panel();
		p_print_ID.add(new Label("학번"));
		p_print_ID.add(print_ID = new TextField(10));
		
		////////////////////// 왼쪽//////////////////////
		Panel left = new Panel();
		left.setLayout(new GridLayout(24, 1));
		
		left.add(title);
		left.add(p_ID);
		left.add(p_name);
		left.add(p_birth);
		left.add(p_phone);
		left.add(mem_add = new Button("회원가입"));
		mem_add.addActionListener(this);
		
		left.add(title2);
		left.add(p_change_ID);
		left.add(p_change_name);
		left.add(p_change_birth);
		left.add(p_change_phone);
		left.add(mem_change = new Button("회원정보수정"));
		mem_change.addActionListener(this);
	
		left.add(title3);
		left.add(p_rend_return_ID);
		left.add(p_rend_or_return);
		left.add(p_rend_return_CD);
		left.add(p_rend_day);
		left.add(rend_return = new Button("확인"));
		rend_return.addActionListener(this);
		
		left.add(title4);
		left.add(p_charge_ID);
		left.add(p_charge_money);
		left.add(p_charge_day);
		left.add(p_charge_person);
		left.add(charge = new Button("충전"));
		charge.addActionListener(this);
		
		/////////////////////오른쪽에 붙이기////////////////////////////
		Panel right = new Panel();
		right.setLayout(new GridLayout(18, 1));
		
		right.add(title5);
		right.add(p_check_ID);
		right.add(check= new Button("확인"));
		check.addActionListener(this);
		
		right.add(title6);
		right.add(p_check_ITdept);
		right.add(check_IT= new Button("확인"));
		check_IT.addActionListener(this);
		
		right.add(title7);
		right.add(p_delete_ID);
		right.add(delete= new Button("확인"));
		delete.addActionListener(this);
		
		right.add(title8);
		right.add(p_print_ID);
		right.add(print= new Button("확인"));
		print.addActionListener(this);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				destroy();
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		add("Center", display);
		add("West", left);
		add("East", right);
		dbcon();
	}
	
	private void dbcon() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/itdept";
			conn = DriverManager.getConnection(url, "root", "wldbs4838ko12dbs");
			stat = conn.createStatement();
			System.out.println("접속 성공!!!!.");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
		
	private void destroy() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (stat != null) {
				stat.close();
			}
		} catch (Exception ex) {}
	}
		
	public void remove() {
		ID.setText("");
		name.setText("");
		birth.setText("");
		phone.setText("");
			
		change_ID.setText("");
		change_name.setText("");
		change_birth.setText("");
		change_phone.setText("");
			
		rend_return_ID.setText("");
		rend_or_return.setText("");
		rend_return_CD.setText("");
		rend_return_date.setText("");

		charge_ID.setText("");
		charge_money.setText("");
		charge_date.setText("");
		charge_person.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		ResultSet rs = null;
		Component c = (Component) e.getSource();
		try {
			if (c == mem_add) {
				String s_ID = ID.getText().trim();
				String s_name = name.getText().trim();
				String s_birth = birth.getText().trim();
				String s_phone = phone.getText().trim();
				
				if (s_ID == null 
						|| s_name == null
						|| s_birth == null 
						|| s_phone == null
						|| s_ID.length() == 0
						|| s_name.length() == 0 
						|| s_birth.length() == 0
						|| s_phone.length() == 0)
					return;
				
				String sql = "insert into member values(?,?,?,?)";
				
				java.sql.PreparedStatement stat = conn
								.prepareStatement(sql);
				stat.setString(1, s_ID);
				stat.setString(2, s_name);
				stat.setString(3, s_birth);
				stat.setString(4, s_phone);
				stat.executeUpdate();
				
				JOptionPane.showMessageDialog(getParent(), "회원가입 되었습니다!");						
				
				remove();
			}
			else if (c == mem_change) {
				String s_change_ID = change_ID.getText().trim();
				String s_change_name = change_name.getText().trim();
				String s_change_birth = change_birth.getText().trim();
				String s_change_phone = change_phone.getText().trim();
				
				if (s_change_ID == null
						|| s_change_name == null
						|| s_change_birth == null 
						|| s_change_phone == null
						|| s_change_ID.length() == 0
						|| s_change_name.length() == 0 
						|| s_change_birth.length() == 0
						|| s_change_phone.length() == 0)
					return;
				
				String sql = "update member set ID=?, name=?,birth=?, phone=? where ID='"+s_change_ID+"'";
				
				java.sql.PreparedStatement stat = conn
						.prepareStatement(sql);
				stat.setString(1, s_change_ID);
				stat.setString(2, s_change_name);
				stat.setString(3, s_change_birth);
				stat.setString(4, s_change_phone);
				stat.executeUpdate();
				
				JOptionPane.showMessageDialog(getParent(), "회원정보가 수정되었습니다!");						
				
				remove();
				
			} 
			else if (c == rend_return) {
				String s_rend_return_ID = rend_return_ID.getText().trim();
				String s_rend_or_return = rend_or_return.getText().trim();
				String s_rend_return_CD = rend_return_CD.getText().trim();
				String s_rend_return_date = rend_return_date.getText().trim();
		
				if (s_rend_return_ID == null 
						|| s_rend_or_return == null 
						|| s_rend_return_CD == null
						|| s_rend_return_date == null
						|| s_rend_return_ID.length() == 0 
						|| s_rend_or_return.length() == 0 
						|| s_rend_return_CD.length() == 0
						|| s_rend_return_date.length() == 0)
					return;
				String sql = "insert into rend_return values (?,?, ?, ?)";
				
				java.sql.PreparedStatement stat = conn
						.prepareStatement(sql);
				stat.setString(1, s_rend_return_ID);
				stat.setString(2, s_rend_or_return);
				stat.setString(3, s_rend_return_CD);
				stat.setString(4, s_rend_return_date);
				stat.executeUpdate();
				
				JOptionPane.showMessageDialog(getParent(), "확인 되었습니다!");						
				
				remove();
				
			} 
			else if (c == charge) {
				String s_charge_ID = charge_ID.getText().trim();
				String s_charge_money = charge_money.getText().trim();
				String s_charge_date = charge_date.getText().trim();
				String s_charge_person = charge_person.getText().trim();
				
				if (s_charge_ID == null 
						|| s_charge_money == null 
						|| s_charge_date == null 
						|| s_charge_person == null
						|| s_charge_ID.length() == 0 
						|| s_charge_money.length() == 0 
						|| s_charge_date.length() == 0 
						|| s_charge_person.length() == 0)
					return;
				
				String sql = "insert into print values (?, ?,?,?)";
				
				java.sql.PreparedStatement stat = conn
						.prepareStatement(sql);
				stat.setString(1, s_charge_ID);
				stat.setString(2, s_charge_money);
				stat.setString(3, s_charge_date);
				stat.setString(4, s_charge_person);
						
				JOptionPane.showMessageDialog(getParent(), "잔액이 충전되었습니다!");						
	
				stat.executeUpdate();
		
				remove();
				
			}
			else if (c == check) {
				String s_rend_return_ID = check_ID.getText().trim();
				
				if (s_rend_return_ID == null || s_rend_return_ID.length() == 0)
					return;
				
				rs = stat.executeQuery("select member.id, name, phone, rend_or_return, CDlist, day from member, rend_return where member.id=rend_return.id and member.id='"+s_rend_return_ID+"'");
				display.append("-------------------------------------------------------------------------------------------------------------------------------------------"+ "\n");
				display.append("학번\t\t|   이름\t\t\t|  전화번호\t\t|   대여/반납\t|  CD list\t\t\t|   date\t  \n");
				display.append("-------------------------------------------------------------------------------------------------------------------------------------------"+ "\n");
				
				while (rs.next()) {
					String mem_ID = rs.getString(1);
					String mem_name = rs.getString(2);
					String mem_phone = rs.getString(3);
					String rend_or_return = rs.getString(4);
					String rend_return_CD = rs.getString(5);
					String rend_return_date = rs.getString(6);
					
					display.append(mem_ID + "\t\t" + mem_name+ "\t\t" + mem_phone+ "\t\t" +rend_or_return + "\t\t"
					+ rend_return_CD + "\t\t" + rend_return_date  +"\n\n");
					System.out.println(mem_ID + "\t\t" + mem_name+ "\t\t" + mem_phone+ "\t\t" +rend_or_return + "\t\t"
					+ rend_return_CD + "\t\t" + rend_return_date + "\n\n");
				}
				remove();
				
			}
			else if (c == check_IT) {
				String s_check_ITdept = check_ITdept.getText().trim();
				
				if (s_check_ITdept == null || s_check_ITdept.length() == 0)
					return;
				
				rs = stat.executeQuery("select * from now_cd where CDLIST='"+s_check_ITdept+"'");
				display.append("-------------------------------------------------------------------------------------------------------------------------------------------"+ "\n");
				display.append(" CD\t\t\t|   학번\t\t|  대여/반납\t|   date\t  \n");
				display.append("-------------------------------------------------------------------------------------------------------------------------------------------"+ "\n");
				while (rs.next()) {
					String rend_return_CD = rs.getString(1);
					String rend_return_ID = rs.getString(2);
					String rend_or_return = rs.getString(3);
					String rend_return_date = rs.getString(4);
					
					display.append(rend_return_CD + "\t\t" + rend_return_ID + " \t\t"+ rend_or_return + " \t\t" + rend_return_date  +"\n\n");
					System.out.println(rend_return_CD + "\t\t" + rend_return_ID + "\t\t"+ rend_or_return + "\t\t" + rend_return_date + "\n\n");
					}
				remove();
				
			}
			else if (c == delete) {
				String s_delete_ID = delete_ID.getText().trim();
				if (s_delete_ID == null || s_delete_ID.length() == 0)
					return;
				
				JOptionPane.showMessageDialog(getParent(), "회원님의 대여/반납 내역이 삭제되었습니다!"+"\n"+"자신의 CD대여/반납상태 메뉴에서 확인해보세요.");						
				stat.executeUpdate("delete from rend_return where ID='"+ s_delete_ID + "'");
				remove();
				
			}
			else if (c == print) {
				String s_print_ID = print_ID.getText().trim();
				
				if (s_print_ID == null || s_print_ID.length() == 0)
					return;
				rs = stat.executeQuery("select member.id, name, phone, money, c_date, from_who from member, print where member.id=print.id and member.id='"+s_print_ID+"'");
				display.append("-------------------------------------------------------------------------------------------------------------------------------------------"+ "\n");
				display.append("학번\t\t|   이름\t\t\t|  전화번호\t\t|  충전금액\t| 충전일자\t\t| 충전경로\t \n");
				display.append("-------------------------------------------------------------------------------------------------------------------------------------------"+ "\n");
				while (rs.next()) {
					String mem_ID = rs.getString(1);
					String mem_name = rs.getString(2);
					String mem_phone = rs.getString(3);
					String print_money = rs.getString(4);
					String print_date = rs.getString(5);
					String print_person = rs.getString(6);
					
					display.append(mem_ID + "\t\t" + mem_name + "\t\t"+ mem_phone + "\t\t"
					+ print_money + "\t\t"+ print_date + "\t\t" + print_person  +"\n\n");
					System.out.println(mem_ID + "\t\t" + mem_name + "\t\t"+ mem_phone + "\t\t" 
					+ print_money + "\t\t"+ print_date + "\t\t" + print_person + "\n\n");
					}
				remove();
			}
		}
		catch (Exception ex) {}
		return;
		}
	public static void main(String args[]) {
		JdbdTest itdept = new JdbdTest();
		itdept.setSize(1350, 800);
		itdept.setVisible(true);
		}
	}


