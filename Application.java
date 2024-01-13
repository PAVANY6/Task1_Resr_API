import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class TaskManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }
}

class Task {
    private String id;
    private String name;
    private String assignee;
    private String project;
    private Date startTime;
    private String rajeshSinghProperty;

    public Task() {
        this.rajeshSinghProperty = generateRajeshSinghProperty();
    }

    private String generateRajeshSinghProperty() {
        String sourceString = "RajeshSingh";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(sourceString.length());
            result.append(sourceString.charAt(index));
        }

        return result.toString();
    }

}

interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByNameContaining(String name);
    List<Task> findByAssigneeOrderByStartTime(String assignee);
}

class TaskService {
    
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findTasksByName(String name) {
        return taskRepository.findByNameContaining(name);
    }

    public List<Task> findFirst10TasksByAssignee(String assignee) {
        return taskRepository.findByAssigneeOrderByStartTime(assignee);
    }
}

@RestController
@RequestMapping("/tasks")
class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public void createTask(@RequestBody Task task) {
        taskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/searchByName")
    public List<Task> findTasksByName(@RequestParam String name) {
        return taskService.findTasksByName(name);
    }

    @GetMapping("/searchByAssignee")
    public List<Task> findFirst10TasksByAssignee(@RequestParam String assignee) {
        return taskService.findFirst10TasksByAssignee(assignee);
    }
}

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

