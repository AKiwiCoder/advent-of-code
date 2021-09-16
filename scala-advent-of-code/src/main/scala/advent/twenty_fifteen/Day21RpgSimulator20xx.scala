package advent.twenty_fifteen

import advent.common.DailyProblem

import scala.annotation.tailrec

case class Equipment(name: String, cost: Int, damage: Int, armor: Int)

case class Fighter(name: String, hp: Int, damage: Int, armor: Int)

case class EquipmentSet(cost: Int, damage: Int, armor: Int)

case class ResultCost(win : Int, lose : Int)

class Day21RpgSimulator20xx(filename: String) extends DailyProblem[Int, Int] {

  private val WEAPONS = Set( //
    Equipment("Dagger", 8, 4, 0), //
    Equipment("Shortsword", 10, 5, 0), //
    Equipment("Warhammer", 25, 6, 0), //
    Equipment("Longsword", 40, 7, 0), //
    Equipment("Greataxe", 74, 8, 0) //
  )

  private val ARMORS = Set( //
    Equipment("Empty Armor", 0, 0, 0), //
    Equipment("Leather", 13, 0, 1), //
    Equipment("Chainmail", 31, 0, 2), //
    Equipment("Splintmail", 53, 0, 3), //
    Equipment("Bandedmail", 75, 0, 4), //
    Equipment("Platemail", 102, 0, 5) //
  )

  private val LEFT_HAND_RINGS = Set( //
    Equipment("Empty Left Hand", 0, 0, 0), //
    Equipment("Damage +1", 25, 1, 0), //
    Equipment("Damage +2", 50, 2, 0), //
    Equipment("Damage +3", 100, 3, 0), //
    Equipment("Defense +1", 20, 0, 1), //
    Equipment("Defense +2", 40, 0, 2), //
    Equipment("Defense +3", 80, 0, 3) //
  )

  private val RIGHT_HAND_RINGS = Set( //
    Equipment("Empty Right Hand", 0, 0, 0), //
    Equipment("Damage +1", 25, 1, 0), //
    Equipment("Damage +2", 50, 2, 0), //
    Equipment("Damage +3", 100, 3, 0), //
    Equipment("Defense +1", 20, 0, 1), //
    Equipment("Defense +2", 40, 0, 2), //
    Equipment("Defense +3", 80, 0, 3) //
  )

  private def combine[A](xs: Traversable[Traversable[A]]): Seq[Seq[A]] = xs.foldLeft(Seq(Seq.empty[A])) {
    (x, y) => for (a <- x; b <- y) yield a :+ b
  }

  private def runSimulations(): ResultCost = {
    @tailrec
    def fight(attacker: Fighter, defender: Fighter): String = {
      val damage = Math.max(1, attacker.damage - defender.armor)
      if (damage >= defender.hp)
        return attacker.name
      fight(Fighter(defender.name, defender.hp - damage, defender.damage, defender.armor), attacker)
    }

    val boss = Fighter("Boss", 100, 8, 2)

    combine(Set(WEAPONS, ARMORS, LEFT_HAND_RINGS, RIGHT_HAND_RINGS)) //
      .map(equip => equip.foldLeft(EquipmentSet(0, 0, 0))((acc, e) => EquipmentSet(acc.cost + e.cost, acc.damage + e.damage, acc.armor + e.armor))) //
      .foldLeft(ResultCost(Integer.MAX_VALUE, Integer.MIN_VALUE))((acc, e) => {
        if (fight(Fighter("Player", 100, e.damage, e.armor), boss).equals("Player")) {
          // Player wins
          ResultCost(Math.min(acc.win, e.cost), acc.lose)
        } else {
          // Boss wins
          ResultCost(acc.win, Math.max(acc.lose, e.cost))
        }
      }
      )
  }

  override val part1Answer: Int = runSimulations().win
  override val part2Answer: Int = runSimulations().lose
}
