package fa.training.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String phoneNumber;
	private String address;
	private List<String> order;

	public Customer() {
		super();
		name = null;
		phoneNumber = null;
		address = null;
		order = new ArrayList<>();
	}

	public Customer(String name, String phoneNumber, String address, List<String> order) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getOrder() {
		return order;
	}

	public void setOrder(List<String> order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", phoneNumber=" + phoneNumber + ", address=" + address + ", order="
				+ order.toString() + "]";
	}
}
