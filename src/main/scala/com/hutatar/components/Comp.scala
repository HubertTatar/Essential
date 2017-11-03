package com.hutatar.components

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

trait Time {
  def getTime(): String
}

trait RecipeFinder {
  def findRecipe(dish: String): String
}

trait Cooker {
  def cook(what: String): Food
}

case class Food(name: String)

trait TimeComponent {
  val time: Time

  class TimeImpl extends Time {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    override def getTime(): String = s"The time is :${LocalDateTime.now().format(formatter)}"
  }
}

trait RecipeComponent {
  val recipe: RecipeFinder

  class RecipeFinderImpl extends RecipeFinder {
    override def findRecipe(dish: String): String = dish match {
      case "chips" => "Fry the potatoes for 10 minutes"
      case "fish"  => "Clean the fish and put it in oven for 10 minutes"
      case "sandwitch" => "Smth"
      case _ => throw new IllegalArgumentException("boom")
    }
  }
}


trait CookingComponent {
  this: RecipeComponent =>
  val cooker: Cooker

  class CookerImpl extends Cooker {
    override def cook(what: String): Food = {
      val recipeText = recipe.findRecipe(what)
      Food(s"cooked ${what} using ${recipeText}")
    }
  }
}


class RobotRegistry extends TimeComponent with RecipeComponent with CookingComponent {
  override val time = new TimeImpl
  override val recipe = new RecipeFinderImpl
  override val cooker = new CookerImpl
}

class Robot extends RobotRegistry {
  def cook(what: String) = cooker.cook(what)
  def getTime() = time.getTime()
}

object RobotExample {
  def main(args: Array[String]): Unit = {
    val robot = new Robot
    println(robot.cook("fish"))
    println(robot.cook("chips"))
    println(robot.cook("sandwitch"))
  }
}