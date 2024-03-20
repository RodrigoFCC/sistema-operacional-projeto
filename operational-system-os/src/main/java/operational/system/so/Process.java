package operational.system.so;

import operational.system.so.memory.AddressMemory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Process {
    private String id;
    private int sizeInMemory;
    private int timeToExecute;
    private int priority;
    private List<Process> processes;
    private int instructions;
    private AddressMemory addressMemory;
    private static int processNumber = 0;

    public Process() {
        setId(incrementId());
        Random r = new Random();
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30, 50);
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        this.sizeInMemory = numbers.get(r.nextInt(numbers.size()));
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public int getInstructions() {
        return instructions;
    }

    public void setInstructions(int instructions) {
        this.instructions = instructions;
    }

    public AddressMemory getAddressMemory() {
        return addressMemory;
    }

    public void setAddressMemory(AddressMemory addressMemory) {
        this.addressMemory = addressMemory;
    }
}
