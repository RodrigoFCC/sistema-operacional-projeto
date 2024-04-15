package operational.system.so.schedule;

import operational.system.so.SoProcess;
import operational.system.so.SubProcess;

import java.util.Comparator;

public class SJF extends SchedulerQueue{
    public SJF() {
        super(new Comparator<SoProcess>() {
            @Override
            public int compare(SoProcess p1, SoProcess p2) {
                return p1.getTimeToExecute() <= p2.getTimeToExecute() ? 1 : -1;
            }
        });
    }
}
