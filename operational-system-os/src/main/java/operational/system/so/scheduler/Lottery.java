package operational.system.so.scheduler;

import operational.system.so.Process;
import operational.system.so.SubProcess;
import operational.system.so.SystemCallTypeEnum;
import operational.system.so.SystemOperation;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Lottery extends Scheduler {
    private LinkedList<Process> processList;
    private LinkedList<SubProcess> subProcessList;
    public Lottery() {
        this.processList = new LinkedList<>();
        this.subProcessList = new LinkedList<>();
    }

    @Override
    public void addSubProcessAndSubProcesses(Process process) {
        this.processList.add(process);
    }

    public SubProcess execute() {
        randomFirstProcess();
        if (subProcessList != null) {
            SubProcess element = this.subProcessList.poll();
            if (element != null) {
                return element;
            }
        }
        return null;
    }

    @Override
    public void delete(Process process) {
    }

    private void randomFirstProcess() {
        if (processList != null && !processList.isEmpty()) {
            int randomIndex = (int) (Math.random() * this.processList.size());
            Process process = this.processList.get(randomIndex);
            if (process != null) {
                List<SubProcess> subProcesses =  SystemOperation.systemCall(SystemCallTypeEnum.READ_PROCESS, process);
                this.subProcessList.addAll(subProcesses);
                this.processList.removeIf(p -> Objects.equals(p.getId(), process.getId()));
            }
        }
    }


}