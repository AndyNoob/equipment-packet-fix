# Equipment Packet Fix (1.20.1 Forge)
Mixes into `CompoundTag` to do two things:
1. Avoid a `ConcurrentModificationException` in `CompoundTag#write` by converting the tag's key set into an array before iterating
2. Avoid a `EncoderException` (caused by `NullFointerException`) in `CompoundTag#writeNamedTag` by adding a null safeguard
## Config
`detectExceptions` - (default `false`) Try and log when exceptions might happen, logging the `CompoundTag` in question
## Modpacks
Free to use in modpacks, but please do not redistribute.
## Credits
Logo textures from Minecraft (armor) and Minecraft Dungeons (hammer)