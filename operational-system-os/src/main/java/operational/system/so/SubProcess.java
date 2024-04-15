package operational.system.so;

public class SubProcess {
    private String id;
    private int instructions;
    private static int subProcessNumber = 0;

    public SubProcess(String processId, int instructionsNumber, int timeToExecute) {
        setId(processId + incrementId());
        this.instructions = instructionsNumber;
    }

    public SubProcess(String processId, int instructionsNumber) {
        this(processId, instructionsNumber, 0);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getInstructions() {
        return instructions;
    }

    public void setInstructions(int instructions) {
        this.instructions = instructions;
    }

    private String incrementId() {
        String id = "-" + Integer.toString(subProcessNumber);
        subProcessNumber++;
        return id;
    }
}
