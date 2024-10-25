import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        Manager manager = new Manager();

        Task task1 = new Task(0, "t1", StatusOfTask.NEW , "first");
        Task task2 = new Task(0, "t2", StatusOfTask.NEW , "second");
        int task1Id = manager.addTask(task1);
        int task2Id = manager.addTask(task2);

        Epic epic1 = new Epic(0, "E1",StatusOfTask.NEW, "", new ArrayList<Integer>());
        Epic epic2 = new Epic(0, "E2",StatusOfTask.NEW, "", new ArrayList<Integer>());
        int epic1Id = manager.addEpic(epic1);
        int epic2Id = manager.addEpic(epic2);

        Subtask epic1subtask1 = new Subtask(0, "Ep1S1", StatusOfTask.NEW , "", epic1Id);
        Subtask epic1subtask2 = new Subtask(0, "Ep1S2", StatusOfTask.NEW , "", epic1Id);
        Subtask epic2subtask1 = new Subtask(0, "Ep2S1", StatusOfTask.NEW , "", epic2Id);
        int epic1subtask1Id = manager.addSubtask(epic1subtask1);
        int epic1subtask2Id = manager.addSubtask(epic1subtask2);
        int epic2subtask1Id = manager.addSubtask(epic2subtask1);

        System.out.println(manager.getTasks());
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubtasks());

        System.out.println("Происходит обновление задач:... ");

        Task task1upd1 = new Task(task1Id, "t1.1",StatusOfTask.DONE, "upd");
        Task task2upd1 = new Task(task2Id, "t2.1",StatusOfTask.IN_PROGRESS, "upd");
        manager.updateTask(task1upd1);
        manager.updateTask(task2upd1);

        Subtask epic1subtask1upd1 = new Subtask(epic1subtask1Id,"Ep1S1.1", StatusOfTask.DONE, "upd", epic1Id );
        Subtask epic1subtask2upd1 = new Subtask(epic1subtask2Id,"Ep1S2.1", StatusOfTask.DONE, "upd", epic1Id );
        Subtask epic2subtask1upd1 = new Subtask(epic2subtask1Id,"Ep2S1.1", StatusOfTask.IN_PROGRESS, "upd", epic2Id );
        manager.updateSubtask(epic1subtask1upd1);
        manager.updateSubtask(epic1subtask2upd1);
        manager.updateSubtask(epic2subtask1upd1);

        System.out.println("Получили после обновления: ");
        System.out.println(manager.getTasks());
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubtasks());

        System.out.println("Удаляем обычную задачу: ");
        manager.delTaskById(task1Id);
        System.out.println("Проверяем список обычных задач: ");
        System.out.println(manager.getTasks());

        System.out.println("Удаляем эпик задачу: ");
        manager.delEpicById(epic1Id);
        System.out.println("Проверяем список эпик задач и подзадач: ");
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubtasks());

    }

}
