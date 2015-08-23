package net.ghostrealms.voting;

import net.ghostrealms.lib.Database;
import net.ghostrealms.lib.GhostMessage;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Cliff on 8/14/2015.
 */
public class Voting extends JavaPlugin implements CommandExecutor
{
    public static Voting instance;
    public Database data;

    public Economy econ;

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

        //setup vault stuff
        if(!setupEconomy()) {
            this.getLogger().severe("Voting has been disabled because there was an issue with Vault!");
            this.getServer().getPluginManager().disablePlugin(this);
        }
    }

    boolean setupEconomy()
    {
        if(getServer().getPluginManager().getPlugin("Vault") == null)
        {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if(rsp == null)
        {
            return false;
        }

        econ = rsp.getProvider();
        return econ != null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player p = (Player) sender;
        GhostMessage.messagePlayer(p, "&aVote for the server and get awesome stuff!", true);
        GhostMessage.messagePlayer(p, "&7Site:&bhttp://topg.org/server-ghost-realms-id412850", true);
        GhostMessage.messagePlayer(p, "&7Site: &bhttps://funminecraftservers.com/s/131", true);
        GhostMessage.messagePlayer(p, "&7Site: &bhttp://minecraftservers.org/server/81610", true);
        GhostMessage.messagePlayer(p, "&9Hint: &bVote on all 3 sites for an extra reward!!", true);
        return true;
    }
}
