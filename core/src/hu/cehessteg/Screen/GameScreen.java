package hu.cehessteg.Screen;

import hu.cehessteg.Stage.GameOverStage;
import hu.cehessteg.Stage.GameStage;
import hu.cehessteg.Stage.HudStage;
import hu.cehessteg.Stage.PauseStage;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

import static hu.cehessteg.Stage.OptionsStage.difficulty;
import static hu.cehessteg.TetrisGame.preferences;


public class GameScreen extends MyScreen {
    public static AssetList assetList = new AssetList();
    static {
        assetList.collectAssetDescriptor(GameStage.class, assetList);
        assetList.collectAssetDescriptor(HudStage.class, assetList);
        assetList.collectAssetDescriptor(GameOverStage.class, assetList);
        assetList.collectAssetDescriptor(PauseStage.class, assetList);
    }

    public GameScreen(MyGame game) {
        super(game);
    }


    @Override
    protected void afterAssetsLoaded() {
        if(difficulty == 0) difficulty = 2;
        preferences.putInteger("difficulty",difficulty);
        preferences.flush();
        addStage(new GameStage(game),1,true);
        addStage(new HudStage(game),2, true);
        addStage(new PauseStage(game),3, true);
        addStage(new GameOverStage(game),4, true);
    }

    @Override
    public AssetList getAssetList() {
        return assetList;
    }

    @Override
    public void init() {

    }
}
