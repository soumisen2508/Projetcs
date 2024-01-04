package mycart.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import mycart.entities.Category;

public class CategoryDao {
private SessionFactory factory;

public CategoryDao(SessionFactory factory) {
	super();
	this.factory = factory;
}
//saves the category object to DB
public int saveCategory(Category cat) {
	Session session = this.factory.openSession();
	Transaction tx = session.beginTransaction();
	int catId = (int) session.save(cat);
	tx.commit();
	session.close();
	return catId;
}
public List<Category> getCategories(){
	Session session = this.factory.openSession();
	Query query = session.createQuery("from Category");
	List<Category> list = query.list();
	return list;
}
public Category getCategoryById(int cid) {
	Category cat = null;
	try {
		Session session = this.factory.openSession();
		cat = session.get(Category.class, cid);
		session.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return cat;
}
}
