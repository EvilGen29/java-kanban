import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = Managers.getDefault();

        System.out.println("1. Этап: ");
        System.out.println("Создадим 2 обычные задачи, 1 эпик с 3 подзадачами и 1 эпик без подзадач:");
        int task1 = taskManager.addTask(new Task(0,"t1",StatusOfTask.NEW,"first"));
        int task2 = taskManager.addTask(new Task(0,"t2",StatusOfTask.NEW,"second"));

        int epic1 = taskManager.addEpic(new Epic(0,"E1",StatusOfTask.NEW,"",new ArrayList<Integer>()));
        int epic1subtask1 = taskManager.addSubtask(new Subtask(0,"Ep1S1",StatusOfTask.NEW,"",epic1));
        int epic1subtask2 = taskManager.addSubtask(new Subtask(0,"Ep1S2",StatusOfTask.IN_PROGRESS,"",epic1));
        int epic1subtask3 = taskManager.addSubtask(new Subtask(0,"Ep1S3",StatusOfTask.IN_PROGRESS,"",epic1));
        int epic2 = taskManager.addEpic(new Epic(0,"E2",StatusOfTask.NEW,"",new ArrayList<Integer>()));

        System.out.println("2. Этап: ");
        System.out.println("Запросим созданные задачи несколько раз в разном порядке:");
        taskManager.getTaskById(task1);
        taskManager.getTaskById(task1);
        taskManager.getTaskById(task2);
        taskManager.getEpicById(epic1);
        taskManager.getSubtaskById(epic1subtask1);
        taskManager.getSubtaskById(epic1subtask2);
        taskManager.getSubtaskById(epic1subtask3);
        taskManager.getSubtaskById(epic1subtask2);
        taskManager.getEpicById(epic2);
        taskManager.getEpicById(epic2);

        System.out.println("3. Этап: ");
        System.out.println("Просмотрим историю:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }

        System.out.println("4. Этап: ");
        System.out.println("Удалим задачу task1:");
        taskManager.delTaskById(task1);

        System.out.println("5. Этап: ");
        System.out.println("Просмотрим историю:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }

        System.out.println("6. Этап: ");
        System.out.println("Удалим эпик epic1 с тремя его подзадачами:");
        taskManager.delEpicById(epic1);

        System.out.println("7. Этап: ");
        System.out.println("Просмотрим историю:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }
    }
}
