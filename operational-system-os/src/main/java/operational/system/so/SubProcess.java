package operational.system.so;

import operational.system.so.memory.AddressMemory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SubProcess {

    private String id;
    private int instructions;
    private AddressMemory addressMemory;
    public static int processNumber = 0;

    public SubProcess(String processId, int instructionsNumber) {
        this.id = processId + processNumber;
        this.instructions = instructionsNumber;
        processNumber++;
    }


//    private String incrementId(String processId) {
//        processNumber++;
//        this.id = processId + processNumber;
//        return "Sp" + this.id;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTimeToExecute() {
        return timeToExecute;
    }

    public void setTimeToExecute(int timeToExecute) {
        this.timeToExecute = timeToExecute;
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
