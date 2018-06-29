package top.wetabq.wguild.config;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import top.wetabq.wguild.WGuild;

/**
 * WGuild
 *
 * @author WetABQ Copyright (c) 2018.06
 * @version 1.0
 */
abstract public class WGuildConfig implements WGuildConfigInterface {

    protected Config config;
    protected ConfigSection configSection;

    public WGuildConfig(String configname) {
        this(new Config(WGuild.getInstance().getDataFolder() + "/" + configname +".yml", Config.YAML));
    }

    private WGuildConfig(Config config) {
        this.config = config;
        this.configSection = config.getRootSection();
    }

    abstract protected void init();

    abstract protected void spawnDefaultConfig();

    abstract public void save();

    public boolean isEmpty() {
        return configSection.isEmpty();
    }


}
