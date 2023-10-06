package sk.anubissvk.motdsystem.variables;

import sk.anubissvk.motdsystem.utils.ChatUtil;
import java.text.SimpleDateFormat;
import org.bukkit.entity.Player;

public class PremenneHrac {
    public static String replaceVariables(String string, Player player) {
        String newString = string;
        if (newString.contains("{ipaddress}"))
            newString = newString.replace("{ipaddress}", String.valueOf(player.getAddress().getAddress()));
        if (newString.contains("{player}"))
            newString = newString.replace("{player}", player.getName());
        if (newString.contains("{displayname}"))
            newString = newString.replace("{player}", player.getDisplayName());
        if (newString.contains("{uuid}"))
            newString = newString.replace("{uuid}", String.valueOf(player.getUniqueId()));
        if (newString.contains("{xp}"))
            newString = newString.replace("{xp}",
                    String.valueOf((int)Math.round(player.getExp() * 100.0D)));
        if (newString.contains("{xp_to_level}"))
            newString = newString.replace("{xp_level}", String.valueOf(player.getExpToLevel()));
        if (newString.contains("{xp_level}"))
            newString = newString.replace("{xp_level}", String.valueOf(player.getLevel()));
        if (newString.contains("{hunger}"))
            newString = newString.replace("{hunger}", String.valueOf(player.getFoodLevel()));
        if (newString.contains("{health}"))
            newString = newString.replace("{health}", String.valueOf(player.getHealth()));
        if (newString.contains("{maxhealth}"))
            newString = newString.replace("{maxhealth}", String.valueOf(player.getMaxHealth()));
        if (newString.contains("{isflying}"))
            newString = newString.replace("{isflying}", String.valueOf(player.isFlying()));
        if (newString.contains("{issneaking}"))
            newString = newString.replace("{issneaking}", String.valueOf(player.isSneaking()));
        if (newString.contains("{issprinting}"))
            newString = newString.replace("{issprinting}", String.valueOf(player.isSprinting()));
        if (newString.contains("{isblocking}"))
            newString = newString.replace("{isblocking}", String.valueOf(player.isBlocking()));
        if (newString.contains("{isdead}"))
            newString = newString.replace("{isdead}", String.valueOf(player.isDead()));
        if (newString.contains("{isinsidevehicle}"))
            newString = newString.replace("{isinsidevehicle}", String.valueOf(player.isInsideVehicle()));
        if (newString.contains("{isleashed}"))
            newString = newString.replace("{isleashed}", String.valueOf(player.isLeashed()));
        if (newString.contains("{isop}"))
            newString = newString.replace("{isop}", String.valueOf(player.isOp()));
        if (newString.contains("{issleeping}"))
            newString = newString.replace("{issleeping}", String.valueOf(player.isSleeping()));
        if (newString.contains("{world}"))
            newString = newString.replace("{world}", player.getWorld().getName());
        if (newString.contains("{locx}"))
            newString = newString.replace("{locx}", String.valueOf((int)player.getLocation().getX()));
        if (newString.contains("{locy}"))
            newString = newString.replace("{locy}", String.valueOf((int)player.getLocation().getY()));
        if (newString.contains("{locz}"))
            newString = newString.replace("{locz}", String.valueOf((int)player.getLocation().getZ()));
        if (newString.contains("{pitch}"))
            newString = newString.replace("{pitch}", String.valueOf((int)player.getLocation().getPitch()));
        if (newString.contains("{yaw}"))
            newString = newString.replace("{yaw}", String.valueOf((int)player.getLocation().getYaw()));
        if (newString.contains("{firstplayed}")) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            newString = newString.replace("{firstplayed}", String.valueOf(format.format(Long.valueOf(player.getFirstPlayed()))));
        }
        if (newString.contains("{gamemode}"))
            newString = newString.replace("{gamemode}", String.valueOf(player.getGameMode()));
        if (newString.contains("{iteminhand:type}"))
            newString = newString.replace("{iteminhand:type}", player.getItemInHand().getType().toString());
        if (newString.contains("{iteminhand:maxstacksize}"))
            newString = newString.replace("{iteminhand:maxstacksize}",
                    String.valueOf(player.getItemInHand().getMaxStackSize()));
        if (newString.contains("{iteminhand:durability}"))
            newString = newString.replace("{iteminhand:durability}",
                    String.valueOf(player.getItemInHand().getDurability()));
        if (newString.contains("{iteminhand:amount}"))
            newString = newString.replace("{iteminhand:amount}", String.valueOf(player.getItemInHand().getAmount()));
        if (newString.contains("{iteminhand:enchantment}"))
            newString = newString.replace("{iteminhand:enchantment}",
                    player.getItemInHand().getEnchantments().toString());
        if (newString.contains("{iteminhand:hasgravity}"))
            newString = newString.replace("{iteminhand:hasgravity}",
                    String.valueOf(player.getItemInHand().getType().hasGravity()));
        if (newString.contains("{iteminhand:isblock}"))
            newString = newString.replace("{iteminhand:isblock}",
                    String.valueOf(player.getItemInHand().getType().isBlock()));
        if (newString.contains("{iteminhand:isburnable}"))
            newString = newString.replace("{iteminhand:isburnable}",
                    String.valueOf(player.getItemInHand().getType().isBurnable()));
        if (newString.contains("{iteminhand:isedible}"))
            newString = newString.replace("{iteminhand:isedible}",
                    String.valueOf(player.getItemInHand().getType().isEdible()));
        if (newString.contains("{iteminhand:isflammable}"))
            newString = newString.replace("{iteminhand:isflammable}",
                    String.valueOf(player.getItemInHand().getType().isFlammable()));
        if (newString.contains("{iteminhand:isoccluding}"))
            newString = newString.replace("{iteminhand:isoccluding}",
                    String.valueOf(player.getItemInHand().getType().isOccluding()));
        if (newString.contains("{iteminhand:isrecord}"))
            newString = newString.replace("{iteminhand:isrecord}",
                    String.valueOf(player.getItemInHand().getType().isRecord()));
        if (newString.contains("{iteminhand:issolid}"))
            newString = newString.replace("{iteminhand:issolid}",
                    String.valueOf(player.getItemInHand().getType().isSolid()));
        if (newString.contains("{iteminhand:istransparent}"))
            newString = newString.replace("{iteminhand:istransparent}",
                    String.valueOf(player.getItemInHand().getType().isTransparent()));
        if (newString.contains("{iteminhand:maxdurability}"))
            newString = newString.replace("{iteminhand:maxdurability}",
                    String.valueOf(player.getItemInHand().getType().getMaxDurability()));
        if (newString.contains("{iteminhand:displayname}"))
            if (!player.getItemInHand().getItemMeta().hasDisplayName()) {
                newString = newString.replace("{iteminhand:displayname}", player.getItemInHand().getType().toString());
            } else {
                newString = newString.replace("{iteminhand:displayname}",
                        ChatUtil.format(player.getItemInHand().getItemMeta().getDisplayName()));
            }
        if (newString.contains("{iteminhand:lore") &&
                player.getItemInHand().getItemMeta().hasLore()) {
            int i = 1;
            for (String s : player.getItemInHand().getItemMeta().getLore())
                newString = newString.replace("{iteminhand:lore" + i + "}", ChatUtil.format(s));
        }
        return newString;
    }
}
