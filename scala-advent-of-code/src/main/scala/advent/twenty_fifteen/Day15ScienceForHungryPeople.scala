package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

case class Ingredient(name: String, capacity: Int, durability: Int, flavor: Int, texture: Int, calories: Int)

case class Recipe(ingredient: Ingredient, amount: Int)

class Day15ScienceForHungryPeople(filename: String) extends DailyProblem[Int, Int] {
  def parse(line: String): Ingredient = {
    val pattern = "([A-Za-z]+): capacity ([-]*[0-9]+), durability ([-]*[0-9]+), flavor ([-]*[0-9]+), texture ([-]*[0-9]+), calories ([-]*[0-9]+)".r
    line match {
      case pattern(name, capacity, durability, flavor, texture, calories) => Ingredient(name, capacity.toInt, durability.toInt, flavor.toInt, texture.toInt, calories.toInt)
    }
  }

  private val ingredients = FileUtilities.readFile(filename, parse)

  private def walk(remaining: List[Ingredient], usage: Int, spaceLeft: Int, recipe: List[Recipe], acc: List[List[Recipe]]): List[List[Recipe]] = {
    if (remaining.tail.isEmpty) {
      (Recipe(remaining.head, spaceLeft) :: recipe) :: acc
    } else {
      if (spaceLeft < 0) {
        Nil
      } else {
        walk(remaining.tail, 0, spaceLeft, Recipe(remaining.head, usage) :: recipe, acc) ::: walk(remaining, usage + 1, spaceLeft - 1, recipe, acc)
      }
    }
  }

  private val combinations = walk(ingredients, 0, 100, List(), List())

  private def multiply(recipe: Recipe): (Int, Int, Int, Int) = {
    (recipe.ingredient.capacity * recipe.amount, recipe.ingredient.durability * recipe.amount, recipe.ingredient.flavor * recipe.amount, recipe.ingredient.texture * recipe.amount)
  }

  private def sum(lhs: (Int, Int, Int, Int), rhs: (Int, Int, Int, Int)): (Int, Int, Int, Int) = {
    lhs match {
      case (a, b, c, d) => rhs match {
        case (e, f, g, h) => (a + e, b + f, c + g, h + d)
      }
    }
  }

  private def score(entry: (Int, Int, Int, Int)): Int = {
    entry match {
      case (a, b, c, d) => (if (a > 0) a else 0) * (if (b > 0) b else 0) * (if (c > 0) c else 0) * (if (d > 0) d else 0)
    }
  }

  private def calories(recipe : List[Recipe]) : Int = {
    recipe.foldLeft(0)((a,r) => a + r.amount * r.ingredient.calories)
  }

  override val part1Answer: Int = combinations.map(entry => score(entry.map(recipe => multiply(recipe)).foldLeft((0, 0, 0, 0))((a, e) => sum(a, e)))).max
  override val part2Answer: Int = combinations.filter(entry => calories(entry) == 500).map(entry => score(entry.map(recipe => multiply(recipe)).foldLeft((0, 0, 0, 0))((a, e) => sum(a, e)))).max
}
