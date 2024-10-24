import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        Manager manager = new Manager();

        Task task1 = new Task(0, "t1", StatusOfTask.NEW , "first");

        int task1Id = manager.addTask(task1);

        Epic epic1 = new Epic(0, "E1", StatusOfTask.NEW , "", new ArrayList<Integer>());
        int epic1Id = manager.addEpic(epic1);

        Subtask epic1subtask1 = new Subtask(0, "Ep1S1", StatusOfTask.NEW , "", epic1Id);
        Subtask epic1subtask2 = new Subtask(0, "Ep1S2", StatusOfTask.NEW , "", epic1Id);
        int epic1subtask1Id = manager.addSubtask(epic1subtask1);
        int epic1subtask2Id = manager.addSubtask(epic1subtask2);

        System.out.println(manager.tasks);
        System.out.println(manager.epics);
        System.out.println(manager.subtasks);
    }
}
