import java.util.*;

public class StudyRoomApp {

    private Scanner scanner = new Scanner(System.in);
    private UserManager userManager = new UserManager();
    private RoomManager roomManager = new RoomManager();

    public static void main(String[] args) {
        new StudyRoomApp().start();
    }

    public void start() {
        System.out.println("=== Study Room App ===");

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> login();
                case "2" -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }

    private void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        User user = userManager.login(username);
        System.out.println("Welcome " + user.getUsername());

        roomMenu(user);
    }

    private void roomMenu(User user) {
        while (true) {
            System.out.println("\n=== Rooms ===");
            System.out.println("1. Create Room");
            System.out.println("2. Join Room");
            System.out.println("3. Logout");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> createRoom(user);
                case "2" -> joinRoom(user);
                case "3" -> { return; }
            }
        }
    }

    private void createRoom(User user) {
        System.out.print("Room name: ");
        String name = scanner.nextLine();

        StudyRoom room = roomManager.createRoom(name);
        room.addUser(user);

        workspace(user, room);
    }

    private void joinRoom(User user) {
        System.out.print("Room name: ");
        String name = scanner.nextLine();

        StudyRoom room = roomManager.findRoom(name);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        room.addUser(user);
        workspace(user, room);
    }

    private void workspace(User user, StudyRoom room) {

        while (true) {
            System.out.println("\n=== " + room.getName() + " ===");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Complete");
            System.out.println("5. Chat");
            System.out.println("6. Leave");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> viewTasks(room);
                case "2" -> addTask(room);
                case "3" -> deleteTask(room);
                case "4" -> completeTask(room);
                case "5" -> chat(room, user);
                case "6" -> {
                    room.removeUser(user);
                    return;
                }
            }
        }
    }

    private void viewTasks(StudyRoom room) {
        List<Task> tasks = room.getTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + ". " + t.getTitle() +
                    (t.isCompleted() ? " [DONE]" : ""));
        }
    }

    private void addTask(StudyRoom room) {
        System.out.print("Task title: ");
        String title = scanner.nextLine();

        room.addTask(new Task(title));
    }

    private void deleteTask(StudyRoom room) {
        viewTasks(room);

        System.out.print("Task # to delete: ");
        int i = Integer.parseInt(scanner.nextLine()) - 1;

        if (i >= 0 && i < room.getTasks().size()) {
            room.getTasks().remove(i);
        }
    }

    private void completeTask(StudyRoom room) {
        viewTasks(room);

        System.out.print("Task # to mark complete: ");
        int i = Integer.parseInt(scanner.nextLine()) - 1;

        if (i >= 0 && i < room.getTasks().size()) {
            room.getTasks().get(i).setCompleted(true);
        }
    }

    private void chat(StudyRoom room, User user) {
        System.out.print("Message: ");
        String msg = scanner.nextLine();

        room.addMessage(user.getUsername() + ": " + msg);

        System.out.println("Chat:");
        for (String m : room.getMessages()) {
            System.out.println(m);
        }
    }
}