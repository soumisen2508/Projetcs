package mycart.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(length=10, name ="category_id")
private int categoryId;
@Column(length=100, name ="category_title")
private String categoryTitle;
@Column(length=3000, name ="category_description")
private String categoryDescription;
@OneToMany(mappedBy = "category")
private List<Product> products = new ArrayList<>();
public Category() {
	super();
}
public Category(int categoryId, String categoryTitle, String categoryDescription) {
	super();
	this.categoryId = categoryId;
	this.categoryTitle = categoryTitle;
	this.categoryDescription = categoryDescription;
}
public Category(String categoryTitle, String categoryDescription) {
	super();
	this.categoryTitle = categoryTitle;
	this.categoryDescription = categoryDescription;
}

public Category(String categoryTitle, String categoryDescription, List<Product> products) {
	super();
	this.categoryTitle = categoryTitle;
	this.categoryDescription = categoryDescription;
	this.products = products;
}
public int getCategoryId() {
	return categoryId;
}
public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}
public String getCategoryTitle() {
	return categoryTitle;
}
public void setCategoryTitle(String categoryTitle) {
	this.categoryTitle = categoryTitle;
}
public String getCategoryDescription() {
	return categoryDescription;
}
public void setCategoryDescription(String categoryDescription) {
	this.categoryDescription = categoryDescription;
}

public List<Product> getProducts() {
	return products;
}
public void setProducts(List<Product> products) {
	this.products = products;
}
@Override
public String toString() {
	return "Category [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDescription="
			+ categoryDescription + "]";
}

}
