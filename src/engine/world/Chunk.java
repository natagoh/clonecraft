package engine.world;

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
	
	public void Render() {
	}
	
	public void Update() {
	}
}