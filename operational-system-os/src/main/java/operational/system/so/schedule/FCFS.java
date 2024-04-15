package operational.system.so.schedule;

import operational.system.so.SoProcess;

import java.util.Comparator;

public class FCFS extends  SchedulerQueue{


    public FCFS() {
        super(new Comparator<SoProcess>() {
            @Override
            public int compare(SoProcess p1, SoProcess p2) {
                return -1;
            }
        });
    }
}
