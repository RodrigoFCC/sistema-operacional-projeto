package operational.system.so.cpu;

import operational.system.so.SubProcess;
import operational.system.so.scheduler.Scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CpuManager {
    private Core[] cores;
    public static int CAPACITY = 7;
    public static int NUMBER_OF_CORES = 4;
    public static int CLOCK = 1000;

    private Scheduler scheduler;

    public CpuManager(Scheduler scheduler) {
        this.cores = new Core[NUMBER_OF_CORES];
        for(int i = 0; i < this.cores.length; i++) {
            this.cores[i] = new Core(i, CAPACITY);
        }
        this.scheduler = scheduler;
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
            if(core.getCurrentSubProcess() == null) {
                if (this.scheduler != null) {
                    SubProcess sp = this.scheduler.execute();
                    if (sp != null) {
                        core.setCurrentSubProcess(sp);
                        core.run();
                    }
                }
            }
        }
        System.out.println("-----> New clock");
    }

    public List<Core> getAvailableCores() {
        return new ArrayList<>();
    }

    public Core[] getCores() {
        return cores;
    }

    public void setCores(Core[] cores) {
        this.cores = cores;
    }
}
