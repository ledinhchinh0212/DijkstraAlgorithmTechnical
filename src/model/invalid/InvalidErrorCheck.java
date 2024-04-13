package model.invalid;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.text.JTextComponent;

import model.algorithm.DijkstraAlgorithmMethod;

public class InvalidErrorCheck {
	//private int graph[][];
	private int dongBo;
	
	public InvalidErrorCheck() {
		this.dongBo = -1;
		 //graph = new int[1001][1001];
	}
	
	static public InvalidErrorCheck getInstance() {
		return new InvalidErrorCheck();
	}
	
	public boolean CheckError(String data, int graph[][], boolean check, DijkstraAlgorithmMethod dijkstra) {
		 String[] lines = data.split("\n");
		 for (String line : lines) {
			 String[] values = line.trim().split("\\s+");
			 if(values.length <= 2) {
				 return false;
			 }
			 else {
				 try {
					 String Snum1 = values[0];
					 String Snum2 = values[1];
					 int num1, num2;
					 if(isAllUpperCase(Snum1) && !isAllUpperCase(Snum2)) {
						 return false;
					 }
					 else if(isAllDigits(Snum1) && !isAllDigits(Snum2)) {
						 return false;
					 }
					 else if(isAllUpperCase(Snum1) && isAllUpperCase(Snum2)) {
						 System.out.println("Trường hợp chữ cái");
						 char num1Ch = Snum1.charAt(0);
						 char num2Ch = Snum2.charAt(0);
						 num1Ch -= 64; num2Ch -= 64;
						 num1 = (int)num1Ch;
						 num2 = (int)num2Ch;
						 if(dongBo == 1) {
							 return false;
						 }
						 else if(dongBo == -1) {
							 dongBo = 2;
						 }
					 }
					 else {
						 // tren 2 duoi 1
						 if(dongBo == 2) {
							 return false;
						 }
						 else if(dongBo == -1) {
							 dongBo = 1;
						 }
						 num1 = Integer.parseInt(values[0]);
						 num2 = Integer.parseInt(values[1]);
					 }
					 int num3 = Integer.parseInt(values[2]);
					 System.out.println(num1 + " - " + num2 + " - " + num3);
					 graph[num1][num2] = num3;
					 if(!check) {	
						 graph[num2][num1] = num3;
					 }
				} catch (NumberFormatException e) {
					return false;
				}
			 }
		 }
		 if(dongBo == 2) {
			 dijkstra.setCharInHead(true);
		 }
		 return true;
	}
	public boolean isAllUpperCase(String str) {
	    return str.matches("^[A-Z]+$");
	}
	
	public boolean isAllDigits(String str) {
        return str.matches("\\d+");
	}
	
	public void TimeSetErrorCPN(Component c) {
		 System.out.println("Component type: " + c.getClass().getName());
	        Timer timer = new Timer(1750, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent evt) {
	                if (c instanceof JTextComponent) {
	                    ((JTextComponent) c).setText(""); // Clear the text of the JTextComponent
	                } else if (c instanceof AbstractButton) {
	                    ((AbstractButton) c).setText(""); // Clear the text of the AbstractButton
	                } else if (c instanceof JLabel) {
	                    ((JLabel) c).setText(""); // Clear the text of the JLabel
	                }
	                ((Timer) evt.getSource()).stop();
	            }
	        });
	        timer.setRepeats(false); // Only fire the timer once
	        timer.start(); 
	 }
	 
	 public boolean CheckSourceAndTarget(String source, String target) {
		 if (source.isEmpty() || target.isEmpty()) {
		        return false;
	    }
		 if(isAllDigits(source) && isAllDigits(target)) {
			 try {
				 int number1 = Integer.parseInt(source);
				 int number2 = Integer.parseInt(target);
				 if(number1 < 0 || number2 < 0) {
					 return false;
				 }
			 } catch (NumberFormatException e) {
				 e.printStackTrace();
				 return false;
			 }
		 }
		 else {
			 return false;
		 }
		 return true;
	 }
	 
	 public boolean CaseHead(String s) {
			return InvalidErrorCheck.getInstance().isAllUpperCase(s) && s.length() == 1;
	}

}