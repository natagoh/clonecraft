package game;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;

import engine.GameItem;
import engine.Window;
import engine.primitives.Shader;
import engine.primitives.Utils;
import engine.view.Camera;
import engine.view.Transformation;
import engine.world.Chunk;

public class Renderer {
	
	private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.f;

    private Shader shader;
	private Transformation transformation;

	public Renderer() {   
		transformation = new Transformation();
    }
    
    public void init(Window window) throws Exception {
        // Create shader
        shader = new Shader();
        // somehow code already knows parent dir is resources...
        shader.createVertexShader(Utils.loadResource("/shaders/vertex.vs"));
        shader.createFragmentShader(Utils.loadResource("/shaders/fragment.fs"));
        shader.link();
        
        // Create uniforms for world and projection matrices
        shader.createUniform("projectionMatrix");
        shader.createUniform("modelViewMatrix");
        shader.createUniform("texture_sampler");

        window.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }
    
    public void render(Window window, Camera camera, Chunk[] chunks) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        shader.bind();
        
        // Update projection Matrix
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shader.setUniform("projectionMatrix", projectionMatrix);

        // Update view Matrix
        Matrix4f viewMatrix = transformation.getViewMatrix(camera);
        shader.setUniform("texture_sampler", 0);
        
        // Render each gameItem
        for(Chunk chunk: chunks) {
//            Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameItem, viewMatrix);
//            shader.setUniform("modelViewMatrix", modelViewMatrix);
            chunk.render(shader, viewMatrix, transformation);
        }
        
        shader.unbind();
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    
    public void cleanup() {
        if (shader != null) {
            shader.cleanup();
        }
    }
}
