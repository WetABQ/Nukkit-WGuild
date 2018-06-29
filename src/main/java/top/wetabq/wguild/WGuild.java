package top.wetabq.wguild;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import top.wetabq.wguild.command.FBanCommand;
import top.wetabq.wguild.command.FriendCommand;
import top.wetabq.wguild.config.MasterConfig;
import top.wetabq.wguild.listener.PlayerEventListener;

/**
 * WGuild
 *
 * @author WetABQ Copyright (c) 2018.06
 * @version 1.0
 */
public class WGuild extends PluginBase {

    private static WGuild plugin;
    private MasterConfig masterConfig;

    @Override
    public void onLoad() {
        plugin = this;
        this.getLogger().notice("WGuild - Loading...");
    }

    @Override
    public void onEnable() {
        this.masterConfig = new MasterConfig();
        this.getServer().getPluginManager().registerEvents(new PlayerEventListener(),this);

        Server.getInstance().getCommandMap().register("", new FriendCommand());
        Server.getInstance().getCommandMap().register("", new FBanCommand());

        this.getLogger().notice("WGuild - Enabled !");
        this.getLogger().notice("NM$L yhzx");

    }

    @Override
    public void onDisable() {
        this.getLogger().notice("WGuild - Disable !");
        this.getLogger().notice("NM$L yhzx");
    }

    public static WGuild getInstance() {
        return plugin;
    }

    public MasterConfig getMasterConfig() {
        return masterConfig;
    }

}
