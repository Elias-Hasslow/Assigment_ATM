package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController {

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtAge;

	@FXML
	private TextField txtID;

	@FXML
	private TextField txtAccountNumber;

	@FXML
	private TextField txtBalance;

	@FXML
	private TextField txtAmount;

	@FXML
	private Button btnAddPerson;

	@FXML
	private Button btnFindPerson;

	@FXML
	private Button btnRemovePerson;

	@FXML
	private Button btnAddAccount;

	@FXML
	private Button btnDeposit;

	@FXML
	private Button btnWithdraw;

	@FXML
	private Button btnTotalBalancePerson;

	@FXML
	private Button btnTotalBalanceRegister;

	@FXML
	private Label testLabel;

	@FXML
	private TableView<Person> tableViewPerson;

	@FXML
	private TableView<BankAccount> tableViewAccount;

	@FXML
	private TableColumn<Person, String> columnName;

	@FXML
	private TableColumn<Person, Integer> columnAge;

	@FXML
	private TableColumn<Person, String> columnID;

	@FXML
	private TableColumn<BankAccount, String> columnAccountNumber;

	@FXML
	private TableColumn<BankAccount, Double> columnBalance;

	@FXML
	private Label findName;

	@FXML
	private Label findAge;

	@FXML
	private Label findID;


	@FXML
	private Label displayLabel;


	@FXML
	private Label errorMessage;



	PersonRegister reg = new PersonRegister();

	private ObservableList<Person> personData = FXCollections.observableArrayList();
	private ObservableList<BankAccount> accountData = FXCollections.observableArrayList();

	public void initialize() {
		columnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		columnAge.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
		columnID.setCellValueFactory(new PropertyValueFactory<Person, String>("identificationNumber"));

		columnAccountNumber.setCellValueFactory(new PropertyValueFactory<BankAccount, String>("accountNumber"));
		columnBalance.setCellValueFactory(new PropertyValueFactory<BankAccount, Double>("balance"));

		tableViewAccount.setItems(accountData);

		tableViewPerson.setItems(personData);
		tableViewAccount.setItems(accountData);

	}

	// Add Person method

	@FXML
	public void btnAdd(ActionEvent event) {
		try {
			String name = txtName.getText();
			String ageString = txtAge.getText();
			int age = Integer.parseInt(ageString);
			String identificationNumber = txtID.getText();

			if (reg.findPerson(identificationNumber) == null) {

				Person tmpPerson = new Person();
				tmpPerson.setName(name);
				tmpPerson.setAge(age);
				tmpPerson.setIdentificationNumber(identificationNumber);
				reg.addPerson(tmpPerson);
				personData.add(tmpPerson);
				displayLabel.setText("Person added succesfully!");
				errorMessage.setText("");
			} else {
				errorMessage.setText("Person with that ID already exist!");
				displayLabel.setText("");

			}

			txtName.clear();
			txtAge.clear();
			txtID.clear();
			accountData.clear();
			findName.setText("");
			findAge.setText("");
			findID.setText("");

		} catch (NumberFormatException e) {
			errorMessage.setText("Make sure you entered all required fields and age as a number");
			displayLabel.setText("");

		}
	}

	// Find Person method
	@FXML
	public void btnFindPerson(ActionEvent event) {
		try {
		for (Person p : reg.getPersons()) {
			if (p.getIdentificationNumber().equals(txtID.getText())) {
				findName.setText(p.getName());
				findAge.setText(String.valueOf(p.getAge()));
				findID.setText(p.getIdentificationNumber());
				errorMessage.setText("");
				accountData.clear();
			}

		}
		for (BankAccount b : reg.findPerson(txtID.getText()).getAccounts()) {
			if (accountData.contains(b)) {

			} else {
				accountData.add(b);

			}
			if (reg.findPerson(txtID.getText()) == null) {
				errorMessage.setText("A person with that ID could not be found!");
				findName.setText("");
				findAge.setText("");
				findID.setText("");
				displayLabel.setText("");


			}
		}
		}catch(NullPointerException e) {
			errorMessage.setText("Could not find a person with that ID!");
			displayLabel.setText("");


		}

	}

	// Add account method
	@FXML
	public void btnAddAccount_Click(ActionEvent event) {
		try {
			String idNumber = txtID.getText();
			String accNr = txtAccountNumber.getText();
			double bal = Double.parseDouble(txtBalance.getText());
			accountData.clear();
			findName.setText("");
			findAge.setText("");
			findID.setText("");
			txtAge.clear();
			txtID.clear();

			Person tmpPerson = reg.findPerson(idNumber);
			if (reg.findAccount(accNr) == null) {

				if (tmpPerson.getAccounts().size() > 4) {
					displayLabel.setText("You already have 3 accounts!");
				} else if (tmpPerson.getAge() < 18) {
					displayLabel.setText("You need to be 18 to open an account!");
				} else {

					BankAccount ba = new BankAccount();

					ba.setAccountNumber(accNr);
					double balance = Double.parseDouble(txtBalance.getText());
					ba.setBalance(balance);

					tmpPerson.addAccount(ba);

					ba.setAccountNumber(accNr);
					ba.setBalance(bal);
					ba.setPerson(tmpPerson);

					tmpPerson.addAccount(ba);

					displayLabel.setText("Account succesfully added");

					txtAccountNumber.clear();
					txtBalance.clear();
					errorMessage.setText("");
					txtAge.clear();
					txtID.clear();


				}
			}
			else {
				errorMessage.setText("Account with this Account-Number already exist!");
				displayLabel.setText("");
			}
		} catch (NumberFormatException e) {
			errorMessage.setText("Make sure youre only using numbers and you have filled in Account-Number, balance and ID!");
			displayLabel.setText("");

		} catch (NullPointerException e) {
			errorMessage.setText("Make sure you entred an ID aswell!");
			displayLabel.setText("");

		}
	}

// Deposit method

	@FXML
	public void btnDeposit_Click(ActionEvent event) {
		try {
			String accNr = txtAccountNumber.getText();
			double amount = Double.parseDouble(txtAmount.getText());

			BankAccount newba = reg.findAccount(accNr);
			if (newba != null) {
				newba.deposit(amount);
				displayLabel.setText("The money deposited succesfully!");
				tableViewAccount.refresh();
				errorMessage.setText("");

			} else {
				errorMessage.setText("No account was found with matching ID!");
				displayLabel.setText("");

			}
			findName.setText("");
			findAge.setText("");
			findID.setText("");
			txtName.clear();
			txtAge.clear();
			txtID.clear();
		} catch (NumberFormatException e) {
			errorMessage.setText("Make sure youre only using numbers and you have filled in Account-Number and ID");
			displayLabel.setText("");

		}
	}

	
	//Withdraw method
	public void btnWithdraw(ActionEvent event) {
		try {
			String accNr = txtAccountNumber.getText();
			double amount = Double.parseDouble(txtAmount.getText());

			BankAccount acc = reg.findAccount(accNr);
			if (acc != null) {
				if (acc.getBalance() <= 0) {
					errorMessage.setText("Withdrawal was unsuccesful, make sure theres money on the account.");
					displayLabel.setText("");
				}
				if (amount > 0.5 * acc.getBalance()) {
					errorMessage.setText("You cant withdraw more than 50% of your balance");
					displayLabel.setText("");
				}
				if (amount <= 0.5 * acc.getBalance() && acc.getBalance() > 0) {
					acc.withdraw(amount);
					displayLabel.setText("The money withdrew succesfully!");
					errorMessage.setText("");
				}
				tableViewAccount.refresh();
			} else {
				errorMessage.setText("No account was found with matching ID");
			}
			findName.setText("");
			findAge.setText("");
			findID.setText("");
			txtName.clear();
			txtAge.clear();
			txtID.clear();
		} catch (NumberFormatException e) {
			errorMessage.setText("Make sure youre only using numbers and you have filled in Account-Number and ID");
			displayLabel.setText("");

		}
	}

	
	//remove person method
	public void btnRemovePerson(ActionEvent event) {
		if (reg.findPerson(txtID.getText()) != null) {
			personData.remove(reg.findPerson(txtID.getText()));
			reg.removePerson(reg.findPerson(txtID.getText()));
			errorMessage.setText("");
			displayLabel.setText("Person removed from register!");
			}
			else {
				errorMessage.setText("Could not find person with that ID!");
			}
		
		findName.setText("");
		findAge.setText("");
		findID.setText("");
		txtName.clear();
		txtAge.clear();
		txtID.clear();


	}

	@FXML
	public void btnTotalBalancePerson(ActionEvent event) {
		try {
		String idNmr = txtID.getText();
		Person tmpPerson = reg.findPerson(idNmr);
		double balance;
		if (reg.findPerson(idNmr).equals(tmpPerson)) {
			balance = tmpPerson.calculateTotalBalance();
			displayLabel.setText(tmpPerson.getName() + "'s total balance on all accounts is: \n" + balance / 2 + "$");
		} else {
			displayLabel.setText("Such person doesn't exist in our register!");
		}
		for (BankAccount b : reg.findPerson(txtID.getText()).getAccounts()) {
			if (accountData.contains(b)) {

			} else {
				accountData.add(b);

			}
		}
		errorMessage.setText("");

		findName.setText("");
		findAge.setText("");
		findID.setText("");
		txtName.clear();
		txtAge.clear();
		txtID.clear();
		} catch(NullPointerException e) {
			errorMessage.setText("Make sure you entered an ID and that person exist!");
			displayLabel.setText("");

		}
	}

	@FXML
	public void btnTotalBalanceAll(ActionEvent event) {
		displayLabel.setText(
				"The total balance for all accounts in register is: \n" + (reg.calculateTotalBalance() / 2) + "$");
		errorMessage.setText("");
		findName.setText("");
		findAge.setText("");
		findID.setText("");
		txtName.clear();
		txtAge.clear();
		txtID.clear();
		}

		}
	

		
