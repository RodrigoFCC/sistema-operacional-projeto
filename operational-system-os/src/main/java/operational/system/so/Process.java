package operational.system.so;

import java.util.LinkedList;
import java.util.List;

public class Process {
    private String id;
    private int sizeInMemory;
    private List<String> processes;
    private static int processNumber = 0;
    private int timeToExecute;
    private int priorityProcessType;

    public Process(int sizeMemory, int timeToExecute, int priority) {
        setId(incrementId());
        this.sizeInMemory = sizeMemory;
        this.processes = this.getProcesses();


        this.timeToExecute =timeToExecute;

        this.priorityProcessType = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private String incrementId() {
        processNumber++;
        return "P" + Integer.toString(processNumber);
    }

    public int getSizeInMemory() {
        return sizeInMemory;
    }

    public void setSizeInMemory(int sizeInMemory) {
        this.sizeInMemory = sizeInMemory;
    }

    public int getTimeToExecute() {
        return timeToExecute;
    }

    public void setTimeToExecute(int timeToExecute) {
        this.timeToExecute = timeToExecute;
    }

    public int getPriorityProcessType() {
        return priorityProcessType;
    }

    public List<String> getProcesses() {
        if(this.processes == null) {
            this.processes = new LinkedList<>();
            for(int i = 0; i < this.sizeInMemory; i++) {
                String spId = this.getId() + i;
                this.processes.add(spId);
            }
        }
        return this.processes;
    }
}
