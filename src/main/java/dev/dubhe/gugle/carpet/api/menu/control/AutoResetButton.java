package dev.dubhe.gugle.carpet.api.menu.control;

import dev.dubhe.gugle.carpet.api.tools.text.ComponentTranslate;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;

public class AutoResetButton extends Button {

    public AutoResetButton(String key) {
        super(false,
                ComponentTranslate.trans(key, ChatFormatting.WHITE, new Style().setBold(true).setItalic(false)),
                ComponentTranslate.trans(key, ChatFormatting.WHITE, new Style().setBold(true).setItalic(false))
        );
        this.addTurnOnFunction(this::turnOffWithoutFunction);
    }
}
