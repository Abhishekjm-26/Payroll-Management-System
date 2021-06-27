import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Update_dept extends JFrame implements ActionListener,ItemListener{
    JLabel l1,l2,l3,l4,l5,l6,l7,emp;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JButton b1,b2;
    Choice c1,c2;
    
    Update_dept(){
        
        super("Update Dept");
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        c2 = new Choice();
        c2.setBounds(160,40,200,20);
       
        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from dept");
      
            while(rs.next()){
                c2.add(rs.getString("did"));    
            }
        }catch(Exception e){ }
       
        emp = new JLabel("Select Dept ID");
        emp.setBounds(40,40,100,20);
        add(emp);
        add(c2);
        
        l1 = new JLabel("Name : ");
        t1 = new JTextField(15);
        
        l1.setBounds(40,80,100,20);
        t1.setBounds(160,80,200,20);
        add(l1);
        add(t1);
       
        l2 = new JLabel("Start Date : ");
        t2 = new JTextField(15);
        
        l2.setBounds(40,120,100,20);
        t2.setBounds(160,120,200,20);
        add(l2);
        add(t2);
        
        b1 =new JButton("Update");
        b2 = new JButton("Delete");
        
        b1.setBounds(40,160,150,30);
        b2.setBounds(200,160,150,30);
        add(b1);
        add(b2);
        
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        c2.addItemListener(this);
        
        setVisible(true);
        setSize(400,550);
        setLocation(600,200);
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==b1){
            String n = t1.getText();
           int g = Integer.parseInt(c2.getSelectedItem());
            String d = t2.getText();
            
            String qry = "update dept set dname='"+n+"',sdate='"+d+"' where did="+g;
       
            try{
                conn c1 = new conn();
                c1.s.executeUpdate(qry);
                JOptionPane.showMessageDialog(null,"Department Updated");
            }catch(Exception ee){
                ee.printStackTrace();
            }
        }
        
        if(ae.getSource()==b2){
            try{
                conn c1 = new conn();
                c1.s.executeUpdate("delete from dept where did="+Integer.parseInt(c2.getSelectedItem()));
                JOptionPane.showMessageDialog(null,"Department Deleted");
                this.setVisible(false);
            }catch(Exception ee){
                ee.printStackTrace();
            }
        }
    }
    
    public void itemStateChanged(ItemEvent ie){
        try{
            conn c1 = new conn();
            ResultSet rs = c1.s.executeQuery("select * from dept where did="+c2.getSelectedItem());
            
            if(rs.next()){
                t1.setText(rs.getString("dname"));
                t2.setText(rs.getString("sdate"));
              
            }
        }catch(Exception ee){
           ee.printStackTrace();
        }
    
    }
    
    public static void main(String[] args){
        new Update_dept();
    }
    
}
