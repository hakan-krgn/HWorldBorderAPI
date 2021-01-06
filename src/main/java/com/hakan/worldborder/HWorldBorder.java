package com.hakan.worldborder;

import org.bukkit.entity.Player;

import java.util.List;

public interface HWorldBorder {

    void send(Player player);

    void remove(Player player);

    void update();

    int getSize();

    void setSize(int size);

    int getDamageAmount();

    void setDamageAmount(int damageAmount);

    int getDamageBuffer();

    void setDamageBuffer(int damageBuffer);

    int getWarningDistance();

    void setWarningDistance(int warningDistance);

    int getWarningTime();

    void setWarningTime(int warningTime);

    List<String> getPlayers();

    void setPlayers(List<String> players);
}