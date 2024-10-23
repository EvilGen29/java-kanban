public class Subtask extends Task {

    public Integer epicId;

    public Subtask(Integer id, TypeOfTask type, String name, StatusOfTask status, String description, Integer epicId) {
        super(id, type, name, status, description);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }
}
