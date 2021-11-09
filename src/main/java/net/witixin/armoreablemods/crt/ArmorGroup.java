package net.witixin.armoreablemods.crt;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.impl.entity.MCEntityType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.witixin.armoreablemods.Reference;
import net.witixin.armoreablemods.Utilities;
import org.openzen.zencode.java.ZenCodeType;

import java.util.*;

@ZenRegister
@ZenCodeType.Name("mods.armoreablemods.ArmorGroup")
public class ArmorGroup extends Object {

    private String name;
    private Map<EquipmentSlotType, ItemStack> slotItemStackMap = new HashMap<>();
    private List<String> stageList;
    private double weight;
    private String packmode;

    public static List<EntityType<?>> overrideArmorGroups = new ArrayList<>();

    @ZenCodeType.Constructor
    public ArmorGroup(String name){
        this.name = name;
        stageList = new ArrayList<>();
        weight = 1.0;
    }
    public ArmorGroup(Iterator<ItemStack> iterator, ItemStack mainhand, ItemStack offhand){
            slotItemStackMap.put(EquipmentSlotType.HEAD, iterator.next());
            slotItemStackMap.put(EquipmentSlotType.CHEST, iterator.next());
            slotItemStackMap.put(EquipmentSlotType.LEGS, iterator.next());
            slotItemStackMap.put(EquipmentSlotType.FEET, iterator.next());
            slotItemStackMap.put(EquipmentSlotType.MAINHAND, mainhand);
            slotItemStackMap.put(EquipmentSlotType.OFFHAND, offhand);
    }

    @ZenCodeType.Method
    public ArmorGroup setWeight(double weight){
        this.weight = weight;
        return this;
    }
    @ZenCodeType.Method
    public ArmorGroup addStages(String... stages){
        for (String s : stages){
            this.stageList.add(s);
        }
        return this;
    }
    @ZenCodeType.Method
    public ArmorGroup inSlot(EquipmentSlotType slot, ItemStack stack){
        slotItemStackMap.put(slot, stack);
        return this;
    }

    @ZenCodeType.Method
    public void register(MCEntityType type){
        Reference.armorList.put(type.getInternal(), Utilities.mergeOrMakeList(Reference.armorList.get(type.getInternal()), this));
        CraftTweakerAPI.logInfo("Registered new ArmorGroup for entity: " + type.getRegistryName().toString() + " under the name: " + this.getName());
    }

    @ZenCodeType.Method
    public Map<EquipmentSlotType, ItemStack> getMap(){
        return this.slotItemStackMap;
    }

    @ZenCodeType.Method
    public List<String> getStages(){
        return this.stageList;
    }

    @ZenCodeType.Method
    public String getPackmode(){
        return this.packmode;
    }

    @ZenCodeType.Method
    public void setPackmode(String packmode){
        this.packmode = packmode;
    }

    @ZenCodeType.Method
    public double getWeight(){return this.weight;}

    @ZenCodeType.Method
    public String getName(){
        return this.name;
    }

    @ZenCodeType.Method
    public ItemStack getStackinSlot(EquipmentSlotType slot){
        return slotItemStackMap.get(slot);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @ZenCodeType.Method
    public static void overrideExistingArmor(MCEntityType type, Map<EquipmentSlotType, IItemStack> map, @ZenCodeType.Optional BlockState state){
        if (state == null) {
            overrideArmorGroups.add(type.getInternal());
        } else {
            addBlockOverrides(type, state, map);
        }
    }
    private static void addBlockOverrides(MCEntityType type, BlockState state, Map<EquipmentSlotType, IItemStack> map){
        Reference.entityBlockStateMapOverrides.put(type.getInternal(), state);
        Reference.blockstateArmorOverries.put(state, map);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        };
        ArmorGroup ag = (ArmorGroup) obj;
        return this.slotItemStackMap.equals(ag.slotItemStackMap);
    }
    public boolean isEmpty(){
        for (ItemStack stack : this.slotItemStackMap.values()){
            if (stack != ItemStack.EMPTY){
                return false;
            }
        }
        return true;
    }
}
