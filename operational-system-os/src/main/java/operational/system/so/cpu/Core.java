package operational.system.so.cpu;

import operational.system.so.SubProcess;
import operational.system.so.utils.ProcessListener;

public class Core implements Runnable {
    private int id;
    private SubProcess currentSubProcess;
    private int numberOfInstructionsPerClock;
    private int count;
    private ProcessListener processListener;
    public Core(int id, int numberOfInstructionsPerClock) {
        this.id = id;
        this.numberOfInstructionsPerClock = numberOfInstructionsPerClock;
    }

    @Override
    public void run() {
        count += numberOfInstructionsPerClock;
        if(this.currentSubProcess != null || count >= this.currentSubProcess.getInstructions()) {
            System.out.println("Running the subprocess -" + this.currentSubProcess.getId() + "- in core: " + this.getId());
            this.finishExecution();
        }
    }

    private void finishExecution() {
        this.count = 0;
        this.currentSubProcess = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SubProcess getCurrentSubProcess() {
        return currentSubProcess;
    }

    public void setCurrentSubProcess(SubProcess currentSubProcess) {
        this.currentSubProcess = currentSubProcess;
    }

    public int getNumberOfInstructionsPerClock() {
        return numberOfInstructionsPerClock;
    }

    public void setNumberOfInstructionsPerClock(int numberOfInstructionsPerClock) {
        this.numberOfInstructionsPerClock = numberOfInstructionsPerClock;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
