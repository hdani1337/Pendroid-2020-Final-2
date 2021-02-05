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
        needGPU = 1;
        needPSU = 1;
        needTimeInSec = 30;
    }

    public void build(){
        boolean error = false;

        if(Stock.cpuCount < needCPU) error = true;
        if(Stock.gpuCount < needGPU) error = true;
        if(Stock.ramCount < needRAM) error = true;
        if(Stock.psuCount < needPSU) error = true;

        if(!error){
            Stock.buy(this);
        }
    }
}
