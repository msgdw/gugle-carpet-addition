package dev.dubhe.gugle.carpet.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractContainerMenu.class)
public class AbstractContainerMenuMixin {

    @Shadow @Final public List<Slot> slots;

    @Inject(method = "doClick", at = @At("HEAD"), cancellable = true)
    private void doClick(int i, int j, ClickType clickType, Player player, CallbackInfoReturnable<ItemStack> cir){
        if (i < 0) return;
        Slot slot = this.slots.get(i);
        ItemStack itemStack = slot.getItem();
        if (itemStack.getTag() != null) {
            if (itemStack.getTag().get("GcaClear") != null) {
                if (itemStack.getTag().getBoolean("GcaClear")) {
                    itemStack.setCount(0);
                    cir.setReturnValue(ItemStack.EMPTY);
                }
            }
        }
    }
}
