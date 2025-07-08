package org.verselstudios.verselsmultiverse.registers;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static DeferredItem<BlockItem> BLACK_JACK_TABLE;

    public static void register(DeferredRegister.Items itemRegister, DeferredRegister<CreativeModeTab> createTabRegister) {
        BLACK_JACK_TABLE = itemRegister.registerSimpleBlockItem("black_jack_table", ModBlocks.BLACK_JACK_TABLE);


        DeferredHolder<CreativeModeTab, CreativeModeTab> FUNCTIONAL_TAB = createTabRegister.register("functional_blocks", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.verselsmultiverse.functional_blocks"))
                .withTabsBefore(CreativeModeTabs.COMBAT)
                .icon(() -> BLACK_JACK_TABLE.get().getDefaultInstance())
                .displayItems((parameters, output) -> {
                    output.accept(BLACK_JACK_TABLE.get());
                }).build());
    }
}
