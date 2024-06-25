import java.util.HashMap;

public class TaskManager {
    public static int count = 1;
    private HashMap<Integer, Task> mapOfTask;
    private HashMap<Integer, Epic> mapOfEpics;

    public TaskManager() {
        mapOfTask = new HashMap<>();
        mapOfEpics = new HashMap<>();

    }

    public void createTask(Task task) {
        mapOfTask.put(task.getId(), task);
    }


    public void createEpic(Epic epic) {
        mapOfEpics.put(epic.getId(), epic);
    }

    public void updateTask(Task task) {
        mapOfTask.get(task.getId()).setTitle(task.getTitle());
        mapOfTask.get(task.getId()).setDescription(task.getDescription());
        if (mapOfTask.get(task.getId()).getTaskStatus().equals(TaskStatus.NEW)) {
            mapOfTask.get(task.getId()).setTaskStatus(TaskStatus.IN_PROGRESS);
        } else if (mapOfTask.get(task.getId()).getTaskStatus().equals(TaskStatus.IN_PROGRESS)) {
            mapOfTask.get(task.getId()).setTaskStatus(TaskStatus.DONE);
        } else {
            System.out.println("Задача завершена");
        }
    }

    public void updateSubTask(SubTask subTask) {
        int subTaskId = subTask.getId();
        for (Epic epic : mapOfEpics.values()) {
            for (SubTask subTask1 : epic.getMapOfSubTasks().values()) {
                if (subTask1.getId() == subTaskId) {
                    int epicId = epic.getId();
                    subTask1.setTitle(subTask.getTitle());
                    subTask1.setDescription(subTask.getDescription());

                    if (subTask1.getTaskStatus().equals(TaskStatus.NEW)) {
                        subTask1.setTaskStatus(TaskStatus.IN_PROGRESS);
                    } else if (subTask1.getTaskStatus().equals(TaskStatus.IN_PROGRESS)) {
                        subTask1.setTaskStatus(TaskStatus.DONE);
                    }
                    mapOfEpics.get(epicId).checkEpicStatus();
                }
            }
        }
    }

    public HashMap<Integer, Task> getMapOfTasks() {
        return mapOfTask;
    }

    public HashMap<Integer, Epic> getMapOfEpics() {
        return mapOfEpics;
    }

    public HashMap<Integer, SubTask> getMapOfSubTasksByEpic(Epic epic) {
        return epic.getMapOfSubTasks();
    }

    public void deleteAllTasks() {
        mapOfTask.clear();
        System.out.println("Задачи удалены \n");
    }

    public void deleteAllEpics() {
        mapOfEpics.clear();
        System.out.println("Эпики удалены \n");
    }

    public String getTaskById(int id) {
        if (mapOfTask.containsKey(id)) {
            return mapOfTask.get(id).toString();
        } else {
            return "Задача не найдена \n";
        }
    }

    public String getEpicByID(int id) {
        if (mapOfEpics.containsKey(id)) {
            return mapOfEpics.get(id).toString();
        } else {
            return "Эпик не найден! \n";
        }
    }

    public void deleteTaskById(int id) {
        if (mapOfTask.containsKey(id)) {
            mapOfTask.remove(id);
            System.out.printf("Задача с id - %d удалена успешно! \n", id);
        } else {
            System.out.printf("Задача с id - %d не найдена! \n", id);
        }
    }

    public void deleteEpicById(int id) {
        if (mapOfEpics.containsKey(id)) {
            mapOfEpics.remove(id);
            System.out.printf("Эпик с id - %d удален успешно! \n", id);
        } else {
            System.out.printf("Эпик с id - %d не найден! \n", id);
        }
    }
}