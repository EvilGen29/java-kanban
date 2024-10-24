public class Subtask extends Task {

    private Integer epicId;

    public Subtask(Integer id, String name, StatusOfTask status, String description, Integer epicId) {
        super(id, name, status, description);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                ", epicId=" + epicId +
                '}';
    }
}
