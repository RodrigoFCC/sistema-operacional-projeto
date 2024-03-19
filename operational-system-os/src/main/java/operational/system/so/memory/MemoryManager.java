package operational.system.so.memory;

import operational.system.so.Process;

public class MemoryManager {
    private String[] memory;
    private StrategyTypeEnum strategy;
    public MemoryManager(StrategyTypeEnum strategy) {
        this.memory = new String[128];
        this.strategy =strategy;
    }

    public void write (Process process) {
        if(strategy.equals(StrategyTypeEnum.FIRST_FIT)) {
            this.writeUsingFirstFit(process);
        } else if(strategy.equals(StrategyTypeEnum.BEST_FIT)) {
            this.writeUsingBesttFit(process);
        }
        else if(strategy.equals(StrategyTypeEnum.WORST_FIT)) {
            this.writeUsingWorstFit(process);
        }
        else if(strategy.equals(StrategyTypeEnum.PAGING)) {
            this.writeUsingPaging(process);
        }
    }

    private void writeUsingPaging(Process process) {
    }

    private void writeUsingWorstFit(Process process) {
    }

    private void writeUsingBesttFit(Process process) {
        int actualSize = 0;
        AddressMemory addressMemory = new AddressMemory(0, 128);

        for(int i = 0; i < memory.length; i++) {
            if(i == (memory.length-1)) {
                if(actualSize > 0 || memory[i] == null) {
                    if(memory[i] == null) {
                        if(process.getSizeInMemory() <= (actualSize+1)) {
                            if ((actualSize+1) > (addressMemory.getEnd()- addressMemory.getStart())) {
                                for (int j = addressMemory.getStart(); j < process.getSizeInMemory(); j++) {
                                    memory[j] = process.getId();
                                }
                                break;
                            }
                            int start = (i - actualSize);
                            for(int j = start; j < (start+process.getSizeInMemory()); j++) {
                                memory[j] = process.getId();
                            }
                            actualSize = 0;
                            break;
                        } else if(process.getSizeInMemory() <= (addressMemory.getEnd()- addressMemory.getStart())) {
                            int contNull = 0;
                            for (int j = addressMemory.getStart(); j < (addressMemory.getStart()+ process.getSizeInMemory()); j++) {
                                 if(memory[j] != null) {
                                     contNull++;
                                 }
                            }
                            if(contNull == 0) {
                                for(int k = addressMemory.getStart(); k < (addressMemory.getStart()+process.getSizeInMemory()); k++) {
                                    memory[k] = process.getId();
                                }
                                actualSize = 0;
                                break;
                            }
                            contNull = 0;
                            actualSize = 0;
                        }
                    } else if (process.getSizeInMemory() < (addressMemory.getEnd()- addressMemory.getStart())) {
                        for(int j = addressMemory.getStart(); j < process.getSizeInMemory(); j++) {
                            memory[j] = process.getId();
                        }
                        actualSize = 0;
                        break;
                    }
                    System.out.println("WARNING: No memory for " + process.getId());
                } else {
                    if(process.getSizeInMemory() < (addressMemory.getEnd()- addressMemory.getStart())) {
                        for(int j = addressMemory.getStart(); j < process.getSizeInMemory(); j++) {
                            memory[j] = process.getId();
                        }
                        actualSize = 0;
                        break;
                    }
                }
            } else if (memory[i] == null) {
                actualSize ++;
            } else {
                if(actualSize > 0) {
                    if(process.getSizeInMemory() == actualSize) {
                        int start = i - actualSize;
                        int end = start + process.getSizeInMemory();
                        for(int j = start; j < end; j++) {
                            memory[j] = process.getId();
                        }
                        actualSize = 0;
                        break;
                    } else if (process.getSizeInMemory() < actualSize) {
                        int start = i - actualSize;
                        int end = i;
                        if((end - start) < (addressMemory.getEnd()- addressMemory.getStart())) {
                            addressMemory.setStart(start);
                            addressMemory.setEnd(end);
                            actualSize = 0;
                        }
                        actualSize = 0;
                    } else {
                        actualSize = 0;
                    }
                }
                actualSize = 0;
            }
        }
        printMemoryStatus();
    }

    private void writeUsingFirstFit(Process process) {
        int actualSize = 0;

        for(int i = 0; i < memory.length; i++) {
            if(i == (memory.length-1)) {
                if(actualSize > 0) {
                    if(process.getSizeInMemory() <= actualSize+1) {
                        int start = (i - actualSize);
                        for(int j = start; j < (start+process.getSizeInMemory()); j++) {
                            memory[j] = process.getId();
                        }
                        actualSize = 0;
                        break;
                    }
                    System.out.println("WARNING: No memory for " + process.getId());
                }
            } else if (memory[i] == null) {
                actualSize ++;
            }
            else {
                if(actualSize > 0) {
                    if(process.getSizeInMemory() < actualSize) {
                        int start = i - actualSize;
                        int end = i - process.getSizeInMemory();
                        for(int j = start; j < end; j++) {
                            memory[j] = process.getId();
                        }
                        actualSize = 0;
                        break;
                    } else if (process.getSizeInMemory() == actualSize) {
                        int start = i - actualSize;

                        int end = i - 1;
                        for(int j = start; j <= end; j++) {
                            memory[j] = process.getId();
                        }
                        actualSize = 0;
                        break;
                    } else {
                        actualSize = 0;
                    }
                }
            }
        }
        printMemoryStatus();
    }

    public void deleteProcessInMemory(Process process) {
        for(int i = 0; i < memory.length; i++) {
            if(memory[i] == process.getId()) {
                memory[i] = null;
            }
        }
        printMemoryStatus();
    }

    private void printMemoryStatus () {
        for (int i = 0; i < memory.length; i++) {
            System.out.print(memory[i] + " | ");
        }
        System.out.println("FINISH");
    }
}
