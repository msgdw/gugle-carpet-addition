package dev.dubhe.gugle.carpet.mixin;

import dev.dubhe.gugle.carpet.GcaExtension;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.SERVER)
@Mixin(DedicatedServer.class)
public class DedicatedServerMixin {

    @Inject(method = "initServer", at = @At("RETURN"))
    private void initServer(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()) {
            GcaExtension.onServerStart((MinecraftServer) (Object) this);
        }
    }
}
