package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaOpponent;

public class AttackCommand implements ActionCommand {

    private ArenaOpponent opponent;
    private int attackPower;
    private int damageDealt;

    public AttackCommand(ArenaOpponent opponent, int attackPower) {
        this.opponent = opponent;
        this.attackPower = attackPower;
    }

    @Override
    public void execute() {

        int before = opponent.getHealth();

        opponent.takeDamage(attackPower);

        int after = opponent.getHealth();

        damageDealt = before - after;
    }

    @Override
    public void undo() {
        opponent.heal(damageDealt);
    }

    @Override
    public String getDescription() {
        return "Attack for " + attackPower;
    }
}