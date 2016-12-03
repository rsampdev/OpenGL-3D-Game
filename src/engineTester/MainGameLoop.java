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
import terrains.Terrain;
import textures.ModelTexture;
import textures.Textures;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		List<Entity> entities = new ArrayList<Entity>();
		List<Terrain> terrains = new ArrayList<Terrain>();

		RawModel model = OBJLoader.loadObjModel("models/dragon/model", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("models/dragon/texture"));
		texture.setShineDamper(10);
		texture.setReflectivity(10);

		TexturedModel texturedModel = new TexturedModel(model, texture);
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -25), 0, 0, 0, 1);
		entities.add(entity);
		
		Terrain terrain1 = new Terrain(-1, -1, loader, Textures.MINECRAFT_DIAMOND_ORE);
		Terrain terrain2 = new Terrain(0, -1, loader, Textures.MINECRAFT_IRON_BLOCK);
		Terrain terrain3 = new Terrain(-1, 0, loader, Textures.MINECRAFT_GOLD_BLOCK);
		Terrain terrain4 = new Terrain(0, 0, loader, Textures.MINECRAFT_GLASS_BLOCK);
		terrain4.getTexture().setHasTransparency(true);
		terrains.add(terrain1);
		terrains.add(terrain2);
		terrains.add(terrain3);
		terrains.add(terrain4);

		Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));
		Camera camera = new Camera();
		
		MasterRenderer renderer = new MasterRenderer();

		while (!Display.isCloseRequested()) {
			camera.move();

			for (Entity e : entities) {
				renderer.processEntity(e);
			}
			
			for (Terrain t : terrains) {
				renderer.processTerrain(t);
			}

			renderer.render(light, camera);

			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}