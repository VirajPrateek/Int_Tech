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
	final double EPSILON=1E-14;
	protected String name;
	protected long accountNumber;
	protected float balance=0;
	public String choiceList="\n1.AddAccount\n2.Withdraw\n3.Deposit\n4.MyAccount\n5.More Options\n\n0.Persistence Exit";
	public String moreOptions="\n6.Minimum and Maximum Balance\n7.Accounts with specific balance\n8.Total Balance in Bank\n\n0.Persistence Exit";
	ArrayList <BankAccount> customer_list;
	public BankAccount(String name,long accountNumber,float balance){
	    this.accountNumber=accountNumber;
	    this.name=name;
	    this.balance = balance; 
	 }
	 public String getName(){
	 	return this.name;
	 }
	 public long get_Account_no(){
	 	return this.accountNumber;
	 }
	 public float get_Balance(){
	 	return this.balance;
	 }
	 public void setBalance(float newBal){
	 	this.balance=newBal;
	 }

	public BankAccount(){//loads from file to arraylist
			BufferedReader in = null;
			customer_list=new ArrayList<BankAccount>();
				try {   
			    in = new BufferedReader(new FileReader("bankfile.txt"));
			    String str;
				    while ((str = in.readLine()) != null) {
			       	    String[] temp_list=str.split(";");
			       	    accountNumber=Long.parseLong(temp_list[1]);
			       	    balance=Float.parseFloat(temp_list[2]);
			       	 	BankAccount customer = new BankAccount(temp_list[0],accountNumber,balance);
						customer_list.add(customer);
			       	}
				}catch (FileNotFoundException e) {e.printStackTrace();
			  } catch (IOException e) { e.printStackTrace();
		    } finally {
		    	if (in != null) {
		        try{ in.close();
		        } catch(Exception e){e.printStackTrace();}
		     }
		}
		for(BankAccount c: customer_list) pw.println(c.getName()+"  "+c.get_Account_no()+"\n");
	}	
	void tax_deduction(){/*deducts 15% for 15000/- and above. 7% for 10000/- to 15000/- and 5% for 0- 10000/- */

	}

	float getTotalbalance(){
		float totalBal=0;
		for(BankAccount cu:customer_list){
			totalBal=totalBal+cu.get_Balance();
		}
		return totalBal;
	}
	void minMaxAmount(){
		float minBal=customer_list.get(0).get_Balance();
		float maxBal=minBal;
		for(BankAccount cu:customer_list){
			if(cu.get_Balance()<minBal) minBal=cu.get_Balance();
			else if(cu.get_Balance()>maxBal) maxBal=cu.get_Balance();
		}
		pw.printf("Min is %5.2f \nMax is %5.2f",minBal,maxBal);
	}
	int countAccounts(){/*counts number of accounts for given specific balance*/
		String rb=JOptionPane.showInputDialog("Amount to look for: ");
		float req_bal=Float.parseFloat(rb);
		int numOfAccounts=0;
		for(BankAccount cu:customer_list){
			if((Math.abs(req_bal-cu.get_Balance()))<=EPSILON) numOfAccounts++;
		}
		return numOfAccounts;
	}
}
class additional_functionality extends BankAccount{ // more facilities
		void addAcount(){
			name=JOptionPane.showInputDialog("Your Name");
		    String acc_s=JOptionPane.showInputDialog(null,"hey! "+name+ "\nPlease Enter your Account Number");
		    try{
		    	accountNumber=Long.parseLong(acc_s);
			} catch(NumberFormatException e){ pw.println(e.getMessage());}
			BankAccount customer = new BankAccount(name,accountNumber,0);
			customer_list.add(customer);
			update(name,accountNumber,balance);
			display("Customer Added!");
		}
		float deposit(float x){
		 	accountNumber=input_account();
		 	if(findAccount(accountNumber).equals("")) display("");
		 	else{
		 		for(int index=0;index<customer_list.size();index++)
		 			if(customer_list.get(index).get_Account_no()==accountNumber){
		 				float curr_bal=customer_list.get(index).get_Balance(); //this thing can be compacted.
		 				curr_bal=curr_bal+x;
		 				customer_list.get(index).setBalance(curr_bal);
		 				pw.printf("Updated balance: %5.2f%n",customer_list.get(index).get_Balance());
		 				display("Deposited!");
		 			}
		 	}
		 	return x;
		 }
		 float withdraw(float x){
		 	accountNumber=input_account();
		 	if(findAccount(accountNumber).equals("")) display("");
		 	else{
		 		for(int index=0;index<customer_list.size();index++)
		 			if(customer_list.get(index).get_Account_no()==accountNumber){
		 				float curr_bal=customer_list.get(index).get_Balance(); //this thing can be compacted.
		 				if((Math.abs(curr_bal-0)<=EPSILON) || x>curr_bal) display("Insufficient Funds");
		 				else{
		 				curr_bal=curr_bal-x;
		 				customer_list.get(index).setBalance(curr_bal);
		 				pw.printf("Updated balance: %5.2f%n",customer_list.get(index).get_Balance());
		 				display("Withdrawn!");
		 				}
		 			}
		 	}
		 	return x;
		 }
		String findAccount(long search_accn){
			   String search_result="";
				 for(BankAccount cust: customer_list){
					if(cust.get_Account_no()==search_accn){
						search_result="A/C#"+cust.get_Account_no()+"\nName: "+cust.getName()+"\nBalance: "+cust.get_Balance();
						break;}
					}
			return search_result;
		 }
		void update(String name,long acc, float bal){
			try{
				    BufferedWriter writer=new BufferedWriter(new FileWriter("bankfile.txt",true));//'true' appends data
				     writer.write(name+";"+accountNumber+";"+balance+"\r\n");
		     		 writer.close();
			}catch(IOException e){pw.println(e.getMessage());}
		}
		void persistence(){
			try{
				BufferedWriter writer=new BufferedWriter(new FileWriter("bankfile.txt"));//'renews file
				   for(BankAccount cu:customer_list){
				    writer.write(cu.getName()+";"+cu.get_Account_no()+";"+cu.get_Balance()+"\r\n");
		     		}writer.close();
			    }catch(IOException e){pw.println(e.getMessage());}	
		}
		void display(String disp_data){
			if(disp_data.equals("")) 
				JOptionPane.showMessageDialog(null,"Not Found","Error",JOptionPane.ERROR_MESSAGE);
			else 
				JOptionPane.showMessageDialog(null,disp_data,"Success",JOptionPane.INFORMATION_MESSAGE);
		}
       long input_account(){
       	String acc_s=JOptionPane.showInputDialog(null,"Input Account Number:");
       	  try{
		    	accountNumber=Long.parseLong(acc_s);
			} catch(NumberFormatException e){ pw.println(e.getMessage());}
		return accountNumber;
       }
}
class banker{
	public static void main(String args[]){
		additional_functionality af=new additional_functionality();
		BankAccount ba=new BankAccount();
		ba.pw.println("This is.. \n \t\tTHE BANK\nplease wait...");
		Integer choice;
	    do{
	    	String ch=JOptionPane.showInputDialog(ba.choiceList);
	    	choice=Integer.parseInt(ch);
	    switch(choice){
	    	case 0:af.persistence();
	    			System.exit(0);
	    			break;
	    	case 1: af.addAcount();
	    			break;
	    	case 2: String amount=JOptionPane.showInputDialog(null,"Withdrwal Amount: ");
	    			af.withdraw(Float.parseFloat(amount));
	    			break;
	    	case 3: amount=JOptionPane.showInputDialog(null,"Deposit Amount: ");
	    			af.deposit(Float.parseFloat(amount));
	    			break;
	    	case 4: af.display(af.findAccount(af.input_account()));
	    			break;
	    	case 5: ch=JOptionPane.showInputDialog(ba.moreOptions);
	    			choice=Integer.parseInt(ch);
	    			switch(choice){
	    				case 6:ba.minMaxAmount();
	    				break;
	    				case 7:af.display(ba.countAccounts()+"account(s) have the required balance.");
	    				break;
	    				case 8: af.display("Total Balance: Rs."+ba.getTotalbalance());
	    				break;
	    			}
	    			break;
	    	default: JOptionPane.showMessageDialog(null,"Wrong Choice!","ERR",JOptionPane.ERROR_MESSAGE);
	    }
	}while(choice!=0);
	    System.exit(0);
  }
}
