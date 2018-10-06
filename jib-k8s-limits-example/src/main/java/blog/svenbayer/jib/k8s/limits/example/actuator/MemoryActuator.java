package blog.svenbayer.jib.k8s.limits.example.actuator;

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
        long allocatedHeapMemory = runtime.totalMemory();
        memoryInfo.put("allocatedHeapMemory", toMegaByte(allocatedHeapMemory));

        long freeHeapMemory = runtime.freeMemory();
        memoryInfo.put("freeHeapMemory", toMegaByte(freeHeapMemory));

        long availableMaxHeapMemory = runtime.maxMemory();
        memoryInfo.put("availableMaxHeapMemory", toMegaByte(availableMaxHeapMemory));

        long usedHeapMemory = allocatedHeapMemory - freeHeapMemory;
        memoryInfo.put("usedHeapMemory", toMegaByte(usedHeapMemory));

        long availableMemoryToAllocateForHeap = availableMaxHeapMemory - usedHeapMemory;
        memoryInfo.put("availableMemoryToAllocateForHeap", toMegaByte(availableMemoryToAllocateForHeap));

        return memoryInfo;
    }

    private String toMegaByte(long memoryInByte) {
        return (memoryInByte / 1_000_000L) + "MB";
    }
}