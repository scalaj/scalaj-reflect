package scalaj.reflect.targets

abstract class PolymorphicSample[T] {
  def sampleVal: T
  def method[T](x: T) = x
}

class polymorphicSub extends PolymorphicSample[Int] {
  def sampleVal: Int = 42
}
