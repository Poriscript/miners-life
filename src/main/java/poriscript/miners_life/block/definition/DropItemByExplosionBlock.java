package poriscript.miners_life.block.definition;

import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class DropItemByExplosionBlock extends Block {
    private final ItemConvertible dropItem;

    public DropItemByExplosionBlock(Settings settings, ItemConvertible dropItem) {
        super(settings);
        this.dropItem = dropItem;
    }

    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        final double distance = MathHelper.magnitude(
                pos.getX() - explosion.getPosition().getX(),
                pos.getY() - explosion.getPosition().getY(),
                pos.getZ() - explosion.getPosition().getZ());

        dropStack(world, pos, new ItemStack(dropItem,
                MathHelper.floor(MathHelper.clamp(5 - distance, 0, 3))));
    }
}
