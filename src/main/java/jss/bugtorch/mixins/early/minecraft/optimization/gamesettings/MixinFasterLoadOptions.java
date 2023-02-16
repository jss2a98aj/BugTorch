package jss.bugtorch.mixins.early.minecraft.optimization.gamesettings;

import com.google.gson.Gson;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(value = GameSettings.class, priority = 990)
public abstract class MixinFasterLoadOptions {

    /**
     * @author jss2a98aj
     * @reason Makes this function not unreasonably slow.
     */
    @Overwrite
    public void loadOptions() {
        try {
            if(!optionsFile.exists()) {
                return;
            }

            BufferedReader bufferedreader = new BufferedReader(new FileReader(optionsFile));
            String optionsEntry;
            mapSoundLevels.clear();

            while((optionsEntry = bufferedreader.readLine()) != null) {
                try {
                    String[] splitEntry = optionsEntry.split(":");

                    if(splitEntry[0].startsWith("key_")) {
                        for(KeyBinding keybinding : keyBindings) {
                            if (splitEntry[0].equals("key_" + keybinding.getKeyDescription())) {
                                keybinding.setKeyCode(Integer.parseInt(splitEntry[1]));
                                break;
                            }
                        }
                    } else if(splitEntry[0].startsWith("soundCategory_")) {
                        SoundCategory[] soundCategories = SoundCategory.values();
                        for(SoundCategory soundcategory : soundCategories) {
                            if (splitEntry[0].equals("soundCategory_" + soundcategory.getCategoryName())) {
                                mapSoundLevels.put(soundcategory, parseFloat(splitEntry[1]));
                                break;
                            }
                        }
                    } else {
                        switch (splitEntry[0]) {
                            case "mouseSensitivity":
                                mouseSensitivity = parseFloat(splitEntry[1]);
                                break;
                            case "invertYMouse":
                                invertMouse = splitEntry[1].equals("true");
                                break;
                            case "fov":
                                fovSetting = parseFloat(splitEntry[1]) * 40.0F + 70.0F;
                                break;
                            case "gamma":
                                gammaSetting = parseFloat(splitEntry[1]);
                                break;
                            case "saturation":
                                saturation = parseFloat(splitEntry[1]);
                                break;
                            case "renderDistance":
                                renderDistanceChunks = Integer.parseInt(splitEntry[1]);
                                break;
                            case "guiScale":
                                guiScale = Integer.parseInt(splitEntry[1]);
                                break;
                            case "particles":
                                particleSetting = Integer.parseInt(splitEntry[1]);
                                break;
                            case "bobView":
                                viewBobbing = splitEntry[1].equals("true");
                                break;
                            case "anaglyph3d":
                                anaglyph = splitEntry[1].equals("true");
                                break;
                            case "advancedOpengl":
                                advancedOpengl = splitEntry[1].equals("true");
                                break;
                            case "maxFps":
                                limitFramerate = Integer.parseInt(splitEntry[1]);
                                break;
                            case "fboEnable":
                                fboEnable = splitEntry[1].equals("true");
                                break;
                            case "difficulty":
                                difficulty = EnumDifficulty.getDifficultyEnum(Integer.parseInt(splitEntry[1]));
                                break;
                            case "fancyGraphics":
                                fancyGraphics = splitEntry[1].equals("true");
                                break;
                            case "ao":
                                if (splitEntry[1].equals("true")) {
                                    ambientOcclusion = 2;
                                } else if (splitEntry[1].equals("false")) {
                                    ambientOcclusion = 0;
                                } else {
                                    this.ambientOcclusion = Integer.parseInt(splitEntry[1]);
                                }
                                break;
                            case "clouds":
                                clouds = splitEntry[1].equals("true");
                                break;
                            case "resourcePacks":
                                resourcePacks = gson.fromJson(optionsEntry.substring(optionsEntry.indexOf(58) + 1), typeListString);
                                if (resourcePacks == null) {
                                    resourcePacks = new ArrayList();
                                }
                                break;
                            case "lastServer":
                                lastServer = optionsEntry.substring(optionsEntry.indexOf(58) + 1);
                                break;
                            case "lang":
                                language = splitEntry[1];
                                break;
                            case "chatVisibility":
                                chatVisibility = EntityPlayer.EnumChatVisibility.getEnumChatVisibility(Integer.parseInt(splitEntry[1]));
                                break;
                            case "chatColors":
                                chatColours = splitEntry[1].equals("true");
                                break;
                            case "chatLinks":
                                chatLinks = splitEntry[1].equals("true");
                                break;
                            case "chatLinksPrompt":
                                chatLinksPrompt = splitEntry[1].equals("true");
                                break;
                            case "chatOpacity":
                                chatOpacity = parseFloat(splitEntry[1]);
                                break;
                            case "snooperEnabled":
                                snooperEnabled = splitEntry[1].equals("true");
                                break;
                            case "fullscreen":
                                fullScreen = splitEntry[1].equals("true");
                                break;
                            case "enableVsync":
                                enableVsync = splitEntry[1].equals("true");
                                break;
                            case "hideServerAddress":
                                hideServerAddress = splitEntry[1].equals("true");
                                break;
                            case "advancedItemTooltips":
                                advancedItemTooltips = splitEntry[1].equals("true");
                                break;
                            case "pauseOnLostFocus":
                                pauseOnLostFocus = splitEntry[1].equals("true");
                                break;
                            case "showCape":
                                showCape = splitEntry[1].equals("true");
                                break;
                            case "touchscreen":
                                touchscreen = splitEntry[1].equals("true");
                                break;
                            case "overrideHeight":
                                overrideHeight = Integer.parseInt(splitEntry[1]);
                                break;
                            case "overrideWidth":
                                overrideWidth = Integer.parseInt(splitEntry[1]);
                                break;
                            case "heldItemTooltips":
                                heldItemTooltips = splitEntry[1].equals("true");
                                break;
                            case "chatHeightFocused":
                                chatHeightFocused = parseFloat(splitEntry[1]);
                                break;
                            case "chatHeightUnfocused":
                                chatHeightUnfocused = parseFloat(splitEntry[1]);
                                break;
                            case "chatScale":
                                chatScale = parseFloat(splitEntry[1]);
                                break;
                            case "chatWidth":
                                chatWidth = parseFloat(splitEntry[1]);
                                break;
                            case "showInventoryAchievementHint":
                                showInventoryAchievementHint = splitEntry[1].equals("true");
                                break;
                            case "mipmapLevels":
                                mipmapLevels = Integer.parseInt(splitEntry[1]);
                                break;
                            case "anisotropicFiltering":
                                anisotropicFiltering = Integer.parseInt(splitEntry[1]);
                                break;
                            case "streamBytesPerPixel":
                                field_152400_J = parseFloat(splitEntry[1]);
                                break;
                            case "streamMicVolume":
                                field_152401_K = parseFloat(splitEntry[1]);
                                break;
                            case "streamSystemVolume":
                                field_152402_L = parseFloat(splitEntry[1]);
                                break;
                            case "streamKbps":
                                field_152403_M = parseFloat(splitEntry[1]);
                                break;
                            case "streamFps":
                                field_152404_N = parseFloat(splitEntry[1]);
                                break;
                            case "streamCompression":
                                field_152405_O = Integer.parseInt(splitEntry[1]);
                                break;
                            case "streamSendMetadata":
                                field_152406_P = splitEntry[1].equals("true");
                                break;
                            case "streamPreferredServer":
                                field_152407_Q = optionsEntry.substring(optionsEntry.indexOf(58) + 1);
                                break;
                            case "streamChatEnabled":
                                field_152408_R = Integer.parseInt(splitEntry[1]);
                                break;
                            case "streamChatUserFilter":
                                field_152409_S = Integer.parseInt(splitEntry[1]);
                                break;
                            case "streamMicToggleBehavior":
                                field_152410_T = Integer.parseInt(splitEntry[1]);
                                break;
                            case "forceUnicodeFont":
                                forceUnicodeFont = splitEntry[1].equals("true");
                                break;
                        }
                    }
                } catch (Exception exception) {
                    logger.warn("Skipping bad option: " + optionsEntry);
                }
            }

            KeyBinding.resetKeyBindingArrayAndHash();
            bufferedreader.close();
        } catch (Exception optionLoadException) {
            logger.error("Failed to load options", optionLoadException);
        }
    }

    @Shadow
    private float parseFloat(String value) {
        return 0f;
    }

    @Shadow @Final
    private static Logger logger;
    @Shadow @Final
    private static Gson gson;
    @Shadow @Final
    private static ParameterizedType typeListString;

    @Shadow protected Minecraft mc;
    @Shadow private Map mapSoundLevels;
    @Shadow private File optionsFile;

    @Shadow public KeyBinding[] keyBindings;
    @Shadow public float mouseSensitivity;
    @Shadow public boolean invertMouse;
    @Shadow public float fovSetting;
    @Shadow public float gammaSetting;
    @Shadow public float saturation;
    @Shadow public int renderDistanceChunks;
    @Shadow public int guiScale;
    @Shadow public int particleSetting;
    @Shadow public boolean viewBobbing;
    @Shadow public boolean anaglyph;
    @Shadow public boolean advancedOpengl;
    @Shadow public int limitFramerate;
    @Shadow public boolean fboEnable;
    @Shadow public EnumDifficulty difficulty;
    @Shadow public boolean fancyGraphics;
    @Shadow public int ambientOcclusion;
    @Shadow public boolean clouds;
    @Shadow public List resourcePacks;
    @Shadow public String lastServer;
    @Shadow public String language;
    @Shadow public EntityPlayer.EnumChatVisibility chatVisibility;
    @Shadow public boolean chatColours;
    @Shadow public boolean chatLinks;
    @Shadow public boolean chatLinksPrompt;
    @Shadow public float chatOpacity;
    @Shadow public boolean snooperEnabled;
    @Shadow public boolean fullScreen;
    @Shadow public boolean enableVsync;
    @Shadow public boolean hideServerAddress;
    @Shadow public boolean advancedItemTooltips;
    @Shadow public boolean pauseOnLostFocus;
    @Shadow public boolean showCape;
    @Shadow public boolean touchscreen;
    @Shadow public int overrideHeight;
    @Shadow public int overrideWidth;
    @Shadow public boolean heldItemTooltips;
    @Shadow public float chatHeightFocused;
    @Shadow public float chatHeightUnfocused;
    @Shadow public float chatScale;
    @Shadow public float chatWidth;
    @Shadow public boolean showInventoryAchievementHint;
    @Shadow public int mipmapLevels;
    @Shadow public int anisotropicFiltering;
    @Shadow public float field_152400_J;
    @Shadow public float field_152401_K;
    @Shadow public float field_152402_L;
    @Shadow public float field_152403_M;
    @Shadow public float field_152404_N;
    @Shadow public int field_152405_O;
    @Shadow public boolean field_152406_P;
    @Shadow public String field_152407_Q;
    @Shadow public int field_152408_R;
    @Shadow public int field_152409_S;
    @Shadow public int field_152410_T;
    @Shadow public boolean forceUnicodeFont;

}
