package com.hutatar

class Adder(val amount: Int) {
  def add(in: Int): Int = amount + in

  def apply(in: Int): Int = amount + in
}

object Adder {
  def apply(amount: Int): Adder = {
    return new Adder(amount)
  }
}
