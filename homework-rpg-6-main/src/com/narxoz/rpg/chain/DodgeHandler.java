package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;
import java.util.Random;

public class DodgeHandler extends DefenseHandler {

    private double dodgeOverride = -1; // если -1 → берем из героя
    private Random random;

    // ✔ стандартный (как в задании)
    public DodgeHandler() {
        this.random = new Random();
    }

    // 🔥 фикс твоей ошибки (чтобы работал new DodgeHandler(0.2, 42))
    public DodgeHandler(double dodgeChance, long seed) {
        this.dodgeOverride = dodgeChance;
        this.random = new Random(seed);
    }

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {

        double chance = (dodgeOverride >= 0) ? dodgeOverride : target.getDodgeChance();

        double roll = random.nextDouble();

        if (roll < chance) {
            System.out.println(target.getName() + " dodged the attack!");
            return; // стоп цепи
        }

        passToNext(incomingDamage, target);
    }
}