package com.desetude.necessities.modules.economy;

import com.desetude.modularity.injector.AutoRegister;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.List;

@AutoRegister
public class NecessitiesEconomy implements Economy {

    @Override
    public boolean isEnabled() {
        return true; //Economy is only registered if the Plugin is enabled
    }

    @Override
    public String getName() {
        return "Necessities Economy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return -1;
    }

    @Override
    public String format(double amount) {
        return "$" + amount;
    }

    @Override
    public String currencyNamePlural() {
        return "dollars";
    }

    @Override
    public String currencyNameSingular() {
        return "dollar";
    }

    @Override
    public boolean hasAccount(String playerName) {
        return true;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return true;
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return true;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return false;
    }

    @Override
    public double getBalance(String playerName) {
        return 10.0;
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return 10.0;
    }

    @Override
    public double getBalance(String playerName, String worldName) {
        return 10.0;
    }

    @Override
    public double getBalance(OfflinePlayer player, String worldName) {
        return 10.0;
    }

    @Override
    public boolean has(String playerName, double amount) {
        return amount <= 10.0;
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        return amount <= 10.0;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return amount <= 10.0;
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return amount <= 10.0;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        double balance = getBalance(playerName);
        if (has(playerName, amount)) {
            return new EconomyResponse(amount, balance, ResponseType.SUCCESS, "");
        }

        return new EconomyResponse(amount, balance, ResponseType.FAILURE, "Insufficient funds.");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        double balance = getBalance(player);
        if (has(player, amount)) {
            return new EconomyResponse(amount, balance, ResponseType.SUCCESS, "");
        }

        return new EconomyResponse(amount, balance, ResponseType.FAILURE, "Insufficient funds.");
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        double balance = getBalance(playerName, worldName);
        if (has(playerName, worldName, amount)) {
            return new EconomyResponse(amount, balance, ResponseType.SUCCESS, "");
        }

        return new EconomyResponse(amount, balance, ResponseType.FAILURE, "Insufficient funds.");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        double balance = getBalance(player);
        if (has(player, amount)) {
            return new EconomyResponse(amount, balance, ResponseType.SUCCESS, "");
        }

        return new EconomyResponse(amount, balance, ResponseType.FAILURE, "Insufficient funds.");
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        return new EconomyResponse(amount, getBalance(playerName), ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        return new EconomyResponse(amount, getBalance(player), ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return new EconomyResponse(amount, getBalance(playerName, worldName), ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return new EconomyResponse(amount, getBalance(player, worldName), ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Necessities Eco does not support bank accounts!");
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Necessities Eco does not support bank accounts!");
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Necessities Eco does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Necessities Eco does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Necessities Eco does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Necessities Eco does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Necessities Eco does not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Necessities Eco does not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Necessities Eco does not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Necessities Eco does not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Necessities Eco does not support bank accounts!");
    }

    @Override
    public List<String> getBanks() {
        return new ArrayList<>();
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return true;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return true;
    }

}
