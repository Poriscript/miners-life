package green_villager.miners_life.enchantment;

import com.mojang.serialization.MapCodec;
import green_villager.miners_life.MinersLife;
import green_villager.miners_life.enchantment.definition.ExplosiveMiningEnchantment;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.predicate.TagPredicate;
import net.minecraft.predicate.item.ItemSubPredicateTypes;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class EnchantmentRegistration {
    public EnchantmentRegistration() {}

    public static final MapCodec<? extends EnchantmentEntityEffect> EXPLOSIVE_MINING = Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, MinersLife.getMinersLifeId("explosive_mining"), new ExplosiveMiningEnchantment().getCodec());
    public static final RegistryKey<Enchantment> EXPLOSIVE_MINING_KEY = RegistryKey.of(RegistryKeys.ENCHANTMENT, MinersLife.getMinersLifeId("explosive_mining"));

    public static void defineEnchantment() {

        var i = Registries.ITEM;
        var p = ItemTags.PICKAXES;
        var r = BuiltinRegistries.createWrapperLookup();

        var n = 1;

//        final var supported_items =
//        })

//        final Enchantment.Definition definition = new Enchantment.Definition(

//        )

//        Enchantment.builder()
    }
}
