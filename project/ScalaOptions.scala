object ScalaOptions {
  val scalaCompileOptions: Seq[String] = List(
    "-target:jvm-1.8",
    "-deprecation", // Emit warning and location for usages of deprecated APIs
    "-encoding",
    "utf-8", // Specify character encoding used by source files
    "-feature", // Emit warning and location for usages of features that should be imported explicitely
    "-language:existentials", // Existential types can be written and inferred
    "-language:higherKinds", // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit function called views
    "-unchecked", // Enable additional warnings where generated code depends on assumptions
    "-Xlog-reflective-calls", // Prints any reflective call
    "-Xfuture", // Turn on future language features
    "-Xlint:nullary-unit", // Warn when a nullary method return Unit
    "-Xlint:private-shadow", // Warn when a private field (or class parameter) shadows a superclass field
    "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`
    "-Xlint:adapted-args", // Make sure we don't have a method with `Unit` type that's trying to return a value
    "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
    "-Ywarn-dead-code" // Warn when dead code is identified
  )

  val docOptions: Seq[String] = List("-groups", "-implicits")
}
