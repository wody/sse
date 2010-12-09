package sse.client.dto;

import java.math.BigDecimal;

import sse.model.Room;

public class RoomReservationDTO {
	
	private Room room;
	private BigDecimal selectedRate;
	private Integer rateTransformer;
	private Integer occupacyAdult;
	private Integer occupacyChild;
	
	
	public RoomReservationDTO() {
		super();
	}

	public RoomReservationDTO(Room room){
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public BigDecimal getSelectedRate() {
		return selectedRate;
	}
	public void setSelectedRate(BigDecimal selectedRate) {
		
		this.selectedRate = selectedRate;
		
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

	public Integer getRateTransformer() {
		return rateTransformer;
	}

	public void setRateTransformer(Integer rateTransformer) {
		this.rateTransformer = rateTransformer;

		switch (rateTransformer) {
		case 1:
			this.selectedRate = room.getRateSingle();
			this.occupacyAdult = 1;
			this.occupacyChild = 0;
			break;
		case 2:
			this.selectedRate = room.getRateDouble();
			this.occupacyAdult = 2;
			this.occupacyChild = 0;
			break;
		case 3:
			this.selectedRate = room.getRateTriple();
			this.occupacyAdult = 3;
			this.occupacyChild = 0;
			break;
		case 4:
			this.selectedRate = room.getRateSingleOneChild();
			this.occupacyAdult = 1;
			this.occupacyChild = 1;
			break;
		case 5:
			this.selectedRate = room.getRateSingleTwoChildren();
			this.occupacyAdult = 1;
			this.occupacyChild = 2;
			break;
		case 6:
			this.selectedRate = room.getRateDoubleOneChild();
			this.occupacyAdult = 2;
			this.occupacyChild = 1;
			break;
		default:
			break;
		}
	}
	
	
	
	
	

}
