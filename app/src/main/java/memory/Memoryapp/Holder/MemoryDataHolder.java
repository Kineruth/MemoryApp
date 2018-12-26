package memory.Memoryapp.Holder;

import memory.Memoryapp.Object.Memory;

public class MemoryDataHolder {
    private Memory memory = null;
    private static final MemoryDataHolder data = new MemoryDataHolder();

    private MemoryDataHolder(){
        memory = new Memory();
    }

    public static MemoryDataHolder getMemoryDataHolder(){
        return data;
    }

    public Memory getMemory(){
        return memory;
    }
}
