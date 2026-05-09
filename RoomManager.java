import java.util.ArrayList;
import java.util.List;

public class RoomManager {

    private List<StudyRoom> rooms = new ArrayList<>();

    public StudyRoom createRoom(String name) {
        StudyRoom r = new StudyRoom(name);
        rooms.add(r);
        return r;
    }

    public StudyRoom findRoom(String name) {
        for (StudyRoom r : rooms) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        return null;
    }
}