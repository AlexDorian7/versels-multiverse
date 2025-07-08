package org.verselstudios.verselsmultiverse.registers;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.verselstudios.verselsmultiverse.menu.BlackJackTableMenu;

public class ModMenus {
    public static DeferredHolder<MenuType<?>, MenuType<BlackJackTableMenu>> BLACK_JACK_TABLE;

    public static void register(DeferredRegister<MenuType<?>> menuRegister) {
        BLACK_JACK_TABLE = menuRegister.register("black_jack_table", () -> new MenuType<>(BlackJackTableMenu::new, FeatureFlags.DEFAULT_FLAGS));
    }
}
