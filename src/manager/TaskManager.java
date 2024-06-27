package manager;

import java.util.HashMap;
import java.util.List;

import task.Epic;
import task.SubTask;
import task.Task;
import task.TaskStatus;

public class TaskManager {
    private static int count = 1;

    public static int getCount() {
        return count++;
    }

    private final HashMap<Integer, Task> mapOfTasks;
    private final HashMap<Integer, Epic> mapOfEpics;
    private final HashMap<Integer, SubTask> mapOfSubTasks;

    public TaskManager() {
        mapOfTasks = new HashMap<>();
        mapOfEpics = new HashMap<>();
        mapOfSubTasks = new HashMap<>();
    }

    public List<Task> getAllTasks() {
        return mapOfTasks.values().stream().toList();
    }

    public List<Epic> getAllEpics() {
        return mapOfEpics.values().stream().toList();
    }

    public List<SubTask> getAllSubTasks() {
        return mapOfSubTasks.values().stream().toList();
    }

    public void deleteAllTasks() {
        mapOfTasks.clear();
        System.out.println("Все задачи удалены! \n");
    }

    public void deleteAllEpics() {
        mapOfEpics.clear();
        System.out.println("Все эпики удалены! \n");
    }

    public void deleteAllSubTasks() {
        mapOfSubTasks.clear();
        System.out.println("Все саб-таски удалены! \n");
    }

    public Task getTaskById(int id) {
        return mapOfTasks.getOrDefault(id, null);
    }

    public Epic getEpicById(int id) {
        return mapOfEpics.getOrDefault(id, null);
    }

    public SubTask getSubTaskById(int id) {
        return mapOfSubTasks.getOrDefault(id, null);
    }

    public void createTask(Task task) {
        mapOfTasks.put(task.getId(), task);
    }

    public void createEpic(Epic epic) {
        mapOfEpics.put(epic.getId(), epic);
    }

    public void createSubTask(SubTask subTask) {
        mapOfSubTasks.put(subTask.getId(), subTask);
        Epic epic = mapOfEpics.get(subTask.getEpicId());
        epic.setSubTasks(subTask);
        if (epic.getTaskStatus().equals(TaskStatus.DONE)) {
            epic.setTaskStatus(TaskStatus.NEW);
        }
    }

    public void updateTask(Task task) {
        mapOfTasks.put(task.getId(), task);
    }

    public void updateSubTask(SubTask subTask) {
        int subTaskId = subTask.getId();
        mapOfSubTasks.put(subTaskId, subTask);
        checkEpicStatus(subTask.getEpicId());
    }

    private void checkEpicStatus(int epicId) {
        boolean isDone = true;
        Epic epic = mapOfEpics.get(epicId);
        for (SubTask subTask : mapOfEpics.get(epicId).getSubTasks()) {
            if (!subTask.getTaskStatus().equals(TaskStatus.DONE)) {
                epic.setTaskStatus(TaskStatus.IN_PROGRESS);
                isDone = false;
                break;
            }
        }
        if (isDone) {
            epic.setTaskStatus(TaskStatus.DONE);
        }
    }

    public void deleteTaskById(int id) {
        if (mapOfTasks.containsKey(id)) {
            mapOfTasks.remove(id);
            System.out.printf("Задача с id - %d удалена \n", id);
        } else {
            System.out.printf("Задача с id - %d не найдена \n", id);
        }
    }

    public void deleteEpicById(int id) {
        if (mapOfEpics.containsKey(id)) {
            for (int i = 0; i < mapOfEpics.get(id).getSubTasks().size(); i++) {
                mapOfEpics.get(id).getSubTasks().remove(i);
            }
            mapOfEpics.remove(id);
            System.out.printf("Эпик с id - %d удален \n", id);
        } else {
            System.out.printf("Эпик с id - %d не найден \n", id);
        }
    }

    public void deleteSubTaskById(int id) {
        SubTask subTask = mapOfSubTasks.get(id);
        int epicId = subTask.getEpicId();
        Epic epic = mapOfEpics.get(epicId);

        for (int i = 0; i < epic.getSubTasks().size(); i++) {
            if (epic.getSubTasks().get(i).getId() == id) {
                epic.getSubTasks().remove(i);
            }
        }
        mapOfSubTasks.remove(id);
        if (epic.getSubTasks().isEmpty()) {
            epic.setTaskStatus(TaskStatus.NEW);
        }
    }

    public List<SubTask> getSubTasksByEpic(Epic epic) {
        return mapOfEpics.getOrDefault(epic.getId(), null).getSubTasks();
    }

    public void printEmAll() {
        System.out.println("Все задачи");
        System.out.println(getAllTasks());
        System.out.println("Все эпики");
        System.out.println(getAllEpics());
        System.out.println("Все саб-таски");
        System.out.println(getAllSubTasks());
    }


}
