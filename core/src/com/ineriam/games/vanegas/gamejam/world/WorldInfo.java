package com.ineriam.games.vanegas.gamejam.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ineriam.games.vanegas.gamejam.GdxGame;
import com.ineriam.games.vanegas.gamejam.data.Const;
import com.ineriam.games.vanegas.gamejam.utils.Tools;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class WorldInfo {
    final GdxGame game;
    private World world;

    private FitViewport viewport;
    private OrthographicCamera camera;
    private Stage stage;
    private Skin skin;
    private Table table;
    private Image imageBackground;
    private Image imageHeart;
    private Image imageSword;
    private Image imageBarContainerHp;
    private Image imageBarContainerAt;
    private Image imageBarHp;
    private Image imageLineVertical;
    private Image imageGrid;
    private Image imagePlayerCursor;

    private Image imageGuardianICursor;
    private Image imageGuardianIICursor;
    private Image imageGuardianIIICursor;
    private Image imageGuardianIVCursor;
    private Image imageGuardianVCursor;
    private Image imageGuardianVICursor;

    private Label lblHp;
    private Label lblAttack;

    private TextureAtlas atlasGraphics;
    private TextureRegion textureRegionBackground;
    private TextureRegion textureRegionHeart;
    private TextureRegion textureRegionSword;

    private int[][] cursorPositions;

    private Vector2 playerGridPosition = new Vector2();

    private int nextLevel = 0;
    private int nextExpLevel = 0;
    private int playerExp = 0;
    private int maxBarWidth = 0;

    private float factor = 3; // grid

    public WorldInfo (GdxGame gdxGame, World world) {
        this.game = gdxGame;
        this.world = world;
    }

    public void show () {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Const.NOKIA_SCREEN_WORLD_INFO_WIDTH, Const.NOKIA_SCREEN_WORLD_INFO_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport);
        atlasGraphics = game.getAssetManager().get(Const.ASSETS_GRAPHICS_ATLAS);

        skin = Tools.getUISkin(game.getAssetManager());

        textureRegionBackground = new TextureRegion(atlasGraphics.findRegion("background"));
        textureRegionHeart = new TextureRegion(atlasGraphics.findRegion("heart"));
        textureRegionSword = new TextureRegion(atlasGraphics.findRegion("sword"));

        imageBackground = new Image(textureRegionBackground);
        imageBackground.setSize(camera.viewportWidth, camera.viewportHeight);

        imageHeart = new Image(textureRegionHeart);
        imageHeart.setPosition(3, 35);

        imageSword = new Image(textureRegionSword);
        imageSword.setPosition(3, 20);

        imageBarContainerHp = new Image(new TextureRegion(atlasGraphics.findRegion("bar_background")));
        imageBarContainerHp.setPosition(3, 30);

        imageBarHp = new Image(new TextureRegion(atlasGraphics.findRegion("bar")));
        imageBarHp.setPosition(4, 31);
        maxBarWidth = (int) imageBarHp.getPrefWidth();

        imageBarContainerAt = new Image(new TextureRegion(atlasGraphics.findRegion("bar_background")));
        imageBarContainerAt.setPosition(3, 21);

        imageLineVertical = new Image(new TextureRegion(atlasGraphics.findRegion("bar")));
        imageLineVertical.setSize(1, Const.NOKIA_SCREEN_WORLD_INFO_HEIGHT);
        imageLineVertical.setPosition(0, 0);

        imageGrid = new Image(new TextureRegion(atlasGraphics.findRegion("grid")));
        imageGrid.setPosition(3, 2);


        imagePlayerCursor = new Image(new TextureRegion(atlasGraphics.findRegion("cursor")));

        imageGuardianICursor = new Image(new TextureRegion(atlasGraphics.findRegion("cursor")));
        imageGuardianIICursor = new Image(new TextureRegion(atlasGraphics.findRegion("cursor")));
        imageGuardianIIICursor = new Image(new TextureRegion(atlasGraphics.findRegion("cursor")));
        imageGuardianIVCursor = new Image(new TextureRegion(atlasGraphics.findRegion("cursor")));
        imageGuardianVCursor = new Image(new TextureRegion(atlasGraphics.findRegion("cursor")));
        imageGuardianVICursor = new Image(new TextureRegion(atlasGraphics.findRegion("cursor")));


        lblHp = new Label("", skin);
        lblHp.setPosition(10, 35);
        lblHp.setSize(25, 8);
        lblHp.setWrap(true);
        lblHp.setAlignment(Align.right);

        lblAttack = new Label("ABC", skin);
        lblAttack.setPosition(10, 20);
        lblAttack.setSize(25, 8);
        lblAttack.setWrap(true);
        lblAttack.setAlignment(Align.right);

        stage.addActor(imageBackground);
        stage.addActor(imageHeart);
        stage.addActor(imageSword);
        stage.addActor(imageBarContainerHp);
        stage.addActor(imageBarHp);
        //stage.addActor(imageBarContainerAt);
        stage.addActor(imageLineVertical);

        stage.addActor(imagePlayerCursor);

        if (!Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_I_COMPLETE)) {
            stage.addActor(imageGuardianICursor);
        }

        if (!Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_II_COMPLETE)) {
            stage.addActor(imageGuardianIICursor);
        }

        if (!Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_III_COMPLETE)) {
            stage.addActor(imageGuardianIIICursor);
        }

        if (!Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_IV_COMPLETE)) {
            stage.addActor(imageGuardianIVCursor);
        }

        if (!Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_V_COMPLETE)) {
            stage.addActor(imageGuardianVCursor);
        }

        if (!Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_VI_COMPLETE)) {
            stage.addActor(imageGuardianVICursor);
        }

        stage.addActor(imageGrid);

        stage.addActor(lblHp);
        stage.addActor(lblAttack);

        drawPlayerGridPosition();

        guardiansAnimation(imageGuardianICursor);
        guardiansAnimation(imageGuardianIICursor);
        guardiansAnimation(imageGuardianIIICursor);
        guardiansAnimation(imageGuardianIVCursor);
        guardiansAnimation(imageGuardianVCursor);
        guardiansAnimation(imageGuardianVICursor);
    }

    public void update (float delta) {
        lblHp.setText(world.player.hp + "/" + world.playerLevel);
        lblAttack.setText("A: " + world.playerLevel);

        drawHpExp();
        drawGuardians();



        camera.update();
        stage.act(delta);
        stage.draw();

    }

    public void resize (int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }


    private void drawPlayerGridPosition () {
        try {
            int posX = (int) world.playerWorldMapPosition.x;
            int posY = (int) world.playerWorldMapPosition.y;

            float x = imageGrid.getX() + (posX * factor) + 1;
            float y = imageGrid.getY() + (posY * factor) + 1;

            imagePlayerCursor.setPosition(x, y);
        } catch (Exception e) {

        }
    }
    // 29,1
    private void drawHpExp () {
        try {
            nextLevel = world.playerLevel + 1;

            if (nextLevel > Const.PLAYER_LEVELS[Const.PLAYER_LEVELS.length - 1].levelId) {
                nextLevel = Const.PLAYER_LEVELS[Const.PLAYER_LEVELS.length - 1].levelId;
                imageBarHp.setWidth(maxBarWidth);
            } else {
                nextExpLevel = Const.PLAYER_LEVELS[nextLevel].hp;
                playerExp = world.playerCurrentExperience;
                imageBarHp.setWidth((playerExp * maxBarWidth) / nextExpLevel);
            }





        } catch (Exception e) {

        }
    }

    private void drawGuardians () {
        try {

            imageGuardianICursor.setPosition(
                    imageGrid.getX() + ((int) Const.POSITION_GUARDIAN_I.x * factor) + 1,
                    imageGrid.getY() + ((int) Const.POSITION_GUARDIAN_I.y * factor) + 1
            );

            imageGuardianIICursor.setPosition(
                    imageGrid.getX() + ((int) Const.POSITION_GUARDIAN_II.x * factor) + 1,
                    imageGrid.getY() + ((int) Const.POSITION_GUARDIAN_II.y * factor) + 1
            );

            imageGuardianIIICursor.setPosition(
                    imageGrid.getX() + ((int) Const.POSITION_GUARDIAN_III.x * factor) + 1,
                    imageGrid.getY() + ((int) Const.POSITION_GUARDIAN_III.y * factor) + 1
            );

            imageGuardianIVCursor.setPosition(
                    imageGrid.getX() + ((int) Const.POSITION_GUARDIAN_IV.x * factor) + 1,
                    imageGrid.getY() + ((int) Const.POSITION_GUARDIAN_IV.y * factor) + 1
            );

            imageGuardianVCursor.setPosition(
                    imageGrid.getX() + ((int) Const.POSITION_GUARDIAN_V.x * factor) + 1,
                    imageGrid.getY() + ((int) Const.POSITION_GUARDIAN_V.y * factor) + 1
            );

            imageGuardianVICursor.setPosition(
                    imageGrid.getX() + ((int) Const.POSITION_GUARDIAN_VI.x * factor) + 1,
                    imageGrid.getY() + ((int) Const.POSITION_GUARDIAN_VI.y * factor) + 1
            );
        } catch (Exception e) {

        }
    }

    private void guardiansAnimation (Image image) {
        try {
            image.addAction(forever(
                    sequence(
                            fadeOut(0),
                            delay(0.5f),
                            fadeIn(0),
                            delay(0.5f)
                    )
            ));
        } catch (Exception e) {

        }
    }
}
