package fr.janalyse

import scala.math._

package object unittools {
  
  // Implicit conversions
  implicit def number2Duration[N<%Number](d:N) = new Duration(d.longValue())
  implicit def string2Duration(str:String) = new Duration(desc2Duration(str))

  implicit def number2ByteSize[N<%Number](d:N) = new ByteSize(d.longValue())
  implicit def string2ByteSize(str:String) = new ByteSize(desc2Size(str))

  
  implicit def string2DurationHelper(desc:String) = new DurationHelper(desc2Duration(desc), desc)
  implicit def long2DurationHelper(value:Long) = new DurationHelper(value, duration2Desc(value))
  implicit def string2SizeHelper(desc:String) = new SizeHelper(desc2Size(desc), desc)
  implicit def long2SizeHelper(value:Long) = new SizeHelper(value, size2Desc(value))
  
  implicit def durationHelper2Long(helper:DurationHelper) = helper.toDuration
  implicit def sizeHelper2Long(helper:SizeHelper) = helper.toSize

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
    val re="""([+-]?\d+)([a-z]+)?"""r
    val values = re.findAllIn(desc) map {_ match {
        case re(n,null)=>n.toLong
        case re(n,unitName) => n.toLong*unitsMap(unitName)
      }
    }
    if (values.isEmpty) 0 else values reduceLeft {_+_}
  }

}
