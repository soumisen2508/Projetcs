package mycart.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;



@Entity
public class OrderItem{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="order_id")
	private int orderId;
	@Column(name="date_ordered")
	private String date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


	@ManyToOne
	private User user;

	public OrderItem() {
		super();
	}
	public OrderItem(int orderId, User user, String date) {
		this.orderId = orderId;
		this.user = user;
		this.date = date;
	}

	public OrderItem(User user, String date) {
		super();
		this.user = user;
		this.date = date;
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Order [orderId=" + orderId + "]";
	}

	
}

	