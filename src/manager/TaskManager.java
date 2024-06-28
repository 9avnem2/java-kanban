package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import task.Epic;
import task.SubTask;
import task.Task;
import task.TaskStatus;

public class TaskManager {
    private int count = 1;

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
        for (Epic epic : mapOfEpics.values()) {
            epic.getSubTasks().clear();
            checkEpicStatus(epic.getId());
        }
        System.out.println("Все саб-таски удалены! \n");
    }

    public Task getTaskById(int id) {
        return mapOfTasks.get(id);
    }

    public Epic getEpicById(int id) {
        return mapOfEpics.get(id);
    }

    public SubTask getSubTaskById(int id) {
        return mapOfSubTasks.get(id);
    }

    public Task createTask(Task task) {
        task.setId(getCount());
        mapOfTasks.put(task.getId(), task);
        return task;
    }

    public Epic createEpic(Epic epic) {
        epic.setId(getCount());
        mapOfEpics.put(epic.getId(), epic);
        return epic;
    }

    public SubTask createSubTask(SubTask subTask) {
        int epicId = subTask.getEpicId();
        Epic epic = mapOfEpics.get(epicId);
        if (epic == null) {
            return null;
        }
        subTask.setId(getCount());
        mapOfSubTasks.put(subTask.getId(), subTask);
        epic.getSubTasks().add(subTask.getId());
        checkEpicStatus(subTask.getEpicId());
        return subTask;
    }

    public void updateTask(Task task) {
        mapOfTasks.put(task.getId(), task);
    }

    public void updateSubTask(SubTask subTask) {
        int subTaskId = subTask.getId();
        mapOfSubTasks.put(subTaskId, subTask);
        checkEpicStatus(subTask.getEpicId());
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
        final Epic epic = mapOfEpics.remove(id);
        for (Integer subTaskId : epic.getSubTasks()) {
            mapOfSubTasks.remove(subTaskId);
        }
        System.out.printf("Эпик с id - %d удален \n", id);
    }

    public void deleteSubTaskById(int id) {
        SubTask subTask = mapOfSubTasks.remove(id);
        if (subTask == null) {
            return;
        }
        Epic epic = mapOfEpics.get(subTask.getEpicId());
        epic.removeSubTask(id);
        System.out.printf("Саб-таск с id - %d удален \n", id);
        checkEpicStatus(epic.getId());
    }

    public List<SubTask> getSubTasksByEpic(int id) {
        List<Integer> epicSubTasksId = mapOfEpics.get(id).getSubTasks();
        List<SubTask> subTasks = new ArrayList<>();

        for (Integer subTaskId : epicSubTasksId) {
            subTasks.add(mapOfSubTasks.get(subTaskId));
        }
        return subTasks;
    }

    public void printEmAll() {
        System.out.println("Все задачи");
        System.out.println(getAllTasks());
        System.out.println("Все эпики");
        System.out.println(getAllEpics());
        System.out.println("Все саб-таски");
        System.out.println(getAllSubTasks());
    }

    private void checkEpicStatus(int epicId) {
        int newSubTaskCounter = 0;
        int doneSubTaskCounter = 0;

        Epic epic = mapOfEpics.get(epicId);
        List<Integer> epicSubTasksId = epic.getSubTasks();
        if (mapOfSubTasks.isEmpty()) {
            epic.setTaskStatus(TaskStatus.NEW);
        } else {
            for (Integer subTaskId : epicSubTasksId) {
                final String currentStatus = mapOfSubTasks.get(subTaskId).getTaskStatus().toString();
                if (currentStatus.equals(TaskStatus.NEW.toString())) {
                    newSubTaskCounter++;
                } else if (currentStatus.equals(TaskStatus.IN_PROGRESS.toString())) {
                    epic.setTaskStatus(TaskStatus.IN_PROGRESS);
                    return;
                } else {
                    doneSubTaskCounter++;
                }
            }
            int size = epicSubTasksId.size();
            if (newSubTaskCounter == size) {
                epic.setTaskStatus(TaskStatus.NEW);
            } else if (doneSubTaskCounter == size) {
                epic.setTaskStatus(TaskStatus.DONE);
            } else {
                epic.setTaskStatus(TaskStatus.IN_PROGRESS);
            }
        }

    }

    private int getCount() {
        return count++;
    }


}
