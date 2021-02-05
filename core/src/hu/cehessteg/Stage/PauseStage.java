package hu.cehessteg.Stage;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.cehessteg.Hud.TextBox;
import hu.cehessteg.Screen.GameScreen;
import hu.cehessteg.SoundManager;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.PrettyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;

import static hu.cehessteg.SoundManager.gameMusic;
import static hu.cehessteg.TetrisGame.muted;
import static hu.cehessteg.TetrisGame.preferences;


public class PauseStage extends PrettyStage {
    //region AssetList
    public static final String BLACK_TEXTURE = "pic/fekete.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.collectAssetDescriptor(TextBox.class,assetList);
        assetList.addTexture(BLACK_TEXTURE);
    }
    //endregion
    //region Változók
    private TextBox info;
    private TextBox pontok;
    private TextBox again;
    private TextBox menu;

    private OneSpriteStaticActor black;
    //endregion
    //region Konstruktor
    public PauseStage(MyGame game) {
        super(new ResponseViewport(900), game);
    }
    //endregion
    //region Absztrakt metódusok
    @Override
    public void assignment() {
        info = new TextBox(game, "Megállítva",TextBox.RETRO_FONT,2f);
        pontok = new TextBox(game, "Pontszámok\n"+GameStage.point,TextBox.RETRO_FONT,1.5f);
        again = new TextBox(game, "Folytatás",TextBox.RETRO_FONT,1.5f);
        menu = new TextBox(game, "Kilépés",TextBox.RETRO_FONT,1.5f);

        black = new OneSpriteStaticActor(game, BLACK_TEXTURE);

        addedActors = false;
        alpha = 0;
    }

    @Override
    public void setSizes() {
        black.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight());
        menu.setWidth(menu.getWidth()*1.1f);
        pontok.setWidth(pontok.getWidth()*0.7f);
    }

    @Override
    public void setPositions() {
        info.setPosition(getViewport().getWorldWidth()/2-info.getWidth()/2,getViewport().getWorldHeight()*0.65f);
        pontok.setPosition(getViewport().getWorldWidth()/2-pontok.getWidth()/2,info.getY()-pontok.getHeight()-48);
        again.setPosition(getViewport().getWorldWidth()/2-again.getWidth()/2,pontok.getY()-again.getHeight()-48);
        menu.setPosition(getViewport().getWorldWidth()/2-menu.getWidth()/2,again.getY()-menu.getHeight()-48);
    }

    @Override
    public void addListeners() {
        again.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(getScreen() != null) {
                    if (getScreen() instanceof GameScreen){}
                        GameStage.isPaused = false;
                }
            }
        });

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(!muted && gameMusic != null) {
                    gameMusic.stop();
                }
                game.setScreenBackByStackPopWithPreloadAssets(new LoadingStage(game));
                preferences.putLong("coin", GameStage.point);
                preferences.flush();
            }
        });
    }

    @Override
    public void setZIndexes() {

    }

    @Override
    public void addActors() {
        /**
         * MINDEN ACTOR LÁTHATATLAN LESZ
         * EZUTÁN HOZZÁADJUK AZ ACTOROKAT A STAGEHEZ
         * ÁTVÁLTJUK AZ {@link addedActors} VÁLTOZÓT IGAZRA, ÍGY CSAK EGYSZER KERÜLNEK FEL A STAGERE AZ ACTOROK, MERT TÖBBSZÖR NEM HÍVÓDIK MEG EZ A METÓDUS
         * **/
        black.setAlpha(0);
        info.setAlpha(0);
        pontok.setAlpha(0);
        again.setAlpha(0);
        menu.setAlpha(0);

        addActor(black);
        addActor(info);
        addActor(pontok);
        addActor(again);
        addActor(menu);

        addedActors = true;
    }

    @Override
    public void afterInit() {
        /**
         * MIVEL AZ addActors() METÓDUS AUTOMATIKUSAN LEFUT, EZÉRT EGYSZER HOZZÁADJA A GOMBOKAT A STAGEHEZ 0-ÁS ALPHÁVAL
         * EZÉRT A STAGE LÉTREHOZÁSA UTÁN EL KELL ŐKET TÁVOLÍTANI, NEHOGY VÉLETLENÜL KATTINTSUNK A GOMBOKRA
         * **/
        black.remove();
        info.remove();
        pontok.remove();
        again.remove();
        menu.remove();
        addedActors = false;
    }
    //endregion
    //region Act metódusai
    private boolean addedActors;
    private float alpha;

    @Override
    public void act(float delta) {
        super.act(delta);
        if(getScreen() != null) {
            if (getScreen() instanceof GameScreen) {
                if (GameStage.isPaused && !GameStage.isGameOver) pause(gameMusic);
                else if (!GameStage.isPaused && addedActors) resume(gameMusic);
            }
        }
    }

    @Deprecated
    private void pause(Music music){
        if(getScreen() != null && (getScreen() instanceof GameScreen)){
           if(!pontok.text.equals("Pontszámod: "+GameStage.point)) {
                pontok.setText("Pontszámod: "+GameStage.point);
                pontok.setX(getViewport().getWorldWidth()/2-pontok.getWidth()/2);
            }
        }
        //Adjuk hozzá a gombokat a stagehez ha még nincsenek rajta
        if(!addedActors) {
            addActors();
            if(music != null && !muted) music.pause();
        }

        //Áttűnés
        if(alpha < 0.95f)
            alpha += 0.05f;
        else
            alpha = 1;

        black.setAlpha(alpha*0.6f);
        info.setAlpha(alpha);
        pontok.setAlpha(alpha);
        again.setAlpha(alpha);
        menu.setAlpha(alpha);
        //Áttűnés vége
    }

    @Deprecated
    private void resume(Music music){
        if(music != null && !muted) music.play();
        //Áttűnéssel tűnnek el a stageről
        if(alpha > 0.05f) {
            //if(!music.isPlaying()) music.play();
            alpha -= 0.05f;
            black.setAlpha(alpha*0.6f);
            info.setAlpha(alpha);
            pontok.setAlpha(alpha);
            again.setAlpha(alpha);
            menu.setAlpha(alpha);
        }
        //Ha már láthatatlanok, akkor eltávolítjuk őket a stageről
        else {
            alpha = 0;
            black.remove();
            info.remove();
            pontok.remove();
            again.remove();
            menu.remove();
            addedActors = false;
        }
    }
    //endregion
}

