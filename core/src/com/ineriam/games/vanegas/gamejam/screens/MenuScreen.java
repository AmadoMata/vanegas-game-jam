package com.ineriam.games.vanegas.gamejam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ineriam.games.vanegas.gamejam.GdxGame;
import com.ineriam.games.vanegas.gamejam.controller.PhoneController;
import com.ineriam.games.vanegas.gamejam.controller.PhoneControllerInterface;
import com.ineriam.games.vanegas.gamejam.data.Const;
import com.ineriam.games.vanegas.gamejam.utils.Tools;

public class MenuScreen implements Screen, PhoneControllerInterface {
    final GdxGame game;
    private PhoneController phoneController;

    private FitViewport viewport;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Stage stage;
    private Skin skin;
    private Table table;
    private Image imageBackground;

    private int nokiaScreenWidth;
    private int nokiaScreenHeight;
    private int nokiaScreenX;
    private int nokiaScreenY;

    private int phoneControllerWidth;
    private int phoneControllerHeight;
    private int phoneControllerX;
    private int phoneControllerY;

    private TextureAtlas atlasGraphics;
    private TextureRegion textureRegionBackground;

    private TextButton btnContinue;
    private TextButton btnNew;
    private TextButton btnInfo;

    private int index = 0;
    private int maxButtons;

    private TextButton[] buttons;
    private InputEvent inputEvent;

    private boolean nowPlaying;

    public MenuScreen (GdxGame gdxGame) {
        this.game = gdxGame;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Const.NOKIA_SCREEN_WIDTH, Const.NOKIA_SCREEN_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        phoneController = new PhoneController(game, this, true, false, false, true);
        phoneController.show();

        atlasGraphics = game.getAssetManager().get(Const.ASSETS_GRAPHICS_ATLAS);

        stage = new Stage(viewport);
        skin = Tools.getUISkin(game.getAssetManager());

        textureRegionBackground = new TextureRegion(atlasGraphics.findRegion("background"));

        imageBackground = new Image(textureRegionBackground);
        imageBackground.setSize(camera.viewportWidth, camera.viewportHeight);
        stage.addActor(imageBackground);

        String continuePlaying = game.bundle.get("continue");
        String newGame = game.bundle.get("newGame");
        String info = game.bundle.get("info");

        btnContinue = new TextButton(continuePlaying, skin);
        btnNew = new TextButton(newGame, skin);
        btnInfo = new TextButton(info, skin);

        btnContinue.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                return true;
            }
        });

        btnNew.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Tools.resetGame();
                Tools.saveNowPlaying(true);
                game.setScreen(new GameScreen(game));
                return true;
            }
        });

        btnInfo.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new InfoScreen(game));
                return true;
            }
        });

        nowPlaying = Tools.getNowPlaying();



        if (nowPlaying) {
            maxButtons = 3;
            buttons = new TextButton[maxButtons];
            buttons[0] = btnContinue;
            buttons[1] = btnNew;
            buttons[2] = btnInfo;

            btnContinue.setVisible(true);

        } else {
            maxButtons = 2;
            buttons = new TextButton[maxButtons];
            buttons[0] = btnNew;
            buttons[1] = btnInfo;

            btnContinue.setVisible(false);
            btnContinue.setDisabled(true);
        }

        buttons[0].setChecked(true);
        index = 0;

        table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        table.add(btnContinue).fillX();
        table.row();
        table.add(btnNew).fillX();
        table.row();
        table.add(btnInfo).fillX();
        table.row();
        stage.addActor(table);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(phoneController.stage);
        inputMultiplexer.addProcessor(phoneController);
        Gdx.input.setInputProcessor(inputMultiplexer);

        inputEvent = new InputEvent();
        inputEvent.setType(InputEvent.Type.touchDown);
    }

    @Override
    public void render(float delta) {
        if (delta > 0) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // Nokia Screen
            Gdx.gl.glViewport(nokiaScreenX, nokiaScreenY, nokiaScreenWidth, nokiaScreenHeight);

            camera.update();
            stage.act(delta);
            stage.draw();

            Gdx.gl.glViewport(phoneControllerX, phoneControllerY, phoneControllerWidth, phoneControllerHeight);
            phoneController.render(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        if (game.hideController) {
            nokiaScreenWidth = width;
            nokiaScreenHeight = height;
            nokiaScreenX = 0;
            nokiaScreenY = 0;

            phoneControllerWidth = 0;
            phoneControllerHeight = 0;
            phoneControllerX = 0;
            phoneControllerY = 0;
        } else {
            nokiaScreenWidth = width;
            nokiaScreenHeight = (int) ((Const.NOKIA_SCREEN_HEIGHT * width) / Const.NOKIA_SCREEN_WIDTH);
            nokiaScreenX = 0;
            nokiaScreenY = height - nokiaScreenHeight;

            phoneControllerWidth = width;
            phoneControllerHeight = height - nokiaScreenHeight;
            phoneControllerX = 0;
            phoneControllerY = 0;
        }


        viewport.update(nokiaScreenWidth, nokiaScreenHeight);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        phoneController.resize(phoneControllerWidth, phoneControllerHeight);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void keyUp() {
        clickButtons(-1);
    }

    @Override
    public void keyDown() {
        clickButtons(1);
    }

    @Override
    public void keyLeft() {
        clickButtons(-1);
    }

    @Override
    public void keyRight() {
        clickButtons(1);
    }

    @Override
    public void keyMenu() {

    }

    @Override
    public void keyEnter() {
        try {
            buttons[index].fire(inputEvent);
        } catch (Exception e) {

        }
    }

    @Override
    public void keyBack() {

    }

    private void clickButtons (int move) {
        try {
            index += move;
            if (index < 0) index = 0;
            if (index >= maxButtons) index = maxButtons - 1;

            for (TextButton textButton : buttons) {
                textButton.setChecked(false);
            }

            buttons[index].setChecked(true);

        } catch (Exception e) {

        }
    }
}
