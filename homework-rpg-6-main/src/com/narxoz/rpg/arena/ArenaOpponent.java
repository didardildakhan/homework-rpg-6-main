package com.narxoz.rpg.arena;

public class ArenaOpponent extends ArenaFighter {

    public ArenaOpponent(String name, int health) {
        super(name, health);
    }

    public ArenaOpponent(String name, int health, int attackPower) {
        super(name, health);
        this.attackPower = attackPower;
    }
}