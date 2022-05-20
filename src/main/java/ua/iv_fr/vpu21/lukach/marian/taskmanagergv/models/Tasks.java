package ua.iv_fr.vpu21.lukach.marian.taskmanagergv.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Tasks {
     private String username;
     private List<Task> tasks;

    public Tasks() {
    }

    public Tasks(List<Task> tasks, String username) {
        this.tasks = tasks;
        this.username = username;
    }


    @XmlElements({@XmlElement(name = "task",type = Task.class)})
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        getTasks().add(task);
    }
    @XmlAttribute
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
