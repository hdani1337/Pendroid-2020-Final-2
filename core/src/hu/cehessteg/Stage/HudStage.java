package hu.cehessteg.Stage;

import hu.cehessteg.Hud.Pause;
import hu.cehessteg.Hud.TextBox;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.PrettyStage;

public class HudStage extends PrettyStage {
    public static AssetList assetList = new AssetList();
    static {
        //add assets here
    }

    //public static BallStage stage;//Hátha kell a GameStageből valami
    private Pause pause;
    private TextBox scoreBoard;

    public HudStage(MyGame game) {
        super(game);
    }

    @Override
    public void assignment() {
        pause = new Pause(game);
        scoreBoard = new TextBox(game,"  0  ",TextBox.RETRO_FONT,1.5f);
    }

    @Override
    public void setSizes() {
        pause.setSize(120,120);
    }

    @Override
    public void setPositions() {
        pause.setPosition(getViewport().getWorldWidth()-pause.getWidth()-28,getViewport().getWorldHeight()-pause.getHeight()-28);
        scoreBoard.setPosition(getViewport().getWorldWidth()/2-scoreBoard.getWidth()/2,pause.getY() + (getViewport().getWorldHeight()-pause.getY())/2 - scoreBoard.getHeight()/2);
    }

    @Override
    public void addListeners() {

    }

    @Override
    public void setZIndexes() {

    }

    @Override
    public void addActors() {
        addActor(scoreBoard);
        addActor(pause);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(scoreBoard.text != "  "+GameStage.point+"  ") {
            scoreBoard.setText("  "+GameStage.point + "  ");
            setPositions();
        }
    }
}
