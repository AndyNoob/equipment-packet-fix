package comfortable_andy.equipmentpacketfix;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = EquipmentPacketFixMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue DETECT_EXCEPTIONS = BUILDER.comment("Should try to detect and log when exceptions occur?").define("detectExceptions", false);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean detectExceptions;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        detectExceptions = DETECT_EXCEPTIONS.get();
    }
}
