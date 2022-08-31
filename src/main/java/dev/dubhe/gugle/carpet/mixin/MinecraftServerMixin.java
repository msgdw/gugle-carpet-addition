package dev.dubhe.gugle.carpet.mixin;

import dev.dubhe.gugle.carpet.GcaExtension;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {


    @Environment(EnvType.SERVER)
    @Inject(method = "loadLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;prepareLevels(Lnet/minecraft/server/level/progress/ChunkProgressListener;)V"))
    private void loadLevel(CallbackInfo ci) {
        GcaExtension.onServerStart((MinecraftServer) (Object) this);
    }

    @Inject(method = "stopServer", at = @At(value = "HEAD"))
    private void stopServer(CallbackInfo ci) {
        GcaExtension.onServerClose((MinecraftServer) (Object) this);
    }
}
