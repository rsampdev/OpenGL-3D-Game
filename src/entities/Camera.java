package entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private static final float ZOOM_IN_LIMIT = 10;
	private static final float ZOOM_OUT_LIMIT = 30;
	private static final float PITCH_LIMIT = 179;
	private static final float DEFAULT_DISTANCE_FROM_PLAYER = ZOOM_IN_LIMIT;
	private static final float DEFAULT_ANGLE_AROUND_PLAYER = 0;
	
	private static final Vector3f DEFAULT_POSITION = new Vector3f(0, 0, 0);
	private static final float DEFAULT_PITCH = 20;
	private static final float DEFAULT_YAW = 0;
	private static final float DEFAULT_ROLL = 0;

	private float distanceFromPlayer = DEFAULT_DISTANCE_FROM_PLAYER;
	private float angleAroundPlayer = DEFAULT_ANGLE_AROUND_PLAYER;

	private Vector3f position = DEFAULT_POSITION;
	private float pitch = DEFAULT_PITCH;
	private float yaw = DEFAULT_YAW;
	private float roll = DEFAULT_ROLL;

	private Player player;

	public Camera(Player player) {
		this.player = player;
	}

	public void move() {
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (player.getRotationY() + angleAroundPlayer);
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

	private void calculateCameraPosition(float horizontalDistance, float verticalDistance) {
		float theta = player.getRotationY() + angleAroundPlayer;
		float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(theta)));
		position.x = player.getPosition().x - offsetX;
		position.z = player.getPosition().z - offsetZ;
		position.y = player.getPosition().y + verticalDistance;
	}

	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}

	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}

	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * 0.01f;

		if (distanceFromPlayer - zoomLevel < ZOOM_IN_LIMIT) {
			distanceFromPlayer = ZOOM_IN_LIMIT;
		} else if (distanceFromPlayer - zoomLevel > ZOOM_OUT_LIMIT) {
			distanceFromPlayer = ZOOM_OUT_LIMIT;
		} else {
			distanceFromPlayer -= zoomLevel;
		}
	}

	private void calculatePitch() {
		if (Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * 0.5f;
			if (pitch - pitchChange > PITCH_LIMIT) {
				pitch = PITCH_LIMIT;
			} else if (pitch - pitchChange < 1) {
				pitch = 1;
			} else {
				pitch -= pitchChange;
			}
		}
	}

	private void calculateAngleAroundPlayer() {
		if (Mouse.isButtonDown(0)) {
			float angleChange = Mouse.getDX() * 0.6f;
			angleAroundPlayer -= angleChange;
		}
	}

}
