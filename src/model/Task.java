package model;

public class Task {
    protected Integer id;
    protected TypeOfTask type;
    protected String name;
    protected StatusOfTask status;
    protected String description;

    public Task(Integer id, TypeOfTask type, String name, StatusOfTask status, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.status = status;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusOfTask getStatus() {
        return status;
    }

    public void setStatus(StatusOfTask status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeOfTask  getType() {
        return type;
    }

    public void setType(TypeOfTask type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "model.Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}';
    }

}
