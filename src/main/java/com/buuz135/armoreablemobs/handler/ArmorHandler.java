package com.buuz135.armoreablemobs.handler;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenClass(value = "com.armoreablemobs.ArmorHandler")
public class ArmorHandler {

    public static final List<ArmorGroup> ARMOR_GROUPS = new ArrayList<>();

    @ZenMethod
    public static ArmorGroup createArmorGroup(String name, double chance) {
        ArmorGroup group = new ArmorGroup(name, chance);
        ARMOR_GROUPS.add(group);
        return group;
    }
}
