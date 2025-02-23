package dev.dubhe.gugle.carpet.mixin;

import carpet.patches.EntityPlayerMPFake;
import dev.dubhe.gugle.carpet.GcaExtension;
import dev.dubhe.gugle.carpet.GcaSetting;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {

    Player self = (Player) (Object) this;

    @Inject(method = "tick", at = @At("RETURN"))
    private void tick(CallbackInfo ci) {
        if (GcaSetting.openFakePlayerInventory && self instanceof ServerPlayer serverPlayer &&
                serverPlayer instanceof EntityPlayerMPFake && serverPlayer.isAlive()) {
            GcaExtension.fakePlayerInventoryContainerMap.get(self).tick();
        }
    }

    @Redirect(method = "interactOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;interact(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;"))
    private InteractionResult interactOn(Entity entity, Player player, InteractionHand hand) {
        if (entity instanceof EntityPlayerMPFake fakePlayer) {
            SimpleMenuProvider provider = null;
            if (player.isShiftKeyDown() && GcaSetting.openFakePlayerEnderChest) {
                provider = new SimpleMenuProvider(
                        (i, inventory, p) -> new ChestMenu(MenuType.GENERIC_9x3, i, inventory, player.getEnderChestInventory(), 3) {
                            @Override
                            public boolean stillValid(Player player) {
                                return fakePlayer.isAlive();
                            }
                        },
                        fakePlayer.getDisplayName());
            } else if (GcaSetting.openFakePlayerInventory) {
                provider = new SimpleMenuProvider((i, inventory, p) -> ChestMenu.sixRows(i, inventory,
                        GcaExtension.fakePlayerInventoryContainerMap.get(fakePlayer)), fakePlayer.getDisplayName());
            }
            if (provider != null) {
                player.openMenu(provider);
            }
            return InteractionResult.SUCCESS;
        } else if (entity instanceof Player) {
            return InteractionResult.CONSUME;
        }
        return entity.interact(player, hand);
    }
}
