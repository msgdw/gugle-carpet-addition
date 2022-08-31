package dev.dubhe.gugle.carpet.api.tools.text;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;

public class ComponentTranslate {

    public static Component trans(String key, Object... args) {
        return trans(key, ChatFormatting.WHITE, args);
    }

    public static Component trans(String key, ChatFormatting color, Object... args){
        return trans(key, color, new Style(), args);
    }

    public static Component trans(String key, ChatFormatting color, Style style, Object... args) {
        return new TextComponent(String.format(key,args)).setStyle(style.setColor(color));
    }
}
