# ArmorGroup

## Importing the class

It might be required for you to import the package if you encounter any issues (like casting an Array), so better be safe than sorry and add the import at the very top of the file.
```zenscript
import mods.armoreablemobs.ArmorGroup;
```


## Static Methods

:::group{name=overrideExistingArmor}

Return Type: void

```zenscript
ArmorGroup.overrideExistingArmor(type as EntityType, map as IItemStack[EquipmentSlot], state as BlockState) as void
```

| Parameter | Type | Description | Optional | DefaultValue |
|-----------|------|-------------|----------|--------------|
| type | [EntityType](/vanilla/api/entity/EntityType) | No Description Provided | false |  |
| map | [IItemStack](/vanilla/api/item/IItemStack)[[EquipmentSlot](/vanilla/api/entity/equipment/EquipmentSlot)] | No Description Provided | false |  |
| state | [BlockState](/vanilla/api/block/BlockState) | No Description Provided | true |  |


:::

## Constructors

No Description Provided
```zenscript
new ArmorGroup(name as string) as ArmorGroup
```
| Parameter | Type | Description |
|-----------|------|-------------|
| name | string | No Description Provided |



## Methods

:::group{name=getMap}

Return Type: [ItemStack](/vanilla/api/item/ItemStack)[[EquipmentSlot](/vanilla/api/entity/equipment/EquipmentSlot)]

```zenscript
// ArmorGroup.getMap() as ItemStack[EquipmentSlot]

myArmorGroup.getMap();
```

:::

:::group{name=getName}

Return Type: string

```zenscript
// ArmorGroup.getName() as string

myArmorGroup.getName();
```

:::

:::group{name=getPackmode}

Return Type: string

```zenscript
// ArmorGroup.getPackmode() as string

myArmorGroup.getPackmode();
```

:::

:::group{name=getStackinSlot}

Return Type: [ItemStack](/vanilla/api/item/ItemStack)

```zenscript
ArmorGroup.getStackinSlot(slot as EquipmentSlot) as ItemStack
```

| Parameter | Type | Description |
|-----------|------|-------------|
| slot | [EquipmentSlot](/vanilla/api/entity/equipment/EquipmentSlot) | No Description Provided |


:::

:::group{name=getStages}

Return Type: stdlib.List&lt;string&gt;

```zenscript
// ArmorGroup.getStages() as stdlib.List<string>

myArmorGroup.getStages();
```

:::

:::group{name=getWeight}

Return Type: double

```zenscript
// ArmorGroup.getWeight() as double

myArmorGroup.getWeight();
```

:::

:::group{name=inSlot}

Return Type: [ArmorGroup](/mods/armoreablemobs/ArmorGroup)

```zenscript
ArmorGroup.inSlot(slot as EquipmentSlot, stack as ItemStack) as ArmorGroup
```

| Parameter | Type | Description |
|-----------|------|-------------|
| slot | [EquipmentSlot](/vanilla/api/entity/equipment/EquipmentSlot) | No Description Provided |
| stack | [ItemStack](/vanilla/api/item/ItemStack) | No Description Provided |


:::

:::group{name=register}

Return Type: void

```zenscript
ArmorGroup.register(type as EntityType) as void
```

| Parameter | Type | Description |
|-----------|------|-------------|
| type | [EntityType](/vanilla/api/entity/EntityType) | No Description Provided |


:::

:::group{name=setWeight}

Return Type: [ArmorGroup](/mods/armoreablemobs/ArmorGroup)

```zenscript
ArmorGroup.setWeight(weight as double) as ArmorGroup
```

| Parameter | Type | Description |
|-----------|------|-------------|
| weight | double | No Description Provided |


:::


