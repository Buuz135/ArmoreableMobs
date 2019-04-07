package com.buuz135.armoreablemobs.entity;

import crafttweaker.annotations.ZenRegister;
import net.minecraft.command.CommandBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.HashMap;

@ZenRegister
@ZenClass(value = "mods.armoreablemobs.ArmorEntity")
public class ArmorEntity {

    private String id;
    private HashMap<NBTContainer, NBTCheckMode> nbtChecks;

    public ArmorEntity(String id) {
        this.id = id;
        nbtChecks = new HashMap<>();
    }

    @ZenMethod
    public ArmorEntity withNBTCheck(String key, int object, String mode) {
        nbtChecks.put(new NBTContainer(key, object), NBTCheckMode.valueOf(mode));
        return this;
    }

    @ZenMethod
    public ArmorEntity withNBTCheck(String key, double object, String mode) {
        nbtChecks.put(new NBTContainer(key, (float) object), NBTCheckMode.valueOf(mode));
        return this;
    }

    @ZenMethod
    public ArmorEntity withNBTCheck(String key, String object, String mode) {
        nbtChecks.put(new NBTContainer(key, object), NBTCheckMode.valueOf(mode));
        return this;
    }

    public boolean checkEntity(Entity entity) {
        if (EntityList.getKey(entity) == null || !EntityList.getKey(entity).toString().equals(id)) return false;
        for (NBTContainer container : nbtChecks.keySet()) {
            if (!nbtChecks.get(container).checkNbt(CommandBase.entityToNBT(entity), container)) return false;
        }
        return true;
    }

    private enum NBTCheckMode {
        LESS {
            @Override
            public boolean checkNbt(NBTTagCompound tag, NBTContainer container) {
                return tag.hasKey(container.key, 5) && container.value instanceof Float && tag.getFloat(container.key) < (float) container.value ||
                        tag.hasKey(container.key, 3) && container.value instanceof Integer && tag.getInteger(container.key) < (int) container.value;
            }
        }, EQUAL {
            @Override
            public boolean checkNbt(NBTTagCompound tag, NBTContainer container) {
                return tag.hasKey(container.key) && tag.getTag(container.key).equals(container.value); //Check
            }
        }, GREATER {
            @Override
            public boolean checkNbt(NBTTagCompound tag, NBTContainer container) {
                return tag.hasKey(container.key, 5) && container.value instanceof Float && tag.getFloat(container.key) > (float) container.value ||
                        tag.hasKey(container.key, 3) && container.value instanceof Integer && tag.getInteger(container.key) > (int) container.value;
            }
        }, CONTAINS {
            @Override
            public boolean checkNbt(NBTTagCompound tag, NBTContainer container) {
                return tag.hasKey(container.key, 8) && container.value instanceof String && tag.getString(container.key).contains((String) container.value);
            }
        }, TAG_EXISTS{
            @Override
            public boolean checkNbt(NBTTagCompound tag, NBTContainer container) {
                return tag.hasKey(container.key);
            }
        }, TAG_MISSING{
            @Override
            public boolean checkNbt(NBTTagCompound tag, NBTContainer container) {
                return !tag.hasKey(container.key);
            }
        };

        public abstract boolean checkNbt(NBTTagCompound tag, NBTContainer container);

    }

    private class NBTContainer {
        public String key;
        public Object value;

        public NBTContainer(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
