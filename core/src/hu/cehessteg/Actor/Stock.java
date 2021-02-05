package hu.cehessteg.Actor;

import hu.cehessteg.Stage.GameStage;

public class Stock {
    public static int cpuCount = 0;
    public static int gpuCount = 0;
    public static int ramCount = 0;
    public static int psuCount = 0;

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
