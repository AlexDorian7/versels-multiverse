package org.verselstudios.verselsmultiverse.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.verselstudios.verselsmultiverse.blocks.entity.BlackJackTableBlockEntity;
import org.verselstudios.verselsmultiverse.registers.ModBlocks;
import org.verselstudios.verselsmultiverse.registers.ModMenus;

import java.util.Objects;

public class BlackJackTableMenu extends AbstractContainerMenu {

    private BlackJackTableBlockEntity blockEntity;
    private final IItemHandler dataInventory;
    private ContainerLevelAccess access;

    public BlackJackTableMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, null, ContainerLevelAccess.NULL, new ItemStackHandler(BlackJackTableBlockEntity.INVENTORY_SIZE), DataSlot.standalone());
    }

    public BlackJackTableMenu(int containerId, Inventory playerInventory, @Nullable BlackJackTableBlockEntity blockEntity, @Nullable ContainerLevelAccess access, IItemHandler dataInventory, DataSlot gameState) {
        super(ModMenus.BLACK_JACK_TABLE.get(), containerId);
        this.blockEntity = blockEntity;
        this.dataInventory = dataInventory;
        this.access = Objects.requireNonNullElseGet(access, () -> {
            if (blockEntity == null) return ContainerLevelAccess.NULL;
            return ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        });

        // Block Inventory
        this.addSlot(new SlotItemHandler(dataInventory, 0, 0, 0));

        // Player Inventory
        this.addSlot(new Slot(playerInventory, 0, 0, 64));

    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int quickMovedSlotIndex) { // Hopefully this will never be called
        // Not allowed to take items from this inventory
        return ItemStack.EMPTY;
        /*
        // The quick moved slot stack
        ItemStack quickMovedStack = ItemStack.EMPTY;
        // The quick moved slot
        Slot quickMovedSlot = this.slots.get(quickMovedSlotIndex);

        // If the slot is in the valid range and the slot is not empty
        if (quickMovedSlot != null && quickMovedSlot.hasItem()) {
            // Get the raw stack to move
            ItemStack rawStack = quickMovedSlot.getItem();
            // Set the slot stack to a copy of the raw stack
            quickMovedStack = rawStack.copy();

        / *
        The following quick move logic can be simplified to if in data inventory,
        try to move to player inventory/hotbar and vice versa for containers
        that cannot transform data (e.g. chests).
        * /

            // If the quick move was performed on the data inventory result slot
            if (quickMovedSlotIndex == 0) {
                // Try to move the result slot into the player inventory/hotbar
                if (!this.moveItemStackTo(rawStack, 5, 41, true)) {
                    // If cannot move, no longer quick move
                    return ItemStack.EMPTY;
                }

                // Perform logic on result slot quick move
                quickMovedSlot.onQuickCraft(rawStack, quickMovedStack);
            }
            // Else if the quick move was performed on the player inventory or hotbar slot
            else if (quickMovedSlotIndex >= 5 && quickMovedSlotIndex < 41) {
                // Try to move the inventory/hotbar slot into the data inventory input slots
                if (!this.moveItemStackTo(rawStack, 1, 5, false)) {
                    // If cannot move and in player inventory slot, try to move to hotbar
                    if (quickMovedSlotIndex < 32) {
                        if (!this.moveItemStackTo(rawStack, 32, 41, false)) {
                            // If cannot move, no longer quick move
                            return ItemStack.EMPTY;
                        }
                    }
                    // Else try to move hotbar into player inventory slot
                    else if (!this.moveItemStackTo(rawStack, 5, 32, false)) {
                        // If cannot move, no longer quick move
                        return ItemStack.EMPTY;
                    }
                }
            }
            // Else if the quick move was performed on the data inventory input slots, try to move to player inventory/hotbar
            else if (!this.moveItemStackTo(rawStack, 5, 41, false)) {
                // If cannot move, no longer quick move
                return ItemStack.EMPTY;
            }

            if (rawStack.isEmpty()) {
                // If the raw stack has completely moved out of the slot, set the slot to the empty stack
                quickMovedSlot.set(ItemStack.EMPTY);
            } else {
                // Otherwise, notify the slot that that the stack count has changed
                quickMovedSlot.setChanged();
            }

        / *
        The following if statement and Slot#onTake call can be removed if the
        menu does not represent a container that can transform stacks (e.g.
        chests).
        * /
            if (rawStack.getCount() == quickMovedStack.getCount()) {
                // If the raw stack was not able to be moved to another slot, no longer quick move
                return ItemStack.EMPTY;
            }
            // Execute logic on what to do post move with the remaining stack
            quickMovedSlot.onTake(player, rawStack);
        }

        return quickMovedStack; // Return the slot stack
        */
    }

    @Override
    public boolean stillValid(Player player) {
        return AbstractContainerMenu.stillValid(access, player, ModBlocks.BLACK_JACK_TABLE.get());
    }


}
