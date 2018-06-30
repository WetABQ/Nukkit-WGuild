package top.wetabq.wguild.config;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import top.wetabq.wguild.WGuild;
import top.wetabq.wguild.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * WGuild
 *
 * @author WetABQ Copyright (c) 2018.06
 * @version 1.0
 */
public class PlayerCacheConfig extends WGuildConfig implements WGuildConfigInterface {

    private String player;

    private List<String> followers = new ArrayList<>();

    private List<String> following = new ArrayList<>();

    private List<String> feedback = new ArrayList<>();

    private boolean notice;


    public PlayerCacheConfig(String playerName) {
        super(playerName);
        this.player = playerName;
        this.init();
    }

    @Override
    protected void init() {
        if (!isEmpty()) {
            try {
                followers = (ArrayList) configSection.get("followers");
                following = (ArrayList) configSection.get("following");
                notice = configSection.getBoolean("notice");
                feedback = (ArrayList) configSection.get("feedback");
            } catch (Exception e) {
                WGuild.getInstance().getLogger().warning(e.getMessage());
                WGuild.getInstance().getLogger().error("Player " + player + " has an error while loading config");
            }
        } else {
            spawnDefaultConfig();
        }
    }

    @Override
    protected void spawnDefaultConfig() {
        if (isEmpty()) {
            configSection.put("followers", followers);
            configSection.put("following", following);
            configSection.put("notice", true);
            configSection.put("feedback", feedback);
        }
        init();
        save();
    }

    @Override
    public void save() {
        if (!isEmpty()) {
            try {
                configSection.clear();
                configSection.put("followers", followers);
                configSection.put("following", following);
                configSection.put("notice", notice);
                configSection.put("feedback", feedback);
                config.setAll(configSection);
                config.save();
            } catch (Exception e) {
                WGuild.getInstance().getLogger().warning(e.getMessage());
                WGuild.getInstance().getLogger().error("Player " + player + " has an error while saving config");
            }
        } else {
            spawnDefaultConfig();
        }
    }

    public String getPlayerName() {
        return player;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    public List<String> getFeedback() {
        return feedback;
    }

    public void clearFeedback() {
        feedback.clear();
        this.save();
    }

    public void addFeedback(String playerName,String message) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.feedback.add(format.format(date) + " 玩家「" + playerName + "」留言到: "+ message);
        this.save();
    }

    public void setNotice(boolean notice) {
        this.notice = notice;
        this.save();
    }

    public void addFollowers(String playerName) {
        this.followers.add(playerName);
        PlayerCacheConfig playerCacheConfig = new PlayerCacheConfig(playerName);
        playerCacheConfig.addFollowing(this.getPlayerName());
        Player player = WGuild.getInstance().getServer().getPlayerExact(playerName);
        if (player != null && playerCacheConfig.getNoticeSwitch()) {
            player.sendMessage(TextFormat.colorize(Constant.TITLE + "&e玩家 「&c" + player.getName() + "&e」 &e关注了你"));
            player.sendTip(TextFormat.colorize("&e玩家 「&c" + player.getName() + "&e」 &e关注了你"));
        }
        Player player0 = WGuild.getInstance().getServer().getPlayerExact(this.getPlayerName());
        if (player0 != null && this.getNoticeSwitch()) {
            player0.sendMessage(TextFormat.colorize(Constant.TITLE + "&e你成功关注了 「&c" + playerName + "&e」"));
            player0.sendTip(TextFormat.colorize("&e你成功关注了 「&c" + playerName + "&e」"));
        }
        this.save();
    }

    public void addFollowing(String playerName) {
        this.following.add(playerName);
        save();
    }

    public void removeFollowers(String playerName) {
        this.followers.remove(playerName);
        PlayerCacheConfig playerCacheConfig = new PlayerCacheConfig(playerName);
        playerCacheConfig.removeFollowing(this.getPlayerName());
        Player player = WGuild.getInstance().getServer().getPlayerExact(playerName);
        if (player != null && playerCacheConfig.getNoticeSwitch()) {
            player.sendTip(TextFormat.colorize("&c玩家 「&e" + player.getName() + "&c」 &c取消关注了你"));
        }
        Player player0 = WGuild.getInstance().getServer().getPlayerExact(this.getPlayerName());
        if (player0 != null && this.getNoticeSwitch()) {
            player0.sendTip(TextFormat.colorize("&c你成功取消关注了 「&e" + playerName + "&c」"));
        }
        save();
    }

    public boolean isFollowing(String playerName) {
        return following.contains(playerName);
    }

    public boolean isFollower(String playerName) {
        return followers.contains(playerName);
    }

    public boolean isEachFollow(String playerName) {
        return isFollower(playerName) && isFollowing(playerName);
    }

    public int getFollowingCount() {
        return this.following.size();
    }

    public int getFollowersCount() {
        return this.followers.size();
    }

    public List<String> getFriends() {
        List<String> list = getFollowers();
        List<String> friends = new ArrayList<>();
        for (String playerName : list) {
            if (this.isEachFollow(playerName)) {
                friends.add(playerName);
            }
        }
        return friends;
    }

    public void removeFollowing(String playerName) {
        this.following.remove(playerName);
        save();
    }

    public boolean getNoticeSwitch() {
        return notice;
    }

}
