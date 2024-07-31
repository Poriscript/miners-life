package poriscript.miners_life.block.definition.redstone;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class RedstoneFenceGate extends FenceGateBlock {
    public RedstoneFenceGate(WoodType type, Settings settings) {
        super(type, settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        final ActionResult result = super.onUse(state, world, pos, player, hit);
        world.updateNeighborsAlways(pos, this);
        return result;
    }

    @Override
    protected int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return (Boolean) state.get(OPEN) ? state.get(FACING).getOpposite() == direction ? 9 : 1 : 0;
    }

    @Override
    protected int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return (Boolean) state.get(OPEN) ? state.get(FACING).getOpposite() == direction ? 9 : 1 : 0;
    }
}
