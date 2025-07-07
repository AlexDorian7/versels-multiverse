package org.verselstudios.verselsmultiverse.registers;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HiddenItemRegister {

    private static Block getAir() { return Blocks.AIR; }
    private static Block getCaveAir() { return Blocks.CAVE_AIR; }
    private static Block getVoidAir() { return Blocks.VOID_AIR; }
    private static Block getWater() { return Blocks.WATER; }
    private static Block getLava() { return Blocks.LAVA; }
    private static Block getFire() { return Blocks.FIRE; }
    private static Block getSoulFire() { return Blocks.SOUL_FIRE; }
    private static Block getNetherPortal() { return Blocks.NETHER_PORTAL; }
    private static Block getEndPortal() { return Blocks.END_PORTAL; }
    private static Block getEndGateway() { return Blocks.END_GATEWAY; }
    private static Block getPistonHead() { return Blocks.PISTON_HEAD; }
    private static Block getMovingPiston() { return Blocks.MOVING_PISTON; }




    public static void register(DeferredRegister.Items itemRegister, DeferredRegister<CreativeModeTab> createTabRegister) {
        DeferredItem<BlockItem> air = itemRegister.registerSimpleBlockItem("air", HiddenItemRegister::getAir);
        DeferredItem<BlockItem> caveAir = itemRegister.registerSimpleBlockItem("cave_air", HiddenItemRegister::getCaveAir);
        DeferredItem<BlockItem> voidAir = itemRegister.registerSimpleBlockItem("void_air", HiddenItemRegister::getVoidAir);
        DeferredItem<BlockItem> water = itemRegister.registerSimpleBlockItem("water", HiddenItemRegister::getWater);
        DeferredItem<BlockItem> lava = itemRegister.registerSimpleBlockItem("lava", HiddenItemRegister::getLava);
        DeferredItem<BlockItem> fire = itemRegister.registerSimpleBlockItem("fire", HiddenItemRegister::getFire);
        DeferredItem<BlockItem> soulFire = itemRegister.registerSimpleBlockItem("soul_fire", HiddenItemRegister::getSoulFire);
        DeferredItem<BlockItem> netherPortal = itemRegister.registerSimpleBlockItem("nether_portal", HiddenItemRegister::getNetherPortal);
        DeferredItem<BlockItem> endPortal = itemRegister.registerSimpleBlockItem("end_portal", HiddenItemRegister::getEndPortal);
        DeferredItem<BlockItem> endGateway = itemRegister.registerSimpleBlockItem("end_gateway", HiddenItemRegister::getEndGateway);
        DeferredItem<BlockItem> pistonHead = itemRegister.registerSimpleBlockItem("piston_head", HiddenItemRegister::getPistonHead);
        DeferredItem<BlockItem> movingPiston = itemRegister.registerSimpleBlockItem("moving_piston", HiddenItemRegister::getMovingPiston);



        DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = createTabRegister.register("hidden_items_tab", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.verselsmultiverse.hidden_items"))
                .withTabsBefore(CreativeModeTabs.COMBAT)
                .icon(() -> fire.get().getDefaultInstance())
                .displayItems((parameters, output) -> {
                    output.accept(air.get());
                    output.accept(caveAir.get());
                    output.accept(voidAir.get());
                    output.accept(water.get());
                    output.accept(lava.get());
                    output.accept(fire.get());
                    output.accept(soulFire.get());
                    output.accept(netherPortal.get());
                    output.accept(endPortal.get());
                    output.accept(endGateway.get());
                    output.accept(pistonHead.get());
                    output.accept(movingPiston.get());
                }).build());
    }
}
