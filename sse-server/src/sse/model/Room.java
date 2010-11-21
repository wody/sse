/**
 * 
 */
package sse.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author tobihammerer
 *
 */
@Entity
@Table(name="room")
public class Room {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer occupancy;
	private BigDecimal rateSingle;
	private BigDecimal rateDouble;
	private BigDecimal rateTriple;
	private BigDecimal rateSingleOneChild;
	private BigDecimal rateSingleTwoChildren;
	private BigDecimal rateDoubleOneChild;
	private List<Reservation> reservations;
	
	public Integer getOccupancy() {
		return occupancy;
	}
	public void setOccupancy(Integer occupancy) {
		this.occupancy = occupancy;
	}
	public BigDecimal getRateSingle() {
		return rateSingle;
	}
	public void setRateSingle(BigDecimal rateSingle) {
		this.rateSingle = rateSingle;
	}
	public BigDecimal getRateDouble() {
		return rateDouble;
	}
	public void setRateDouble(BigDecimal rateDouble) {
		this.rateDouble = rateDouble;
	}
	public BigDecimal getRateTriple() {
		return rateTriple;
	}
	public void setRateTriple(BigDecimal rateTriple) {
		this.rateTriple = rateTriple;
	}
	public BigDecimal getRateSingleOneChild() {
		return rateSingleOneChild;
	}
	public void setRateSingleOneChild(BigDecimal rateSingleOneChild) {
		this.rateSingleOneChild = rateSingleOneChild;
	}
	public BigDecimal getRateSingleTwoChildren() {
		return rateSingleTwoChildren;
	}
	public void setRateSingleTwoChildren(BigDecimal rateSingleTwoChildren) {
		this.rateSingleTwoChildren = rateSingleTwoChildren;
	}
	public BigDecimal getRateDoubleOneChild() {
		return rateDoubleOneChild;
	}
	public void setRateDoubleOneChild(BigDecimal rateDoubleOneChild) {
		this.rateDoubleOneChild = rateDoubleOneChild;
	}
	public Long getId() {
		return id;
	}
	
	@OneToMany
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
	
	

	
}
