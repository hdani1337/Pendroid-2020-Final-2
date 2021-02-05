package hu.cehessteg.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;

import hu.cehessteg.Stage.GameStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

import static hu.cehessteg.Stage.OptionsStage.difficulty;

public class AlkatreszActor extends OneSpriteStaticActor {
    private GameStage gameStage;
    public AlkatreszType type;

    public float toresEsely = 1f;

    public boolean isSelected;

    public boolean torott;

    public AlkatreszActor(MyGame game, AlkatreszType type, GameStage gameStage) {
        super(game, getHash(type, false));
        this.gameStage = gameStage;
        this.type = type;
        this.isSelected = false;
        if(Math.random() < toresEsely) torott = true;
        else torott = false;
        sprite.setTexture(game.getMyAssetManager().getTexture(getHash(type, torott)));
        float scale = 180.0f/getHeight() ;
        setSize(180,scale*getHeight());
        setY(gameStage.szalagok.get(0).getY() + gameStage.szalagok.get(0).getHeight()/2 - this.getHeight()/2);
        setX(gameStage.getViewport().getWorldWidth()+125+new Random().nextInt(500));
        addListeners();
    }

    public void addListeners(){
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(gameStage.selectedActor != AlkatreszActor.this){
                    gameStage.selectedActor = AlkatreszActor.this;
                    isSelected = true;
                    sprite.setTexture(game.getMyAssetManager().getTexture(getSelectedHash(type, torott)));
                }else{
                    gameStage.selectedActor = null;
                    isSelected = false;
                    sprite.setTexture(game.getMyAssetManager().getTexture(getHash(type, torott)));
                }
            }
        });
    }

    public static String getHash(AlkatreszType type, boolean torott){
        if(!torott) {
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
        }else{
            switch (type) {
                case CPU:
                    return (Math.random() < 0.5) ? "cputorott.png" : "cputorott2.png";
                case GPU:
                    return (Math.random() < 0.5) ? "gputorott.png" : "gputorott2.png";
                case RAM:
                    return (Math.random() < 0.5) ? "ramtorott.png" : "ramtorott2.png";
                case PSU:
                    return (Math.random() < 0.5) ? "psutorott.png" : "psutorott2.png";
            }
        }
        return null;
    }

    public static String getSelectedHash(AlkatreszType type, boolean torott){
        if(!torott) {
            switch (type) {
                case CPU:
                    return "cpufenyes.png";
                case GPU:
                    return "videokartyafenyes.png";
                case RAM:
                    return "ramfenyes.png";
                case PSU:
                    return "psufenyes.png";
            }
        }else{
            switch (type) {
                case CPU:
                    return (Math.random() < 0.5) ? "cputorottfenyes.png" : "cputorott2fenyes.png";
                case GPU:
                    return (Math.random() < 0.5) ? "gputorottfenyes.png" : "gputorott2fenyes.png";
                case RAM:
                    return (Math.random() < 0.5) ? "ramtorottfenyes.png" : "ramtorott2fenyes.png";
                case PSU:
                    return (Math.random() < 0.5) ? "psutorottfenyes.png" : "psutorott2fenyes.png";
            }
        }
        return null;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!GameStage.isPaused && !GameStage.isGameOver) {
            setX(getX() - difficulty * 2);
            setZIndex(10000);
            if (getX() < -getWidth()) {
                GameStage.point--;
                gameStage.alkatreszek.remove(this);
                remove();
            }
        }
    }
}
