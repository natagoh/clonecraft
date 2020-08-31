package game;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.opengl.GL11.glViewport;

import engine.GameItem;
import engine.IGameLogic;
import engine.Window;
import engine.block.Mesh;

public class DummyGame implements IGameLogic {

    private int direction = 0;
    private float color = 0.0f;
    private final Renderer renderer;
    private GameItem[] gameItems;
    
    public DummyGame() {
        renderer = new Renderer();
    }
    
    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        float[] positions = new float[] {
			-0.5f, 0.5f, 0.0f,
			-0.5f, -0.5f, 0.0f,
			0.5f, -0.5f, 0.0f,
			0.5f, 0.5f, 0.0f,
		};
        float[] colors = new float[]{
        		0.5f, 0.0f, 0.0f,
        		0.0f, 0.5f, 0.0f,
        		0.0f, 0.0f, 0.5f,
        		0.0f, 0.5f, 0.5f,
        		};
		int[] indices = new int[] {
			0, 1, 3, 3, 1, 2,
		};
		Mesh mesh = new Mesh(positions, colors, indices);
        GameItem gameItem = new GameItem(mesh);
        gameItem.setPosition(0, 0, -2);
        gameItems = new GameItem[] { gameItem };
    }
    
    @Override
    public void input(Window window) {
        if ( window.isKeyPressed(GLFW_KEY_UP) ) {
            direction = 1;
        } else if ( window.isKeyPressed(GLFW_KEY_DOWN) ) {
            direction = -1;
        } else {
            direction = 0;
        }
    }

    @Override
    public void update(float interval) {
        color += direction * 0.01f;
        if (color > 1) {
            color = 1.0f;
        } else if ( color < 0 ) {
            color = 0.0f;
        }
    }

    @Override
    public void render(Window window) {
    	renderer.render(window, gameItems);
    	
//        if (window.isResized()) {
//            glViewport(0, 0, window.getWidth(), window.getHeight());
//            window.setResized(false);
//        }
//
//        window.setClearColor(color, color, color, 0.0f);
//        renderer.clear();
    }
    
    @Override
    public void cleanup() {
//        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }
}