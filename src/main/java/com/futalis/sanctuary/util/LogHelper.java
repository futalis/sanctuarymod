package com.futalis.sanctuary.util;

import com.futalis.sanctuary.Sanctuary;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLLog;

//import java.util.logging.Level;
import org.apache.logging.log4j.Level;


public class LogHelper {
    public static final Level LOGGING_LEVEL= Level.ERROR;//None, Fatal, Error, Warn, Info, Debug, Trace, All

    public static void log(Level level, Object object){
        if (level.intLevel() <= LOGGING_LEVEL.intLevel()) {
            FMLLog.log(Sanctuary.MODID, level, String.valueOf(object));
        }
    }

    public static void chat(Level level, TextFormatting color, Object object){
        if (level.intLevel() <= LOGGING_LEVEL.intLevel()) {
            TextComponentString message = new TextComponentString(String.valueOf(object));
            message.setStyle(message.getStyle().setColor(color));
            //MinecraftServer.getServer().getConfigurationManager().sendChatMsg(message);
        }
    }

    public static void info (Object object){log(Level.INFO,object);}
    public static void debug (Object object){log(Level.DEBUG,object);}
    public static void warn (Object object){log(Level.WARN,object);}
    public static void error (Object object){log(Level.ERROR,object);}
    public static void fatal (Object object){log(Level.FATAL,object);}

    public static void chatInfo(Object object){ chat(Level.INFO, TextFormatting.DARK_GRAY, "[Info]" + String.valueOf(object));}
    public static void chatDebug(Object object){ chat(Level.DEBUG, TextFormatting.AQUA, "[Debug]"+String.valueOf(object));}
    public static void chatWarn(Object object){ chat(Level.DEBUG, TextFormatting.YELLOW, "[Warning]"+String.valueOf(object));}
    public static void chatError(Object object){ chat(Level.DEBUG, TextFormatting.RED, "[Error]"+String.valueOf(object));}
    //No fatal, because a fatal error would not leave a chat visible. Just Log those, instead.
}
