package hu.cehessteg.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

import static hu.cehessteg.Stage.GameStage.isGameOver;
import static hu.cehessteg.Stage.GameStage.isPaused;

public class BuilderActor extends OneSpriteStaticActor {
    public Computer computer;

    public BuilderActor(MyGame game, String hash) {
        super(game, hash);
        computer = new Computer();
        addListeners();
    }

    private void addListeners(){
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(!isGameOver && !isPaused) computer.build();
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(computer.isBuildable()){
            setAlpha(1);
        }else{
            setAlpha(0.4f);
        }
    }
}
