package com.hutatar.patterns.oop

trait Car
class Audi extends Car
class Skoda extends Car
class Dacia extends Car
class Fiat extends Car

object Car {
  def apply(car: String): Car = car match {
    case "audi" => new Audi()
    case "skoda" => new Skoda()
    case "dacia" => new Dacia()
    case "fiat" => new Fiat()
    case _ => throw new IllegalArgumentException()
  }
}