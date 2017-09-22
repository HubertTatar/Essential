package com.hutatar.aafp

import scala.concurrent.duration.{Duration, MINUTES}

class Bakery {

  case class Ingredient(name: String)
  case class Superstition()
  case class Love() extends Superstition
  case class Cookie()
  case class Batch[A]()

  def makeCoockies(ingredients: List[Ingredient]): Batch[Cookie] = {
    val cookieDough = mix(ingredients)
    val betterCookieDough = combine(cookieDough, love)
    val cookies = shapeIntoLittleCookies(betterCookieDough)
    bake(cookies, 350.DegreesFahrenheit, 10.Minutes)
  }

  def mix(list: List[Ingredient]) = {

  }

  def combine(ingredients: List[Bakery.this.Ingredient], value: Any) = {

  }

  def bake(coockies: List[Cookie], temperature: Temperture ,time: Duration) = {

  }

}
