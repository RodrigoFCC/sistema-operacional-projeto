package operational.system.so.scheduler;

import operational.system.so.Process;

import java.util.Comparator;

public class FCFS extends SchedulerQueue {

    public FCFS() {
        super(new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return -1;
            }
        });
    }
}
