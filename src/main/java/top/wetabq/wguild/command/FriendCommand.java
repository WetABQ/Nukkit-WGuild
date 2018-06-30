package top.wetabq.wguild.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.TextFormat;
import top.wetabq.wguild.WGuild;
import top.wetabq.wguild.config.PlayerCacheConfig;
import top.wetabq.wguild.utils.Constant;
import top.wetabq.wguild.utils.FileUtils;

import java.util.HashMap;
import java.util.List;

/**
 * WGuild
 *
 * @author WetABQ Copyright (c) 2018.06
 * @version 1.0
 */
public class FriendCommand extends Command {

    private static WGuild plugin = WGuild.getInstance();

    public FriendCommand() {
        super("friend");
        this.setAliases(new String[]{
                "fr",
                "friends",
                "f"
        });
        this.setDescription("Friends Command");
        this.setUsage("/f(fr,friend,friends) <subcommand> [args]");
        this.setCommandParameters(new HashMap<String, CommandParameter[]>() {
            {
                put("1arg", new CommandParameter[]{
                        new CommandParameter("help", false, new String[]{"help","h"}),
                });
                put("2arg", new CommandParameter[]{
                        new CommandParameter("follow(f)", false, new String[]{"follow", "f"}),
                        new CommandParameter("playerName", CommandParameter.ARG_TYPE_PLAYER, false)
                });
                put("3arg", new CommandParameter[]{
                        new CommandParameter("unfollow(unf)", false, new String[]{"unfollow", "unf"}),
                        new CommandParameter("playerName", CommandParameter.ARG_TYPE_PLAYER, false)
                });
                put("4arg", new CommandParameter[]{
                        new CommandParameter("tell(t)", false, new String[]{"tell", "t"}),
                        new CommandParameter("playerName", CommandParameter.ARG_TYPE_PLAYER, false),
                        new CommandParameter("content", CommandParameter.ARG_TYPE_RAW_TEXT, false)
                });
                put("5arg", new CommandParameter[]{
                        new CommandParameter("notice(n)", false, new String[]{"notice", "n"}),
                });
                put("6arg", new CommandParameter[]{
                        new CommandParameter("viewinfo(vi)", false, new String[]{"viewinfo", "vi"}),
                        new CommandParameter("playerName", CommandParameter.ARG_TYPE_PLAYER, true)
                });
                put("7arg", new CommandParameter[]{
                        new CommandParameter("viewfeedback(vf)", false, new String[]{"viewfeedback", "vf"}),
                });
            }
        });
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (args.length <= 0 || args[0].equals("help") || args.length >= 4 || args[0].equals("h")) {
            sendHelp(sender);
            return true;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerCacheConfig playerCacheConfig = new PlayerCacheConfig(player.getName());
            switch (args[0]) {
                case "f":
                case "follow":
                    if (args.length != 2) {
                        sendHelp(player);
                        return true;
                    }
                    if (FileUtils.isPlayerConfigExists(args[1])) {
                        if (!plugin.getMasterConfig().isBanPlayer(player.getName())) {
                            if(!plugin.getMasterConfig().isBanPlayer(args[1])) {
                                if (!playerCacheConfig.isFollower(args[1])) {
                                    playerCacheConfig.addFollowers(args[1]);
                                } else {
                                    player.sendMessage(Constant.TITLE + TextFormat.RED + "你已经关注过他了");
                                }
                            } else {
                                player.sendMessage(Constant.TITLE + TextFormat.RED + "你关注的人已经被WGuild系统封禁");
                            }
                        } else {
                            player.sendMessage(Constant.TITLE + TextFormat.RED + "你已被WGuild系统封禁");
                        }
                    } else {
                        player.sendMessage(Constant.TITLE + TextFormat.RED + "玩家不存在");
                    }
                    break;
                case "unf":
                case "unfollow":
                    if (args.length != 2) {
                        sendHelp(player);
                        return true;
                    }
                    if (FileUtils.isPlayerConfigExists(args[1])) {
                        if (!plugin.getMasterConfig().isBanPlayer(player.getName())) {
                            if (playerCacheConfig.isFollower(args[1])) {
                                playerCacheConfig.removeFollowers(args[1]);
                            } else {
                                player.sendMessage(Constant.TITLE + TextFormat.RED + "你没有关注过他");
                            }
                        } else {
                            player.sendMessage(Constant.TITLE + TextFormat.RED + "你已被WGuild系统封禁");
                        }
                    } else {
                        player.sendMessage(Constant.TITLE + TextFormat.RED + "玩家不存在");
                    }
                    break;
                case "t":
                case "tell":
                    if (args.length != 3) {
                        sendHelp(player);
                        return true;
                    }
                    if (FileUtils.isPlayerConfigExists(args[1])) {
                        if (!plugin.getMasterConfig().isBanPlayer(player.getName())) {
                            if(!plugin.getMasterConfig().isBanPlayer(args[1])) {
                                if (playerCacheConfig.isEachFollow(args[1])) {
                                    if (plugin.getMasterConfig().isEnableTalkWithFriend()) {
                                        Player player1 = plugin.getServer().getPlayer(args[1]);
                                        PlayerCacheConfig playerCacheConfig1 = new PlayerCacheConfig(args[1]);
                                        if (player1 != null) {
                                            if (playerCacheConfig1.getNoticeSwitch()) {
                                                player.sendMessage(Constant.TITLE + TextFormat.colorize("&l&c>>> &r&e「&c" + player1.getName() + "&e」 &6对他说 &e" + args[2] + " &l&c<<<"));
                                                player1.sendMessage(Constant.TITLE + TextFormat.colorize("&l&c>>> &r&e「&c" + player.getName() + "&e」 &6对你说: &e" + args[2] + " &l&c<<<"));
                                            } else {
                                                player1.sendMessage(Constant.TITLE + TextFormat.colorize("&c对方关闭了好友提醒,已自动发送&l&e留言"));
                                                playerCacheConfig1.addFeedback(args[1], args[2]);
                                            }
                                        } else {
                                            player1.sendMessage(Constant.TITLE + TextFormat.colorize("&c好友不在线,已自动发送&l&e留言"));
                                            playerCacheConfig1.addFeedback(args[1], args[2]);
                                        }
                                    } else {
                                        player.sendMessage(Constant.TITLE + TextFormat.RED + "服务器关闭了该功能");
                                    }
                                } else {
                                    player.sendMessage(Constant.TITLE + TextFormat.RED + "对方不是你的好友");
                                }
                            } else {
                                player.sendMessage(Constant.TITLE + TextFormat.RED + "对方已经被WGuild系统封禁");
                            }
                        } else {
                            player.sendMessage(Constant.TITLE + TextFormat.RED + "你已被WGuild系统封禁");
                        }
                    } else {
                        player.sendMessage(Constant.TITLE + TextFormat.RED + "玩家不存在");
                    }
                    break;
                case "n":
                case "notice":
                    if (args.length != 1) {
                        sendHelp(player);
                        return true;
                    }
                    if (!plugin.getMasterConfig().isBanPlayer(player.getName())) {
                        boolean notice = playerCacheConfig.getNoticeSwitch();
                        notice = !notice;
                        playerCacheConfig.setNotice(notice);
                        if (notice) {
                            player.sendMessage(Constant.TITLE + TextFormat.GREEN + "成功启用好友提醒");
                        } else {
                            player.sendMessage(Constant.TITLE + TextFormat.GREEN + "成功关闭好友提醒");
                        }
                    } else {
                        player.sendMessage(Constant.TITLE + TextFormat.RED + "你已被WGuild系统封禁");
                    }
                    break;
                case "vi":
                case "viewinfo":
                    if (args.length > 2) {
                        sendHelp(player);
                        return true;
                    }
                    if (!plugin.getMasterConfig().isBanPlayer(player.getName())) {
                        if (args.length == 1) {
                            sendInfo(player,player.getName());
                        } else {
                            sendInfo(player,args[1]);
                        }
                    } else {
                        player.sendMessage(Constant.TITLE + TextFormat.RED + "你已被WGuild系统封禁");
                    }
                    break;
                case "vf":
                case "viewfeedback":
                    if (args.length != 1) {
                        sendHelp(player);
                        return true;
                    }
                    if (!plugin.getMasterConfig().isBanPlayer(player.getName())) {
                        List<String> feedback = playerCacheConfig.getFeedback();
                        if (feedback.size() != 0) {
                            for (String fd : feedback) {
                                player.sendMessage(Constant.TITLE + TextFormat.GOLD + fd);
                            }
                            playerCacheConfig.clearFeedback();
                            player.sendMessage(Constant.TITLE + TextFormat.RED + "已自动清空已查看留言");
                        } else {
                            player.sendMessage(Constant.TITLE + TextFormat.RED + "没有人给你留言，或已经清空(每次查看后会自动清空留言箱)");
                        }
                    } else {
                        player.sendMessage(Constant.TITLE + TextFormat.RED + "你已被WGuild系统封禁");
                    }
                    break;
                default:
                    sendHelp(player);
                    player.sendMessage(Constant.TITLE + TextFormat.RED + "你输入的子命令不存在 (ps:括号里面的是简写 可以一样使用)");
            }
        } else {
            sender.sendMessage("请在游戏里使用命令");
        }
        return true;
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(TextFormat.GOLD + "----WGuild Friend----");
        sender.sendMessage(TextFormat.AQUA + "/f help(h) - 查看帮助");
        sender.sendMessage(TextFormat.AQUA + "/f follow(f) <playerName> - 关注玩家");
        sender.sendMessage(TextFormat.AQUA + "/f unfollow(unf) <playerName> - 取消关注玩家");
        sender.sendMessage(TextFormat.AQUA + "/f tell(t) <playerName> <content> - 和好友私聊/留言");
        sender.sendMessage(TextFormat.AQUA + "/f notice(n) - 开启(关闭)好友提醒(包括私聊)");
        sender.sendMessage(TextFormat.AQUA + "/f viewinfo(vi) [playerName] - 查看玩家WGuild系统信息(查看自己的不用填后一项)");
        sender.sendMessage(TextFormat.AQUA + "/f viewfeedback(vf) - 查看好友留言");
    }

    private void sendInfo(Player sender,String playerName) {
        PlayerCacheConfig playerCacheConfig = new PlayerCacheConfig(playerName);
        sender.sendMessage(TextFormat.GOLD + "----WGuild Friend----");
        sender.sendMessage(TextFormat.AQUA + "关注: " + playerCacheConfig.getFollowersCount());
        sender.sendMessage(TextFormat.AQUA + "粉丝: " + playerCacheConfig.getFollowingCount());
        sender.sendMessage(TextFormat.AQUA + "好友提醒: " + ((playerCacheConfig.getNoticeSwitch()) ? "开启" : "关闭"));
    }

}
