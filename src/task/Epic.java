package task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<SubTask> subTasks;

    public Epic(String title, String description) {
        super(title, description);
        subTasks = new ArrayList<>();
    }

    public void setSubTasks(SubTask subTask) {
        subTasks.add(subTask);
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }


    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", subTaskSize='" + subTasks.size() + '\'' +
                ", EpicStatus=" + taskStatus +
                '}';
    }
}
