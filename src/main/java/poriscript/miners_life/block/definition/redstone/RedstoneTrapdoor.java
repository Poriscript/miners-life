package poriscript.miners_life.block.definition.redstone;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class RedstoneTrapdoor extends TrapdoorBlock {
    public RedstoneTrapdoor(BlockSetType type, Settings settings) {
        super(type, settings);
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        final ActionResult result = super.onUse(state, world, pos, player, hit);

        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(state.get(FACING)), this);

        return result;
    }

    @Override
    protected int getWeakRedstonePower(@NotNull BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return (Boolean) state.get(OPEN) ? 11 : 5;
    }

    @Override
    protected int getStrongRedstonePower(@NotNull BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return (Boolean) state.get(OPEN) ? 11 : 5;
    }
}
