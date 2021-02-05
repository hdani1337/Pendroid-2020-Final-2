package hu.cehessteg.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.cehessteg.Stage.GameStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class AlkatreszActor extends OneSpriteStaticActor {
    public SzalagActor szalagActor;
    public int posID;
    private GameStage gameStage;
    public AlkatreszType type;

    public AlkatreszActor(MyGame game, AlkatreszType type, SzalagActor szalagActor, int posID, GameStage gameStage) {
        super(game, getHash(type));
        this.szalagActor = szalagActor;
        this.posID = posID;
        this.gameStage = gameStage;
        this.type = type;
        setSize(180,(180.0f/getHeight())*getHeight());
        setY(szalagActor.getY() + szalagActor.getHeight()/2 - this.getHeight()/2);
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
        setX(szalagActor.getX() + (szalagActor.getWidth()/4)*posID);
        if(getX() < -getWidth()*2){
            System.out.println("KIMENT BAZDMEG");
            gameStage.alkatreszek.remove(this);
            remove();
        }
    }

    @Override
    public boolean isVisible() {
        return (getX()>=getWidth() && getX() <= getWidth()*3);
    }
}
