package hu.cehessteg.Stage;

import java.util.ArrayList;
import java.util.Random;

import hu.cehessteg.Actor.AlkatreszActor;
import hu.cehessteg.Actor.AlkatreszType;
import hu.cehessteg.Actor.LadaActor;
import hu.cehessteg.Actor.SzalagActor;
import hu.cehessteg.SoundManager;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PrettySimpleStage;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

import static hu.cehessteg.TetrisGame.muted;

public class GameStage extends PrettySimpleStage {

    public static int point;
    public static boolean isPaused;
    public static boolean isGameOver;

    public AlkatreszActor selectedActor;

    public ArrayList<SzalagActor> szalagok;
    public ArrayList<LadaActor> ladak;
    public ArrayList<AlkatreszActor> alkatreszek;

    public GameStage(MyGame game) {
        super(new ResponseViewport(1280),game);
    }

    @Override
    public void assignment() {
        SoundManager.assign();
        isPaused = false;
        isGameOver = false;
        szalagok = new ArrayList<>();
        ladak = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            szalagok.add(new SzalagActor(game,i,this));
        }
        for (int i = 0; i < 5; i++){
            ladak.add(new LadaActor(game, AlkatreszType.values()[i],this));
        }
        alkatreszek = new ArrayList<>();
    }

    @Override
    public void setSizes() {
        for (LadaActor a : ladak){
            a.setSize(360,360);
        }
    }

    @Override
    public void setPositions() {
        for (int i = 1; i < ladak.size(); i++){
            ladak.get(i).setX(ladak.get(i-1).getX()+ladak.get(i-1).getWidth()+25);
        }
    }

    @Override
    public void addListeners() {

    }

    @Override
    public void setZIndexes() {

    }

    @Override
    public void addActors() {
        for (SzalagActor a : szalagok) {
            addActor(a);
            a.setZIndex(0);
        }
        for (LadaActor a : ladak){
            addActor(a);
        }
    }



    @Override
    public void afterInit() {
        super.afterInit();
        addTimers();
    }

    boolean foglalt = false;

    private void addTimers(){
        addTimer(new TickTimer(1,true,new TickTimerListener(){
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                generatePart();
            }
        }));
    }

    private void generatePart(){
        foglalt = false;
        Random r = new Random();
        ArrayList<SzalagActor> visibleSzalag = new ArrayList<>();
        for (SzalagActor sz : szalagok) if(sz.isVisible()) visibleSzalag.add(sz);

        SzalagActor randomSzalag = visibleSzalag.get(r.nextInt(visibleSzalag.size()));
        AlkatreszType randomType = AlkatreszType.values()[r.nextInt(4)];
        int randomPos = r.nextInt(4);
        System.out.println(randomPos);

        AlkatreszActor actor = new AlkatreszActor(game,randomType,randomSzalag,randomPos,this);

        if(alkatreszek.size() > 0) {
            for (int i = 0; i < alkatreszek.size(); i++){
                if(alkatreszek.get(i).posID == randomPos && alkatreszek.get(i).szalagActor == randomSzalag){
                    foglalt = true;
                    break;
                }
            }
        }

        if(!foglalt) {
            alkatreszek.add(actor);
            addActor(actor);
        }else generatePart();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!isPaused) {
            for (SzalagActor a : szalagok) {
                a.setX(a.getX()-2);
            }
        }
    }

    public void playSound(int id){
        if(!muted){
            if(id == 0 && SoundManager.clearSound != null){
                //CLEAR
                SoundManager.clearSound.play();
            }else if(id == 1 && SoundManager.fallSound != null){
                //FALL
                SoundManager.fallSound.play();
            }
        }
    }
}
