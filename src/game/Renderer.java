package game;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.*;

import engine.GameItem;
import engine.Window;

public class Renderer {

    public Renderer() {        
    }
    
    
    public void init(Window window) throws Exception {
//        // Create shader
//        shaderProgram = new ShaderProgram();
//        shaderProgram.createVertexShader(Utils.loadResource("/vertex.vs"));
//        shaderProgram.createFragmentShader(Utils.loadResource("/fragment.fs"));
//        shaderProgram.link();
//        
//        // Create uniforms for world and projection matrices
//        shaderProgram.createUniform("projectionMatrix");
//        shaderProgram.createUniform("worldMatrix");
        
        window.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }
    
    public void render(Window window, GameItem[] gameItems) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

//        shaderProgram.bind();
        
        // Update projection Matrix
//        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
//        shaderProgram.setUniform("projectionMatrix", projectionMatrix);
        
        // Render each gameItem
        for (GameItem gameItem : gameItems) {
            // Set world matrix for this item
//            Matrix4f worldMatrix = transformation.getWorldMatrix(
//                    gameItem.getPosition(),
//                    gameItem.getRotation(),
//                    gameItem.getScale());
//            shaderProgram.setUniform("worldMatrix", worldMatrix);
            // Render the mes for this game item
            gameItem.getMesh().render();
        }

//        shaderProgram.unbind();
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
