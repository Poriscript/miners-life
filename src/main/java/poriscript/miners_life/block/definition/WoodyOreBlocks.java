package poriscript.miners_life.block.definition;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import poriscript.miners_life.block.definition.redstone.*;
import poriscript.miners_life.data.enums.Identifiers;

public class WoodyOreBlocks {
    private static final BlockSetType METAL_BLOCK_SET_TYPE = new BlockSetTypeBuilder()
            .openableByHand(false)
            .openableByWindCharge(false)
            .buttonActivatedByArrows(false)
            .pressurePlateActivationRule(BlockSetType.ActivationRule.EVERYTHING)
            .soundGroup(BlockSoundGroup.METAL)
            .doorCloseSound(SoundEvents.BLOCK_IRON_DOOR_CLOSE)
            .doorOpenSound(SoundEvents.BLOCK_IRON_DOOR_OPEN)
            .trapdoorCloseSound(SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE)
            .trapdoorOpenSound(SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN)
            .pressurePlateClickOffSound(SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF)
            .pressurePlateClickOnSound(SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON)
            .buttonClickOffSound(SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF)
            .buttonClickOnSound(SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON)
            .register(Identifiers.METAL.getId());

    private static final BlockSetType GEM_BLOCK_SET_TYPE = new BlockSetTypeBuilder()
            .openableByHand(true)
            .openableByWindCharge(true)
            .buttonActivatedByArrows(false)
            .pressurePlateActivationRule(BlockSetType.ActivationRule.MOBS)
            .soundGroup(BlockSoundGroup.STONE)
            .doorCloseSound(SoundEvents.BLOCK_IRON_DOOR_CLOSE)
            .doorOpenSound(SoundEvents.BLOCK_IRON_DOOR_OPEN)
            .trapdoorCloseSound(SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE)
            .trapdoorOpenSound(SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN)
            .pressurePlateClickOffSound(SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF)
            .pressurePlateClickOnSound(SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON)
            .buttonClickOffSound(SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON)
            .buttonClickOnSound(SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF)
            .register(Identifiers.GEM.getId());

    public static final WoodType METAL_FENCE_GATE_WOOD_TYPE = new WoodTypeBuilder()
            .soundGroup(BlockSoundGroup.METAL)
            .fenceGateCloseSound(SoundEvents.BLOCK_NETHER_BRICKS_BREAK)
            .fenceGateOpenSound(SoundEvents.BLOCK_NETHER_BRICKS_PLACE)
            .hangingSignSoundGroup(BlockSoundGroup.NETHER_WOOD_HANGING_SIGN)
            .build(Identifiers.METAL.getId(), METAL_BLOCK_SET_TYPE);


    private final BlockFamily blockFamily;

    public BlockFamily getBlockFamily() {
        return blockFamily;
    }

    public WoodyOreBlocks(Block block, boolean isMetal) {
        final BlockSetType blockSetType = isMetal ? METAL_BLOCK_SET_TYPE : GEM_BLOCK_SET_TYPE;
        final int pressTick = isMetal ? 10 : 20;

        if (block instanceof RedstoneBlock) {
            blockFamily = new BlockFamily
                    .Builder(block)
                    .button(new RedstoneButton(blockSetType, pressTick, AbstractBlock.Settings.copy(Blocks.STONE).strength(0.6f)))
                    .slab(new RedstoneSlab(AbstractBlock.Settings.copy(Blocks.STONE_SLAB).strength(2.0f, 6.0f)))
                    .stairs(new RedstoneStairs(block.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE_STAIRS).strength(2.0f, 6.0f)))
                    .trapdoor(new RedstoneTrapdoor(blockSetType, AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR).strength(5f)))
                    .fence(new RedstoneFence(AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_FENCE).strength(2.0f, 6.0f)))
                    .fenceGate(new RedstoneFenceGate(METAL_FENCE_GATE_WOOD_TYPE, AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_FENCE).strength(2.0f, 6.0f)))
                    .build();
        } else {
            blockFamily = new BlockFamily
                    .Builder(block)
                    .button(new ButtonBlock(blockSetType, pressTick, AbstractBlock.Settings.copy(Blocks.STONE).strength(0.6f)))
                    .slab(new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_SLAB).strength(2.0f, 6.0f)))
                    .stairs(new StairsBlock(block.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE_STAIRS).strength(2.0f, 6.0f)))
                    .trapdoor(new TrapdoorBlock(blockSetType, AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR).strength(5f)))
                    .fence(new FenceBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_FENCE).strength(2.0f, 6.0f)))
                    .fenceGate(new FenceGateBlock(METAL_FENCE_GATE_WOOD_TYPE, AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_FENCE).strength(2.0f, 6.0f)))
                    .build();
        }
    }
}
