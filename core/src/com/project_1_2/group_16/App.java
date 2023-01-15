package com.project_1_2.group_16;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.project_1_2.group_16.models.TerrainBuilder;
import com.project_1_2.group_16.screens.GameScreen;
import com.project_1_2.group_16.screens.TitleScreen;

/**
 * The main hub of the application. 
 * This class is the entry point for the entire game.
 */
public class App extends Game {

	// constants
	public static final ColorAttribute AMBIENT_LIGHT = new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f);
	public static final DirectionalLight SUN_LIGHT = new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f);
	public static final int FIELD_SIZE = 20;
	public static final float FIELD_DETAIL = FIELD_SIZE * 5f;
	public static final float RENDER_DISTANCE = FIELD_SIZE * 2f;
	public static final float TILE_SIZE = FIELD_SIZE / FIELD_DETAIL;
	public static final boolean OS_IS_WIN = System.getProperty("os.name").toLowerCase().startsWith("win");
	public static final float MIN_POWER = 1f;
	public static final float MAX_POWER = 5f;
	public static final float POWER_DELTA = 0.05f; 
	public static int SCREEN_WIDTH; // can't init some constants due to class compilation
	public static int SCREEN_HEIGHT;
	public Sound hitSound;
    public Sound dropSound;

	// batches
	public ModelBatch modelBatch;
	public ShapeRenderer shapeBatch;
	public SpriteBatch spriteBatch;
	public TerrainBuilder terrainBuilder;
	
	// environment
	public Environment environment;

	// screens
	public final TitleScreen TITLE_SCREEN = new TitleScreen(this);
	public final GameScreen GAME_SCREEN = new GameScreen(this);
	
	@Override
	public void create() {
		// set fullscreen
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

		// create constants
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		hitSound = Gdx.audio.newSound(Gdx.files.internal("hit_sound.wav"));
		dropSound = Gdx.audio.newSound(Gdx.files.internal("water_sound.wav"));
		
		// init batches
		this.modelBatch = new ModelBatch();
		this.shapeBatch = new ShapeRenderer();
		this.spriteBatch = new SpriteBatch();

		// create environment
		this.environment = new Environment();
		this.environment.set(AMBIENT_LIGHT);
		this.environment.add(SUN_LIGHT);

		// open title screen
		this.TITLE_SCREEN.create();
		this.setScreen(this.TITLE_SCREEN);
	}
}
