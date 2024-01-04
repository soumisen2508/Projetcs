package mycart.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import mycart.entities.Category;
import mycart.entities.User;

public class UserDao {
	private SessionFactory factory;

	public UserDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	//get  user by email and password
	public User getUserByEmailAndPassword(String email, String password) {
		User user=null;
		try {
			String query="from User where userEmail =:e and userPassword =:p";
			Session session=this.factory.openSession();
			Query q=session.createQuery(query);
			q.setParameter("e", email);
			q.setParameter("p", password);
			user = (User) q.uniqueResult();
			session.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User getUserById(int uid) {
		User user = null;
		try {
			Session session = this.factory.openSession();
			user = session.get(User.class, uid);
			session.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
