package green_villager.miners_life.callback.definition;

import green_villager.miners_life.MinersLife;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BlockBreakCallback {
    public static final RegistryKey<Enchantment> EXPLOSIVE_MINING_REGISTRY_KEY = RegistryKey.of(RegistryKeys.ENCHANTMENT, MinersLife.getMinersLifeId("explosive_mining"));

    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            final ItemStack tool = player.getStackInHand(player.getActiveHand());

            if (tool.isEmpty()) {
                return;
            }

            final boolean tool_is_pickaxes = tool.streamTags().anyMatch(itemTagKey -> {
                return itemTagKey == ItemTags.PICKAXES;
            });

            if (!tool_is_pickaxes) {
                return;
            }

            if (!tool.hasEnchantments()) {
                return;
            }

            final Set<RegistryEntry<Enchantment>> enchantments = tool.getEnchantments().getEnchantments();
            final List<RegistryEntry<Enchantment>> explosive_mining_enchantment_filtered_list = enchantments.stream().filter(enchantmentRegistryEntry -> {
                return enchantmentRegistryEntry.matchesKey(EXPLOSIVE_MINING_REGISTRY_KEY);
            }).toList();

            if (explosive_mining_enchantment_filtered_list.isEmpty()) {
                return;
            }

            final RegistryEntry<Enchantment> explosive_mining_enchantment = explosive_mining_enchantment_filtered_list.getFirst();

            final int level = tool.getEnchantments().getLevel(explosive_mining_enchantment);
            final Direction horizontal_facing_direction = player.getFacing();
            final BlockPos explosion_position = pos.offset(horizontal_facing_direction, level);

            ExplosionBehavior behavior = new ExplosionBehavior() {
                private List<Block> unbreakableBlocks = List.of(
                        Blocks.BEDROCK,
                        Blocks.NETHER_PORTAL,
                        Blocks.END_PORTAL_FRAME,
                        Blocks.END_PORTAL,
                        Blocks.LAVA,
                        Blocks.WATER,
                        Blocks.OBSIDIAN,
                        Blocks.CRYING_OBSIDIAN
                );

                @Override
                public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
                    return unbreakableBlocks.contains(state.getBlock()) ? false : super.canDestroyBlock(explosion, world, pos, state, power);
                }

                @Override
                public Optional<Float> getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState) {
                    return unbreakableBlocks.contains(blockState.getBlock()) ? Optional.empty() : super.getBlastResistance(explosion, world, pos, blockState, fluidState);
                }
            };

            final Explosion explosion = world.createExplosion(
                    null,
                    Explosion.createDamageSource(world, null),
                    behavior,
                    Vec3d.of(explosion_position),
                    MathHelper.lerp(level / 5f, 2f, 6f),
                    false,
                    World.ExplosionSourceType.TNT);
        });
    }
}
