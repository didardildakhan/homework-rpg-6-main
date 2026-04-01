# Quickstart Guide

## Prerequisites

- Java 17 or later
- Any IDE (IntelliJ IDEA, Eclipse, VS Code with Java extension)

## Setup

1. Open the `homework-rpg-6` folder in your IDE.
2. Mark `src` as the **Sources Root** (IntelliJ: right-click → Mark Directory as → Sources Root).
3. Read files in this order before writing any code:
   - `README.md`
   - `ASSIGNMENT.md`
   - `src/com/narxoz/rpg/hints/CHAIN_HINTS.md`
   - `src/com/narxoz/rpg/hints/COMMAND_HINTS.md`

## Compile and Run (PowerShell)

From the `homework-rpg-6` directory:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
java -cp out com.narxoz.rpg.Main
```

The project compiles from the skeleton before you add any code (all stubs return placeholder values).
Run it first to confirm the setup works.

## Recommended Implementation Order

Work in this sequence to build incrementally and test as you go:

1. **`HpHandler`** — the simplest handler; no randomness, no calculation, just apply damage. Start here.
2. **`ArmorHandler`** — flat damage reduction, always passes to next.
3. **`BlockHandler`** — percentage reduction, always passes to next.
4. **`DodgeHandler`** — random dodge roll, stops chain on success.
5. **Test the chain in `Main.java`** — build the chain manually and print HP before/after a fixed hit.
6. **`AttackCommand`** — implement `execute()` first (deal damage), then `undo()` (restore it).
7. **`HealCommand`** — implement `execute()` (heal + potion check), then `undo()`.
8. **`DefendCommand`** — implement `execute()` (boost dodge), then `undo()` (remove boost).
9. **`ActionQueue`** — implement all four methods; test enqueue/undo/executeAll in `Main.java`.
10. **`ArenaFighter` mutation methods** — add clamping to `takeDamage`, `heal`, `modifyDodgeChance`.
11. **`TournamentEngine.runTournament()`** — tie everything together.
12. **Finalize `Main.java`** — expand the demo to clearly prove all three sections.

## Checking Your Work

After completing all TODOs, your demo must show:
- The queue containing 3 entries, then 2 after `undoLast()`.
- HP changing (but less than the full incoming amount) in the chain demo.
- A multi-round tournament log ending with a named winner.
- `result.getWinner()` is a real name, not `"TODO"`.
