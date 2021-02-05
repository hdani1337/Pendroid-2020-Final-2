package hu.cehessteg.Stage;

import hu.cehessteg.SoundManager;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PrettySimpleStage;

import static hu.cehessteg.TetrisGame.muted;

public class GameStage extends PrettySimpleStage {

    public static int point;

    public GameStage(MyGame game) {
        super(new ResponseViewport(800),game);
    }

    @Override
    public void assignment() {
        SoundManager.assign();

    }

    @Override
    public void setSizes() {

    }

    @Override
    public void setPositions() {

    }

    @Override
    public void addListeners() {

    }

    @Override
    public void setZIndexes() {

    }

    @Override
    public void addActors() {

    }



    @Override
    public void afterInit() {
        super.afterInit();
    }

    private void addTimers(){

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
