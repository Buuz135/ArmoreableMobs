package net.witixin.armoreablemobs;

import com.blamejared.crafttweaker.natives.entity.ExpandEntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static <T> List<T> mergeOrMakeList(List<T> objList, T obj) {
        if (objList == null) {
            objList = new ArrayList<>();
        }
        objList.add(obj);
        return objList;
    }

    public static String toCraftTweakerBEP(final EntityType<Entity> entityType) {
        return ExpandEntityType.getCommandString(entityType);
    }
}
