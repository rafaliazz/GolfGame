package com.project_1_2.group_16.camera;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

/**
 * Camera that allows the user to freely move around the course.
 * Adapted from: https://stackoverflow.com/a/34058580
 */
public class FreeCamera implements InputProcessor {

    /**
     * The camera sensitivity.
     */
	public final float rotateSpeed = 0.1f;

    /**
     * How quick the camera moves.
     */
    public final float movementSpeed = 1.5f;

    private Camera camera;
    private Vector3 util = new Vector3();
    private int dragX, dragY;

    public FreeCamera(Camera camera) {
        this.camera = camera;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
		// rotating on the y axis
		float x = this.dragX - screenX;
		this.camera.rotate(Vector3.Y, x * rotateSpeed);
	
		// rotating on the x and z axis
		float y = (float)Math.sin((double)(this.dragY -screenY) / 180f);
		if (Math.abs(this.camera.direction.y + y * (rotateSpeed * 5.0f)) < 0.9) {
			this.camera.direction.y +=  y * (rotateSpeed * 5.0f) ;
		}
	
        // update camera
		this.camera.update();
		this.dragX = screenX;
		this.dragY = screenY;
		return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        this.mouseMoved(screenX, screenY);
        return true;
    }

    /**
     * The controls for the free camera.
     * @param input input reference
     * @param dT the time since the last frame
     */
    public void move(Input input, float dT) {
        if (input.isKeyPressed(Keys.W)) { // move forward
            this.util.set(this.camera.direction);
            this.util.x *= movementSpeed * dT;
            this.util.y = 0f;
            this.util.z *= movementSpeed * dT;
            this.camera.translate(util);
            this.camera.update();
        }
        else if (input.isKeyPressed(Keys.UP)) { // move forward
            this.util.set(this.camera.direction);
            this.util.x *= movementSpeed * dT;
            this.util.y = 0f;
            this.util.z *= movementSpeed * dT;
            this.camera.translate(util);
            this.camera.update();
        }

        if (input.isKeyPressed(Keys.S)) { // move backward
            this.util.set(this.camera.direction);
            this.util.x *= -movementSpeed * dT;
            this.util.y = 0f;
            this.util.z *= -movementSpeed * dT;
            this.camera.translate(util);
            this.camera.update();
        }
        else if (input.isKeyPressed(Keys.DOWN)) { // move backward
            this.util.set(this.camera.direction);
            this.util.x *= -movementSpeed * dT;
            this.util.y = 0f;
            this.util.z *= -movementSpeed * dT;
            this.camera.translate(util);
            this.camera.update();
        }

        if (input.isKeyPressed(Keys.A)) { // move left
            this.util.set(this.camera.direction);
            this.util.x *= movementSpeed * dT;
            this.util.y = 0f;
            this.util.z *= movementSpeed * dT;
            this.util.rotate(Vector3.Y, 90);
            this.camera.translate(util);
            this.camera.update();
        }
        else if (input.isKeyPressed(Keys.LEFT)) { // move left
            this.util.set(this.camera.direction);
            this.util.x *= movementSpeed * dT;
            this.util.y = 0f;
            this.util.z *= movementSpeed * dT;
            this.util.rotate(Vector3.Y, 90);
            this.camera.translate(util);
            this.camera.update();
        }

        if (input.isKeyPressed(Keys.D)) { // move right
            this.util.set(this.camera.direction);
            this.util.x *= movementSpeed * dT;
            this.util.y = 0f;
            this.util.z *= movementSpeed * dT;
            this.util.rotate(Vector3.Y, -90);
            this.camera.translate(util);
            this.camera.update();
        }
        else if (input.isKeyPressed(Keys.RIGHT)) { // move right
            this.util.set(this.camera.direction);
            this.util.x *= movementSpeed * dT;
            this.util.y = 0f;
            this.util.z *= movementSpeed * dT;
            this.util.rotate(Vector3.Y, -90);
            this.camera.translate(util);
            this.camera.update();
        }

        if (input.isKeyPressed(Keys.SPACE)) { // move up
            this.camera.translate(0, movementSpeed * dT, 0);
            this.camera.update();
        }

        if (input.isKeyPressed(Keys.SHIFT_LEFT)) { // move down
            this.camera.translate(0, -movementSpeed * dT, 0);
            this.camera.update();
        }
        else if (input.isKeyPressed(Keys.SHIFT_RIGHT)) { // move down
            this.camera.translate(0, -movementSpeed * dT, 0);
            this.camera.update();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
