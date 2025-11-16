package comfortable_andy.equipmentpacketfix.mixin;

import comfortable_andy.equipmentpacketfix.Config;
import comfortable_andy.equipmentpacketfix.EquipmentPacketFixMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.DataOutput;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Mixin(CompoundTag.class)
public abstract class CompoundTagWriteMixin {

    @Redirect(method = "write", at = @At(value = "INVOKE", target = "Ljava/util/Set;iterator()Ljava/util/Iterator;"))
    private Iterator<String> threadSafeWrite(Set<String> instance) {
        if (Config.detectExceptions) {
            try {
                return instance.iterator();
            } catch (ConcurrentModificationException e) {
                EquipmentPacketFixMod.LOGGER.warn("ConcurrentModificationException caught while writing compound tag:\n{}", this);
            }
        }
        Set<String> set = new HashSet<>();
        for (Object o : instance.toArray()) {
            if (o == null) set.add(null);
            else set.add((String) o);
        }
        return set.iterator();
    }

    @Redirect(method = "write", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/CompoundTag;writeNamedTag(Ljava/lang/String;Lnet/minecraft/nbt/Tag;Ljava/io/DataOutput;)V"))
    private void skipWriteIfNull(String p_128369_, Tag p_128370_, DataOutput p_128371_) {
        if (p_128370_ == null) {
            if (Config.detectExceptions) {
                EquipmentPacketFixMod.LOGGER.warn("Attempted to write a null named tag while writing compound tag:\n{}", this);
            }
            return;
        }
        writeNamedTag(p_128369_, p_128370_, p_128371_);
    }

    @Shadow
    private static void writeNamedTag(String p_128369_, Tag p_128370_, DataOutput p_128371_) {
        throw new IllegalStateException("shadow writeNamedTag failed");
    }
}
