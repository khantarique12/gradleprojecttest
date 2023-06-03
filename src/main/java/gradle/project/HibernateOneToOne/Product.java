package gradle.project.HibernateOneToOne;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
	private long productId;
	private String name;
	private String description;
	private float price;
	private ProductDetail productDetail;

	public Product() {
	}

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID")
	public long getProductId() {
		return productId;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", description=" + description + ", price="
				+ price + ", productDetail=" + productDetail + "]";
	}

}