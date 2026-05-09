import java.util.ArrayList;
import java.util.List;

public class StudyRoom {

    private String name;
    private List<User> users = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

    public StudyRoom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addMessage(String msg) {
        messages.add(msg);
    }
}