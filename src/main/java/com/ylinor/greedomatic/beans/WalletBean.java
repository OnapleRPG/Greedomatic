package com.ylinor.greedomatic.beans;

public class WalletBean {

    private String player;
    private int money;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public WalletBean(String player) {
        this.player = player;
        this.money = 0;
    }

    public WalletBean(String player, int money) {
        this.player = player;
        this.money = money;
    }
}
