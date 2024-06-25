public class SubTask extends Task {
    public SubTask(String title, String description) {
        super(title, description);
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + super.getId() +
                ", title='" + super.getTitle() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", subTaskStatus=" + super.getTaskStatus() +
                '}';
    }
}
