# simple block

@commune:ModBLocks 
```java
public static final RegistryObject<Block> <name> = registerblock("<uuid>", 
        ()-> new Block(props.stone_prop(1));
```

@resource:models/block/<uuid>.json
*redirect:blockbench&&mojank
*#resource: https://riptutorial.com/minecraft/example/30047/the-block-model-json

@resource:blockstates/<uuid>.json
```json
{
    "variants":{
        "": { "model": "commune:block/<uuid>"}
    }
}
```

@resource:models/item/<uuid>.json
```json
{
    "parent":"commune:block/<uuid>"
}
```

@resource:lang/en_us.json
*addelement: source, next
```json
    "block.commune.<uuid>": "<name>"
```

@data:llot_tables/blocks/<uuid>.json
```json
{
    "type":"minecraft:block",
    "pools":[
        {
            "rolls":<rolls>, //the number of rolls, use one as default
            "entries": [
                {
                    "type":"minecraft:block",
                    "name":"commune:<uuid>"
                }
            ]
        }
    ]
}
```

# simple item

# tab

# recipie