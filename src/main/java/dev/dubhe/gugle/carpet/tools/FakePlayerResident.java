package dev.dubhe.gugle.carpet.tools;
import carpet.patches.EntityPlayerMPFake;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.Map;

public class FakePlayerResident {

    public static JsonObject save(Player player) {
        double pos_x = player.x;
        double pos_y = player.y;
        double pos_z = player.z;
        double yaw = player.yRot;
        double pitch = player.xRot;
        String dimension = player.level.dimension.getType().toString();
        String gamemode = ((ServerPlayer) player).gameMode.getGameModeForPlayer().getName();
        JsonObject fakePlayer = new JsonObject();
        fakePlayer.addProperty("pos_x", pos_x);
        fakePlayer.addProperty("pos_y", pos_y);
        fakePlayer.addProperty("pos_z", pos_z);
        fakePlayer.addProperty("yaw", yaw);
        fakePlayer.addProperty("pitch", pitch);
        fakePlayer.addProperty("dimension", dimension);
        fakePlayer.addProperty("gamemode", gamemode);
        return fakePlayer;
    }

    public static void load(Map.Entry<String, JsonElement> entry, MinecraftServer server) {
        String username = entry.getKey();
        JsonObject fakePlayer = entry.getValue().getAsJsonObject();
        double pos_x = fakePlayer.get("pos_x").getAsDouble();
        double pos_y = fakePlayer.get("pos_y").getAsDouble();
        double pos_z = fakePlayer.get("pos_z").getAsDouble();
        double yaw = fakePlayer.get("yaw").getAsDouble();
        double pitch = fakePlayer.get("pitch").getAsDouble();
        String dimension = fakePlayer.get("dimension").getAsString();
        String gamemode = fakePlayer.get("gamemode").getAsString();
        EntityPlayerMPFake.createFake(username, server, pos_x, pos_y, pos_z, yaw, pitch,
                DimensionType.getByName(new ResourceLocation(dimension)),
                GameType.byName(gamemode));
    }
}
