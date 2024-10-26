import java.util.HashMap;
import java.util.ArrayList;

public class Manager {
    protected Integer id = 0;

    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int counterId() {
        id++;
        return id;
    }


    private void epicStatus(Epic epic) {
        if (epic.getSubtaskId().isEmpty()) {
            epic.setStatus(StatusOfTask.NEW);
            return;
        }
        boolean statusNew = false;
        boolean statusInProgress = false;
        boolean statusDone = false;
        for (Integer subtaskId : epic.getSubtaskId()) {
            if (subtasks.get(subtaskId).getStatus().equals(StatusOfTask.NEW)) {
                statusNew = true;
            }
            if (subtasks.get(subtaskId).getStatus().equals(StatusOfTask.DONE)) {
                statusDone = true;
            }
            if (subtasks.get(subtaskId).getStatus().equals(StatusOfTask.IN_PROGRESS)) {
                statusInProgress = true;
            }
        }
        if (statusNew && !statusInProgress && !statusDone) {
            epic.setStatus(StatusOfTask.NEW);
        } else if (statusDone && !statusNew && !statusInProgress) {
            epic.setStatus(StatusOfTask.DONE);
        } else {
            epic.setStatus(StatusOfTask.IN_PROGRESS);
        }
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
        epicStatus(epic);
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
        epicStatus(epic);
        return id;
    }

    public void updateTask(Task task) {
        final int id = task.getId();
        final Task savedTask = tasks.get(id);
        if (savedTask == null) {
            return;
        }
        tasks.put(id, task);
    }

    public void updateEpic(Epic epic) {
        final int id = epic.getId();
        final Task savedEpic = epics.get(id);
        if (savedEpic == null) {
            return;
        }
        epics.put(id, epic);
    }

    public void updateSubtask(Subtask subtask) {
        final int id = subtask.getId();
        final Task savedSubtask = subtasks.get(id);
        if (savedSubtask == null) {
            return;
        }
        subtasks.put(id, subtask);
        Epic epic = epics.get(subtask.getEpicId());
        epicStatus(epic);
    }

    public Task getTaskById(Integer id) {
        return tasks.get(id);
    }

    public Epic getEpicById(Integer id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(Integer id) {
        return subtasks.get(id);
    }

    public void delAllTasks() {
        tasks.clear();
    }

    public void delAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void delAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtaskId().clear();
            epicStatus(epic);
        }
        subtasks.clear();
    }

    public void delTaskById(Integer id) {
        tasks.remove(id);
    }

    public void delEpicById(Integer id) {
        for (Integer subtaskId : epics.get(id).getSubtaskId()) {
            subtasks.remove(subtaskId);
        }
        epics.remove(id);
    }

    public void delSubtasksById(Integer id) {
        Epic epic = epics.get(subtasks.get(id).getEpicId());
        epic.getSubtaskId().remove(id);
        epicStatus(epic);
        subtasks.remove(id);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(this.tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(this.epics.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(this.subtasks.values());
    }

    public ArrayList<Subtask> getSubtasksOfEpic(Epic epic) {
        ArrayList<Subtask> subtasksOfEpic = new ArrayList<>();
        for (Integer subtaskId : epic.getSubtaskId()) {
            subtasksOfEpic.add(subtasks.get(subtaskId));
        }
        return subtasksOfEpic;
    }
}
