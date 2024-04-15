package operational.system.so.cpu;

import operational.system.so.SubProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CpuManager {
    private Core[] cores;

    public static int CAPACITY = 7;
    public static int NUMBER_OF_CORES = 4;
    public static int CLOCK = 1000;

    public CpuManager() {
        this.cores = new Core[NUMBER_OF_CORES];
        for(int i = 0; i < this.cores.length; i++) {
            this.cores[i] = new Core(i, CAPACITY);
        }
        clock();
    }

    public void registerProcess(int coreIndex, SubProcess subProcess){
        this.cores[coreIndex].setCurrentSubProcess(subProcess);
    }

    public void clock(){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                executeProcess();
            }
        }, 0, CLOCK);
    }

    private void executeProcess() {
        for(Core core : this.cores) {
            if(core.getCurrentSubProcess() != null) {
                core.run();
            }
        }
    }

    public List<Core> getAvailableCores() {
        List<Core> availableCores = new ArrayList<>();
        return availableCores;
    }

    public Core[] getCores() {
        return cores;
    }

    public void setCores(Core[] cores) {
        this.cores = cores;
    }
}
