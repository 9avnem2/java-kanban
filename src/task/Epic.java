package task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    protected List<Integer> subTasks;

    public Epic(String title, String description) {
        super(title, description);
        subTasks = new ArrayList<>();
    }

    public List<Integer> getSubTasks() {
        return subTasks;
    }


    @Override
    public String toString() {
        return "Epic{" + "id=" + id + ", title='" + title + '\'' + ", description='" + description + '\'' + ", subTaskSize='" + subTasks.size() + '\'' + ", EpicStatus=" + taskStatus + '}';
    }

    public void removeSubTask(int id) {
        for (Integer subTaskId : subTasks) {
            if (subTaskId == id) {
                subTasks.remove(subTaskId);
                return;
            }
        }
    }
}
