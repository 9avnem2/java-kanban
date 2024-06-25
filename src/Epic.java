import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Integer, SubTask> mapOfSubTasks;

    public Epic(String title, String description) {
        super(title, description);
        mapOfSubTasks = new HashMap<>();
    }

    public void createSubTask(SubTask subTask) {
        mapOfSubTasks.put(subTask.getId(), subTask);
    }

    public HashMap<Integer, SubTask> getMapOfSubTasks() {
        return mapOfSubTasks;
    }

    public String getSubTaskById(int id) {
        if (mapOfSubTasks.containsKey(id)) {
            return mapOfSubTasks.get(id).toString();
        } else {
            return "Cаб-таск не найден!` \n";
        }
    }

    public void deleteAllSubTasks() {
        mapOfSubTasks.clear();
    }

    public void deleteSubTaskById(int id) {
        if (mapOfSubTasks.containsKey(id)) {
            mapOfSubTasks.remove(id);
        } else {
            System.out.printf("Саб-таск с такими id - %d не найден \n", id);
        }
    }

    public void checkEpicStatus() {
        boolean isDone = true;

        for (SubTask subTask : mapOfSubTasks.values()) {
            if (!subTask.getTaskStatus().equals(TaskStatus.DONE)) {
                isDone = false;
            }
            if (subTask.getTaskStatus().equals(TaskStatus.IN_PROGRESS)) {
                this.setTaskStatus(TaskStatus.IN_PROGRESS);
                isDone = false;
                break;
            }
        }
        if (isDone) {
            this.setTaskStatus(TaskStatus.DONE);
        }
    }


    @Override
    public String toString() {
        return "Epic{" +
                "id=" + super.getId() +
                ", title='" + super.getTitle() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", epicStatus=" + super.getTaskStatus() +
                '}';
    }
}
