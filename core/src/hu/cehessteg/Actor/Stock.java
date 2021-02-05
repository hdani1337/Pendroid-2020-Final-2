package hu.cehessteg.Actor;

import hu.cehessteg.Stage.GameStage;

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
        GameStage.point += 25;
    }

    public static void reset(){
        cpuCount = 0;
        gpuCount = 0;
        ramCount = 0;
        psuCount = 0;
    }
}
