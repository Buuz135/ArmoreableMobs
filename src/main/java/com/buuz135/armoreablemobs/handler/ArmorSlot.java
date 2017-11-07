package com.buuz135.armoreablemobs.handler;

import crafttweaker.api.item.IItemStack;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.WeightedRandom;
import stanhebben.zenscript.annotations.ZenClass;

@ZenClass(value = "mods.armoreablemobs.ArmorSlot")
public class ArmorSlot extends WeightedRandom.Item {

    private EntityEquipmentSlot slot;
    private IItemStack stack;
    private int weight;
    private double chanceToDrop;

    public ArmorSlot(EntityEquipmentSlot slot, IItemStack stack, int weight, double chanceToDrop) {
        super(weight);
        this.slot = slot;
        this.stack = stack;
        this.weight = weight;
        this.chanceToDrop = chanceToDrop;
    }

    public EntityEquipmentSlot getSlot() {
        return slot;
    }

    public IItemStack getStack() {
        return stack;
    }

    public int getWeight() {
        return weight;
    }

    public double getChanceToDrop() {
        return chanceToDrop;
    }
}
