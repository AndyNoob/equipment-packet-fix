package comfortable_andy.equipmentpacketfix;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EquipmentPacketFixMod.MODID)
public class EquipmentPacketFixMod {

    public static final String MODID = "equipmentpacketfix";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public EquipmentPacketFixMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
