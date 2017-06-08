package com.hutatar.traits


/*

polymorphism;
pattern matching in the base trait; and
pattern matching in an external object (as in the Diner example above).
 */
class FelinesBare {
  sealed trait Feline
  final case class Lion() extends Feline
  final case class Tiger() extends Feline
  final case class Panther() extends Feline
  final case class Cat(favouriteFood: String) extends Feline

  sealed trait Food
  final case object Antelope extends Food
  final case object TigerFood extends Food
  final case object Licorice extends Food
  final case class CatFood(food: String) extends Food
}

class FelinesPolimorhism {

  sealed trait Feline {
    def dinner: Food
  }
  final case class Lion() extends Feline {
    override def dinner: Food = Antelope
  }
  final case class Tiger() extends Feline {
    override def dinner: Food = TigerFood
  }
  final case class Panther() extends Feline {
    override def dinner: Food = Licorice
  }
  final case class Cat(favouriteFood: String) extends Feline {
    override def dinner: Food = CatFood(favouriteFood)
  }

  sealed trait Food
  final case object Antelope extends Food
  final case object TigerFood extends Food
  final case object Licorice extends Food
  final case class CatFood(food: String) extends Food
}

class FelinesPatternMatching {

  sealed trait Feline {
    def dinner: Food =
      this match {
        case Lion() => Antelope
        case Tiger() => TigerFood
        case Panther() => Licorice
        case Cat(favouriteFood) => CatFood(favouriteFood)
      }
  }

  object Dinner {
    def dinner(feline: Feline): Food =
      feline match {
        case Lion() => Antelope
        case Tiger() => TigerFood
        case Panther() => Licorice
        case Cat(food) => CatFood(food)
      }
  }

  final case class Lion() extends Feline {
    override def dinner: Food = Antelope
  }
  final case class Tiger() extends Feline {
    override def dinner: Food = TigerFood
  }
  final case class Panther() extends Feline {
    override def dinner: Food = Licorice
  }
  final case class Cat(favouriteFood: String) extends Feline {
    override def dinner: Food = CatFood(favouriteFood)
  }

  sealed trait Food
  final case object Antelope extends Food
  final case object TigerFood extends Food
  final case object Licorice extends Food
  final case class CatFood(food: String) extends Food
}
