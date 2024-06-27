import manager.TaskManager;
import task.Epic;
import task.SubTask;
import task.Task;
import task.TaskStatus;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("Первая задача", "Встать");
        Task task2 = new Task("Вторая задача", "Умыться");
        taskManager.createTask(task);
        taskManager.createTask(task2);

        Epic epic = new Epic("Pervii", "Pereezd");
        taskManager.createEpic(epic);
        taskManager.createSubTask(new SubTask("Pervii", "Sobrat vehsi", epic.getId()));
        taskManager.createSubTask(new SubTask("Pervii", "Sobrat ne veshi", epic.getId()));

        Epic epic2 = new Epic("Vtorii", "Prava");
        taskManager.createEpic(epic2);
        taskManager.createSubTask(new SubTask("Vtorii", "Avtoshkola", epic2.getId()));

        taskManager.printEmAll();
        System.out.println("меняем состояние");
        task.setTaskStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateTask(task);
        task2.setTaskStatus(TaskStatus.DONE);
        taskManager.updateTask(task2);

        SubTask subTask = taskManager.getSubTaskById(4);
        subTask.setTaskStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateSubTask(subTask);
        taskManager.printEmAll();

        SubTask subTask1 = taskManager.getSubTaskById(5);
        subTask1.setTaskStatus(TaskStatus.DONE);
        taskManager.updateSubTask(subTask1);
        System.out.println();
        System.out.println(taskManager.getSubTasksByEpic(epic));
        taskManager.printEmAll();
        subTask.setTaskStatus(TaskStatus.DONE);
        taskManager.updateSubTask(subTask);
        taskManager.printEmAll();
        taskManager.deleteTaskById(1);
        taskManager.deleteEpicById(6);
        taskManager.deleteSubTaskById(4);
        taskManager.deleteSubTaskById(5);
        taskManager.printEmAll();
    }

}
