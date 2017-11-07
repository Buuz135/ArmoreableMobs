package com.buuz135.armoreablemobs.handler;

import com.buuz135.armoreablemobs.entity.ArmorEntity;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenClass(value = "mods.armoreablemobs.ArmorGroup")
public class ArmorGroup {

    private String name;
    private double chance;
    private List<ArmorEntity> entities;
    private List<ArmorSlot> slots;

    public ArmorGroup(String name, double chance) {
        this.name = name;
        this.chance = chance;
        this.entities = new ArrayList<>();
        this.slots = new ArrayList<>();
    }

    @ZenMethod
    public void addEntity(ArmorEntity entity) {
        entities.add(entity);
    }

    @ZenMethod
    public void addArmor(ArmorSlot slot) {
        slots.add(slot);
    }

    public String getName() {
        return name;
    }

    public double getChance() {
        return chance;
    }

    public List<ArmorEntity> getEntities() {
        return entities;
    }

    public List<ArmorSlot> getSlots() {
        return slots;
    }
}
