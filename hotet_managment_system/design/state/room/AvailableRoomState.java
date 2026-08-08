package hotet_managment_system.design.state.room;

import hotet_managment_system.enums.RoomStatus;

public class AvailableRoomState implements RoomState {
    @Override
    public RoomStatus getStatus() {
        return RoomStatus.AVAILABLE;
    }
}
