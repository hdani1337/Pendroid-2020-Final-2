package hu.cehessteg.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.cehessteg.Stage.GameStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class LadaActor extends OneSpriteStaticActor {

    private GameStage gameStage;
    private AlkatreszType type;

    public LadaActor(MyGame game, AlkatreszType type, GameStage gameStage) {
        super(game, getHash(type));
        this.gameStage = gameStage;
        this.type = type;
        addListeners();
    }

    public void addListeners(){
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(LadaActor.this.type == AlkatreszType.TRASH){
                    gameStage.selectedActor.remove();
                }else{
                    if(LadaActor.this.type == gameStage.selectedActor.type){
                        switch (type){
                            case CPU:
                                Stock.cpuCount++;
                                break;
                            case GPU:
                                Stock.gpuCount++;
                                break;
                            case RAM:
                                Stock.ramCount++;
                                break;
                            case PSU:
                                Stock.psuCount++;
                                break;
                        }
                        gameStage.selectedActor.remove();
                    }
                }
                gameStage.selectedActor = null;
            }
        });
    }

    public static String getHash(AlkatreszType type){
        switch (type) {
            case CPU:
                return "cpudoboz.png";
            case GPU:
                return "gpudoboz.png";
            case RAM:
                return "ramdoboz.png";
            case PSU:
                return "psudoboz.png";
            case TRASH:
                return "kuka.png";
        }
        return null;
    }
}
