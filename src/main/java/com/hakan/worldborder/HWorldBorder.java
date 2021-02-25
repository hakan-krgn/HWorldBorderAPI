package com.hakan.worldborder;

import org.bukkit.entity.Player;

import java.util.List;

public interface HWorldBorder {

    void send(Player player);

    void remove(Player player);

    void update();

    HBorderColor getColor();

    void setColor(HBorderColor hBorderColor);

    double getSize();

    void setSize(double size);

    double getDamageAmount();

    void setDamageAmount(double damageAmount);

    double getDamageBuffer();

    void setDamageBuffer(double damageBuffer);

    int getWarningDistance();

    void setWarningDistance(int warningDistance);

    int getWarningTime();

    void setWarningTime(int warningTime);

    List<String> getPlayers();

    void setPlayers(List<String> players);
}