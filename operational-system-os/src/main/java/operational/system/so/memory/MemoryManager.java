package operational.system.so.memory;

import operational.system.so.Process;

import java.util.Objects;

public class MemoryManager {
    private String[] memory;
    private StrategyTypeEnum strategy;
    public MemoryManager(StrategyTypeEnum strategy) {
        this.memory = new String[12];
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
        AddressMemory addressMemory = new AddressMemory(0, 0);

        for(int i = 0; i < memory.length; i++) {
            if(memory[i] == null) {
                actualSize++;
            } else if (memory[i] != null) {
                if(actualSize == process.getSizeInMemory()) {
                    // CAMINHO 100% FELIZ - ACTUALSIZE == PROCESSSIZE
                    int start = i - actualSize;
                    for(int j = start; j < (start+process.getSizeInMemory()); j++) {
                        memory[j] = process.getId();
                    }
                    break;
                } else {
                    if(actualSize > process.getSizeInMemory()) {
                        if(actualSize < addressMemory.addressMemorySize(addressMemory)) {
                            // TODO - SETA A NOVA MEMÓRIA
                            addressMemory.setStart((i - actualSize));
                            addressMemory.setEnd((i-1));
                            actualSize = 0;
                        }
                    } else {
                        actualSize = 0;
                    }
                }
            }
            if(i == (memory.length-1)) {
                // TODO - GRAVAR O P NA ÚLTIMA MEMÓRIA SALVA
                for(int j = addressMemory.getStart(); j < addressMemory.getEnd(); j++) {
                    memory[j] = process.getId();
                }
            }
        }
        printMemoryStatus();
    }

    private void writeUsingFirstFit(Process process) {
        int actualSize = 0;

        for(int i = 0; i < memory.length; i++) {
            if(i == (memory.length-1)) {
                if(actualSize > 0) {
//                    actualSize ++;
                    if(process.getSizeInMemory() <= actualSize) {
                        int start = (i - actualSize);
                        System.out.println("ÚLTIMA - PARA O PROCESSO APÓS O DELETE O START É: " + start);
                        System.out.println("ÚLTIMA - PARA O PROCESSO APÓS O DELETE O actualsize É: " + actualSize);
                        System.out.println("ÚLTIMA - PARA O PROCESSO APÓS O DELETE O i É: " + i);
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
        System.out.println("");
    }
}
