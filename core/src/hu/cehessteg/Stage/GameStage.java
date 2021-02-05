package hu.cehessteg.Stage;

import java.util.ArrayList;
import java.util.Random;

import hu.cehessteg.Actor.AlkatreszActor;
import hu.cehessteg.Actor.AlkatreszType;
import hu.cehessteg.Actor.BuilderActor;
import hu.cehessteg.Actor.LadaActor;
import hu.cehessteg.Actor.Stock;
import hu.cehessteg.Actor.SzalagActor;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PrettySimpleStage;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

import static hu.cehessteg.Stage.OptionsStage.difficulty;
import static hu.cehessteg.TetrisGame.muted;
import static hu.cehessteg.TetrisGame.preferences;

public class GameStage extends PrettySimpleStage {

    public static long point = preferences.getLong("coin");
    public static boolean isPaused;
    public static boolean isGameOver;

    public AlkatreszActor selectedActor;

    private OneSpriteStaticActor hatter;

    public ArrayList<SzalagActor> szalagok;
    public ArrayList<LadaActor> ladak;
    public ArrayList<AlkatreszActor> alkatreszek;

    public BuilderActor builderActor;

    private Random random;

    public GameStage(MyGame game) {
        super(new ResponseViewport(800),game);
    }

    @Override
    public void assignment() {
        Stock.reset();
        if(difficulty == 0) difficulty = 2;
        hatter = new OneSpriteStaticActor(game,"hatter.png");
        builderActor = new BuilderActor(game,"szereles.png");
        random = new Random();
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
        builderActor.setSize(160,160);
        hatter.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight());
        for (LadaActor a : ladak){
            a.setSize(220,220);
        }
    }

    @Override
    public void setPositions() {
        ladak.get(0).setX(18);
        ladak.get(0).setY(18);
        builderActor.setPosition(16,getViewport().getWorldHeight()-16-builderActor.getHeight());
        for (int i = 1; i < ladak.size(); i++){
            ladak.get(i).setX(ladak.get(i-1).getX()+ladak.get(i-1).getWidth()+25);
            ladak.get(i).setY(18);
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
        addActor(hatter);
        addActor(builderActor);
        for (SzalagActor a : szalagok) {
            addActor(a);
            a.setZIndex(10);
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

    private void addTimers(){
        addTimer(new TickTimer(3,true,new TickTimerListener(){
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                AlkatreszActor actor = new AlkatreszActor(game,AlkatreszType.values()[random.nextInt(4)],GameStage.this);
                alkatreszek.add(actor);
                addActor(actor);
                if(Math.random() <= 0.03f && point >= 5) point -= Math.random()*5;
            }
        }));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(point < 0) isGameOver = true;
        if(!isPaused && !isGameOver) {
            for (SzalagActor a : szalagok) {
                a.setX(a.getX()-difficulty*3);
            }
        }


    }
}
