package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

import java.util.Random;

public class DodgeHandler extends DefenseHandler {

    private Random random = new Random();

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {

        double roll = random.nextDouble();

        if (roll < target.getDodgeChance()) {
            System.out.println(target.getName() + " dodged the attack!");
            return; // ❗ СТОП цепи
        }

        passToNext(incomingDamage, target);
    }
}