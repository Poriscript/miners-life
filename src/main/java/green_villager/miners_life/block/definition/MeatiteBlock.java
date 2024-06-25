package green_villager.miners_life.block.definition;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class MeatiteBlock extends Block {
    public MeatiteBlock(Settings settings) {
        super(settings);
    }

    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {

    }

}
