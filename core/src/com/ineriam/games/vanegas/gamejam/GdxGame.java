package com.ineriam.games.vanegas.gamejam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.I18NBundle;
import com.ineriam.games.vanegas.gamejam.actors.RainSystem;
import com.ineriam.games.vanegas.gamejam.data.Const;
import com.ineriam.games.vanegas.gamejam.screens.LoadingScreen;

public class GdxGame extends Game {
	private final AssetManager assetManager;
	public boolean hideController = false;
	public RainSystem rainSystem;

	private FileHandle fileHandle;
	public I18NBundle bundle;

	public GdxGame () {
		assetManager = new AssetManager();
	}

	public void create () {
		fileHandle = Gdx.files.internal(Const.LOCALIZATION_PATH);
		bundle = I18NBundle.createBundle(fileHandle);

		rainSystem = new RainSystem();
		setScreen(new LoadingScreen(this));
	}

	public void render () {
		super.render();
		rainSystem.update(Gdx.graphics.getDeltaTime());
	}

	public void dispose() {

	}

	public AssetManager getAssetManager() {
		return assetManager;
	}
}
