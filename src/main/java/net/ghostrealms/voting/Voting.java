package net.ghostrealms.voting;

import net.ghostrealms.lib.Database;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Cliff on 8/14/2015.
 */
public class Voting extends JavaPlugin implements CommandExecutor
{
    public static Voting instance;
    public Database data;

    @Override
    public void onEnable()
    {
        instance = this;
        data = new Database("voting", this, Database.SQL.MYSQL);

        this.getServer().getPluginManager().registerEvents(new VotingListener(), this);


        //create voting table
        String sql = "CREATE TABLE IF NOT EXISTS `votes` (`id` INT (8) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "`player` TEXT NOT NULL, " +
                "`uuid` TEXT NOT NULL, " +
                "`time` TIMESTAMP NOT NULL DEFAULT NOW()," +
                "`service` TEXT NOT NULL)";

        data.write(sql);


        //handle /vote
        this.getCommand("vote").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        sender.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.GREEN + "Vote for the server and get awesome stuff!");
        sender.sendMessage(ChatColor.GRAY + "Site: " + ChatColor.AQUA + "http://topg.org/server-ghost-realms-id412850");
        sender.sendMessage(ChatColor.GRAY + "Site: " + ChatColor.AQUA + "https://funminecraftservers.com/s/131");
        sender.sendMessage(ChatColor.GRAY + "Site: " + ChatColor.AQUA + "http://minecraftservers.org/server/81610");
        sender.sendMessage(ChatColor.BLUE + "Hint: " + ChatColor.AQUA + "Vote on all 3 sites for an extra reward!!");
        return false;
    }
}
