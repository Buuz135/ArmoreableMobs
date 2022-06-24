package net.witixin.armoreablemobs;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

import java.util.*;

@ZenRegister
@ZenCodeType.Name("mods.armoreablemobs.ArmorGroup")
@Document("mods/ArmoreableMobs/ArmorGroup")
public class ArmorGroup {

    private String name;
    private final Map<EquipmentSlot, ItemStack> slotItemStackMap = new HashMap<>();
    private List<String> stageList;
    private double weight;
    private String packmode;

    public static Map<EntityType, Map<EquipmentSlot, IItemStack>> overrideArmorGroups = new HashMap<>();

    /**
     *
     * @param name The display name of the group to create.
     *
     */

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
        this.name = "DummyGroup";
    }
    public ArmorGroup(Iterator<ItemStack> iterator){
        slotItemStackMap.put(EquipmentSlot.HEAD, iterator.next());
        slotItemStackMap.put(EquipmentSlot.CHEST, iterator.next());
        slotItemStackMap.put(EquipmentSlot.LEGS, iterator.next());
        slotItemStackMap.put(EquipmentSlot.FEET, iterator.next());
        slotItemStackMap.put(EquipmentSlot.MAINHAND, iterator.next());
        slotItemStackMap.put(EquipmentSlot.OFFHAND, iterator.next());
    }

    /**
     * Sets the weight at which the armor group can spawn. The chance a group has to spawn on an entity is determined using a pseudo random number and the total weight of ArmorGroups that entity can have.
     *
     * @param weight The weight at which the ArmorGroup will spawn on the entity type.
     * @return The ArmorGroup that has been modified.
     *
     * @docParam weight 3.0
     */
    @ZenCodeType.Method
    public ArmorGroup setWeight(double weight){
        this.weight = weight;
        return this;
    }

    /**
     * Links a slot to the ItemStack the entity will get when spawning. Accepts all {@link EquipmentSlot} types.
     *
     * @param slot The slot at which the ItemStack will be placed
     * @param stack The {@link IItemStack} to give to the Entity.
     *
     * @docParam slot <constant:minecraft:equipmentslot:feet>
     * @docParam stack <item:minecraft:iron_boots>
     *
     * @return The ArmorGroup that has been modified.
     */
    @ZenCodeType.Method
    public ArmorGroup inSlot(EquipmentSlot slot, IItemStack stack){
        slotItemStackMap.put(slot, stack.getInternal());
        return this;
    }

    /**
     * Registers the ArmorGroup.
     *
     * @param type The entity at which the ArmorGroup will be applied on spawn.
     *
     * @docParam type <entitytype:minecraft:zombie>
     */

    @ZenCodeType.Method
    public void register(EntityType type){
        CommonClass.armorList.put(type, Utilities.mergeOrMakeList(CommonClass.armorList.get(type), this));
        CraftTweakerAPI.LOGGER.info("Registered new ArmorGroup for entity: " + EntityType.getKey(type).toString() + " under the name: " + this.getName());
    }

    /**
     * Returns the AssociativeArray that corresponds to the internal `EquipmentSlot[IItemStack]`
     * @return The internal map as `EquipmentSlot[IItemStack]`
     */
    @ZenCodeType.Method
    public Map<EquipmentSlot, ItemStack> getMap(){
        return this.slotItemStackMap;
    }

    /**
     * Returns the names of the stages required to unlock this group. Will be empty on Fabric.
     * @return The list of stages
     */

    @ZenCodeType.Method
    public List<String> getStages(){
        return this.stageList;
    }

    //@ZenCodeType.Method
    public String getPackmode(){
        return this.packmode;
    }



    /*
    packmode isn't on 1.18 as of now
    @ZenCodeType.Method
    public ArmorGroup setPackmode(String packmode){
        this.packmode = packmode;
        return this;

    }
    */

    /**
     * Will only work on Forge.
     * Gates the ArmorGroup from being given unless there's a player nearby with ALL of the stages in the Group.
     * @param stages The list of stages necessary for the {@link ArmorGroup} to be given.
     * @return The {@link ArmorGroup} itself.
     */

    @ZenCodeType.Method
    public ArmorGroup addStages(String... stages){
        if (Services.PLATFORM.getPlatformName().equals("Forge")){
            this.stageList.addAll(Arrays.asList(stages));
        }
        else {
            CraftTweakerAPI.LOGGER.warn("Forge only method (ArmorGroup#addStages) was run on a Fabric Platform! This will not do anything!");
        }
        return this;
    }



    /**
     * Gets the weight of the ArmorGroup
     * @return The weight of the group as a double.
     */
    @ZenCodeType.Method
    public double getWeight(){return this.weight;}

    /**
     * Gets the name of the ArmorGroup
     * @return The name of the group as a string.
     */
    @ZenCodeType.Method
    public String getName(){
        return this.name;
    }
    /**
     * Gets the ItemStack the group will give in a selected slot. Can be null. Would be the same as using {@link ArmorGroup#getMap()} and passing the {@link EquipmentSlot} as a key.
     * @return The ItemStack at the selected location. Can be null.
     *
     * @docParam slot <constant:minecraft:equipmentslot:head>
     */
    @ZenCodeType.Method
    public ItemStack getStackinSlot(EquipmentSlot slot){
        return slotItemStackMap.get(slot);
    }

    @ZenCodeType.Caster(implicit = true)
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * A powerful method to override the armor of a mob depending on which block they are standing on.
     *
     * @param type The {@link EntityType} to Override
     * @param map The Associative Array, as `EquipmentSlot[IItemStack]` that will be used as the entities armor. If an {@link EquipmentSlot} is empty, it won't override what's there.
     * @param state The BlockState to override the armor if the aforementioned {@link EntityType} spawns on top of.
     *
     * @docParam type <entitytype:minecraft:zombie>
     * @docParam map {<constant:minecraft:equipmentslot:chest> : <item:minecraft:netherite_chestplate>, <constant:minecraft:equipmentslot:mainhand> : <item:minecraft:netherite_sword>}
     * @docParam state <blockstate:minecraft:sand>
     */
    @ZenCodeType.Method
    public static void overrideExistingArmor(EntityType type, Map<EquipmentSlot, IItemStack> map, @ZenCodeType.Optional BlockState state){
        if (state == null) {
            overrideArmorGroups.put(type, map);
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
