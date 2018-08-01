package top.wetabq.wguild.config;

import top.wetabq.wguild.WGuild;

/**
 * WGuild
 *
 * @author WetABQ Copyright (c) 2018.07
 * @version 1.0
 */
public class LanguageConfig extends WGuildConfig implements WGuildConfigInterface {

    private int lang;
    //Lang 1 = CHS, 0 = ENG

    public String listener_join_feedback_notice_1;
    public String listener_join_feedback_notice_2;
    public String listener_join_online_notice_1;
    public String listener_join_online_notice_2;
    public String listener_join_ban_notice;
    public String listener_join_unban_notice;
    public String listener_quit_notice_1;
    public String listener_quit_notice_2;
    public String listener_attack_damager_notice;
    public String listener_attack_entity_notice_1;
    public String listener_attack_entity_notice_2;

    public String feedback_1;
    public String feedback_2;
    public String follower_add_1;
    public String follower_add_2;
    public String follower_add_3;
    public String follower_add_4;
    public String follower_remove_1;
    public String follower_remove_2;
    public String follower_remove_3;
    public String follower_remove_4;

    public String command_player_not_exist;
    public String command_player_ban;
    public String command_player_ban_0;
    public String command_follow_exist;
    public String command_follow_not_exist;
    public String command_tell_1;
    public String command_tell_2;
    public String command_tell_3;
    public String command_tell_4;
    public String command_server_close;
    public String command_tell_not_friend;
    public String command_notice_1;
    public String command_notice_2;
    public String command_viewfeedback_1;
    public String command_viewfeedback_2;

    public String command_help_1;
    public String command_help_2;
    public String command_help_3;
    public String command_help_4;
    public String command_help_5;
    public String command_help_6;
    public String command_help_7;

    public String command_viewinfo_1;
    public String command_viewinfo_2;
    public String command_viewinfo_3;


    public LanguageConfig(int lang) {
        super("a-languageConfig");
        this.lang = lang;
        this.init();
    }

    @Override
    protected void init() {
        if (!isEmpty()) {
            try {
                listener_join_feedback_notice_1 = configSection.getString("listener_join_feedback_notice_1");
                listener_join_feedback_notice_2 = configSection.getString("listener_join_feedback_notice_2");
                listener_join_online_notice_1 = configSection.getString("listener_join_online_notice_1");
                listener_join_online_notice_2 = configSection.getString("listener_join_online_notice_2");
                listener_join_ban_notice = configSection.getString("listener_join_ban_notice");
                listener_join_unban_notice = configSection.getString("listener_join_unban_notice");
                listener_quit_notice_1 = configSection.getString("listener_quit_notice_1");
                listener_quit_notice_2 = configSection.getString("listener_quit_notice_2");
                listener_attack_damager_notice = configSection.getString("listener_attack_damager_notice");
                listener_attack_entity_notice_1 = configSection.getString("listener_attack_entity_notice_1");
                listener_attack_entity_notice_2 = configSection.getString("listener_attack_entity_notice_2");
                feedback_1 = configSection.getString("feedback_1");
                feedback_2 = configSection.getString("feedback_2");
                follower_add_1 = configSection.getString("follower_add_1");
                follower_add_2 = configSection.getString("follower_add_2");
                follower_add_3 = configSection.getString("follower_add_3");
                follower_add_4 = configSection.getString("follower_add_4");
                follower_remove_1 = configSection.getString("follower_remove_1");
                follower_remove_2 = configSection.getString("follower_remove_2");
                follower_remove_3 = configSection.getString("follower_remove_3");
                follower_remove_4 = configSection.getString("follower_remove_4");
                command_player_not_exist = configSection.getString("command_player_not_exist");
                command_player_ban = configSection.getString("command_player_ban");
                command_player_ban_0 = configSection.getString("command_player_ban_0");
                command_follow_exist = configSection.getString("command_follow_exist");
                command_follow_not_exist = configSection.getString("command_follow_not_exist");
                command_tell_1 = configSection.getString("command_tell_1");
                command_tell_2 = configSection.getString("command_tell_2");
                command_tell_3 = configSection.getString("command_tell_3");
                command_tell_4 = configSection.getString("command_tell_4");
                command_server_close = configSection.getString("command_server_close");
                command_tell_not_friend = configSection.getString("command_tell_not_friend");
                command_notice_1 = configSection.getString("command_notice_1");
                command_notice_2 = configSection.getString("command_notice_2");
                command_viewfeedback_1 = configSection.getString("command_viewfeedback_1");
                command_viewfeedback_2 = configSection.getString("command_viewfeedback_2");
                command_help_1 = configSection.getString("command_help_1");
                command_help_2 = configSection.getString("command_help_2");
                command_help_3 = configSection.getString("command_help_3");
                command_help_4 = configSection.getString("command_help_4");
                command_help_5 = configSection.getString("command_help_5");
                command_help_6 = configSection.getString("command_hel  p_6");
                command_help_7 = configSection.getString("command_help_7");
                command_viewinfo_1 = configSection.getString("command_viewinfo_1");
                command_viewinfo_2 = configSection.getString("command_viewinfo_2");
                command_viewinfo_3 = configSection.getString("command_viewinfo_3");
            } catch (Exception e) {
                WGuild.getInstance().getLogger().warning(e.getMessage());
                WGuild.getInstance().getLogger().error("Language Config(a-languageConfig.yml) while loading config");
            }
        } else {
            spawnDefaultConfig();
        }
    }

    @Override
    protected void spawnDefaultConfig() {
        if (isEmpty()) {
            if (this.lang == 1) {
                configSection.put("listener_join_feedback_notice_1","你还有 ");
                configSection.put("listener_join_feedback_notice_2","条 好友留言未查看. 输入/f vf 查看");
                configSection.put("listener_join_online_notice_1","&a你的好友&e「&l&c");
                configSection.put("listener_join_online_notice_2","&r&e」&a上线了");
                configSection.put("listener_join_ban_notice","你已经被WGuild系统封禁至 ");
                configSection.put("listener_join_unban_notice","你已经被WGuild自动解封");
                configSection.put("listener_quit_notice_1","&c你的好友&e「&l&a");
                configSection.put("listener_quit_notice_2","&r&e」&c下线了");
                configSection.put("listener_attack_damager_notice","你攻击了你的好友!");
                configSection.put("listener_attack_entity_notice_1","你的好友「");
                configSection.put("listener_attack_entity_notice_2","」攻击了你");
                configSection.put("feedback_1","玩家「");
                configSection.put("feedback_2","」留言到:");
                configSection.put("follower_add_1","&e玩家 「&c");
                configSection.put("follower_add_2","&e」 &e关注了你");
                configSection.put("follower_add_3","&e你成功关注了 「&c");
                configSection.put("follower_add_4","&e」");
                configSection.put("follower_remove_1","&c玩家 「&e");
                configSection.put("follower_remove_2","&c」 &c取消关注了你");
                configSection.put("follower_remove_3","&c你成功取消关注了 「&e");
                configSection.put("follower_remove_4","&c」");
                configSection.put("command_player_not_exist","玩家不存在");
                configSection.put("command_player_ban","你已被WGuild系统封禁");
                configSection.put("command_player_ban_0","你关注的人已经被WGuild系统封禁");
                configSection.put("command_follow_exist","你已经关注过他了");
                configSection.put("command_follow_not_exist","你没有关注过他");
                configSection.put("command_tell_1","对他说");
                configSection.put("command_tell_2","对你说");
                configSection.put("command_tell_3","&c对方关闭了好友提醒,已自动发送&l&e留言");
                configSection.put("command_tell_4","&c好友不在线,已自动发送&l&e留言");
                configSection.put("command_server_close","服务器关闭了该功能");
                configSection.put("command_tell_not_friend","对方不是你的好友");
                configSection.put("command_notice_1","成功启用好友提醒");
                configSection.put("command_notice_2","成功关闭好友提醒");
                configSection.put("command_viewfeedback_1","已自动清空已查看留言");
                configSection.put("command_viewfeedback_2","没有人给你留言，或已经清空(每次查看后会自动清空留言箱)");
                configSection.put("command_help_1","查看帮助");
                configSection.put("command_help_2","关注玩家");
                configSection.put("command_help_3","取消关注玩家");
                configSection.put("command_help_4","和好友私聊/留言");
                configSection.put("command_help_5","开启(关闭)好友提醒(包括私聊)");
                configSection.put("command_help_6","查看玩家WGuild系统信息(查看自己的不用填后一项)");
                configSection.put("command_help_7","查看好友留言");
                configSection.put("command_viewinfo_1","关注: ");
                configSection.put("command_viewinfo_2","粉丝: ");
                configSection.put("command_viewinfo_3","好友提醒: ");
            } else {
                configSection.put("listener_join_feedback_notice_1","You still have");
                configSection.put("listener_join_feedback_notice_2","friend's message not viewed. Enter /f vf to view");
                configSection.put("listener_join_online_notice_1","&cYour friend&e「&l&c");
                configSection.put("listener_join_online_notice_2","&r&e」&ais online.");
                configSection.put("listener_join_ban_notice","You have been banned by the WGuild system until ");
                configSection.put("listener_join_unban_notice","You have been automatically unsealed by WGuild");
                configSection.put("listener_quit_notice_1","&cYour friend&e「&l&a");
                configSection.put("listener_quit_notice_2","&r&e」&cis offline");
                configSection.put("listener_attack_damager_notice","You attacked your friend!");
                configSection.put("listener_attack_entity_notice_1","Your friend「");
                configSection.put("listener_attack_entity_notice_2","」attacked you");
                configSection.put("feedback_1","Player「");
                configSection.put("feedback_2","」Message to");
                configSection.put("follower_add_1","&ePlayer 「&c");
                configSection.put("follower_add_2","&e」 &eFollowed you");
                configSection.put("follower_add_3","&eYou have successfully followed「&c");
                configSection.put("follower_add_4","&e」");
                configSection.put("follower_remove_1","");
                configSection.put("follower_remove_2","");
                configSection.put("follower_remove_3","");
                configSection.put("follower_remove_4","");
                configSection.put("command_player_not_exist","");
                configSection.put("command_player_ban","");
                configSection.put("command_player_ban_0","");
                configSection.put("command_follow_exist","");
                configSection.put("command_follow_not_exist","");
                configSection.put("command_tell_1","");
                configSection.put("command_tell_2","");
                configSection.put("command_tell_3","");
                configSection.put("command_tell_4","");
                configSection.put("command_server_close","");
                configSection.put("command_tell_not_friend","");
                configSection.put("command_notice_1","");
                configSection.put("command_notice_2","");
                configSection.put("command_viewfeedback_1","");
                configSection.put("command_viewfeedback_2","");
                configSection.put("command_help_1","");
                configSection.put("command_help_2","");
                configSection.put("command_help_3","");
                configSection.put("command_help_4","");
                configSection.put("command_help_5","");
                configSection.put("command_help_6","");
                configSection.put("command_help_7","");
                configSection.put("command_viewinfo_1","");
                configSection.put("command_viewinfo_2","");
                configSection.put("command_viewinfo_3","");
            }
        }
        init();
        save();
    }

    @Override
    public void save() {
        if (!isEmpty()) {
            try {
                configSection.clear();
                configSection.put("listener_join_feedback_notice_1",listener_join_feedback_notice_1);
                configSection.put("listener_join_feedback_notice_2",listener_join_feedback_notice_2);
                configSection.put("listener_join_online_notice_1",listener_join_online_notice_1);
                configSection.put("listener_join_online_notice_2",listener_join_online_notice_2);
                configSection.put("listener_join_ban_notice",listener_join_ban_notice);
                configSection.put("listener_join_unban_notice",listener_join_unban_notice);
                configSection.put("listener_quit_notice_1",listener_quit_notice_1);
                configSection.put("listener_quit_notice_2",listener_quit_notice_2);
                configSection.put("listener_attack_damager_notice",listener_attack_damager_notice);
                configSection.put("listener_attack_entity_notice_1",listener_attack_entity_notice_1);
                configSection.put("listener_attack_entity_notice_2",listener_attack_entity_notice_2);
                configSection.put("feedback_1",feedback_1);
                configSection.put("feedback_2",feedback_2);
                configSection.put("follower_add_1",follower_add_1);
                configSection.put("follower_add_2",follower_add_2);
                configSection.put("follower_add_3",follower_add_3);
                configSection.put("follower_add_4",follower_add_4);
                configSection.put("follower_remove_1",follower_add_1);
                configSection.put("follower_remove_2",follower_add_2);
                configSection.put("follower_remove_3",follower_add_3);
                configSection.put("follower_remove_4",follower_add_4);
                configSection.put("command_player_not_exist",command_player_not_exist);
                configSection.put("command_player_ban",command_player_ban);
                configSection.put("command_player_ban_0",command_player_ban_0);
                configSection.put("command_follow_exist",command_follow_exist);
                configSection.put("command_follow_not_exist",command_follow_not_exist);
                configSection.put("command_tell_1",command_tell_1);
                configSection.put("command_tell_2",command_tell_2);
                configSection.put("command_tell_3",command_tell_3);
                configSection.put("command_tell_4",command_tell_4);
                configSection.put("command_server_close",command_server_close);
                configSection.put("command_tell_not_friend",command_tell_not_friend);
                configSection.put("command_notice_1",command_notice_1);
                configSection.put("command_notice_2",command_notice_2);
                configSection.put("command_viewfeedback_1",command_viewfeedback_1);
                configSection.put("command_viewfeedback_2",command_viewfeedback_2);
                configSection.put("command_help_1",command_help_1);
                configSection.put("command_help_2",command_help_2);
                configSection.put("command_help_3",command_help_3);
                configSection.put("command_help_4",command_help_4);
                configSection.put("command_help_5",command_help_5);
                configSection.put("command_help_6",command_help_6);
                configSection.put("command_help_7",command_help_7);
                configSection.put("command_viewinfo_1",command_viewinfo_1);
                configSection.put("command_viewinfo_2",command_viewinfo_2);
                configSection.put("command_viewinfo_3",command_viewinfo_3);
                config.setAll(configSection);
                config.save();
            } catch (Exception e) {
                WGuild.getInstance().getLogger().warning(e.getMessage());
                WGuild.getInstance().getLogger().error("Language Config(a-languageConfig.yml) has an error while saving config");
            }
        } else {
            spawnDefaultConfig();
        }
    }

    public int getLang() {
        return lang;
    }
}
