import java.util.HashMap;
import java.util.ArrayList;

public class Manager {
    Integer id = 0;

    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int counterId() {
        id++;
        return id;
    }

    public Integer addTask(Task task) {
        final int id = counterId();
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public Integer addEpic(Epic epic) {
        final int id = counterId();
        epic.setId(id);
        epics.put(id, epic);
        return id;
    }

    public Integer addSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            return null;
        }
        final int id = counterId();
        subtask.setId(id);
        subtasks.put(id, subtask);
        epic.getSubtaskId().add(id);
        return id;
    }
}
