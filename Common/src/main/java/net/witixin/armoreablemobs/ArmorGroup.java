package net.witixin.armoreablemobs;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

import java.util.*;

@ZenRegister
@ZenCodeType.Name("mods.armoreablemobs.ArmorGroup")
@Document("mods/armoreablemobs/ArmorGroup")
public class ArmorGroup {

    private String name;
    private final Map<EquipmentSlot, ItemStack> slotItemStackMap = new HashMap<>();
    private List<String> stageList;
    private double weight;
    private String packmode;

    public static List<EntityType> overrideArmorGroups = new ArrayList<>();

    @ZenCodeType.Constructor
    public ArmorGroup(String name){
        this.name = name;
        stageList = new ArrayList<>();
        packmode = "";
        weight = 1.0;
    }
    public ArmorGroup(Iterator<ItemStack> iterator, ItemStack mainhand, ItemStack offhand){
        slotItemStackMap.put(EquipmentSlot.HEAD, iterator.next());
        slotItemStackMap.put(EquipmentSlot.CHEST, iterator.next());
        slotItemStackMap.put(EquipmentSlot.LEGS, iterator.next());
        slotItemStackMap.put(EquipmentSlot.FEET, iterator.next());
        slotItemStackMap.put(EquipmentSlot.MAINHAND, mainhand);
        slotItemStackMap.put(EquipmentSlot.OFFHAND, offhand);

    }

    @ZenCodeType.Method
    public ArmorGroup setWeight(double weight){
        this.weight = weight;
        return this;
    }
    @ZenCodeType.Method
    public ArmorGroup inSlot(EquipmentSlot slot, ItemStack stack){
        slotItemStackMap.put(slot, stack);
        return this;
    }

    @ZenCodeType.Method
    public void register(EntityType type){
        CommonClass.armorList.put(type, Utilities.mergeOrMakeList(CommonClass.armorList.get(type), this));
        CraftTweakerAPI.LOGGER.info("Registered new ArmorGroup for entity: " + EntityType.getKey(type).toString() + " under the name: " + this.getName());
    }

    @ZenCodeType.Method
    public Map<EquipmentSlot, ItemStack> getMap(){
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
    /*
    @ZenCodeType.Method
    public void setPackmode(String packmode){
        this.packmode = packmode;
    }

    @ZenCodeType.Method
    public ArmorGroup addStages(String... stages){
        for (String s : stages){
            this.stageList.add(s);
        }
        return this;
    }
    */

    @ZenCodeType.Method
    public double getWeight(){return this.weight;}

    @ZenCodeType.Method
    public String getName(){
        return this.name;
    }

    @ZenCodeType.Method
    public ItemStack getStackinSlot(EquipmentSlot slot){
        return slotItemStackMap.get(slot);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @ZenCodeType.Method
    public static void overrideExistingArmor(EntityType type, Map<EquipmentSlot, IItemStack> map, @ZenCodeType.Optional BlockState state){
        if (state == null) {
            overrideArmorGroups.add(type);
        } else {
            addBlockOverrides(type, state, map);
        }
    }
    private static void addBlockOverrides(EntityType type, BlockState state, Map<EquipmentSlot, IItemStack> map){
        CommonClass.entityBlockStateMapOverrides.put(type, state);
        CommonClass.blockstateArmorOverries.put(state, map);
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
