package fa.training.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fa.training.entities.Customer;
import fa.training.utils.Constants;
import fa.training.utils.Validator;

public class CustomerService implements Serializable {

	private static final long serialVersionUID = 1L;
	private Scanner sc = new Scanner(System.in);
	private List<String> customers = new ArrayList<>();
	private Customer customer;
	private OrderService orderService = new OrderService();
	private String phone;
	private String name;
	private String address;

	public List<String> createCustomer() {
		sc = new Scanner(System.in);
		customer = new Customer();

		System.out.println("----Enter Customer information--");

		do {
			System.out.print("Enter name: ");
			name = sc.nextLine();
		} while (!Validator.isName(name));
		customer.setName(name);

		do {
			System.out.print("Enter phone: ");
			phone = sc.nextLine();
		} while (!Validator.isPhone(phone));
		customer.setPhoneNumber(phone);

		do {
			System.out.print("Enter address: ");
			address = sc.nextLine();
		} while (!Validator.isAddress(address));
		customer.setAddress(address);

		System.out.println("Enter order infor: ");

		customer.setOrder(orderService.createOrder());

		customers.add(customer.toString());

		return customers;
	}

	public String save(List<String> customers) throws Exception {
		ObjectOutputStream objectOutputStream = null;

		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(Constants.FILE_PATH));
			objectOutputStream.writeObject(customers);
		} catch (Exception exception) {
			throw new Exception();
		} finally {
			if (objectOutputStream != null) {
				objectOutputStream.close();
			}
		}
		return Constants.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public List<String> findAll() throws IOException {
		ObjectInputStream objectInputStream = null;
		List<String> customers;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(Constants.FILE_PATH));
			customers = (List<String>) objectInputStream.readObject();
		} catch (Exception exception) {
			throw new IOException();
		} finally {
			if (objectInputStream != null) {
				objectInputStream.close();
			}
		}
		return customers;
	}

	public void display(List<String> customers) {
		System.out.println("---------------LIST OF Customer-------------------");

		for (String customer : customers) {
			System.out.println(customer);
		}
	}

	public List<String> search(String phone) throws IOException {
		List<String> customers = findAll();

		List<String> ordersSearch = new ArrayList<String>();

		for (String customer : customers) {
			if (customer.contains("phoneNumber=" + phone)) {
				ordersSearch.add(customer);
			}
		}

		if (ordersSearch.isEmpty()) {
			ordersSearch.add("Phone is not exist");
		}

		return ordersSearch;
	}

	public boolean remove(String phone) throws Exception {
		boolean removed = false;

		List<String> customers = findAll();
		if (customers == null) {
			throw new IOException();
		}
		Iterator<String> iterator = customers.iterator();
		while (iterator.hasNext()) {
			String customer = iterator.next();
			if (customer.contains("phoneNumber=" + phone)) {
				iterator.remove();
				removed = true;
				break;
			}
		}

		if (removed) {
			try {
				save(customers);
			} catch (Exception e) {
				throw new Exception();
			}
			return true;
		}
		return false;
	}
}
