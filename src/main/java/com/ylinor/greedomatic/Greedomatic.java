package com.ylinor.greedomatic;

import com.ylinor.greedomatic.access.WallletDao;
import com.ylinor.greedomatic.beans.WalletBean;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.EventContextKey;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;

import javax.inject.Inject;
import javax.sql.ConnectionEvent;
import java.util.UUID;


@Plugin(id = "greedomatic", name = "Greedomatic", version = "0.0.1")
public class Greedomatic {


    private Logger logger;
    @Inject
    void setLogger(Logger logger) {
        this.logger = logger;
    }
    public Logger getLogger() {
        return logger;
    }


    private static Greedomatic instance;
    public static Greedomatic getInstance() {
        return instance;
    }

    @Inject
    private WalletAction walletAction;



    @Listener
    public void onServerStart(GameStartedServerEvent event){
        instance = this;
        logger.info("greedomatic loaded");
        walletAction.init();
    }

    @Listener
    public void OnPlayerConnection(ClientConnectionEvent.Join event){
        getLogger().info(event.toString());
        event.getContext().keySet().stream().forEach(eventContextKey -> getLogger().info(eventContextKey.getName()));
      /*  if (event.getContext().get(EventContextKeys.PLAYER).isPresent()){
           wallletAction.AddNewWallet(event.getContext().get(EventContextKeys.PLAYER).get());
        }*/


    }
}
