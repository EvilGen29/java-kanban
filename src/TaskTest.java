import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    TaskManager taskManager = Managers.getDefault();
    HistoryManager historyManager = Managers.getDefaultHistory();

    Task t1 = new Task(0,"t1",StatusOfTask.NEW,"d");
    int t1Id = taskManager.addTask(t1);

    Epic epic1 = new Epic(0, "E1",StatusOfTask.NEW, "", new ArrayList<Integer>());
    int epic1Id = taskManager.addEpic(epic1);

    Subtask epic1subtask1 = new Subtask(0, "Ep1S1", StatusOfTask.NEW , "", epic1Id);
    int epic1subtask1Id = taskManager.addSubtask(epic1subtask1);

    @Test
    void testTaskSavedEqualsByIdSize() {
        Task taskT1 = taskManager.getTaskById(t1Id);
        final int taskId = taskManager.addTask(taskT1);

        final Task savedTask = taskManager.getTaskById(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(t1, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(2, tasks.size(), "Неверное количество задач.");
        assertEquals(taskT1, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void testEpicSavedEqualsByIdSize() {
        Epic epicT1 = taskManager.getEpicById(epic1Id);
        final int epicId = taskManager.addEpic(epicT1);

        final Task savedEpic = taskManager.getEpicById(epicId);
        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(epic1, savedEpic, "Задачи не совпад0ают.");

        List<Epic> savedEpics = taskManager.getEpics();
        assertNotNull(savedEpics, "Задачи на возвращаются.");
        assertEquals(2, savedEpics.size(), "Неверное количество задач.");
        assertEquals(epicT1, savedEpics.get(1), "Задачи не совпадают.");
    }

    @Test
    void findById() {
        assertEquals(taskManager.getTaskById(t1Id),taskManager.getTaskById(1) , "Неверно задан id");
        assertEquals(taskManager.getEpicById(epic1Id),taskManager.getEpicById(2) , "Неверно задан id");
        assertEquals(taskManager.getSubtaskById(epic1subtask1Id),taskManager.getSubtaskById(3) , "Неверно задан id");
    }

    @Test
    void immutabilityTask() {

        Task t2 = new Task(0,"t2",StatusOfTask.NEW,"e");
        String descr1 = t2.getDescription();
        String name1 = t2.getName();
        StatusOfTask status1 = t2.getStatus();
        int t2Id = taskManager.addTask(t2);
        assertEquals(name1, taskManager.getTaskById(t2Id).getName() ,"Поле изменилось");
        assertEquals(status1, taskManager.getTaskById(t2Id).getStatus() ,"Поле изменилось");
        assertEquals(descr1, taskManager.getTaskById(t2Id).getDescription() ,"Поле изменилось");
    }

    @Test
    void historyAdd() {
        historyManager.add(t1);
        historyManager.add(epic1);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(2, history.size(), "История не пустая.");
    }

    //доп
    @Test
    public void addHistoryUpdateChange() {
        int t1Id = taskManager.addTask(t1);
        taskManager.getTaskById(t1Id);
        Task task1upd1 = new Task(t1Id, "t1.1",StatusOfTask.DONE, "upd");
        taskManager.updateTask(task1upd1);
        taskManager.getTaskById(t1Id);
        assertEquals(taskManager.getHistory().size(), 2, "В истории менее 2 задач.");
        assertEquals(taskManager.getHistory().get(0).id,taskManager.getHistory().get(1).id , "id задач разные");
        assertNotEquals(taskManager.getHistory().get(0).getName(),taskManager.getHistory().get(1).getName() , "Сходятся имена");
        assertNotEquals(taskManager.getHistory().get(0).getDescription(),taskManager.getHistory().get(1).getDescription(), "Сходятся описания");
        assertNotEquals(taskManager.getHistory().get(0).getStatus(),taskManager.getHistory().get(1).getStatus(), "Сходятся Статусы");
    }


}