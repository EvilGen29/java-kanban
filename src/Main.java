import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = Managers.getDefault();

        Task task1 = new Task(0, "t1", StatusOfTask.NEW , "first");
        Task task2 = new Task(0, "t2", StatusOfTask.NEW , "second");
        int task1Id = taskManager.addTask(task1);
        int task2Id = taskManager.addTask(task2);

        Epic epic1 = new Epic(0, "E1",StatusOfTask.NEW, "", new ArrayList<Integer>());
        Epic epic2 = new Epic(0, "E2",StatusOfTask.NEW, "", new ArrayList<Integer>());
        int epic1Id = taskManager.addEpic(epic1);
        int epic2Id = taskManager.addEpic(epic2);

        Subtask epic1subtask1 = new Subtask(0, "Ep1S1", StatusOfTask.NEW , "", epic1Id);
        Subtask epic1subtask2 = new Subtask(0, "Ep1S2", StatusOfTask.IN_PROGRESS , "", epic1Id);
        Subtask epic2subtask1 = new Subtask(0, "Ep2S1", StatusOfTask.NEW , "", epic2Id);
        int epic1subtask1Id = taskManager.addSubtask(epic1subtask1);
        int epic1subtask2Id = taskManager.addSubtask(epic1subtask2);
        int epic2subtask1Id = taskManager.addSubtask(epic2subtask1);

        System.out.println("ПОКАЗЫВАЕМ ЗАДАЧИ: ");

        System.out.println("Задачи:");
        for (Task task : taskManager.getTasks()) {
            System.out.println(taskManager.getTaskById(task.getId()));
        }
        System.out.println("Эпики:");
        for (Task epic : taskManager.getEpics()) {
            System.out.println(taskManager.getEpicById(epic.getId()));

        }
        System.out.println("Подзадачи:");
        for (Task subtask : taskManager.getSubtasks()) {
            System.out.println(taskManager.getSubtaskById(subtask.getId()));
        }


        System.out.println("История:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }
    }
}
