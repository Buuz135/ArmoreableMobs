package com.buuz135.armoreablemobs.handler;

import com.buuz135.armoreablemobs.entity.ArmorEntity;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenClass(value = "mods.armoreablemobs.ArmorHandler")
public class ArmorHandler {

    public static final List<ArmorGroup> ARMOR_GROUPS = new ArrayList<>();

    @ZenMethod
    public static ArmorGroup createArmorGroup(String name, double chance) {
        ArmorGroup group = new ArmorGroup(name, chance);
        ARMOR_GROUPS.add(group);
        return group;
    }

    @ZenMethod
    public static ArmorEntity createArmorEntity(String id) {
        return new ArmorEntity(id);
    }

    @ZenMethod
    public static ArmorSlot createArmorSlot(String slot, IItemStack itemStack, int weightToSpawn, double chanceToDrop) {
        return new ArmorSlot(slot, itemStack, weightToSpawn, chanceToDrop);
    }
}
