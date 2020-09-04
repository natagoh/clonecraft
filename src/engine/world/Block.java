package engine.world;

import engine.GameItem;
import engine.primitives.Mesh;
import engine.primitives.Texture;

public class Block {
	private BlockType blockType;
	private Mesh mesh;
	private GameItem gameItem;
	
	public Block(BlockType blockType) {
		this.blockType = blockType;
		mesh = createBlockMesh(blockType);
		gameItem = new GameItem(mesh);
	}
	
	private Mesh createBlockMesh(BlockType blockType) {
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
		return mesh;
	}
	
	public GameItem getGameItem() {
		return gameItem;
	}
	
}
