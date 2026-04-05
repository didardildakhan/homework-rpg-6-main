package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class ArmorHandler extends DefenseHandler {

    private int armorValue;

    public ArmorHandler(int armorValue) {
        this.armorValue = armorValue;
    }

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {

        int reduced = incomingDamage - armorValue;
        if (reduced < 0) reduced = 0;

        passToNext(reduced, target);
    }
}