package operational.system.so;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Process {
    private String id;
    private int sizeInMemory;
    private List<String> processes;
    private static int processNumber = 0;
    private int timeToExecute;
    private PriorityProcessType priorityProcessType;

    public Process(int sizeMemory) {
        setId(incrementId());
        this.sizeInMemory = sizeMemory;
        this.processes = this.getProcesses();

        Random rand = new Random();
        List<Integer> times = Arrays.asList(100, 200, 500, 1000, 5000, 10000);
        this.timeToExecute = times.get(rand.nextInt(times.size()));

        PriorityProcessType[] priorityList = PriorityProcessType.values();
        this.priorityProcessType = priorityList[(rand.nextInt(priorityList.length))];
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

    public PriorityProcessType getPriorityProcessType() {
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
