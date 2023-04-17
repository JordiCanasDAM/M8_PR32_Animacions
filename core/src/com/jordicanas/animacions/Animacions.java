package com.jordicanas.animacions;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class Animacions extends ApplicationAdapter {
	// Constant rows and columns of the sprite sheet
	private static final int FRAME_COLS = 6, FRAME_ROWS = 5;

	// Objects used
	Texture walkSheet;
	Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)


	//Sprite afegit
	Texture walkPrinceOfPersia;
	Animation<TextureRegion> princeOfPersia;

	SpriteBatch spriteBatch;

	// A variable for tracking elapsed time for the animation
	float stateTime;



	@Override
	public void create() {

		// Load the sprite sheet as a Texture
		walkSheet = new Texture(Gdx.files.internal("animation_sheet.png"));
		walkPrinceOfPersia = new Texture(Gdx.files.internal("PrinceOfPersia_Walking.png"));

		// Use the split utility method to create a 2D array of TextureRegions. This is
		// possible because this sprite sheet contains frames of equal size and they are
		// all aligned.
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);
		// Place the regions into a 1D array in the correct order, starting from the top
		// left, going across first. The Animation constructor requires a 1D array.
		TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}

		TextureRegion animationFrames[] = new TextureRegion[16];
		animationFrames[0] = new TextureRegion(walkPrinceOfPersia,0,0,13,53);
		animationFrames[1] = new TextureRegion(walkPrinceOfPersia,24,0,34-24,53);
		animationFrames[2] = new TextureRegion(walkPrinceOfPersia,48,0,60-48,53);
		animationFrames[3] = new TextureRegion(walkPrinceOfPersia,72,0,88-72,53);
		animationFrames[4] = new TextureRegion(walkPrinceOfPersia,96,0,113-96,53);
		animationFrames[5] = new TextureRegion(walkPrinceOfPersia,120,0,140-120,53);
		animationFrames[6] = new TextureRegion(walkPrinceOfPersia,152,0,176-152,53);
		animationFrames[7] = new TextureRegion(walkPrinceOfPersia,184,0,208-184,53);
		animationFrames[8] = new TextureRegion(walkPrinceOfPersia,216,0,239-216,53);
		animationFrames[9] = new TextureRegion(walkPrinceOfPersia,247,0,281-247,53);
		animationFrames[10] = new TextureRegion(walkPrinceOfPersia,287,0,316-287,53);
		animationFrames[11] = new TextureRegion(walkPrinceOfPersia,327,0,353-327,53);
		animationFrames[12] = new TextureRegion(walkPrinceOfPersia,359,0,385-359,53);
		animationFrames[13] = new TextureRegion(walkPrinceOfPersia,392,0,427-392,53);
		animationFrames[14] = new TextureRegion(walkPrinceOfPersia,439,0,467-439,53);
		animationFrames[15] = new TextureRegion(walkPrinceOfPersia,480,0,500-480,53);


		// Initialize the Animation with the frame interval and array of frames
		walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);
		princeOfPersia = new Animation<TextureRegion>(0.1f,animationFrames);


		// Instantiate a SpriteBatch for drawing and reset the elapsed animation
		// time to 0
		spriteBatch = new SpriteBatch();
		stateTime = 0f;
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		// Get current frame of animation for the current stateTime
		TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		TextureRegion spriteFrame = princeOfPersia.getKeyFrame(stateTime,true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, 50, 50, 500, 500); // Draw current frame at (50, 50)
		spriteBatch.draw(spriteFrame, 50, 50);
		spriteBatch.end();
	}

	@Override
	public void dispose() { // SpriteBatches and Textures must always be disposed
		spriteBatch.dispose();
		walkSheet.dispose();
	}
}
