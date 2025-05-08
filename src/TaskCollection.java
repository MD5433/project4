import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TaskCollection implements Iterable<Task> {
    //from Task class
    private ArrayList<Task> tasks;

    public TaskCollection() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void sortTasks() {
        Collections.sort(tasks);
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    public void loadTask(String filename) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            TaskData data = gson.fromJson(reader, TaskData.class);
            if (data != null && data.tasks != null) {
                tasks = data.tasks;
            }
        }
    }

    public void saveTask(String filename) throws IOException {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filename)) {
            TaskData data = new TaskData();
            data.tasks = tasks;
            gson.toJson(data, writer);
            System.out.println("Saving " + tasks.size() + " tasks.");

        }
    }


}


