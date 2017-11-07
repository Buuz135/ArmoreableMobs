package com.buuz135.armoreablemobs.handler;

import com.buuz135.armoreablemobs.util.ZenWeightedRandom;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;

@ZenClass(value = "mods.armoreablemobs.ArmorSlot")
public class ArmorSlot extends ZenWeightedRandom.Item {

    private String slot;
    private IItemStack stack;
    private int weight;
    private double chanceToDrop;

    public ArmorSlot(String slot, IItemStack stack, int weight, double chanceToDrop) {
        super(weight);
        this.slot = slot;
        this.stack = stack;
        this.weight = weight;
        this.chanceToDrop = chanceToDrop;
    }

    public String getSlot() {
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
