package operational.system.so.cpu;

import operational.system.so.SubProcess;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CpuManager {

    private Core[] cores;

    public static int CAPACITY = 7;

    public CpuManager(int numOfCores) {
        this.cores = new Core[numOfCores];
        for (int i = 0; i < this.cores.length; i++) {
            this.cores[i] = new Core(i, CAPACITY);
        }
    }

    public void registerProcess(int coreIndex, SubProcess sp) {
        this.cores[coreIndex].setCurrentSubProcess(sp);
    }

    public void clock() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                executeProcesses();
            }
        }, 500);
    }

    private void executeProcesses() {
        for (Core core: this.cores) {
            core.run();
        }
    }

    public List<Core> getAvailableCores() {
        return null;
    }

}
