package net.ghostrealms.voting;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import net.ghostrealms.lib.GhostMessage;
import net.ghostrealms.lib.UUIDLib;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

/**
 * Created by Cliff on 8/14/2015.
 */
public class VotingListener implements Listener
{
    @EventHandler
    public void onVote(VotifierEvent event)
    {
        Vote vote = event.getVote();

        String username = vote.getUsername();
        UUID playerUUID = UUIDLib.getID(vote.getUsername());
        String service = vote.getServiceName();

        String sql = "INSERT INTO `votes` (`player`, `uuid`, `service`) VALUES (?, ?, ?)";

        Voting.instance.data.write(sql, username, playerUUID.toString(), service);

        Bukkit.getPlayer(playerUUID).sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.AQUA + "Thank you for voting! Here is $300!");

        //pay player
        Voting.instance.econ.depositPlayer(Bukkit.getPlayer(playerUUID), 300);

        //broadcast
        GhostMessage.broadcast("&dThank you &b" + username + " &dfor voting!", true);

    }
}
