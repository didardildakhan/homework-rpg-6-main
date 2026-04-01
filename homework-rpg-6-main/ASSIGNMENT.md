# Homework 6: Grand Arena Tournament
## Behavioral Patterns — Chain of Responsibility + Command

---

## Background Story

After surviving the dungeon (Homework 5), our hero has been invited to compete in the
**Grand Arena Tournament** — the ultimate test of skill and strategy.

Two things set the arena apart from previous battles:

1. **Strategic planning** — before each round, the hero queues a sequence of actions
   (attack, heal, defend) as command objects. The hero can inspect the queue and undo
   queued actions before they execute. This is the **Command** pattern.

2. **Layered defense** — when the opponent strikes back, the incoming damage is not applied
   directly. Instead it travels through a chain of defense handlers: dodge check, block
   reduction, armor absorption, and finally HP damage. Each handler decides whether to
   absorb, reduce, or simply forward the damage. This is the **Chain of Responsibility** pattern.

Your task is to implement both patterns, connect them in a `TournamentEngine`, and demonstrate
them clearly in `Main.java`.

---

## Part 1 — Chain of Responsibility (35 points)

Implement the defense chain that processes incoming damage.

### `DefenseHandler` (abstract)
- Already has `setNext(DefenseHandler)` and `passToNext(int, ArenaFighter)` provided.
- You must implement `handle(int incomingDamage, ArenaFighter target)` in each subclass.

### Concrete Handlers

| Class | Behaviour | Stops chain? |
|-------|-----------|--------------|
| `DodgeHandler` | Random roll — if roll < `dodgeChance`, damage is fully absorbed | Yes, on success |
| `BlockHandler` | Reduces damage by `blockPercent` (0.0–1.0); passes remainder | No |
| `ArmorHandler` | Reduces damage by flat `armorValue`; passes remainder (min 0) | No |
| `HpHandler` | Applies final damage via `target.takeDamage()`; never passes further | Always |

### Requirements
- The chain must be built externally (in `TournamentEngine` and `Main.java`) using fluent `setNext()`.
- `HpHandler` must **never** call `passToNext`.
- `DodgeHandler` must **stop** the chain on a successful dodge.
- `BlockHandler` and `ArmorHandler` must **always** continue, even if the reduced damage is 0.

---

## Part 2 — Command Pattern (35 points)

Implement command objects that encapsulate hero actions and an invoker that manages them.

### Concrete Commands

| Class | `execute()` | `undo()` |
|-------|-------------|----------|
| `AttackCommand` | Deals `attackPower` damage to `ArenaOpponent` | Restores the exact `damageDealt` |
| `HealCommand` | Heals `ArenaFighter` by `healAmount` (if potions remain) | Removes the `actualHealApplied` |
| `DefendCommand` | Boosts `dodgeChance` by `dodgeBoost` | Removes the boost |

### `ActionQueue` (Invoker)

| Method | Behaviour |
|--------|-----------|
| `enqueue(ActionCommand)` | Adds the command to the end of the queue |
| `undoLast()` | Removes the last command without executing it |
| `executeAll()` | Executes all commands in order, then clears the queue |
| `getCommandDescriptions()` | Returns a snapshot list of `getDescription()` strings |

### Requirements
- `ActionQueue` must depend only on the `ActionCommand` interface — never on concrete command types.
- `undo()` on `AttackCommand` must use `damageDealt` (actual damage applied), not `attackPower`.
- `executeAll()` must leave the queue empty after running.

---

## Part 3 — TournamentEngine Integration (20 points)

Complete `TournamentEngine.runTournament()` so it uses **both patterns** together.

### Requirements
- Build the defense chain inside `runTournament()` using hero stats.
  - `new BlockHandler(hero.getBlockRating() / 100.0)` — note the int-to-double conversion.
- Use `ActionQueue` to queue hero actions each round; call `executeAll()` to run them.
- When the opponent attacks, route through the chain: `defenseChain.handle(opponent.getAttackPower(), hero)`.
  - **Do not** call `hero.takeDamage()` directly in the engine for opponent attacks.
  - **Do not** call `opponent.takeDamage()` directly — use `AttackCommand.execute()` instead.
- Include a max-round safeguard to prevent infinite loops.
- Return a `TournamentResult` with `winner`, `rounds`, and a `log` of round-by-round events.

---

## Part 4 — Main Demo + UML (10 points)

### Demo (`Main.java`)

Your demo must clearly show all three of the following:

1. **Command queue** — enqueue 3 actions, print the queue, call `undoLast()`, print again, then execute.
2. **Defense chain** — manually build a chain, send a fixed damage value, and print HP before and after.
3. **Full tournament** — run `TournamentEngine.runTournament()` and print the winner, round count, and log.

### UML Diagrams

Create two class diagrams (hand-drawn or tool-generated, included as image files or embedded in a PDF):

1. **Command Pattern** — show `ActionCommand` interface, three concrete commands, `ActionQueue` invoker, and the two receiver classes.
2. **Chain of Responsibility** — show `DefenseHandler` abstract class, four concrete handlers, and how the engine builds and starts the chain.

---

## Grading Rubric

| Part | Points | Key criteria |
|------|--------|-------------|
| Chain of Responsibility | 35 | All four handlers correct; chain stops/continues as specified; chain built externally |
| Command Pattern | 35 | Three commands with working execute/undo; `ActionQueue` manages queue correctly |
| TournamentEngine | 20 | Uses queue and chain correctly; no direct `takeDamage` calls for opponent attacks |
| Demo + UML | 10 | All three demo sections present; two UML diagrams |
| **Total** | **100** | |

---

## Common Pitfalls

- **Calling `hero.takeDamage()` directly in `TournamentEngine`** for opponent attacks — this bypasses the chain entirely. All opponent attacks must go through `defenseChain.handle()`.
- **Calling `opponent.takeDamage()` directly** — use `AttackCommand.execute()` instead.
- **Using `attackPower` instead of `damageDealt` in `AttackCommand.undo()`** — they differ when the opponent had less HP remaining than `attackPower`.
- **Calling `passToNext()` from `HpHandler`** — `HpHandler` is terminal; it must never forward damage.
- **Forgetting to clear the queue in `executeAll()`** — the next call to `enqueue` would re-execute old commands.
- **Integer division in chain building** — `hero.getBlockRating() / 100` is `0` for any value under 100. Use `hero.getBlockRating() / 100.0`.

---

## Academic Integrity

All code you submit must be your own work. You may discuss concepts with classmates, but
do not share or copy implementations. Cite any external resources you consult.
