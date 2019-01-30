package com.ineriam.games.vanegas.gamejam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ineriam.games.vanegas.gamejam.GdxGame;
import com.ineriam.games.vanegas.gamejam.data.Const;
import com.ineriam.games.vanegas.gamejam.utils.Tools;

public class LoadingScreen implements Screen {
    final GdxGame game;

    private FitViewport viewport;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private int nokiaScreenWidth;
    private int nokiaScreenHeight;
    private int nokiaScreenX;
    private int nokiaScreenY;

    private float progress;
    private float progressBarWidth;

    private FileHandleResolver fileHandleResolver;
    private FreetypeFontLoader.FreeTypeFontLoaderParameter defaultFontParameter;
    private FreetypeFontLoader.FreeTypeFontLoaderParameter titleFontParameter;
    private Rectangle rectBackground;
    private Rectangle rectProgressBar;

    public LoadingScreen (GdxGame gdxGame) {
        this.game = gdxGame;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Const.NOKIA_SCREEN_WIDTH, Const.NOKIA_SCREEN_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        shapeRenderer = new ShapeRenderer();

        rectBackground = new Rectangle(0, 0, Const.NOKIA_SCREEN_WIDTH, Const.NOKIA_SCREEN_HEIGHT);

        rectProgressBar = new Rectangle();
        rectProgressBar.width = rectBackground.width * 0.6f;
        rectProgressBar.height = rectBackground.height * 0.2f;
        rectProgressBar.x = (rectBackground.width / 2) - (rectProgressBar.width / 2);
        rectProgressBar.y = (rectBackground.height / 2) - (rectProgressBar.height / 2);

        progressBarWidth = rectProgressBar.width;

        fileHandleResolver = new InternalFileHandleResolver();

        game.getAssetManager().setLoader(
                FreeTypeFontGenerator.class,
                new FreeTypeFontGeneratorLoader(fileHandleResolver));

        game.getAssetManager().setLoader(
                BitmapFont.class,
                ".ttf",
                new FreetypeFontLoader(fileHandleResolver));


        defaultFontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        defaultFontParameter.fontFileName = Const.DEFAULT_FONT;
        defaultFontParameter.fontParameters.size = Const.DEFAULT_FONT_SIZE;
        //defaultFontParameter.fontParameters.color = Tools.toRGB(67,82, 61);
        defaultFontParameter.fontParameters.minFilter = Texture.TextureFilter.Nearest;
        defaultFontParameter.fontParameters.magFilter = Texture.TextureFilter.Nearest;
        game.getAssetManager().load(Const.DEFAULT_FONT, BitmapFont.class, defaultFontParameter);



        game.getAssetManager().load(Const.ASSETS_GRAPHICS_ATLAS, TextureAtlas.class);

    }

    @Override
    public void render(float delta) {
        if (delta > 0) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


            // Nokia Screen
            Gdx.gl.glViewport(nokiaScreenX, nokiaScreenY, nokiaScreenWidth, nokiaScreenHeight);

            camera.update();

            if (game.getAssetManager().update()) {
                game.setScreen(new SplashScreen(game));
                //game.setScreen(new MenuScreen(game));
            } else {
                progress = game.getAssetManager().getProgress();
            }

            rectProgressBar.width = progress * progressBarWidth;

            shapeRenderer.setTransformMatrix(camera.view);
            shapeRenderer.setProjectionMatrix(camera.projection);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

            shapeRenderer.setColor(Tools.toRGB(199, 240, 216));
            Tools.renderRectangle(shapeRenderer, rectBackground);

            shapeRenderer.setColor(Tools.toRGB(67, 82, 61));
            Tools.renderRectangle(shapeRenderer, rectProgressBar);

            shapeRenderer.end();
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

