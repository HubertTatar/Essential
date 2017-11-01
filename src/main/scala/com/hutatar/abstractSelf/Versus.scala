package com.hutatar.abstractSelf

abstract class PrintData
abstract class PrintMaterial
abstract class PrintMedia

trait Printer {
  type Data <: PrintData
  type Material <: PrintMaterial
  type Media <: PrintMedia

  def print(data: Data, material: Material, media: Media) = s"Printing $data with $material on $data"
}

case class Paper() extends PrintMedia
case class Air() extends PrintMedia
case class Text() extends PrintData
case class Model() extends PrintData
case class Toner() extends PrintMaterial
case class Plastic() extends PrintMaterial

class LaserPrinter extends Printer {
  override type Media = Paper
  override type Material = Toner
  override type Data = Text
}

class ThreeDPrinter extends Printer {
  override type Media = Air
  override type Material = Plastic
  override type Data = Model
}

object PrintExample {
  def main(args: Array[String]): Unit = {
    val a = new ThreeDPrinter()
    val b = new LaserPrinter()

    println(a.print(Model(), Plastic(), Air()))
    println(b.print(Text(), Toner(), Paper()))

    val genericLaser = new GenericLaserPrinter[Text, Toner, Paper]
    val genericThreeD = new GenericThreeDPrinter[Model, Plastic, Air]
    System.out.println(genericLaser.print(Text(), Toner(), Paper()))
    System.out.println(genericThreeD.print(Model(), Plastic(), Air()))
  }
}

//generic way
trait GenericPrinter[Data <: PrintData, Material <: PrintMaterial, Media <: PrintMedia] {
  def print(data: Data, material: Material, media: Media) = s"Printing $data, with $material on $media"
}

class GenericLaserPrinter[Data <: Text, Material <: Toner, Media <: Paper] extends GenericPrinter[Data, Material, Media]
class GenericThreeDPrinter[Data <: Model, Material <: Plastic, Media <: Air] extends GenericPrinter[Data, Material, Media]