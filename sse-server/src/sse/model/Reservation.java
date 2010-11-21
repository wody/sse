/**
 * 
 */
package sse.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author tobihammerer
 *
 */
@Entity
@Table(name="reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Double discount;
	private BigDecimal roomRate;
	private Customer customer;
	private Bill bill;
	private Room room;
	
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public BigDecimal getRoomRate() {
		return roomRate;
	}
	public void setRoomRate(BigDecimal roomRate) {
		this.roomRate = roomRate;
	}
	public Long getId() {
		return id;
	}
	
	@ManyToOne
	@JoinColumn(name = "customer_fk")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@ManyToOne
	@JoinColumn(name = "bill_fk")
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	@ManyToOne
	@JoinColumn(name = "room_fk")
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	
	
	
}
