package green_villager.miners_dream;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinersDream implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("miners_dream");

    public static final String MOD_ID = "miners_dream";


    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // ブロックを生成
        final Block CHARCOAL_BLOCK = new Block(AbstractBlock.Settings.copy(Blocks.COAL_BLOCK));

        // ブロックを登録
        Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "charcoal_block"), CHARCOAL_BLOCK);

        // ブロック用アイテムを登録
        final BlockItem CHARCOAL_BLOCK_ITEM = new BlockItem(CHARCOAL_BLOCK, new Item.Settings());
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "charcoal_block"), CHARCOAL_BLOCK_ITEM);

        // 独自のアイテムグループを作成
        //@formatter:off
		final ItemGroup MINERS_DREAM_ITEMGROUP = FabricItemGroup.builder()
                .icon(()-> new ItemStack(CHARCOAL_BLOCK_ITEM))
                .displayName(Text.translatable("item_group.miners_dream.building_blocks"))
                .entries((context, entries) -> {
                    entries.add(CHARCOAL_BLOCK_ITEM);
                })
                .build();

        // 独自のアイテムグループに追加。クリエイティブインベントリでカテゴリ分けされる
        Registry.register(Registries.ITEM_GROUP, Identifier.of(MOD_ID, "charcoal_block"), MINERS_DREAM_ITEMGROUP);
    }
}

