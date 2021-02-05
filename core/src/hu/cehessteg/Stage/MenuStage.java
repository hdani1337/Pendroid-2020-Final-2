package hu.cehessteg.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

import hu.cehessteg.Hud.Logo;
import hu.cehessteg.Hud.TextBox;
import hu.cehessteg.Screen.GameScreen;
import hu.cehessteg.Screen.InfoScreen;
import hu.cehessteg.Screen.OptionsScreen;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.PrettyStage;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

import static hu.cehessteg.Hud.TextBox.RETRO_FONT;


//TODO TEXTÚRÁK BEHELYETTESÍTÉSE HA KÉSZ LESZNEK
//TODO ESETLEG VALAMI ANIMÁCIÓ A HÁTTÉRBE (KÁRTYÁK UGRÁLNAK, STB)

public class MenuStage extends PrettyStage {
    public static final String STARTBUTTON_TEXTURE = "buttons/play.png";
    public static final String OPTIONSBUTTON_TEXTURE = "buttons/options.png";
    public static final String INFOBUTTON_TEXTURE = "buttons/info.png";
    public static final String EXITBUTTON_TEXTURE = "buttons/close.png";
    public static final String BACKGROUND_TEXTURE = "hatter.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.collectAssetDescriptor(Logo.class, assetList);
        assetList.collectAssetDescriptor(TextBox.class, assetList);
    }

    public MenuStage(MyGame game) {
        super(game);
    }

    private Logo logo;
    private OneSpriteStaticActor start;
    private OneSpriteStaticActor info;
    private OneSpriteStaticActor options;
    private OneSpriteStaticActor exit;
    private OneSpriteStaticActor bg;
    private OneSpriteStaticActor gephaz;
    private OneSpriteStaticActor penzActor;
    private MyLabel penzLabel;

    private ArrayList<OneSpriteStaticActor> menuElements;

    @Override
    public void assignment() {
        bg = new OneSpriteStaticActor(game,BACKGROUND_TEXTURE);
        menuElements = new ArrayList<>();
        penzActor = new OneSpriteStaticActor(game,"coin.png");
        penzLabel = new MyLabel(game, GameStage.point+"", new Label.LabelStyle(game.getMyAssetManager().getFont(RETRO_FONT), Color.WHITE)) {
            @Override
            public void init() {

            }
        };
        logo = new Logo(game, Logo.LogoType.MENU);
        start = new OneSpriteStaticActor(game, STARTBUTTON_TEXTURE);
        info = new OneSpriteStaticActor(game, INFOBUTTON_TEXTURE);
        options = new OneSpriteStaticActor(game, OPTIONSBUTTON_TEXTURE);
        exit = new OneSpriteStaticActor(game, EXITBUTTON_TEXTURE);
        gephaz = new OneSpriteStaticActor(game, "buttons/gephaz.png");

        menuElements.add(start);
        menuElements.add(options);
        menuElements.add(logo);
        menuElements.add(exit);
        menuElements.add(info);
    }

    @Override
    public void setSizes() {
        penzActor.setSize(96,96);
        exit.setSize(exit.getWidth()*0.5f,exit.getHeight()*0.5f);
        bg.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight());
        gephaz.setSize(gephaz.getWidth()*0.85f,gephaz.getHeight()*0.85f);
        start.setSize(start.getWidth()*0.6f,start.getHeight()*0.6f);
        options.setSize(options.getWidth()*0.6f,options.getHeight()*0.6f);
        info.setSize(info.getWidth()*0.6f,info.getHeight()*0.6f);
    }

    @Override
    public void setPositions() {
        penzActor.setPosition(12,getViewport().getWorldHeight()-penzActor.getHeight()-12);

        penzLabel.setAlignment(0);
        penzLabel.setPosition(penzActor.getX()+penzActor.getWidth()+5,penzActor.getY()+penzActor.getHeight()/2-penzLabel.getHeight()/2);

        logo.setPosition(getViewport().getWorldWidth()/2-logo.getWidth()/3,getViewport().getWorldHeight()-logo.getHeight()*1.1f);

        gephaz.setPosition(getViewport().getWorldWidth()-gephaz.getWidth()*1.15f,getViewport().getWorldHeight()/2-gephaz.getHeight()/2-60);

        start.setX(getViewport().getWorldWidth()*0.075f);
        start.setY(getViewport().getWorldHeight()*0.59f - start.getHeight()/2);

        info.setY(start.getY() - info.getHeight()*1.2f);
        info.setX(getViewport().getWorldWidth()*0.075f);

        options.setY(info.getY() - options.getHeight()*1.2f);
        options.setX(getViewport().getWorldWidth()*0.075f);

        exit.setY(15);
        exit.setX(getViewport().getWorldWidth() - 15 - exit.getWidth());
    }

    @Override
    public void addListeners() {
        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreenWithPreloadAssets(GameScreen.class, new LoadingStage(game));
            }
        });

        info.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreenWithPreloadAssets(InfoScreen.class, new LoadingStage(game));
            }
        });

        options.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreenWithPreloadAssets(OptionsScreen.class, new LoadingStage(game));
            }
        });

        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void setZIndexes() {

    }

    @Override
    public void addActors() {
        addActor(bg);
        addActor(penzActor);
        addActor(penzLabel);
        addActor(start);
        addActor(info);
        addActor(options);
        addActor(exit);
        addActor(gephaz);
        addActor(logo);
    }
}
