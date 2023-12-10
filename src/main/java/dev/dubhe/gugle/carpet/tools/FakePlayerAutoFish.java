package dev.dubhe.gugle.carpet.tools;

import carpet.fakes.ServerPlayerInterface;
import carpet.helpers.EntityPlayerActionPack;
import carpet.helpers.EntityPlayerActionPack.ActionType;
import carpet.helpers.EntityPlayerActionPack.Action;
import com.mojang.datafixers.util.Pair;
import dev.dubhe.gugle.carpet.GcaExtension;
import dev.dubhe.gugle.carpet.GcaSetting;
import net.minecraft.world.entity.player.Player;

public class FakePlayerAutoFish {

    public static void autoFish(Player player) {
        EntityPlayerActionPack ap = ((ServerPlayerInterface) player).getActionPack();
        long l = player.level().getGameTime();
        // 收杆时间
        long caughtTime = l + 5;
        GcaExtension.planFunction.add(new Pair<>(caughtTime, () -> ap.start(ActionType.USE, Action.once())));
        // 抛竿时间
        long fishingTime = caughtTime + GcaSetting.fakePlayerAutoFishInterval;
        GcaExtension.planFunction.add(new Pair<>(fishingTime, () -> ap.start(ActionType.USE, Action.once())));
    }
}
