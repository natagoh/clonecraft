package engine.world;

import org.joml.Matrix4f;

import engine.primitives.Shader;
import engine.view.Transformation;

// TODO: put all block meshes in a chunk in a single VAO
public class Chunk {

	static final int CHUNK_SIZE = 16;
	private Block[] blocks;

	public Chunk() {
		blocks = new Block[CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE];
		for (int x = 0; x < CHUNK_SIZE; x++) {
			for (int y = 0; y < CHUNK_SIZE; y++) {
				for (int z = 0; z < CHUNK_SIZE; z++) {
					Block block = new Block(BlockType.GRASS);
					block.setPosition(x, y, z);
					blocks[x + CHUNK_SIZE * (y + CHUNK_SIZE * z)] = block;
				}
			}
		}
	}
	
	public Block[] getBlocks() {
		return blocks;
	}
	
	// get the block at the specified chunk coordinates
	public Block getBlockAt(int x, int y, int z) {
		return blocks[x + CHUNK_SIZE * (y + CHUNK_SIZE * z)];
	}
	
	private boolean isVisibleAt(int x, int y, int z) {	
		// check edge of chunk
		boolean xInRange = x > 0 && x < CHUNK_SIZE - 1;
		boolean yInRange = y > 0 && y < CHUNK_SIZE - 1;
		boolean zInRange = z > 0 && z < CHUNK_SIZE - 1;
		if (!xInRange || !yInRange || !zInRange) {
			return true;
		}
		
		// check if neighboring blocks exist
		if (getBlockAt(x + 1, y, z).getBlockType() != BlockType.AIR && getBlockAt(x - 1, y, z).getBlockType() != BlockType.AIR &&
			getBlockAt(x, y + 1, z).getBlockType() != BlockType.AIR && getBlockAt(x, y - 1, z).getBlockType() != BlockType.AIR &&
			getBlockAt(x, y, z + 1).getBlockType() != BlockType.AIR && getBlockAt(x, y, z - 1).getBlockType() != BlockType.AIR) {
			return false;
		}
		
		return true;
	}
	
	
	public void render(Shader shader, Matrix4f viewMatrix, Transformation transformation) {
		for (int x = 0; x < CHUNK_SIZE; x++) {
			for (int y = 0; y < CHUNK_SIZE; y++) {
				for (int z = 0; z < CHUNK_SIZE; z++) {
					// only render if a block is visible to player
					if (isVisibleAt(x, y, z)) {
						Block block = getBlockAt(x, y, z);
					
				        // Render each gameItem
			            Matrix4f modelViewMatrix = transformation.getModelViewMatrix(block, viewMatrix);
			            shader.setUniform("modelViewMatrix", modelViewMatrix);
			            block.getMesh().render();
				        
					}
				}
			}
		}
	}
	
	public void update() {
	}
}