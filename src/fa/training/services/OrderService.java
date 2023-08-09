package fa.training.services;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fa.training.entities.Order;
import fa.training.utils.Validator;

public class OrderService implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> orders;
	private boolean status;
	private Date date;
	private String number;

	public List<String> createOrder() {
		orders = new ArrayList<>();
		Order order = new Order();
		String choice;
		Scanner sc = new Scanner(System.in);

		do {
			do {
				status = false;
				try {
					sc = new Scanner(System.in);
					System.out.print("+ number: ");
					number = sc.nextLine();

					if (!Validator.isNumber(number)) {
						status = true;
					}
				} catch (Exception e) {
					status = true;
				}
			} while (status);

			order.setNumber(number);

			do {
				status = false;
				try {
					System.out.print("+ date: ");
					date = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
				} catch (Exception e) {
					status = true;
				}
			} while (status);

			order.setDate(date);

			orders.add(order.toString());

			sc = new Scanner(System.in);
			System.out.println("Enter 'n' or 'N' to stop enter order");
			choice = sc.nextLine();

		} while (!"n".equals(choice) && !"N".equals(choice));

		return orders;
	}
}
