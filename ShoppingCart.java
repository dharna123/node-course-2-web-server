package com.java.collect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ShoppingCart implements ActionListener {
	
	

JFrame f,f1,f2,f3,f4,f5;
JLabel l1,l2,l3;
JTextField tf1,tf2,tf3,tf4,tf5;
JButton b,b1,insert_btn,login_btn,shop_cart;
JTable item_table;
DefaultTableModel tableModel = new DefaultTableModel();
String cols[]= {"ID","Name","Price"};
String data[][]=new String[5][3];
JPanel panel;

public static Connection getConnection() throws ClassNotFoundException, SQLException
{
	String dbDriver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/";
	String dbname="newdb";
	String username="root";
	String password="";
	Class.forName(dbDriver);
	Connection con=DriverManager.getConnection(url+dbname,username,password);
	return con;
	
}

void home_screen()
{
	b.setBounds(50,140,200,30);
	b.addActionListener(this);

	b1.setBounds(50,180,200,30);
	
	
	//f.add(b1);
	f.add(b);
	f.add(b1);
	f.setSize(400,400);
	f.setLayout(null);
	f.setVisible(true);
	
}

void signup_screen()
{
	f1=new JFrame();
	tf1=new JTextField();
	tf1.setBounds(50,100,200,30);
	
	tf2=new JTextField();
	tf2.setBounds(50,140,200,30);
	insert_btn=new JButton("Create Account");
	insert_btn.setBounds(50,180,200,30);
	insert_btn.addActionListener(this);
	
	f1.add(tf1);
	f1.add(tf2);
	f1.add(insert_btn);
	f1.setSize(400,400);
	f1.setLayout(new BorderLayout());
	f1.setVisible(true);
	
	
	
}

void insert() throws ClassNotFoundException, SQLException
{
	int status=0;
	String s1=tf1.getText();
	String s2=tf2.getText();
	
	Connection con=ShoppingCart.getConnection();
	PreparedStatement ps=con.prepareStatement("insert into users(email,password)values(?,?)");
	ps.setString(1, s1);
	ps.setString(2, s2);
	status=ps.executeUpdate();
	if(status>0)
	{
		JOptionPane.showMessageDialog(insert_btn, "Account Created");
		home_screen();
		f1.dispose();
	}else {
		JOptionPane.showMessageDialog(insert_btn, "Account Not Created");
	}
}

boolean getSingleRecord() throws ClassNotFoundException, SQLException
{
	boolean flag=false;
	
	String s1=tf1.getText();
	String s2=tf2.getText();
	Connection con=ShoppingCart.getConnection();
	PreparedStatement ps=con.prepareStatement("select email,password from users where email=? and password=?");
	ps.setString(1, s1);
	ps.setString(2, s2);
	ResultSet rs=ps.executeQuery();
	while(rs.next())
	{
		flag=true;
	}
	return flag;
	
}


void login_win()
{
	f.dispose();
	f2=new JFrame();
	tf1=new JTextField();
	tf1.setBounds(50,100,200,30);
	
	tf2=new JTextField();
	tf2.setBounds(50,140,200,30);
	login_btn=new JButton("Login");
	login_btn.setBounds(50,180,200,30);
	login_btn.addActionListener(this);
	
	f2.add(tf1);
	f2.add(tf2);
	f2.add(login_btn);
	f2.setSize(400,400);
	f2.setLayout(new BorderLayout());
	f2.setVisible(true);
}

void login() throws ClassNotFoundException, SQLException
{

	String s1=tf1.getText();
	String s2=tf2.getText();
	Connection con=ShoppingCart.getConnection();
	PreparedStatement ps=con.prepareStatement("select email,password from users where email=? and password=?");
	ps.setString(1, s1);
	ps.setString(2, s2);
	ResultSet rs=ps.executeQuery();
	if(rs.next())
	{
		JOptionPane.showMessageDialog(login_btn, "Success");
		dashboard();
		
	}else {
		JOptionPane.showMessageDialog(login_btn, "Failed");
	}
	

}

void dashboard()
{
	
	f3=new JFrame();
	shop_cart=new JButton("Buy Items");
	shop_cart.setBounds(50,180,200,30);
	shop_cart.addActionListener(this);
	
	f3.add(shop_cart);
	f3.setLayout(null);
	f3.setVisible(true);
	
	f3.setSize(400,400);
	}

void get_items() throws ClassNotFoundException, SQLException
{
	f4=new JFrame();
	
	item_table=new JTable(data,cols);
	item_table.setDefaultEditor(Object.class, null);
	item_table.setBounds(60,400,200,300);
	Connection con=ShoppingCart.getConnection();
	PreparedStatement ps=con.prepareStatement("select * from items");
	ResultSet rs=ps.executeQuery();
	int i=0;
	while(rs.next())
	{
		for(int j=0;j<3;j++)
		{
			data[i][j]=rs.getString(j+1);
		}
		i=i+1;
	}
	f4.add(item_table);
	f4.setSize(400,400);
	f4.setVisible(true);
	
	item_table.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			int row=item_table.getSelectedRow();
			fetch_items();
			String value1=item_table.getModel().getValueAt(row, 0).toString();
			String value2=item_table.getModel().getValueAt(row, 1).toString();
			String value3=item_table.getModel().getValueAt(row, 2).toString();
			
			
			tf3.setText(value1);
			tf3.setEditable(false);
			tf4.setText(value2);
			tf4.setEditable(false);
			tf5.setText(value3);
			
			tf5.setEditable(false);
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	});





		
		

	
		

	
	
}

void fetch_items()
{
	
	f5=new JFrame();
	tf3=new JTextField();
	//tf3.setEditable(false);
	tf3.setBounds(50,50,100,30);
	//tf4.setEditable(false);
	tf4=new JTextField();
	tf4.setBounds(50,80,100,30);
	tf5=new JTextField();
	//tf5.setEditable(false);
	tf5.setBounds(50,100,100,30);
f5.add(tf3);
	f5.add(tf4);
	f5.add(tf5);
	
	f5.setLayout(null);
	f5.setVisible(true);
	f5.setSize(400,400);
}
	
	

void selectData()
{
	
}
ShoppingCart()
{
	
	f=new JFrame("My Cart");
	JLabel l=new JLabel("Welcome to Shopping Cart");
	 b=new JButton("Sign Up");
	 b1=new JButton("Sign In");
	b1.addActionListener(this);
	
	
	
	
	
	
	
	
	
	
	
}


@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==b)
	{
		f.dispose();
		signup_screen();
		
	}
	if(e.getSource()==insert_btn)
	{
		try
		{
		boolean result=getSingleRecord();
		if(result==true)
		{
			JOptionPane.showMessageDialog(insert_btn, "Record Already exists");
			tf1.setText("");
			tf2.setText("");
			
		}else {
			insert();
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	if(e.getSource()==b1)
	{
		
			login_win();
		
			
		
	}
	if(e.getSource()==login_btn)
	{
		try {
			login();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	if(e.getSource()==shop_cart)
	{
		try {
			get_items();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}

public static void main(String args[])
{
	ShoppingCart sc=new ShoppingCart();
	sc.home_screen();
}

}