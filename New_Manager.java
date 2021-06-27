import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class New_Manager extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4,l5,l6,l7;
    JComboBox t1,t2;
    JTextField t3,t4,t5,t6,t7;
    JButton b1,b2;
    Choice c1;
    New_Manager()
    {
	  super("New Manager");
      
      setSize(600,650);
      setLocation(600,200);
      getContentPane().setBackground(Color.WHITE);
      
      JPanel p1= new JPanel();
      p1.setBackground(Color.WHITE);
    
      p1.setLayout(new GridLayout(9,2,10,40));
      l1 = new JLabel("Employee Name");
      t1 = new JComboBox();
      
      try {
    	  String q="select * from employee";
          conn c1=new conn();
          ResultSet rs=c1.s.executeQuery(q);
          while(rs.next()){
              // i = 0 j = 0
             
            t1.addItem(rs.getString("name"));
             
          }
          
          
    	  
      }
      
      catch(Exception e) { }
      p1.add(l1);
      p1.add(t1);
     
      
     
      l2 = new JLabel("Dept");
      t2 = new JComboBox();
      
      try {
    	  String q="select * from dept";
          conn c1=new conn();
          ResultSet rs=c1.s.executeQuery(q);
          while(rs.next()){
              // i = 0 j = 0
             
            t2.addItem(rs.getString("dname"));
             
          }
          
          
    	  
      }
      
      catch(Exception e) { }
      
      
      p1.add(l2);
      p1.add(t2);
      
      b1 =new JButton("Submit");
      b2 = new JButton("Cancel");
      p1.add(b1);
      p1.add(b2);
     
      setLayout(new BorderLayout());
      add(new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/new_employee.png"))),"West");
      add(p1,"Center");
     
      b1.addActionListener(this);
      b1.setBackground(Color.BLACK);
      b1.setForeground(Color.WHITE);
      
      b2.addActionListener(this);
      b2.setBackground(Color.BLACK);
      b2.setForeground(Color.WHITE);
}
    
    public void actionPerformed(ActionEvent ae){
    	 String msg= ae.getActionCommand();
    	 if(msg=="Cancel")
    		 return;
    	String ename=t1.getSelectedItem().toString();
    	String dname=t2.getSelectedItem().toString();
    	int eid=-1,did=-1;
    	try {
      	  String q="select * from employee where name='"+ename+"'";
      	  System.out.println(q);
            conn c1=new conn();
            ResultSet rs=c1.s.executeQuery(q);
            if(rs.next()){
                // i = 0 j = 0
               
              eid=rs.getInt(1);
               
            }
            
            q="select * from dept where dname='"+dname+"'";
            //conn c1=new conn();
            rs=c1.s.executeQuery(q);
            if(rs.next()){
                // i = 0 j = 0
               
              did=rs.getInt(1);
              
              
               
            }
            
            
      	  
        }
        
        catch(Exception e) {e.printStackTrace(); }
    	
    	String qry = "update dept set mgr="+eid+" where did="+did;
    	System.out.println(qry);
        
        try{
            conn c1 = new conn();
            c1.s.executeUpdate(qry);
            JOptionPane.showMessageDialog(null,"Manager Assigned");
        }catch(Exception ee){
            ee.printStackTrace();
        }
        
        /*String n = t1.getText();
        String g = c1.getSelectedItem();
        String a = t3.getText();
        String s = t4.getText();
        String c = t5.getText();
        String e = t6.getText();
        String p = t7.getText();
        String qry = "insert into employee values(null,'"+n+"','"+g+"','"+a+"','"+s+"','"+c+"','"+e+"','"+p+"')";
       
        try{
            conn c1 = new conn();
            c1.s.executeUpdate(qry);
            JOptionPane.showMessageDialog(null,"Employee Created");
            this.setVisible(false);  
        }catch(Exception ee){
            ee.printStackTrace();
        }*/
    }
        
        public static void main(String s[]){
            new New_Manager().setVisible(true);
        }
    }
