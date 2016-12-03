package engineTester;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		List<Entity> entities = new ArrayList<Entity>();

		RawModel model = OBJLoader.loadObjModel("models/dragon/model", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("models/dragon/texture"));
		texture.setShineDamper(10);
		texture.setReflectivity(1);

		TexturedModel texturedModel = new TexturedModel(model, texture);
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -25), 0, 0, 0, 1);
		entities.add(entity);

		Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));
		Camera camera = new Camera();
		MasterRenderer renderer = new MasterRenderer();

		while (!Display.isCloseRequested()) {
			camera.move();

			for (Entity e : entities) {
				renderer.processEntity(e);
			}

			renderer.render(light, camera);

			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}