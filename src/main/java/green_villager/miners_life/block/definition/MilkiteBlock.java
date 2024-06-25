package green_villager.miners_life.block.definition;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class MilkiteBlock extends Block {
    public static final BooleanProperty IS_MILK_FULL = BooleanProperty.of("is_milk_full");

    public MilkiteBlock(Settings settings) {
        super(settings.ticksRandomly());
        setDefaultState(this.stateManager.getDefaultState().with(IS_MILK_FULL, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(IS_MILK_FULL);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.01f && !state.get(IS_MILK_FULL)) {
            world.setBlockState(pos, state.with(IS_MILK_FULL, true));
        }
    }

    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!stack.isOf(Items.BUCKET) || !state.get(IS_MILK_FULL)) {
            return ItemActionResult.FAIL;
        }

        player.playSound(SoundEvents.ENTITY_GOAT_MILK, 1.0F, 1.0F);
        ItemStack new_stack = ItemUsage.exchangeStack(stack, player, Items.MILK_BUCKET.getDefaultStack());
        player.setStackInHand(hand, new_stack);
        world.setBlockState(pos, state.with(IS_MILK_FULL, false));
        world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);

        return ItemActionResult.success(world.isClient);
    }
}
