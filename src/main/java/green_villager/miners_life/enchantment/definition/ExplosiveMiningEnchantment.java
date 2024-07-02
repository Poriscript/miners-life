package green_villager.miners_life.enchantment.definition;

import com.mojang.serialization.MapCodec;
import green_villager.miners_life.MinersLife;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.function.Consumer;

public record ExplosiveMiningEnchantment() implements EnchantmentEntityEffect {
    private static final ExplosiveMiningEnchantment INSTANCE = new ExplosiveMiningEnchantment();
    public static final MapCodec<ExplosiveMiningEnchantment> CODEC = MapCodec.unit(INSTANCE);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        Consumer<Item> result = context.onBreak().andThen(item -> {

        });
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}

