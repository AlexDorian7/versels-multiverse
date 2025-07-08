package org.verselstudios.verselsmultiverse.registers;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.verselstudios.verselsmultiverse.blocks.entity.BlackJackTableBlockEntity;

public class ModBlockEntityTypes {

    public static DeferredHolder<BlockEntityType<?>, BlockEntityType<BlackJackTableBlockEntity>> BLACK_JACK_TABLE;

    public static void register(DeferredRegister<BlockEntityType<?>> blockEntityTypeRegister) {
        BLACK_JACK_TABLE = blockEntityTypeRegister.register("black_jack_table", () -> BlockEntityType.Builder.of(
                BlackJackTableBlockEntity::new,
                ModBlocks.BLACK_JACK_TABLE.get()
        ).build(null));
    }
}
