package top.wetabq.wguild.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.TextFormat;
import top.wetabq.wguild.WGuild;
import top.wetabq.wguild.utils.Constant;

import java.util.HashMap;

/**
 * WGuild
 *
 * @author WetABQ Copyright (c) 2018.06
 * @version 1.0
 */
public class FBanCommand extends Command {

    public FBanCommand() {
        super("fban");
        this.setDescription("Ban player in WGuild System");
        this.setAliases(new String[]{
                "fb"
        });
        this.setUsage("/fban(fb) <playerName> <day(s)>");
        this.setCommandParameters(new HashMap<String, CommandParameter[]>() {
            {
                put("1arg", new CommandParameter[]{
                        new CommandParameter("Player Name", CommandParameter.ARG_TYPE_PLAYER, false),
                        new CommandParameter("Day(s)", CommandParameter.ARG_TYPE_INT, false)
                });
            }
        });
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender.isOp()) {
            if (args.length != 2) {
                return false;
            }
            try {
                WGuild.getInstance().getMasterConfig().addBanPlayer(args[0], Integer.parseInt(args[1]));
                sender.sendMessage(Constant.TITLE + TextFormat.GREEN + "封禁成功");
            }catch (Exception e) {
                sender.sendMessage(Constant.TITLE + "第二项(天数)请输入数字");
            }
        }
        return true;
    }

}
