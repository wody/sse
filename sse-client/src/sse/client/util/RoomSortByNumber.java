/**
 * 
 */
package sse.client.util;

import java.util.Comparator;

import sse.model.Room;

/**
 * @author Tobi
 *
 */
public class RoomSortByNumber implements Comparator<Room> {

	@Override
	public int compare(Room r1, Room r2) {
		return r1.getRoomNumber().compareTo(r2.getRoomNumber());
	}

}
