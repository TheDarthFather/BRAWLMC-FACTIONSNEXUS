package brawl.factionsnexus;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.Factions;
import events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import util.NexusOperations;


public final class FactionsNexus extends JavaPlugin {

    NexusController nexusController;
    NexusOperations nexusOperations;

    @Override
    public void onEnable() {


        nexusController = new NexusController(getMagicAPI(),this, getFactionsInstance(), getBoardInstance());
        nexusOperations = new NexusOperations();

        writeDefaultConfig();
        registerListeners();

    }

    Board getBoardInstance(){
        Board board = Board.getInstance();
        if (board == null){
            return null;
        }
        return board;
    }

    Factions getFactionsInstance(){
        Factions factions = Factions.getInstance();
        if (factions == null){
            return null;
        }
        return factions;
    }

    MagicAPI getMagicAPI() {
        Plugin magicPlugin = Bukkit.getPluginManager().getPlugin("Magic");
        if (!(magicPlugin instanceof MagicAPI)) {
            return null;
        }

        return (MagicAPI)magicPlugin;
    }

    @Override
    public void onDisable() {

    }

    private void writeDefaultConfig()
    {
        this.saveDefaultConfig();
    }

    private void registerListeners()
    {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new FactionCreateListener(), this);
        pluginManager.registerEvents(new FactionSetHomeListener(), this);
        pluginManager.registerEvents(new FactionLeaveListener(),this);
        pluginManager.registerEvents(new FactionDisbandListener(), this);
        pluginManager.registerEvents(new BlockPlaceListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(),this);
        pluginManager.registerEvents(new FactionUnclaimListener(),this);
        pluginManager.registerEvents(new FactionUnclaimAllListener(),this);

    }

}
