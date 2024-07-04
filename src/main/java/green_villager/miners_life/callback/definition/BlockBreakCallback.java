package green_villager.miners_life.callback.definition;

import green_villager.miners_life.MinersLife;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.List;
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
            Direction horizontal_facing_direction = player.getFacing();
            BlockPos explosion_position = pos.offset(horizontal_facing_direction, level);

            Explosion explosion = world.createExplosion(
                    null,
                    explosion_position.getX(),
                    explosion_position.getY(),
                    explosion_position.getZ(),
                    MathHelper.lerp(level / 5f, 2f, 6f),
                    World.ExplosionSourceType.TNT);
        });
    }
}
