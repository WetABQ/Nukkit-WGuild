package top.wetabq.wguild.utils;

import top.wetabq.wguild.WGuild;

import java.io.File;

/**
 * WGuild
 *
 * @author WetABQ Copyright (c) 2018.06
 * @version 1.0
 */
public class FileUtils {

    public static boolean isPlayerConfigExists(String playerName) {
        return new File(WGuild.getInstance().getDataFolder() + "/" + playerName +".yml").exists();
    }

}
