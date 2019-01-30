package com.ineriam.games.vanegas.gamejam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ineriam.games.vanegas.gamejam.GdxGame;
import com.ineriam.games.vanegas.gamejam.controller.PhoneController;
import com.ineriam.games.vanegas.gamejam.controller.PhoneControllerInterface;
import com.ineriam.games.vanegas.gamejam.data.Const;
import com.ineriam.games.vanegas.gamejam.utils.Tools;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class InfoScreen implements Screen, PhoneControllerInterface {
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

    private Label lblVanegas;
    private Label lblIneriam;
    private Label lblJam;
    private Label lblCredits;

    public InfoScreen (GdxGame gdxGame) {
        this.game = gdxGame;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Const.NOKIA_SCREEN_WIDTH, Const.NOKIA_SCREEN_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        phoneController = new PhoneController(game, this, false, false, true, false);
        phoneController.show();

        atlasGraphics = game.getAssetManager().get(Const.ASSETS_GRAPHICS_ATLAS);

        stage = new Stage(viewport);
        skin = Tools.getUISkin(game.getAssetManager());

        textureRegionBackground = new TextureRegion(atlasGraphics.findRegion("background"));

        imageBackground = new Image(textureRegionBackground);
        imageBackground.setSize(camera.viewportWidth, camera.viewportHeight);
        stage.addActor(imageBackground);

        String vanegas = game.bundle.get("game");
        String version = game.bundle.get("version");
        String date = game.bundle.get("date");
        String ineriam = game.bundle.get("ineriam");
        String ineriamUrl = game.bundle.get("ineriamUrl");
        String gameJam = game.bundle.get("gameJam");
        String libgdx = game.bundle.get("libgdx");

        String gameInfo = vanegas + " " + version + ", " + date;
        String ineriamInfo = ineriam + " " + ineriamUrl;

        lblVanegas = new Label(gameInfo, skin);
        lblIneriam = new Label(ineriamInfo, skin);
        lblJam = new Label(gameJam, skin);
        lblCredits = new Label(libgdx, skin);

        lblVanegas.setPosition(lblVanegas.getWidth() / 2, 35);
        lblIneriam.setPosition(lblIneriam.getWidth() / 2, 24);
        lblJam.setPosition(lblJam.getWidth() / 2, 13);
        lblCredits.setPosition(lblCredits.getWidth() / 2, 2);


        stage.addActor(lblVanegas);
        stage.addActor(lblIneriam);
        stage.addActor(lblJam);
        stage.addActor(lblCredits);

        lblVanegas.addAction(forever(
                sequence(
                        moveTo(-(lblVanegas.getWidth() / 2), lblVanegas.getY(), lblVanegas.getText().length / 2),
                        moveTo((lblVanegas.getWidth() / 2), lblVanegas.getY(), lblVanegas.getText().length / 2)
                )
        ));

        lblIneriam.addAction(forever(
                sequence(
                        moveTo(-(lblIneriam.getWidth() / 2), lblIneriam.getY(), lblIneriam.getText().length / 2),
                        moveTo((lblIneriam.getWidth() / 2), lblIneriam.getY(), lblIneriam.getText().length / 2)
                )
        ));

        lblJam.addAction(forever(
                sequence(
                        moveTo(-(lblJam.getWidth() / 2), lblJam.getY(), lblJam.getText().length / 2),
                        moveTo((lblJam.getWidth() / 2), lblJam.getY(), lblJam.getText().length / 2)
                )
        ));

        lblCredits.addAction(forever(
                sequence(
                        moveTo(-(lblCredits.getWidth() / 2), lblCredits.getY(), lblCredits.getText().length / 2),
                        moveTo((lblCredits.getWidth() / 2), lblCredits.getY(), lblCredits.getText().length / 2)
                )
        ));

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(phoneController.stage);
        inputMultiplexer.addProcessor(phoneController);
        Gdx.input.setInputProcessor(inputMultiplexer);
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

    }

    @Override
    public void keyDown() {

    }

    @Override
    public void keyLeft() {

    }

    @Override
    public void keyRight() {

    }

    @Override
    public void keyMenu() {

    }

    @Override
    public void keyEnter() {

    }

    @Override
    public void keyBack() {
        try {
            game.setScreen(new MenuScreen(game));
        } catch (Exception e) {

        }
    }
}

