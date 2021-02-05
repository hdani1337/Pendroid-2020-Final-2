package hu.cehessteg.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;

import hu.cehessteg.Stage.GameStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class AlkatreszActor extends OneSpriteStaticActor {
    private GameStage gameStage;
    public AlkatreszType type;

    public AlkatreszActor(MyGame game, AlkatreszType type, GameStage gameStage) {
        super(game, getHash(type));
        this.gameStage = gameStage;
        this.type = type;
        setSize(180,(180.0f/getHeight())*getHeight());
        setY(gameStage.szalagok.get(0).getY() + gameStage.szalagok.get(0).getHeight()/2 - this.getHeight()/2);
        setX(gameStage.getViewport().getWorldWidth()+new Random().nextInt(500));
        System.out.println(gameStage.getViewport().getWorldWidth());
        addListeners();
    }

    public void addListeners(){
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(gameStage.selectedActor != AlkatreszActor.this){
                    gameStage.selectedActor = AlkatreszActor.this;
                }
            }
        });
    }

    public static String getHash(AlkatreszType type){
        switch (type) {
            case CPU:
                return "cpu.png";
            case GPU:
                return "videokartya.png";
            case RAM:
                return "ram.png";
            case PSU:
                return "psu.png";
        }
        return null;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX()-2);
        setZIndex(10000);
        System.out.println(getX());
    }
}
