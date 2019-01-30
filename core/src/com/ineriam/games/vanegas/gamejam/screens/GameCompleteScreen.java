package com.ineriam.games.vanegas.gamejam.screens;

import com.badlogic.gdx.Gdx;
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
import com.ineriam.games.vanegas.gamejam.data.Const;
import com.ineriam.games.vanegas.gamejam.utils.Tools;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class GameCompleteScreen implements Screen {
    final GdxGame game;

    private FitViewport viewport;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Stage stage;
    private Skin skin;
    private Table table;
    private Label lblGameOver;
    private Image imageBackground;

    private TextureAtlas atlasGraphics;
    private TextureRegion textureRegionBackground;

    private int nokiaScreenWidth;
    private int nokiaScreenHeight;
    private int nokiaScreenX;
    private int nokiaScreenY;

    public GameCompleteScreen (GdxGame gdxGame) {
        this.game = gdxGame;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Const.NOKIA_SCREEN_WIDTH, Const.NOKIA_SCREEN_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport);

        atlasGraphics = game.getAssetManager().get(Const.ASSETS_GRAPHICS_ATLAS);

        skin = Tools.getUISkin(game.getAssetManager());

        textureRegionBackground = new TextureRegion(atlasGraphics.findRegion("background"));
        imageBackground = new Image(textureRegionBackground);
        imageBackground.setSize(camera.viewportWidth, camera.viewportHeight);
        stage.addActor(imageBackground);

        String gameComplete = game.bundle.get("gameComplete");
        lblGameOver = new Label(gameComplete , skin);

        table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        table.add(lblGameOver);
        table.row();

        stage.addActor(table);

        table.addAction(sequence(
                fadeOut(0),
                delay(0.5f),
                fadeIn(0),
                delay(2),
                fadeOut(0),
                delay(1),
                run(new Runnable() {
                    @Override
                    public void run() {
                        Tools.resetGame();
                        game.setScreen(new MenuScreen(game));
                    }
                })
        ));
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
        }
    }

    @Override
    public void resize(int width, int height) {
        if (game.hideController) {
            nokiaScreenWidth = width;
            nokiaScreenHeight = height;
            nokiaScreenX = 0;
            nokiaScreenY = 0;
        } else {
            nokiaScreenWidth = width;
            nokiaScreenHeight = (int) ((Const.NOKIA_SCREEN_HEIGHT * width) / Const.NOKIA_SCREEN_WIDTH);
            nokiaScreenX = 0;
            nokiaScreenY = height - nokiaScreenHeight;
        }



        viewport.update(nokiaScreenWidth, nokiaScreenHeight);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
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
}


