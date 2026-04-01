# Student Checklist

Work through each phase in order. Check off items as you complete them.

---

## Phase 1 — Understanding

- [ ] Read `README.md`
- [ ] Read `ASSIGNMENT.md` in full
- [ ] Read `CHAIN_HINTS.md`
- [ ] Read `COMMAND_HINTS.md`
- [ ] Compile the skeleton project — it should compile without errors before you change anything
- [ ] Run `Main.java` — it should print the banners even before any TODOs are filled

---

## Phase 2 — Chain of Responsibility

- [ ] Implement `HpHandler.handle()` — apply damage, never call `passToNext`
- [ ] Implement `ArmorHandler.handle()` — flat reduction, always pass remainder
- [ ] Implement `BlockHandler.handle()` — percentage reduction, always pass remainder
- [ ] Implement `DodgeHandler.handle()` — roll random, stop chain on dodge, pass on miss
- [ ] Test the chain in `Main.java` chain demo section
  - [ ] HP before and after differ by a *reduced* amount (not the full 20)
  - [ ] Occasionally prints a dodge message (depending on seed)

---

## Phase 3 — Command Pattern

- [ ] Implement `AttackCommand.execute()` — deal damage, store `damageDealt`
- [ ] Implement `AttackCommand.undo()` — restore `damageDealt` (not `attackPower`)
- [ ] Implement `AttackCommand.getDescription()`
- [ ] Implement `HealCommand.execute()` — check potions, heal, store `actualHealApplied`
- [ ] Implement `HealCommand.undo()` — remove the heal that was applied
- [ ] Implement `HealCommand.getDescription()`
- [ ] Implement `DefendCommand.execute()` — boost dodge chance
- [ ] Implement `DefendCommand.undo()` — remove the boost
- [ ] Implement `DefendCommand.getDescription()`
- [ ] Implement `ActionQueue.enqueue()`
- [ ] Implement `ActionQueue.undoLast()` — removes without executing
- [ ] Implement `ActionQueue.executeAll()` — runs all, then clears queue
- [ ] Implement `ActionQueue.getCommandDescriptions()`
- [ ] Test queue in `Main.java` command demo section
  - [ ] Queue shows 3 items, then 2 after `undoLast()`, then runs without the removed command

---

## Phase 4 — ArenaFighter

- [ ] Fix `takeDamage()` — clamp `health` to minimum of 0
- [ ] Fix `heal()` — clamp `health` to `maxHealth`, guard against 0 potions
- [ ] Fix `modifyDodgeChance()` — clamp `dodgeChance` between 0.0 and 1.0
- [ ] Verify `isAlive()` returns correctly

---

## Phase 5 — TournamentEngine

- [ ] Build the defense chain with fluent `setNext()`
- [ ] Create an `ActionQueue` inside `runTournament()`
- [ ] Implement the round loop (enqueue → print → execute → opponent attacks via chain)
- [ ] Add a max-round safeguard (e.g. `maxRounds = 20`)
- [ ] Set `result.setWinner(...)` with the correct fighter's name
- [ ] Set `result.setRounds(round)`
- [ ] Confirm `result.getWinner()` is not `"TODO"` after a full run

---

## Phase 6 — Final Demo and UML

- [ ] Expand `Main.java` so the three demo sections all show meaningful output
- [ ] Create a **Command Pattern UML** diagram
  - [ ] Shows `ActionCommand` interface
  - [ ] Shows `AttackCommand`, `HealCommand`, `DefendCommand`
  - [ ] Shows `ActionQueue` invoker
  - [ ] Shows `ArenaFighter` and `ArenaOpponent` as receivers
- [ ] Create a **Chain of Responsibility UML** diagram
  - [ ] Shows `DefenseHandler` abstract class
  - [ ] Shows all four concrete handlers
  - [ ] Shows `TournamentEngine` as the chain builder
- [ ] Final compile with zero errors
- [ ] Final run — demo prints winner, rounds, and log with real names (no `"TODO"`)
