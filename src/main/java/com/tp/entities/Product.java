package com.tp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
@Entity
@Table(name = "PRODUCTS")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Product implements Serializable {
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@NonNull
		@Size(min = 4 , max = 50)
		private String label;
		@DecimalMin(value = "1")
		private double price;
		@Min(value = 1)
		private int quantity;
		@Temporal(TemporalType.DATE)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date add_date;
		private boolean available;

}
