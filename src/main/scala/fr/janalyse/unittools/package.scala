/*
 * Copyright 2011-2023 David Crosson, Inc.
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

package fr.janalyse

import scala.math._

package object unittools {
  
  // Implicit conversions
  implicit def number2Duration[N](d:N)(implicit ev: N => Number):Duration = new Duration(ev(d).longValue())
  implicit def string2Duration(str:String):Duration = new Duration(desc2Duration(str))

  implicit def number2ByteSize[N](d:N)(implicit ev: N => Number):ByteSize = new ByteSize(ev(d).longValue())
  implicit def string2ByteSize(str:String):ByteSize = new ByteSize(desc2Size(str))

  
  implicit def string2DurationHelper(desc:String):DurationHelper = new DurationHelper(desc2Duration(desc), desc)
  implicit def long2DurationHelper(value:Long):DurationHelper = new DurationHelper(value, duration2Desc(value))
  implicit def string2SizeHelper(desc:String):SizeHelper = new SizeHelper(desc2Size(desc), desc)
  implicit def long2SizeHelper(value:Long):SizeHelper = new SizeHelper(value, size2Desc(value))
  
  implicit def durationHelper2Long(helper:DurationHelper):Long = helper.toDuration()
  implicit def sizeHelper2Long(helper:SizeHelper):Long = helper.toSize()

  // Duration unit specification, milliseconds is the default/base unit
  private val durationUnits = List( 
      "w"->7*24L*3600L*1000L, "d"->24L*3600L*1000L,
      "h"->3600L*1000L, "m"->60*1000L, "s"->1000L, "ms"->1L)
  private val durationUnitsMap=durationUnits.toMap
  
  // Data size unit specification, bytes is the default/base unit
  private val sizeUnits = List(
      "tb"->pow(1024L,4).toLong, "gb"->pow(1024L,3).toLong,
      "mb"->pow(1024L,2).toLong, "kb"->1024L, "b"->1L)
  private val sizeUnitsMap = sizeUnits.toMap

  
  // Some defs just for "classic" invocation
  def duration2Desc(duration:Long):String = {
    value2Desc(value=duration, units=durationUnits)
  }
  def desc2Duration(desc:String):Long = {
    desc2Value(desc=desc.toLowerCase, unitsMap=durationUnitsMap)
  }

  def size2Desc(size:Long):String = {
    value2Desc(value=size, units=sizeUnits)
  }
  def desc2Size(desc:String):Long = {
    desc2Value(desc=desc.toLowerCase, unitsMap=sizeUnitsMap)
  }

  // Main methods to make raw data more sexy  
  private def value2Desc(value:Long, units:List[Tuple2[String,Long]]):String = {
    value2Desc(value=value, units=units,desc="") match {
      case "" => "0"+units.last._1
      case x  => x
    }
  }
  
  private def value2Desc(value:Long, units:List[Tuple2[String,Long]], desc:String):String = {
    units match {
      case Nil => desc
      case Tuple2(unitName, baseUnit)::tail =>
        val remain = value / baseUnit
        val ndesc = if (remain!=0) desc+remain+unitName else desc
        value2Desc(value=value-remain*baseUnit, desc=ndesc, units=tail)
    }
  }

  private def desc2Value(desc:String, unitsMap:Map[String,Long]):Long = {
    val re="""([+-]?\d+)([a-z]+)?""".r
    val values = re.findAllIn(desc) map {_ match {
        case re(n,null)=>n.toLong
        case re(n,unitName) => n.toLong*unitsMap(unitName)
      }
    }
    if (values.isEmpty) 0 else values reduceLeft {_+_}
  }

}
