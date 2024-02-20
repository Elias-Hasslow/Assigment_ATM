package application;
import java.util.ArrayList;

public class PersonRegister {
	private ArrayList<Person> personList = new ArrayList<Person>();
	
	public void addPerson(Person person) {
		this.personList.add(person);
	}
	
	public Person findPerson(String identificationNumber) {
		for (Person person : personList) {
			if(person.getIdentificationNumber().equals(identificationNumber)) {
				return person;
			}
		}
		return null;
	}
	
	public BankAccount findAccount(String accountNumber) {
		for (Person p : this.personList) {
			for(BankAccount b : p.getAccounts()) {
				if(b.getAccountNumber().equals(accountNumber)) {
					return b;
					}
				}
			}
		return null;
	}

	public ArrayList<Person> getPersons() {
		return personList;
	}

	public void setPersons(ArrayList<Person> persons) {
		this.personList = persons;
	}

	public double calculateTotalBalance() {
		double total = 0;
			for	(Person person : personList) {
				for (BankAccount bankAccount : person.getAccounts()) {
					 total += bankAccount.getBalance();
				}

			}
			return total;
	}
	
	public void removePerson(Person person) {
		personList.remove(person);
	}
}
