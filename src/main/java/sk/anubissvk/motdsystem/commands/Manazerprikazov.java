package sk.anubissvk.motdsystem.commands;

import sk.anubissvk.motdsystem.Motd;
import sk.anubissvk.motdsystem.configuration.SpravcaSuborov;
import sk.anubissvk.motdsystem.utils.ChatUtil;
import sk.anubissvk.motdsystem.variables.Premenne;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Manazerprikazov implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof CommandSender))
            return true;
        if (cmd.getName().equalsIgnoreCase("motd")) {
            if (args.length == 0 || args.length > 1) {
                if (!sender.hasPermission("motd.skontrolovat")) {
                    sender.sendMessage(ChatUtil.format("&cNemáte na to povolenie!"));
                    return true;
                }
                sender.sendMessage(ChatUtil.format("&5---------------&fV hre MOTD&5---------------"));
                if (sender instanceof Player) {
                    Player p = (Player)sender;
                    for (String s1 : SpravcaSuborov.getConfigFile().getStringList("Pridat-sa-do-hry-MOTD.Spravy"))
                        p.sendMessage(ChatUtil.format(Premenne.replaceVariables(s1, p)));
                } else {
                    for (String s1 : SpravcaSuborov.getConfigFile().getStringList("Pridat-sa-do-hry-MOTD.Spravy"))
                        sender.sendMessage(ChatUtil.format(s1));
                }
                String motd1 = SpravcaSuborov.getConfigFile().getString("Server-MOTD.Linka-1");
                String motd2 = SpravcaSuborov.getConfigFile().getString("Server-MOTD.Linka-2");
                sender.sendMessage("");
                sender.sendMessage(ChatUtil.format("&eServer MOTD Linka 1 &6" + motd1));
                sender.sendMessage(ChatUtil.format("&eServer MOTD Linka 2 &6" + motd2));
                return true;
            }
            if (args.length == 1 && (
                    args[0].equalsIgnoreCase("znovu-nacitat") || args[0].equalsIgnoreCase("rl"))) {
                if (!sender.hasPermission("motd.znovunacitat")) {
                    sender.sendMessage(ChatUtil.format("&cNemáte na to povolenie!"));
                    return true;
                }
                try {
                    Motd.getInstance().reloadConfig();
                    sender.sendMessage(ChatUtil.format("&aÚspešná konfigurácia opätovného načítania."));
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(ChatUtil.format("&cOpätovné načítanie konfigurácie zlyhalo."));
                    e.printStackTrace();
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("nastavservermotd")) {
            if (!sender.hasPermission("motd.nastavit")) {
                sender.sendMessage(ChatUtil.format("&cNemáte na to povolenie!"));
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatUtil.format("&e/nastavservermotd [riadok] [motd]"));
                sender.sendMessage(ChatUtil.format("&cVyberte prosím, ktorý riadok chcete nastaviť!"));
                return true;
            }
            int line = 1;
            try {
                line = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex) {
                sender.sendMessage(ChatUtil.format("&e/nastavservermotd [riadok] [motd]"));
                sender.sendMessage(ChatUtil.format("&cVyžaduje sa číslo!"));
                return true;
            }
            if (line < 1 || line > 2) {
                sender.sendMessage(ChatUtil.format("&e/nastavservermotd [riadok] [motd]"));
                sender.sendMessage(ChatUtil.format("&cArgument [riadok] akceptuje iba hodnoty 1 a 2."));
                return true;
            }
            if (args.length == 1) {
                sender.sendMessage(ChatUtil.format("&e/nastavservermotd [line] [motd]"));
                sender.sendMessage(ChatUtil.format("&cZadajte správu!"));
                return true;
            }
            StringBuilder str = new StringBuilder();
            for (int i = 1; i < args.length; i++)
                str.append(String.valueOf(args[i]) + ((i == args.length - 1) ? "" : " "));
            String motd = str.toString();
            SpravcaSuborov.getConfigFile().set("Server-MOTD.Linka-" + line, motd);
            Motd.getInstance().saveConfig();
            Motd.getInstance().reloadConfig();
            String newMotd = SpravcaSuborov.getConfigFile().getString("Server-MOTD.Linka-" + line);
            sender.sendMessage(ChatUtil.format("&eNový server MOTD (riadok " + line + ") &6" + newMotd));
            return true;
        }
        return true;
    }
}
