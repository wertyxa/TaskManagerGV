package ua.iv_fr.vpu21.lukach.marian.taskmanagergv.models;

import ua.iv_fr.vpu21.lukach.marian.taskmanagergv.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
@XmlType(name = "task")
public class Task {
    private String mandateNumber;
    private String name;

    private LocalDate startDate;
    private LocalDate endDate;
    private String publisher;
    private String executor;
    private boolean statusE;

    public Task() {
    }

    public Task(String mandateNumber, String name, LocalDate startDate, LocalDate endDate, String publisher, String executor, boolean statusE) {
        this.mandateNumber = mandateNumber;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.publisher = publisher;
        this.executor = executor;
        this.statusE = statusE;
    }

    public String getMandateNumber() {
        return mandateNumber;
    }

    public void setMandateNumber(String mandateNumber) {
        this.mandateNumber = mandateNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public boolean getStatusE() {
        return statusE;
    }

    public void setStatusE(boolean statusE) {
        this.statusE = statusE;
    }

}
