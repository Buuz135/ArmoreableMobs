package com.buuz135.armoreablemobs.handler;

import com.buuz135.armoreablemobs.entity.ArmorEntity;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenClass
public class ArmorGroup {

    private final String name;
    private final double chance;
    private final List<ArmorEntity> entities;

    public ArmorGroup(String name, double chance) {
        this.name = name;
        this.chance = chance;
        this.entities = new ArrayList<>();
    }

    @ZenMethod
    public void addEntity(ArmorEntity entity) {
        entities.add(entity);
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
}
