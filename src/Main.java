import model.*;
import service.FileBackedTasksManager;
import service.Managers;
import service.TaskManager;

import java.io.File;
import java.util.ArrayList;



public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        File file = new File("file.csv");

        TaskManager taskManagerFile = Managers.getDefaultFile(file);


        System.out.println("1. Выведем Задачи:");
        System.out.println(taskManagerFile.getTasks());
        System.out.println(taskManagerFile.getEpics());
        System.out.println(taskManagerFile.getSubtasks());


        System.out.println("2.  Попробуем загрузить таскменеджер из пустого CSV");
        TaskManager taskManagerFileBackup = FileBackedTasksManager.loadFromFile(file);
        //
                System.out.println("2.1  Попробуем посмотерь загруженные задачи из пустого CSV:");
        System.out.println(taskManagerFileBackup.getTasks());
        System.out.println(taskManagerFileBackup.getEpics());
        System.out.println(taskManagerFileBackup.getSubtasks());

        System.out.println("3 Добавим несколько задач и соответсвенно сохраним");
        System.out.println("Создадим 2 обычные задачи, 1 эпик с 3 подзадачами и 1 эпик без подзадач:");
        int task1 = taskManagerFile.addTask(new Task(0, TypeOfTask.TASK,"firsttask",StatusOfTask.NEW,"gfg"));
        int task2 = taskManagerFile.addTask(new Task(0,TypeOfTask.TASK,"secondtask", StatusOfTask.NEW,"fgfg"));

        int epic1 = taskManagerFile.addEpic(new Epic(0,TypeOfTask.EPIC,"firstEpic",StatusOfTask.NEW,"gfg",new ArrayList<Integer>()));
        int epic1subtask1 = taskManagerFile.addSubtask(new Subtask(0,TypeOfTask.SUBTASK,"firstsubforfirstepic",StatusOfTask.NEW,"dssd",epic1));
        int epic1subtask2 = taskManagerFile.addSubtask(new Subtask(0,TypeOfTask.SUBTASK,"secondsubforepic1",StatusOfTask.IN_PROGRESS,"dssd",epic1));
        int epic1subtask3 = taskManagerFile.addSubtask(new Subtask(0,TypeOfTask.SUBTASK,"thirdsubforepic1",StatusOfTask.IN_PROGRESS,"dssd",epic1));
        int epic2 = taskManagerFile.addEpic(new Epic(0,TypeOfTask.EPIC,"secondEpic",StatusOfTask.NEW,"fdf",new ArrayList<Integer>()));

        System.out.println("3.1 Проверим что задачи создались:");
        System.out.println(taskManagerFile.getTasks());
        System.out.println(taskManagerFile.getEpics());
        System.out.println(taskManagerFile.getSubtasks());

        System.out.println("1.2 Запросим таски для создания истории");
        System.out.println(taskManagerFile.getTaskById(task1));
        System.out.println(taskManagerFile.getTaskById(task2));
        System.out.println(taskManagerFile.getEpicById(epic2));


        System.out.println("1.3 Просмотрим историю:");
        for (Task task : taskManagerFile.getHistory()) {
            System.out.println(task);
        }


        System.out.println("1.4 Удалим задачу task1(проверка сохранностии функционала): ");
        taskManagerFile.delTaskById(task1);

        System.out.println("1.5 Просмотрим историю(проверка сохранностии функционала): ");
        for (Task task : taskManagerFile.getHistory()) {
            System.out.println(task);
        }
        System.out.println("2.  Попробуем загрузить таскменеджер из CSV");
        FileBackedTasksManager.loadFromFile(file);
        System.out.println("2.1  Попробуем посмотерь загруженные задачи из CSV:");
        System.out.println(taskManagerFileBackup.getTasks());
        System.out.println(taskManagerFileBackup.getEpics());
        System.out.println(taskManagerFileBackup.getSubtasks());


    }
}
