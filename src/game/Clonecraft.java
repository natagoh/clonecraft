package game;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector2f;
import org.joml.Vector3f;

import engine.GameItem;
import engine.IGameLogic;
import engine.Input;
import engine.Window;
import engine.primitives.Mesh;
import engine.primitives.Texture;
import engine.view.Camera;
import engine.view.FirstPersonCamera;

public class Clonecraft implements IGameLogic {

	private final Renderer renderer;
    private GameItem[] gameItems;
    private FirstPersonCamera fpvCamera;
    
    public Clonecraft() {
        renderer = new Renderer();
        fpvCamera = new FirstPersonCamera();
        Input input = new Input();
    }
    
    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        
        // block mesh
        float[] positions = new float[] {
            -0.5f, 0.5f, 0.5f,		// V0
            -0.5f, -0.5f, 0.5f,		// V1
            0.5f, -0.5f, 0.5f,		// V1
            0.5f, 0.5f, 0.5f,		// V3
            -0.5f, 0.5f, -0.5f,		// V4
            0.5f, 0.5f, -0.5f,		// V5
            -0.5f, -0.5f, -0.5f,	// V6
            0.5f, -0.5f, -0.5f,		// V7
            // For text coords in top face
            -0.5f, 0.5f, -0.5f,		// V8: V4 repeated
            0.5f, 0.5f, -0.5f,		// V9: V5 repeated
            -0.5f, 0.5f, 0.5f,		// V10: V0 repeated
            0.5f, 0.5f, 0.5f,		// V11: V3 repeated
            // For text coords in right face
            0.5f, 0.5f, 0.5f,		// V12: V3 repeated
            0.5f, -0.5f, 0.5f,		// V13: V2 repeated
            // For text coords in left face
            -0.5f, 0.5f, 0.5f,		// V14: V0 repeated
            -0.5f, -0.5f, 0.5f,		// V15: V1 repeated
            // For text coords in bottom face
            -0.5f, -0.5f, -0.5f,	// V16: V6 repeated
            0.5f, -0.5f, -0.5f,		// V17: V7 repeated
            -0.5f, -0.5f, 0.5f,		// V18: V1 repeated
            0.5f, -0.5f, 0.5f,		// V19: V2 repeated
        };
        float[] texCoords = new float[] {
            0.0f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.5f, 0.0f,
            0.0f, 0.0f,
            0.5f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,
            // For tex coords in top face
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.0f, 1.0f,
            0.5f, 1.0f,
            // For tex coords in right face
            0.0f, 0.0f,
            0.0f, 0.5f,
            // For tex coords in left face
            0.5f, 0.0f,
            0.5f, 0.5f,
            // For tex coords in bottom face
            0.5f, 0.0f,
            1.0f, 0.0f,
            0.5f, 0.5f,
            1.0f, 0.5f,
        };
        int[] indices = new int[] {
            0, 1, 3, 3, 1, 2,		// Front face
            8, 10, 11, 9, 8, 11,	// Top Face
            12, 13, 7, 5, 12, 7,	// Right face
            14, 15, 6, 4, 14, 6,	// Left face
            16, 18, 19, 17, 16, 19,	// Bottom face
            4, 6, 7, 5, 4, 7,		// Back face
        };
		Texture texture = new Texture("resources/textures/grass.png");
		Mesh mesh = new Mesh(positions, texCoords, indices, texture);
				
//        GameItem gameItem = new GameItem(mesh);
//        gameItem.setPosition(0, 0, 0);
//        
//        GameItem gameItem2 = new GameItem(mesh);
//        gameItem.setPosition(0, 1, -2);
//        
//        GameItem gameItem3 = new GameItem(mesh);
//        gameItem.setPosition(0, -1, -2);
        
        GameItem gameItem1 = new GameItem(mesh);
        gameItem1.setPosition(0, 0, -3);

        GameItem gameItem2 = new GameItem(mesh);
        gameItem2.setPosition(1f, 0, -3);

        GameItem gameItem3 = new GameItem(mesh);
        gameItem3.setPosition(0, 0, -4f);

        GameItem gameItem4 = new GameItem(mesh);
        gameItem4.setPosition(1f, 0, -4f);
        
        GameItem gameItem5 = new GameItem(mesh);
        gameItem5.setPosition(1f, 1f, -4f);
        gameItems = new GameItem[]{gameItem1, gameItem2, gameItem3, gameItem4, gameItem5};
    }
    
    public void update(float deltaTime) {
    	fpvCamera.update(deltaTime);
    }
    
    @Override
    public void render(Window window) {
    	renderer.render(window, fpvCamera, gameItems);
    }
    
    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }
}