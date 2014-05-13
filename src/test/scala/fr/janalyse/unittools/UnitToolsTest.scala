/*
 * Copyright 2011-2014 David Crosson, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package fr.janalyse.unittools


import org.scalatest.FunSuite
import org.scalatest.ShouldMatchers

import scala.math._

class SelfTest extends FunSuite with ShouldMatchers {

  def selfDurationTest(spec:String) = spec.toDuration.toDurationDesc should equal(spec)
  
  // -------------------------------------------------------------------------------------
  
  test("duration basics") {
    0.toDurationDesc should equal("0ms")
    1.toDurationDesc should equal("1ms")
    3600000.toDurationDesc should equal("1h")
    10L.toDurationDesc should equal("10ms")
    "0".toDuration should equal(0)
    "1ms".toDuration should equal(1)
    "1h".toDuration should equal(3600L*1000L)
  }
  
  test("duration negative values") {
    (-1).toDurationDesc should equal("-1ms")
    "-1h".toDuration should equal(-3600000)
    "-1h10m".toDuration should equal("-50m".toDuration)
    "1h-5s".toDuration should equal("59m55s".toDuration)
  }
  
  test("duration more") {
    val samples="7w1s"::"2d99ms"::"1w1d1h1m1s1ms"::"7w6d5h4m3s2ms"::Nil
    samples foreach { selfDurationTest _ }
  }
  
  test("duration even more") {
    selfDurationTest("-1d-2h-3m-4ms")
  }
  
  test("duration rewritten") {
    "1".toDuration.toDurationDesc should equal("1ms")
    "7d".toDuration.toDurationDesc should equal("1w")
    "60m".toDuration.toDurationDesc should equal("1h")
    "3600s".toDuration.toDurationDesc should equal("1h")
    "3600000ms".toDuration.toDurationDesc should equal("1h")
  }
  
  test("duration monkey tests") {
    "".toDuration.toDurationDesc should equal("0ms")
    "gloups".toDuration should equal(0)
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
  
  // -------------------------------------------------------------------------------------
  // NEW test linked to API additions and various enhancements
  
  test("basics") {
    (5.m).value should equal (60L*1000*5)
    (3.h).value should equal (3600L*1000*3)
    (1.m + 1.s).value should equal (61L*1000)
    (1.h + 10.m + 10.s).value should equal (3600L*1000+600*1000+10*1000)
  }
  
}
