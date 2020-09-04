package engine;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

import org.lwjgl.glfw.GLFW;

public class GameEngine implements Runnable {
    
    private final Window window;    
    private final IGameLogic gameLogic;
    private float deltaTime = 0.0f; 	// Time between current frame and last frame
	private float lastFrame = 0.0f;	// Time of last frame

    public GameEngine(String windowTitle, int width, int height, boolean vSync, IGameLogic gameLogic) throws Exception {
        window = new Window(windowTitle, width, height, vSync);
        this.gameLogic = gameLogic;
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception excp) {
            excp.printStackTrace();
        }
    }

    protected void init() throws Exception {
        window.init();
        
        // lock cursor
        window.disableCursor(true);
        gameLogic.init(window);
    }

    protected void gameLoop() {
        boolean running = true;
        while (running && !window.windowShouldClose()) {
        	float currentFrame = (float) glfwGetTime();
        	deltaTime = currentFrame - lastFrame;
        	lastFrame = currentFrame;          	
            update(deltaTime);
            render();
            if (Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            	return;
            }
        }
    }
    
    protected void update(float deltaTime) {
        gameLogic.update(deltaTime);
        window.update();
    }

    protected void render() {
        gameLogic.render(window);
        window.swapBuffer();
    }
}