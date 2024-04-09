package operational.system.so;

import java.util.LinkedList;
import java.util.List;

public class SoProcess {
    private String id;
    private int sizeInMemory;
    private List<String> processes;
    private static int processNumber = 0;

    private static int numberOfInstructions = 7;
    private static int timeToExecute = 5000;

    public SoProcess(int sizeMemory) {
        setId(incrementId());
        this.sizeInMemory = sizeMemory;
        this.processes = this.getProcesses();
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

    public List<String> getProcesses() {
        if(this.processes == null) {
            this.processes = new LinkedList<>();
            for (int i = 0; i < this.sizeInMemory; i++){
                String spId = this.getId() + i;
                this.processes.add(spId);
            }
        }

        return this.processes;
    }
}



