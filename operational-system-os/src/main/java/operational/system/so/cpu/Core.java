package operational.system.so.cpu;

import operational.system.so.SubProcess;

public class Core implements Runnable {
    private int id;
    private SubProcess currentSubProcess;
    private int numberOfInstructionsPerClock;
    private int count;

    public Core(int id, int numberOfInstructionsPerClock) {
        this.id = id;
        this.numberOfInstructionsPerClock = numberOfInstructionsPerClock;
    }

    @Override
    public void run() {
        count += numberOfInstructionsPerClock;
        if(count >= this.currentSubProcess.getInstructions()) {
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
