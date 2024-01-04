package mycart.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import mycart.entities.OrderItem;
import mycart.entities.Product;

public class OrderDao {
	private SessionFactory factory;

	public OrderDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	public boolean saveOrder(OrderItem order) {
		boolean f = false;
		try {
			Session session = this.factory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(order);
			tx.commit();
			session.close();
			f=true;
			
		}catch(Exception e) {
			e.printStackTrace();
			f = false;
		}
		return f;
	}
	
	//get all orders
	
		public List<OrderItem> getAllOrders(){
			Session s = this.factory.openSession();
			Query query = s.createQuery("from Order");
			List<OrderItem> list = query.list();
			return list;
		}
		
		//get all orders of a given user
		
		public List<OrderItem> getAllOrdersById(int uid){
			Session s = this.factory.openSession();
			Query query = s.createQuery("from Order as o where o.user.userId =: id");
			query.setParameter("id", uid);
			List<OrderItem> list = query.list();
			return list;
		}
	

}
