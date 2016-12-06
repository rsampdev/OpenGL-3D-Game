package entities;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderEngine.DisplayManager;
import terrains.Terrain;

public class Player extends Entity {

	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER = 30;
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;

	private boolean isInAir = false;

	public Player(TexturedModel model, Vector3f position, float rotationX, float rotationY, float rotationZ, float scale) {
		super(model, position, rotationX, rotationY, rotationZ, scale);
	}

	public void move(List<Terrain> terrains) {
		// implement multiple terrain collision detection
		Terrain terrain = terrains.get(0);
		checkInputs();
		super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotationY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotationY())));
		super.increasePosition(dx, 0.0f, dz);
		upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);

		float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
		if (super.getPosition().y < terrainHeight) {
			upwardsSpeed = 0;
			this.isInAir = false;
			super.getPosition().y = terrainHeight;
		}
	}

	private void jump() {
		if (!isInAir) {
			this.upwardsSpeed = JUMP_POWER;
			this.isInAir = true;
		}
	}

	private void resetPosition() {
		this.getPosition().x = 0;
		this.getPosition().y = Terrain.MAX_HEIGHT;
		this.getPosition().z = 0;
	}

	private void checkInputs() {
		if (Keyboard.isKeyDown(Keyboard.KEY_TAB)) {
			resetPosition();
		} else {
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				this.currentSpeed = RUN_SPEED;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				this.currentSpeed = -RUN_SPEED;
			} else {
				this.currentSpeed = 0;
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				this.currentTurnSpeed = -TURN_SPEED;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
				this.currentTurnSpeed = TURN_SPEED;
			} else {
				this.currentTurnSpeed = 0;
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) || Keyboard.isKeyDown(Keyboard.KEY_F)) {
				jump();
			}
		}
	}

}
