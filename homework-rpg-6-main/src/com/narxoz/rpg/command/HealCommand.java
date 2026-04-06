package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class HealCommand implements ActionCommand {

    private ArenaFighter fighter;
    private int healAmount;
    private int actualHeal;

    public HealCommand(ArenaFighter fighter, int healAmount) {
        this.fighter = fighter;
        this.healAmount = healAmount;
    }

    @Override
    public void execute() {

        if (fighter.getPotions() <= 0) return;

        int before = fighter.getHealth();

        fighter.heal(healAmount);

        int after = fighter.getHealth();

        actualHeal = after - before;

        fighter.usePotion();
    }

    @Override
    public void undo() {
        fighter.takeDamage(actualHeal);
    }

    @Override
    public String getDescription() {
        return "Heal " + healAmount;
    }
}