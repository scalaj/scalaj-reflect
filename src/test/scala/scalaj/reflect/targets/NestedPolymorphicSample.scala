package scalaj.reflect.targets

abstract class NestedPolymorphicSample[T] {
  abstract class Inner {
    def sampleVal: T
    def method[T](x: T) = x
  }
}
