# unittools [![Build Status][travisImg]][travisLink] [![License][licenseImg]][licenseLink] [![Maven][mavenImg]][mavenLink] [![Scaladex][scaladexImg]][scaladexLink] [![Codacy][codacyImg]][codacyLink] [![codecov][codecovImg]][codecovLink]
Some basic unit conversion operation. 
It Assumes that default units are milliseconds for durations, and bytes for data size.

In your build.sbt, add this :

`libraryDependencies += "fr.janalyse" %% "unittools" % version`

## Usage

Examples coming from the test cases :

```
  test("duration rewritten") {
    "1".toDuration.toDurationDesc should equal("1ms")
    "7d".toDuration.toDurationDesc should equal("1w")
    "60m".toDuration.toDurationDesc should equal("1h")
    "3600s".toDuration.toDurationDesc should equal("1h")
    "3600000ms".toDuration.toDurationDesc should equal("1h")
  }

  
  test("size basics") {
    0.toSizeDesc should equal("0b")
    1.toSizeDesc should equal("1b")
    "1mb".toSize should equal(pow(1024L,2))
    "10mb25kb".toSize should equal(10*pow(1024L,2)+25*1024L)
  }


  test("classes parameter usage") {
    import fr.janalyse.unittools._
    case class ExampleClass(howlong:DurationHelper, amount:SizeHelper)
    val example = ExampleClass(howlong="5h30m", amount="10mb")
    val howlong:Long = example.howlong
    val amount:Long  = example.amount
  }

  
  test("basics") {
    (5 m).value should equal (60L*1000*5)
    (3 h).value should equal (3600L*1000*3)
    (1.m + 1.s).value should equal (61L*1000)
    (1.h + 10.m + 10.s).value should equal (3600L*1000+600*1000+10*1000)
  }
```

[mavenImg]: https://img.shields.io/maven-central/v/fr.janalyse/unittools_2.13.svg
[mavenLink]: https://search.maven.org/#search%7Cga%7C1%7Cfr.janalyse.unittools

[scaladexImg]: https://index.scala-lang.org/dacr/unittools/unittools/latest.svg
[scaladexLink]: https://index.scala-lang.org/dacr/unittools

[licenseImg]: https://img.shields.io/github/license/dacr/unittools.svg
[licenseLink]: LICENSE

[codacyImg]: https://img.shields.io/codacy/a23d442ea78f40b08e016e2f2fff5709.svg
[codacyLink]: https://www.codacy.com/app/dacr/unittools/dashboard

[codecovImg]: https://img.shields.io/codecov/c/github/dacr/unittools/master.svg
[codecovLink]: http://codecov.io/github/dacr/unittools?branch=master

[travisImg]: https://img.shields.io/travis/dacr/unittools.svg
[travisLink]:https://travis-ci.org/dacr/unittools
