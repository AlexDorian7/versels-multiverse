package org.verselstudios.verselsmultiverse.registers;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.verselstudios.verselsmultiverse.blocks.BlackJackTable;

public class ModBlocks {

    public static DeferredBlock<BlackJackTable> BLACK_JACK_TABLE;


    public static void register(DeferredRegister.Blocks blockRegister) {
        BLACK_JACK_TABLE = blockRegister.registerBlock("black_jack_table", BlackJackTable::new, BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN).strength(2.0F).sound(SoundType.WOOD).ignitedByLava().pushReaction(PushReaction.BLOCK));
    }
}
