/*	
BankAccount with Persistence

@author: Kumar Prateek Viraj
@StartDate: Oct26/2017
*/
import java.io.*;
import java.util.*;
import javax.swing.*;

class BankAccount{ //core banking facilities
	PrintStream pw=System.out;
	protected String name;
	protected long accountNumber;
	protected float balance=0;
	public String choiceList="\n0.Display\n1.AddAccount\n2.Withdraw\n3.Deposit\n4.Balance Enquiry";

	  public BankAccount(String name,long accountNumber,float balance){
    this.accountNumber=accountNumber;
    this.name=name;
    this.balance = balance; 
  }
	public BankAccount(){//loads from file to arraylist
			BufferedReader in = null;
				try {   
			    in = new BufferedReader(new FileReader("bankfile.txt"));
			    String str;
				    while ((str = in.readLine()) != null) {
			       	    String[] temp_list=str.split(";");
			       	    //ArrayList <BankAccount> list=new ArrayList<BankAccount>(Arrays.asList(str.split("\\s*;\\s*")));
			       	    ArrayList <BankAccount> list=new ArrayList<BankAccount>();
			       	 	list.add(new BankAccount(temp_list[0],Long.parseLong(temp_list[1]),Float.parseFloat(temp_list[2])));
			       		for(int i=0;i<3;i++)
			       		pw.println(temp_list[i]);		    		}
				}catch (FileNotFoundException e) {e.printStackTrace();
			} catch (IOException e) { e.printStackTrace();
		} finally {
	    	if (in != null) {
	        try{ in.close();} catch(Exception e){e.printStackTrace();}
	    	}
		}
	} 
	void search(){
		String search=JOptionPane.showInputDialog("Search who?");
	}

//	float deposit(float x){}
//	float withdraw(float x){}
//	long get_Account_no(){}
//	float get_Balance(){}
//	void tax_deduction(){}
}
class additional_functionality extends BankAccount{ //more facilities
	void addAcount(){
		name=JOptionPane.showInputDialog("Your Name");
	    String acc_s=JOptionPane.showInputDialog(null,"hey! "+name+ "\nPlease Enter your Account Number");
	    try{
	    	accountNumber=Long.parseLong(acc_s);
		} catch(NumberFormatException e){ pw.println(e.getMessage());}
		persistence(name,accountNumber,balance);
		JOptionPane.showMessageDialog(null,"Customer Added!","Success",JOptionPane.INFORMATION_MESSAGE);
	}
//	float getTotalbalance(){/*total balance in bank*/}
//	float getMinAndMaxBalance(){}
//	long findAccount(){}
//	int countAccounts(){/*counts number of accounts for given specific balance*/}
	void persistence(String name,long acc, float bal){
		try{
			    BufferedWriter writer=new BufferedWriter(new FileWriter("bankfile.txt",true));//'true' appends data
			     writer.write(name);
	     		 writer.write(";"+accountNumber);
	     		 writer.write(";"+balance+"\r\n");
	     		 writer.close();
		}catch(IOException e){pw.println(e.getMessage());}
	}
}
public class banker {
	public static void main(String args[]){
		additional_functionality af=new additional_functionality();
		BankAccount ba=new BankAccount();
	    ba.pw.println("This is.. \n \t\tTHE BANK\nplease wait...");
	    String ch=JOptionPane.showInputDialog(ba.choiceList);
	    Integer choice=Integer.parseInt(ch);
	    switch(choice){
	    	case 1: af.addAcount();
	    			break;
	    	default: JOptionPane.showMessageDialog(null,"Wrong Choice!","ERR",JOptionPane.ERROR_MESSAGE);
	    }System.exit(0);
  }
}