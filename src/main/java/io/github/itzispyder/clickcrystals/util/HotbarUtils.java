package io.github.itzispyder.clickcrystals.util;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static io.github.itzispyder.clickcrystals.ClickCrystals.mc;

/**
 * Client hot bar utils
 */
public abstract class HotbarUtils {

    /**
     * Search of the item in a player's hot bar.
     * Scrolls to the item.
     * @param item item to search
     */
    public static boolean search(Item item) {
        final PlayerInventory inv = mc.player.getInventory();
        for (int i = 0; i <= 8; i ++) {
            if (inv.getStack(i).isOf(item)) {
                inv.selectedSlot = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks of the hotbar has an item
     * @param item item
     * @return has item
     */
    public static boolean has(Item item) {
        final PlayerInventory inv = mc.player.getInventory();
        for (int i = 0; i <= 8; i ++) {
            if (inv.getStack(i).isOf(item)) return true;
        }
        return false;
    }

    /**
     * If the player's held item matches the provided item type
     * @param item item type
     * @return match
     */
    public static boolean isHolding(Item item) {
        return isHolding(item,Hand.MAIN_HAND);
    }

    /**
     * If the players held item in the specified hand matches the provided type
     * @param item item type
     * @param hand hand to check
     * @return match
     */
    public static boolean isHolding(Item item, Hand hand) {
        return mc.player.getStackInHand(hand).isOf(item);
    }

    /**
     * If the player's held item's name contains the following string
     * @param contains string to check
     * @return match
     */
    public static boolean nameContains(String contains) {
        return nameContains(contains,Hand.MAIN_HAND);
    }

    /**
     * If the player's held item in thee specified hand's name contains the following string
     * @param contains string to check
     * @param hand hand to check
     * @return match
     */
    public static boolean nameContains(String contains, Hand hand) {
        ItemStack item = mc.player.getStackInHand(hand);
        return item != null && item.getTranslationKey().toLowerCase().contains(contains.toLowerCase());
    }

    public static void forEachItem(Consumer<ItemStack> run) {
        for (int i = 0; i < 9; i ++) {
            ItemStack item = mc.player.getInventory().getStack(i);
            if (item == null) continue;
            if (item.isEmpty()) continue;
            run.accept(item);
        }
    }

    public static void forEachItem(BiConsumer<Integer,ItemStack> run) {
        for (int i = 0; i < 9; i ++) {
            ItemStack item = mc.player.getInventory().getStack(i);
            if (item == null) continue;
            if (item.isEmpty()) continue;
            run.accept(i,item);
        }
    }

    public static ItemStack[] getContents() {
        PlayerInventory inv = mc.player.getInventory();
        ItemStack[] stacks = new ItemStack[]{};
        for (int i = 0; i < 9; i++) {
            stacks[i] = inv.getStack(i);
        }
        return stacks;
    }
}
