package com.narxoz.rpg.arena;

public class ArenaFighter {

    protected String name;
    protected int health;
    protected int maxHealth;

    protected double dodgeChance;
    protected int blockRating;
    protected int armor;
    protected int attackPower;
    protected int potions;

    // 🔹 БАЗОВЫЙ конструктор
    public ArenaFighter(String name, int health) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
    }

    // 🔥 НУЖНЫЙ КОНСТРУКТОР (ИСПРАВЛЕНИЕ)
    public ArenaFighter(String name, int health,
                        double dodgeChance,
                        int blockRating,
                        int armor,
                        int attackPower,
                        int potions) {

        this.name = name;
        this.health = health;
        this.maxHealth = health;

        this.dodgeChance = dodgeChance;
        this.blockRating = blockRating;
        this.armor = armor;
        this.attackPower = attackPower;
        this.potions = potions;
    }

    public void takeDamage(int dmg) {
        health -= dmg;
        if (health < 0) health = 0;
    }

    public void heal(int amount) {
        if (potions <= 0) return;

        health += amount;
        if (health > maxHealth) health = maxHealth;
    }

    public void usePotion() {
        if (potions > 0) potions--;
    }

    public void modifyDodgeChance(double value) {
        dodgeChance += value;

        if (dodgeChance < 0) dodgeChance = 0;
        if (dodgeChance > 1) dodgeChance = 1;
    }

    public boolean isAlive() {
        return health > 0;
    }

    // getters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public double getDodgeChance() { return dodgeChance; }
    public int getBlockRating() { return blockRating; }
    public int getArmor() { return armor; }
    public int getAttackPower() { return attackPower; }
    public int getPotions() { return potions; }
}