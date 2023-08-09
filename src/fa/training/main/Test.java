package fa.training.main;

import java.util.Scanner;

import fa.training.services.CustomerService;
import fa.training.utils.Constants;
import fa.training.utils.Validator;

public class Test {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		CustomerService customerService = new CustomerService();
		String choice;
		String phone;
		String status;
		String enterCustomer;
		try {

			do {
				System.out.println("Choose function:");
				System.out.println("1. Add a new Customer");
				System.out.println("2. Show all Customers");
				System.out.println("3. Search Customer");
				System.out.println("4. Remove Customer");
				System.out.println("5. Exit");

				sc = new Scanner(System.in);
				choice = sc.nextLine();

				switch (choice) {
				case Constants.ADD:
					do {
						customerService.createCustomer();
						try {
							status = customerService.save(customerService.createCustomer());
							System.out.println("Add: " + status);
						} catch (Exception e) {
							System.out.println("Add fail!");
						}
						System.out.println("Enter 'n' or 'N' to stop enter customer");
						enterCustomer = sc.nextLine();
						sc = new Scanner(System.in);
					} while (!"n".equals(enterCustomer) && !"N".equals(enterCustomer));
					break;

				case Constants.DISPLAY:
					customerService.display(customerService.findAll());
					break;

				case Constants.SEARCH:

					do {
						System.out.print("Enter phone number of customer to search: ");
						phone = sc.nextLine();
					} while (!Validator.isPhone(phone));

					System.out.println(customerService.search(phone).toString());
					break;

				case Constants.REMOVE:
					do {
						System.out.print("Enter customer phone to remove: ");
						phone = sc.nextLine();
					} while (!Validator.isPhone(phone));
					System.out.println(customerService.remove(phone));
					break;

				default:
					choice = Constants.EXIT;
					break;
				}
			} while (!(Constants.EXIT).equalsIgnoreCase(choice));

		} finally {
			if (sc != null) {
				sc.close();
			}
		}

	}

}