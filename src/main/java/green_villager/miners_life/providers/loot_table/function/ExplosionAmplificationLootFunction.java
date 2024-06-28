package green_villager.miners_life.providers.loot_table.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import green_villager.miners_life.MinersLife;
import green_villager.miners_life.providers.loot_table.LootTableProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.util.math.random.Random;

import java.util.List;

public class ExplosionAmplificationLootFunction extends ConditionalLootFunction {
    public static final MapCodec<ExplosionAmplificationLootFunction> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return addConditionsField(instance).apply(instance, ExplosionAmplificationLootFunction::new);
    });

    protected ExplosionAmplificationLootFunction(List<LootCondition> conditions) {
        super(conditions);
    }

    public LootFunctionType<ExplosionAmplificationLootFunction> getType() {
        return MinersLife.EXPLOSION_AMPLIFICATION_LOOT_FUNCTION_TYPE;
    }

    public ItemStack process(ItemStack stack, LootContext context) {
        Float f = context.get(LootContextParameters.EXPLOSION_RADIUS);
        if (f != null) {
            Random random = context.getRandom();
            int i = stack.getCount();
            int j = 0;

            for (int k = 0; k < i; ++k) {
                if (random.nextFloat() <= f) {
                    ++j;
                }
            }

            stack.setCount(j);
        }

        return stack;
    }

    public static ConditionalLootFunction.Builder<?> builder() {
        return builder(ExplosionAmplificationLootFunction::new);
    }
}
