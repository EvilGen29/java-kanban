import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class InMemoryTaskManager implements TaskManager {
    protected Integer id = 0;

    Map<Integer, Task> tasks = new HashMap<>();
    Map<Integer, Epic> epics = new HashMap<>();
    Map<Integer, Subtask> subtasks = new HashMap<>();

    HistoryManager historyManager = Managers.getDefaultHistory();

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

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public Integer addTask(Task task) {
        final int id = counterId();
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    @Override
    public Integer addEpic(Epic epic) {
        final int id = counterId();
        epic.setId(id);
        epicStatus(epic);
        epics.put(id, epic);
        return id;
    }

    @Override
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

    @Override
    public void updateTask(Task task) {
        final int id = task.getId();
        final Task savedTask = tasks.get(id);
        if (savedTask == null) {
            return;
        }
        tasks.put(id, task);
    }

    @Override
    public void updateEpic(Epic epic) {
        final int id = epic.getId();
        final Task savedEpic = epics.get(id);
        if (savedEpic == null) {
            return;
        }
        epics.put(id, epic);
    }

    @Override
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

    @Override
    public Task getTaskById(Integer id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpicById(Integer id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public Subtask getSubtaskById(Integer id) {
        historyManager.add(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
    public void delAllTasks() {
        tasks.clear();
    }

    @Override
    public void delAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void delAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtaskId().clear();
            epicStatus(epic);
        }
        subtasks.clear();
    }

    @Override
    public void delTaskById(Integer id) {
        tasks.remove(id);
    }

    @Override
    public void delEpicById(Integer id) {
        for (Integer subtaskId : epics.get(id).getSubtaskId()) {
            subtasks.remove(subtaskId);
        }
        epics.remove(id);
    }

    @Override
    public void delSubtasksById(Integer id) {
        Epic epic = epics.get(subtasks.get(id).getEpicId());
        epic.getSubtaskId().remove(id);
        epicStatus(epic);
        subtasks.remove(id);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(this.tasks.values());
    }

    @Override
    public List<Epic> getEpics() {
        return new ArrayList<>(this.epics.values());
    }

    @Override
    public List<Subtask> getSubtasks() {
        return new ArrayList<>(this.subtasks.values());
    }

    @Override
    public List<Subtask> getSubtasksOfEpic(Epic epic) {
        ArrayList<Subtask> subtasksOfEpic = new ArrayList<>();
        for (Integer subtaskId : epic.getSubtaskId()) {
            subtasksOfEpic.add(subtasks.get(subtaskId));
        }
        return subtasksOfEpic;
    }
}
