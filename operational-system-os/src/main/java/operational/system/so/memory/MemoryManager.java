package operational.system.so.memory;

import operational.system.so.Process;
import operational.system.so.utils.Util;

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
            this.writeUsingBestFit(process);
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
        int actualSize = 0;
        AddressMemory addressMemory = new AddressMemory(0, 0);

        for(int i = 0; i < memory.length; i++) {
            if(i == (memory.length-1)) {
                if(actualSize > 0 || memory[i] == null) {
                    int lastStart = i - actualSize; // 11 - 3 = 8
                    int lastEnd = i; //= 11
                    if(((lastEnd - lastStart) >= (addressMemory.getEnd()- addressMemory.getStart())) && process.getSizeInMemory() <= (lastEnd - lastStart)) {
                        addressMemory.setStart(lastStart);
                        addressMemory.setEnd(lastEnd);
                    }
                    if(memory[i-1] == null) {
                        if (((lastEnd - lastStart) >= (addressMemory.getEnd() - addressMemory.getStart())) && process.getSizeInMemory() <= ((lastEnd - lastStart) + 1)) {
                            addressMemory.setStart(lastStart);
                            addressMemory.setEnd(lastEnd);
                        }
                    }
                    if(memory[i] == null) {
                        if(process.getSizeInMemory() <= (actualSize+1)) {
                            if ((actualSize+1) > (addressMemory.getEnd()- addressMemory.getStart())) {
                                int contNull = Util.nullValidation(memory, addressMemory.getStart(), addressMemory.getStart()+ process.getSizeInMemory());
                                if(contNull == 0) {
                                    Util.paintMemory(memory, process, addressMemory.getStart(), addressMemory.getStart()+process.getSizeInMemory());
                                    actualSize = 0;
                                    break;
                                }
                                contNull = 0;
                            }
                            int start = (i - actualSize);
                            int contNull = Util.nullValidation(memory, addressMemory.getStart(), addressMemory.getStart()+ process.getSizeInMemory());
                            if(contNull == 0) {
                                Util.paintMemory(memory, process, addressMemory.getStart(), addressMemory.getStart()+process.getSizeInMemory());
                                actualSize = 0;
                                break;
                            }
                            contNull = 0;
                        } else if(process.getSizeInMemory() <= (addressMemory.getEnd()- addressMemory.getStart())) {
                            int contNull = Util.nullValidation(memory, addressMemory.getStart(), addressMemory.getStart()+ process.getSizeInMemory());
                            if(contNull == 0) {
                                Util.paintMemory(memory, process, addressMemory.getStart(), addressMemory.getStart()+process.getSizeInMemory());
                                actualSize = 0;
                                break;
                            }
                            contNull = 0;
                            actualSize = 0;
                        }
                    } else if (process.getSizeInMemory() < (addressMemory.getEnd()- addressMemory.getStart())) {
                        int contNull = Util.nullValidation(memory, addressMemory.getStart(), addressMemory.getStart()+ process.getSizeInMemory());
                        if(contNull == 0) {
                            Util.paintMemory(memory, process, addressMemory.getStart(), addressMemory.getStart() + process.getSizeInMemory());
                            actualSize = 0;
                            break;
                        }
                        contNull = 0;
                        actualSize = 0;
                    }
                    System.out.println("WARNING: No memory for " + process.getId());
                } else {
                    if(process.getSizeInMemory() <= (addressMemory.getEnd()- addressMemory.getStart())) {
                        int contNull = Util.nullValidation(memory, addressMemory.getStart(), addressMemory.getStart()+ process.getSizeInMemory());
                        if(contNull == 0) {
                            Util.paintMemory(memory, process, addressMemory.getStart(), addressMemory.getStart()+process.getSizeInMemory());
                            actualSize = 0;
                            break;
                        }
                        contNull = 0;
                        actualSize = 0;
                    }
                }
            } else if (memory[i] == null) {
                actualSize ++;
            } else {
                if(actualSize > 0) {
                    if (process.getSizeInMemory() <= actualSize) {
                        int start = i - actualSize; // 8 - 4 = 4
                        int end = i; // 8
                        if((end - start) > (addressMemory.getEnd()- addressMemory.getStart())) {
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
        Util.printMemoryStatus(memory);
    }

    private void writeUsingBestFit(Process process) {
        int actualSize = 0;
        AddressMemory addressMemory = new AddressMemory(0, 128);

        for(int i = 0; i < memory.length; i++) {
            if(i == (memory.length-1)) {
                if(actualSize > 0 || memory[i] == null) {
                    int lastStart = i - actualSize;
                    int lastEnd = i;
                    if(((lastEnd - lastStart) < (addressMemory.getEnd()- addressMemory.getStart())) && process.getSizeInMemory() <= (lastEnd - lastStart)) {
                        addressMemory.setStart(lastStart);
                        addressMemory.setEnd(lastEnd);
                    }
                    if(memory[i] == null) {
                        if(process.getSizeInMemory() <= (actualSize+1)) {
                            if ((actualSize+1) > (addressMemory.getEnd()- addressMemory.getStart())) {
                                Util.paintMemory(memory, process, addressMemory.getStart(), addressMemory.getStart()+process.getSizeInMemory());
                                break;
                            }
                            int start = (i - actualSize);
                            Util.paintMemory(memory, process, start, start+process.getSizeInMemory());
                            actualSize = 0;
                            break;
                        } else if(process.getSizeInMemory() <= (addressMemory.getEnd()- addressMemory.getStart())) {
                            int contNull = Util.nullValidation(memory, addressMemory.getStart(), addressMemory.getStart()+ process.getSizeInMemory());
                            if(contNull == 0) {
                                Util.paintMemory(memory, process, addressMemory.getStart(), addressMemory.getStart()+process.getSizeInMemory());
                                actualSize = 0;
                                break;
                            }
                            contNull = 0;
                            actualSize = 0;
                        }
                    } else if (process.getSizeInMemory() < (addressMemory.getEnd()- addressMemory.getStart())) {
                        int contNull = Util.nullValidation(memory, addressMemory.getStart(), addressMemory.getStart()+ process.getSizeInMemory());
                        if(contNull == 0) {
                            Util.paintMemory(memory, process, addressMemory.getStart(), addressMemory.getStart() + process.getSizeInMemory());
                            actualSize = 0;
                            break;
                        }
                        contNull = 0;
                        actualSize = 0;
                    }
                    System.out.println("WARNING: No memory for " + process.getId());
                } else {
                    if(process.getSizeInMemory() < (addressMemory.getEnd()- addressMemory.getStart())) {
                        int contNull = Util.nullValidation(memory, addressMemory.getStart(), addressMemory.getStart()+ process.getSizeInMemory());
                        if(contNull == 0) {
                            Util.paintMemory(memory, process, addressMemory.getStart(), addressMemory.getStart()+process.getSizeInMemory());
                            actualSize = 0;
                            break;
                        }
                        contNull = 0;
                        actualSize = 0;
                    }
                }
            } else if (memory[i] == null) {
                actualSize ++;
            } else {
                if(actualSize > 0) {
                    if(process.getSizeInMemory() == actualSize) {
                        int start = i - actualSize;
                        int end = start + process.getSizeInMemory();
                        Util.paintMemory(memory, process, start, end);
                        actualSize = 0;
                        break;
                    } else if (process.getSizeInMemory() < actualSize) {
                        int start = i - actualSize; // 4
                        int end = i; // 8
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
        Util.printMemoryStatus(memory);
    }

    private void writeUsingFirstFit(Process process) {
        int actualSize = 0;

        for(int i = 0; i < memory.length; i++) {
            if(i == (memory.length-1)) {
                if(actualSize > 0) {
                    if(process.getSizeInMemory() <= actualSize+1) {
                        int start = (i - actualSize);
                        Util.paintMemory(memory, process, start, start+ process.getSizeInMemory());
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
                    if(process.getSizeInMemory() == actualSize) {
                        int start = i - actualSize;
                        int end = start + process.getSizeInMemory();
                        Util.paintMemory(memory, process, start, end);
                        actualSize = 0;
                        break;
                    } else if (process.getSizeInMemory() < actualSize) {
                        int start = i - actualSize;
                        int end = start + process.getSizeInMemory();
                        Util.paintMemory(memory, process, start, end);
                        actualSize = 0;
                        break;
                    } else {
                        actualSize = 0;
                    }
                }
            }
        }
        Util.printMemoryStatus(memory);
    }

    public void deleteProcessInMemory(Process process) {
        for(int i = 0; i < memory.length; i++) {
            if(memory[i] == process.getId()) {
                memory[i] = null;
            }
        }
        Util.printMemoryStatus(memory);
    }
}
