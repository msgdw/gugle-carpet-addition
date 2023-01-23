package dev.dubhe.gugle.carpet.mixin;

import dev.dubhe.gugle.carpet.GcaExtension;
import dev.dubhe.gugle.carpet.GcaSetting;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {

    ServerPlayer self = (ServerPlayer) (Object) this;

    @Inject(method = "initInventoryMenu", at = @At("RETURN"))
    private void load(CallbackInfo ci) {
        if (GcaSetting.fakePlayerResident && self.getServer() != null && self.getServer().isSingleplayerOwner(self.getGameProfile())) {
            GcaExtension.onServerStart(self.getServer());
        }
    }
}
