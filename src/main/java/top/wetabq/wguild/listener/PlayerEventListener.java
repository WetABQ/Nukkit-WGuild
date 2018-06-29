package top.wetabq.wguild.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.utils.TextFormat;
import top.wetabq.wguild.WGuild;
import top.wetabq.wguild.config.PlayerCacheConfig;
import top.wetabq.wguild.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * WGuild
 *
 * @author WetABQ Copyright (c) 2018.06
 * @version 1.0
 */
public class PlayerEventListener implements Listener {

    private static WGuild plugin = WGuild.getInstance();

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.getMasterConfig().isFriendOnlineNotice() &&
                !plugin.getMasterConfig().isBanPlayer(player.getName())) {
            PlayerCacheConfig playerCacheConfig = new PlayerCacheConfig(player.getName());
            if (playerCacheConfig.getFeedback().size() != 0) {
                player.sendMessage(Constant.TITLE + TextFormat.GOLD + "你还有 " + playerCacheConfig.getFeedback().size() + " 条 好友留言未查看. 输入/f vf 查看");
            }
            for (String playerName : playerCacheConfig.getFriends()) {
                Player friend = plugin.getServer().getPlayer(playerName);
                if (friend != null && plugin.getMasterConfig().isBanPlayer(playerName) && new PlayerCacheConfig(playerName).getNoticeSwitch()) {
                    friend.sendMessage(Constant.TITLE + TextFormat.colorize("&a你的好友&e「&l&c" + player.getName() + "&r&e」&a上线了"));
                }
            }
        }
        if (plugin.getMasterConfig().isBanPlayer(player.getName())) {
            Date d = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (plugin.getMasterConfig().getBanPlayers().get(player.getName()) != Integer.parseInt(format.format(d))) {
                player.sendMessage(Constant.TITLE + TextFormat.RED + "你已经被WGuild系统封禁至" + plugin.getMasterConfig().getBanPlayers().get(player.getName()));
            } else {
                player.sendMessage(Constant.TITLE + TextFormat.GOLD + "你已经被WGuild自动解封");
            }
        }

    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (plugin.getMasterConfig().isFriendOnlineNotice() &&
                !plugin.getMasterConfig().isBanPlayer(player.getName())) {
            PlayerCacheConfig playerCacheConfig = new PlayerCacheConfig(player.getName());
            for (String playerName : playerCacheConfig.getFriends()) {
                Player friend = plugin.getServer().getPlayer(playerName);
                if (friend != null && plugin.getMasterConfig().isBanPlayer(playerName) && new PlayerCacheConfig(playerName).getNoticeSwitch()) {
                    friend.sendMessage(Constant.TITLE + TextFormat.colorize("&c你的好友&e「&l&a" + player.getName() + "&r&e」&c下线了"));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerAttackEvent(EntityDamageEvent e) {
        if (plugin.getMasterConfig().isEnableProtectAttackFriend()) {
            if (e instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
                if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
                    Player damager = (Player) event.getDamager();
                    Player entity = (Player) event.getEntity();
                    PlayerCacheConfig playerCacheConfig = new PlayerCacheConfig(damager.getName());
                    if (playerCacheConfig.isEachFollow(entity.getName())) {
                        damager.sendTip(TextFormat.RED + "你攻击了你的好友!");
                        entity.sendTip(TextFormat.RED + "你的好友「" + damager.getName() + "」攻击了你");
                        event.setCancelled();
                    }
                }
            }
        }
    }


}
