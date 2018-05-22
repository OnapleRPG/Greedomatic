package com.ylinor.greedomatic;

import com.ylinor.greedomatic.access.WallletDao;
import org.spongepowered.api.entity.living.player.Player;

import javax.inject.Inject;
import javax.inject.Singleton;



@Singleton
public class WalletAction {

    public WalletAction() {
    }

    @Inject
    private WallletDao wallletDao;
    public void AddNewWallet(Player player){

    }

    public void init(){
        wallletDao.createTableIfNotExist();
    }
}
