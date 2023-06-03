package gradle.project.HibernateOneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT_DETAIL")
public class ProductDetail {
	private long productId;
	private String partNumber;
	private String dimension;
	private float weight;
	private String manufacturer;
	private String origin;
	private Product product;

	public ProductDetail() {
	}

	@Id
	@GeneratedValue(generator = "foreigngen")
	@GenericGenerator(strategy = "foreign", name = "foreigngen", parameters = @Parameter(name = "property", value = "product"))
	@Column(name = "PRODUCT_ID")
	public long getProductId() {
		return productId;
	}

	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	@OneToOne(mappedBy = "productDetail")
	public Product getProduct() {
		return product;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductDetail [productId=" + productId + ", partNumber=" + partNumber + ", dimension=" + dimension
				+ ", weight=" + weight + ", manufacturer=" + manufacturer + ", origin=" + origin + ", product="
				+ product + "]";
	}

}