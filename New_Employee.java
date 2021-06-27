import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class New_Employee extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JComboBox t8;
    JButton b1,b2;
    Choice c1;
    
    New_Employee(){
        
        super("New Employee");
        
        setSize(600,650);
        setLocation(600,200);
        getContentPane().setBackground(Color.WHITE);
        
        JPanel p1= new JPanel();
        p1.setBackground(Color.WHITE);
      
        p1.setLayout(new GridLayout(9,2,10,40));
        l1 = new JLabel("Name");
        t1 = new JTextField(15);
        p1.add(l1);
        p1.add(t1);
       
        c1 = new Choice();
        c1.add("Male");
        c1.add("Female");
       
        l2 = new JLabel("Gender");
        p1.add(l2);
        p1.add(c1);
        l3 = new JLabel("Address");
        t3 = new JTextField(15);
        p1.add(l3);
        p1.add(t3);
        l4 = new JLabel("State");
        t4 = new JTextField(15);
        p1.add(l4);
        p1.add(t4); 
        l5 = new JLabel("City");
        t5 = new JTextField(15);
        p1.add(l5);
        p1.add(t5);
        l6 = new JLabel("Email");
        t6 = new JTextField(15);
        p1.add(l6);
        p1.add(t6);
        l7 = new JLabel("Phone");
        t7= new JTextField(15);
        p1.add(l7);
        p1.add(t7);
        
        l8 = new JLabel("Dept");
        t8= new JComboBox();
        
        try {
      	  String q="select * from dept";
            conn c1=new conn();
            ResultSet rs=c1.s.executeQuery(q);
            while(rs.next()){
                // i = 0 j = 0
               
              t8.addItem(rs.getString("dname"));
               
            }
            
            
      	  
        }
        
        catch(Exception e) { }
        
        
        p1.add(l8);
        p1.add(t8);
        
        b1 =new JButton("Add");
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
       
        String n = t1.getText();
        String g = c1.getSelectedItem();
        String a = t3.getText();
        String s = t4.getText();
        String c = t5.getText();
        String e = t6.getText();
        String p = t7.getText();
        String dname=t8.getSelectedItem().toString();
        int did=-1;
        try {
        	  String q="select * from dept where dname='"+dname+"'";
        	  System.out.println(q);
              conn c1=new conn();
              ResultSet rs=c1.s.executeQuery(q);
              if(rs.next()){
                  // i = 0 j = 0
                 
                did=rs.getInt(1);
                 
              }
              
           
              
              
        	  
          }
          
          catch(Exception ee) {ee.printStackTrace(); }
      	
        		String qry = "insert into employee values(null,'"+n+"','"+g+"','"+a+"','"+s+"','"+c+"','"+e+"','"+p+"',"+did+")";
       
        try{
            conn c1 = new conn();
            c1.s.executeUpdate(qry);
            JOptionPane.showMessageDialog(null,"Employee Created");
            this.setVisible(false);  
        }catch(Exception ee){
            ee.printStackTrace();
        }
    }
    public static void main(String s[]){
        new New_Employee().setVisible(true);
    }
}
