package hu.cehessteg.Actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.cehessteg.Stage.GameStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

import static hu.cehessteg.Hud.TextBox.RETRO_FONT;

public class LadaActor extends MyGroup {

    private GameStage gameStage;
    private AlkatreszType type;
    private MyLabel counter;
    private OneSpriteStaticActor lada;

    public LadaActor(MyGame game, AlkatreszType type, GameStage gameStage) {
        super(game);
        this.gameStage = gameStage;
        this.type = type;
        lada = new OneSpriteStaticActor(game,getHash(type));
        counter = new MyLabel(game, "0", new Label.LabelStyle(game.getMyAssetManager().getFont(RETRO_FONT), Color.WHITE)) {
            @Override
            public void init() {

            }
        };
        addListeners();
        addActor(lada);
        addActor(counter);

        counter.setPosition(10,lada.getHeight()-10-counter.getHeight());
    }

    public void addListeners(){
        counter.setTouchable(null);
        lada.addListener(new ClickListener(){
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

    @Override
    public void act(float delta) {
        super.act(delta);
        setCounter();
    }

    public void setCounter(){
        switch (type){
            case CPU:
                counter.setText(Stock.cpuCount);
                break;
            case GPU:
                counter.setText(Stock.gpuCount);
                break;
            case RAM:
                counter.setText(Stock.ramCount);
                break;
            case PSU:
                counter.setText(Stock.psuCount);
                break;
            case TRASH:
                counter.setText("");
                break;
        }
    }

    @Override
    public void setSize(float width, float height) {
        lada.setSize(width, height);
        counter.setPosition(37,lada.getHeight()-30-counter.getHeight());
    }

    @Override
    public float getWidth() {
        return lada.getWidth();
    }
}
