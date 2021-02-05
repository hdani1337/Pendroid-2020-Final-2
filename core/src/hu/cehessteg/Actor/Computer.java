package hu.cehessteg.Actor;

public class Computer {
    public int needGPU;
    public int needCPU;
    public int needRAM;
    public int needPSU;

    public int needTimeInSec;

    public Computer() {
        needCPU = 1;
        needRAM = 4;
        needGPU = 2;
        needPSU = 1;
        needTimeInSec = 10;
    }

    public void build(){
        if(isBuildable()) Stock.buy(this);
    }

    public boolean isBuildable(){
        boolean error = true;

        if(Stock.cpuCount < needCPU) error = false;
        else if(Stock.gpuCount < needGPU) error = false;
        else if(Stock.ramCount < needRAM) error = false;
        else if(Stock.psuCount < needPSU) error = false;

        return error;
    }
}
