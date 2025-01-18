package model;

import java.util.List;

public class Epic extends Task {
    public List<Integer> subtaskId;

    public Epic(Integer id,TypeOfTask type, String name, StatusOfTask status, String description, List<Integer> subtaskId) {
        super(id,type, name,status, description);
        this.subtaskId = subtaskId;
    }

    public List<Integer> getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(List<Integer> subtaskId) {
        this.subtaskId = subtaskId;
    }

    @Override
    public String toString() {
        return "model.Epic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                ", subtaskId=" + subtaskId +
                '}';
    }

}
