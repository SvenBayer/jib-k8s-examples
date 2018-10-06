package blog.svenbayer.jib.k8s.limits.shenandoah.example.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "memory")
public class MemoryActuator {

    @ReadOperation
    public Map<String, String> memory() {
        Map<String, String> memoryInfo = new HashMap<>();

        Runtime runtime = Runtime.getRuntime();
        long allocatedHeapMemory = runtime.totalMemory(); // current heap allocated to the VM process
        memoryInfo.put("allocatedHeapMemory", toMegaByte(allocatedHeapMemory));

        long freeHeapMemory = runtime.freeMemory(); // out of the current heap, how much is free
        memoryInfo.put("freeHeapMemory", toMegaByte(freeHeapMemory));

        long availableMaxHeapMemory = runtime.maxMemory(); // Max heap VM can use e.g. Xmx setting
        memoryInfo.put("availableMaxHeapMemory", toMegaByte(availableMaxHeapMemory));

        long usedHeapMemory = allocatedHeapMemory - freeHeapMemory; // how much of the current heap the VM is using
        memoryInfo.put("usedHeapMemory", toMegaByte(usedHeapMemory));

        long availableMemoryToAllocateForHeap = availableMaxHeapMemory - usedHeapMemory; // available memory i.e. Maximum heap size minus the current amount used
        memoryInfo.put("availableMemoryToAllocateForHeap", toMegaByte(availableMemoryToAllocateForHeap));

        return memoryInfo;
    }

    private String toMegaByte(long memoryInByte) {
        return (memoryInByte / 1_000_000L) + "MB";
    }
}