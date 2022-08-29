package dev.dubhe.gugle.carpet.tools;

import com.mojang.datafixers.util.Pair;
import dev.dubhe.gugle.carpet.GcaExtension;
import net.cjsah.mod.carpet.fakes.ServerPlayerEntityInterface;
import net.cjsah.mod.carpet.helpers.EntityPlayerActionPack;
import net.cjsah.mod.carpet.helpers.EntityPlayerActionPack.ActionType;
import net.cjsah.mod.carpet.helpers.EntityPlayerActionPack.Action;
import net.minecraft.world.entity.player.Player;

public class FakePlayerAutoFish {

    public static void autoFish(Player player) {
        EntityPlayerActionPack ap = ((ServerPlayerEntityInterface) player).getActionPack();
        long l = player.getLevel().getGameTime();
        GcaExtension.planFunction.add(new Pair<>(l + 5, () -> ap.start(ActionType.USE, Action.once())));
        GcaExtension.planFunction.add(new Pair<>(l + 15, () -> ap.start(ActionType.USE, Action.once())));
    }
}
