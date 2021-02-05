package hu.cehessteg.Actor;

public class Stock {
    public static int cpuCount;
    public static int gpuCount;
    public static int ramCount;
    public static int psuCount;

    public static void buy(Computer computer) {
        cpuCount -= computer.needCPU;
        gpuCount -= computer.needGPU;
        ramCount -= computer.needRAM;
        psuCount -= computer.needPSU;
    }
}