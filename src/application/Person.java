package application;
import java.util.ArrayList;

public class Person {
	private String identificationNumber;
	private String name;
	private int age;
	private ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

	public void addAccount(BankAccount account) {
			accounts.add(account);
//			if (accounts.size() > 3) {
//				account.setBalance(0);
//				accounts.remove(account);

			}
//			if (account.getPerson().getAge() < 18) {
//				account.setBalance(0);
//			accounts.clear();
//				
//			}
		

	public BankAccount findAccount(String accountNumber) {
		for (BankAccount account : accounts) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		}
		return null;
	}

	public double calculateTotalBalance() {
		double total = 0;
		for (BankAccount tmp : accounts) {
			total += tmp.getBalance();
		}
		return total;
	}

	public ArrayList<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

}
