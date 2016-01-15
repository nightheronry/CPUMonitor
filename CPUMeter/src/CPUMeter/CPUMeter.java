package CPUMeter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.io.*;
import java.text.DecimalFormat;
import javax.swing.*;
import java.util.*;


public class CPUMeter extends JFrame{
	private JPanel panel;
	private JLabel Label,Label1,Label2,Label3,Label4,Label5,Label6;
	private final int ww=820,wh=580;
	private static double x1,x2;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DecimalFormat f=new DecimalFormat("#0.0");
		openfile();
		// TODO Auto-generated method stub
		CPUMeter w=new CPUMeter();
		
		
	new Thread(){
	    public void run(){
	    	String str;
	    	for (int i = 0; i < 100; i++) {
	    		try 
	    		{
	    			Thread.sleep(1000); // do nothing for 1000 miliseconds (1 second)
	    		} 
	    		catch(InterruptedException e)
	    		{
	    			e.printStackTrace();
	    		}
    	
	    		str=printUsage();
	    		w.Label2.setText(f.format(Double.parseDouble(str)*100) +" %");
	    		
	    		w.Label6.setText(f.format(Double.parseDouble(str)*x1+x2)+"");
	    		System.out.println(str);
    	  
	    	}
	  	}
	}.start(); 
}

	private static String printUsage() {
		String str ="";
		  OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		  for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
		    method.setAccessible(true);
		    if (method.getName().startsWith("get") 
		        && Modifier.isPublic(method.getModifiers())) {
		            Object value;
		        try {
		            value = method.invoke(operatingSystemMXBean);
		        } catch (Exception e) {
		            value = e;
		        } // try
		        
		        if (method.getName().equals("getSystemCpuLoad"))
		        	str = value +"";
		        
		        
		        //System.out.println(method.getName() + " = " + value);
		    } // if
		  } // for
		  return str;
		}
	
	
	
	
	public CPUMeter()
	{
		setTitle("CPU Meter");
		setSize(ww,wh);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildPanel();
		add(panel);
		setVisible(true);
		
	}
	
	
	public void buildPanel()
	{
		Label=new JLabel(new ImageIcon("src/back.png"));
		Label1=new JLabel();
		Label2=new JLabel();
		Label3=new JLabel();
		Label4=new JLabel();
		Label5=new JLabel();
		Label6=new JLabel();
		
		panel = new JPanel();
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width-this.getWidth())/2,(d.height-this.getHeight())/2);
		
		panel.setLayout(null);
		panel.setBackground(new Color(255,255,255));
		
		Label.setBounds(1,0,ww-20,wh);
		
		Label1.setFont(new Font("Calibri", Font.BOLD, 48));
		Label1.setBounds(460,110,600,100);
		Label1.setText("CPU Utilization");
		Label1.setForeground(Color.BLACK);
		
		Label2.setFont(new Font("Calibri", Font.BOLD, 60));
		Label2.setBounds(560,220,600,100);
		Label2.setForeground(Color.BLACK);
		
		Label3.setFont(new Font("Calibri", Font.BOLD, 30));
		Label3.setBounds(50,440,600,100);
		Label3.setText("Formula :");
		Label3.setForeground(Color.WHITE);
		
		Label4.setFont(new Font("Calibri", Font.BOLD, 30));
		Label4.setBounds(190,440,600,100);
		Label4.setText("Estimate Watt  =  "+ x1 + " * cpu_utilization + "+ x2 );
		Label4.setForeground(Color.WHITE);
		
		Label5.setFont(new Font("Calibri", Font.BOLD, 48));
		Label5.setBounds(60,110,600,100);
		Label5.setText("Estimate Watt" );
		Label5.setForeground(Color.BLACK);
		
		Label6.setFont(new Font("Calibri", Font.BOLD, 60));
		Label6.setBounds(140,220,600,100);
		Label6.setForeground(Color.BLACK);
		
		
		panel.add(Label1);
		panel.add(Label2);
		panel.add(Label3);
		panel.add(Label4);
		panel.add(Label5);
		panel.add(Label6);
		panel.add(Label);
	}
	
	
	
	public static void openfile()
	{
		int i = 0;
		double[] x ={0,0};
		try
		{
			File file=new File("x.txt");
			Scanner inputfile = new Scanner(file);
			
			while(inputfile.hasNext())
			{
				x[i]=inputfile.nextDouble();
				i++;
			}
			x1=x[0];
			x2=x[1];
			inputfile.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.print("File is not exist.");
		}
		
	}
	
}
