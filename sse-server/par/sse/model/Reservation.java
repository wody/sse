/**
 * 
 */
package sse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

/**
 * @author tobihammerer
 * @author cog
 * 
 */
@Entity
@Table(name = "reservation")
@NamedQueries(value = {
        @NamedQuery(name = "arrivals", query = "select r from Reservation r where r.fromDate = :arrival"),
        @NamedQuery(name = "departures", query = "select r from Reservation r where r.toDate = :departure")
})
public class Reservation implements Serializable {
	private static final long serialVersionUID = 7007420404145906823L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double discount;
	private BigDecimal roomRate;
    private Integer occupacyAdult;
    private Integer occupacyChild;
//	@Column
//	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDate")
//	private LocalDate fromDate;
//	@Column
//	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDate")
//	private LocalDate toDate;
    private Date fromDate;
    private Date toDate;

	@ManyToOne
	@JoinColumn(name = "customer_fk")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "bill_fk")
	private Bill bill;

	@ManyToOne
	@JoinColumn(name = "room_fk")
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

//    public LocalDate getFromDate() {
//        return fromDate;
//    }
//
//    public void setFromDate(LocalDate fromDate) {
//        this.fromDate = fromDate;
//    }
//
//    public LocalDate getToDate() {
//        return toDate;
//    }
//
//    public void setToDate(LocalDate toDate) {
//        this.toDate = toDate;
//    }
	
		
	
	

    public Customer getCustomer() {
		return customer;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

    public Integer getOccupacyAdult() {
        return occupacyAdult;
    }

    public void setOccupacyAdult(Integer occupacyAdult) {
        this.occupacyAdult = occupacyAdult;
    }

    public Integer getOccupacyChild() {
        return occupacyChild;
    }

    public void setOccupacyChild(Integer occupacyChild) {
        this.occupacyChild = occupacyChild;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
