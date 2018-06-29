package top.wetabq.wguild.config;

import top.wetabq.wguild.WGuild;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * WGuild
 *
 * @author WetABQ Copyright (c) 2018.06
 * @version 1.0
 */
public class MasterConfig extends WGuildConfig {

    private boolean enableTalkWithFriend;
    private boolean friendOnlineNotice;
    private boolean enableProtectAttackFriend;

    private Map<String,Integer> banPlayers = new HashMap<>(); //本系统对指定玩家封禁 不是服务器层面（例如刷违禁词的可以ban

    public MasterConfig() {
        super("a-pluginConfig");
        this.init();
    }

    @Override
    protected void init() {
        if (!isEmpty()) {
            try {
                enableTalkWithFriend = configSection.getBoolean("enableTalkWithFriend");
                friendOnlineNotice = configSection.getBoolean("friendOnlineNotice");
                enableProtectAttackFriend = configSection.getBoolean("enableProtectAttackFriend");

                banPlayers = (Map) configSection.get("banPlayers");
            } catch (Exception e) {
                WGuild.getInstance().getLogger().warning(e.getMessage());
                WGuild.getInstance().getLogger().error("Master Config(a-pluginConfig.yml) while loading config");
            }
        } else {
            spawnDefaultConfig();
        }
    }

    @Override
    protected void spawnDefaultConfig() {
        if (isEmpty()) {
            configSection.put("enableTalkWithFriend", true);
            configSection.put("friendOnlineNotice", true);
            configSection.put("enableProtectAttackFriend", true);
            configSection.put("banPlayers", banPlayers);
        }
        init();
        save();
    }

    @Override
    public void save() {
        if (!isEmpty()) {
            try {
                configSection.clear();
                configSection.put("enableTalkWithFriend", enableTalkWithFriend);
                configSection.put("friendOnlineNotice", friendOnlineNotice);
                configSection.put("enableProtectAttackFriend", enableProtectAttackFriend);
                configSection.put("banPlayers", banPlayers);
                config.setAll(configSection);
                config.save();
            } catch (Exception e) {
                WGuild.getInstance().getLogger().warning(e.getMessage());
                WGuild.getInstance().getLogger().error("Master Config(a-pluginConfig.yml) has an error while saving config");
            }
        } else {
            spawnDefaultConfig();
        }
    }

    public Map<String, Integer> getBanPlayers() {
        return banPlayers;
    }

    public boolean isEnableProtectAttackFriend() {
        return enableProtectAttackFriend;
    }

    public boolean isEnableTalkWithFriend() {
        return enableTalkWithFriend;
    }

    public boolean isFriendOnlineNotice() {
        return friendOnlineNotice;
    }

    public void addBanPlayer(String playerName,int days) {
        Date d;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, days);
        d = ca.getTime();
        String enddate = format.format(d);

        banPlayers.put(playerName,Integer.getInteger(enddate));
        save();
    }

    public boolean isBanPlayer(String playerName) {
        return banPlayers.containsKey(playerName);
    }

    public void setEnableTalkWithFriend(boolean enableTalkWithFriend) {
        this.enableTalkWithFriend = enableTalkWithFriend;
        save();
    }

    public void setFriendOnlineNotice(boolean friendOnlineNotice) {
        this.friendOnlineNotice = friendOnlineNotice;
        save();
    }
}
