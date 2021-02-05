package hu.cehessteg.Actor;

import hu.cehessteg.Stage.GameStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class SzalagActor extends OneSpriteStaticActor {
    public static final String SZALAG_TEXTURE = "futoszalag.png";

    public int id;
    private GameStage stage;

    public SzalagActor(MyGame game, int id, GameStage gameStage) {
        super(game, SZALAG_TEXTURE);
        this.id = id;
        this.stage = gameStage;
        setY(gameStage.getViewport().getWorldHeight()/2-this.getHeight()/2);
        setX(getWidth()*id);
        setOrigin(0,0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(getX() <= -getWidth()){
            setX(stage.szalagok.get(getLastId()).getX()+getWidth());
            setZIndex(0);
        }
    }

    private int getLastId(){
        if(id == 0) return 3;
        else return id-1;
    }
}
