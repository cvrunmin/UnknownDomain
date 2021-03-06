package unknowndomain.engine.world.chunk;

import unknowndomain.engine.player.Player;
import unknowndomain.engine.world.World;

import java.util.Collection;

public interface ChunkManager<T extends World> {
    boolean shouldChunkUnload(Chunk chunk, Player player);

    Chunk loadChunk(int x, int y, int z);

    void unloadChunk(int x, int y, int z);

    T getWorld();

    Collection<Chunk> getChunks();
}
