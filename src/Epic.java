import java.util.ArrayList;

public class Epic extends Task {
    public ArrayList<Integer> subtaskId;

    public Epic(Integer id, String name, StatusOfTask status, String description, ArrayList<Integer> subtaskId) {
        super(id, name,status, description);
        this.subtaskId = subtaskId;
    }

    public ArrayList<Integer> getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(ArrayList<Integer> subtaskId) {
        this.subtaskId = subtaskId;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                ", subtaskId=" + subtaskId +
                '}';
    }

}
