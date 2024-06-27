package green_villager.miners_life.block.definition;

import green_villager.miners_life.item.ItemRegistration;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class MeatiteOre extends Block {
    public MeatiteOre(Settings settings) {
        super(settings);
    }

    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        Entity exploded_entity = explosion.getEntity();
        LivingEntity exploding_entity = explosion.getCausingEntity();

        if (exploded_entity == null || exploding_entity == null) {
            return;
        }

        double distance_exploded_entity = MathHelper.magnitude(
                pos.getX() - exploded_entity.getX(),
                pos.getY() - exploded_entity.getY(),
                pos.getZ() - exploded_entity.getZ());

        dropStack(world, pos, new ItemStack(ItemRegistration.MEATITE,
                MathHelper.floor(MathHelper.clamp(5 - distance_exploded_entity, 0, 3))));
    }
}
