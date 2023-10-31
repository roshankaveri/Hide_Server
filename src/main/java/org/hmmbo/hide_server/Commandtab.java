package org.hmmbo.hide_server;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commandtab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length==1)
        {
            return StringUtil.copyPartialMatches(strings[0], Arrays.asList("setspawn","setwait"), new ArrayList<>());
        }
        return null;
    }
}
