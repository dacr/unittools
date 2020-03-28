/*
 * Copyright 2011-2020 David Crosson, Inc.
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

import scala.reflect.ClassTag

class DurationHelper(value:Long, desc:String) {
  def toDuration()=value
  def toDurationDesc()=desc
  override def toString=desc
}

class SizeHelper(value:Long, desc:String) {
  def toSize()=value
  def toSizeDesc()=desc
  override def toString=desc
}



object Duration {
  implicit def number2Duration[N](d:N)(implicit ev: N => Number) = new Duration(d.longValue())
  implicit def string2Duration(str:String) = new Duration(desc2Duration(str))
}

case class Duration(value:Long) {
  def ms = this
  def s  = Duration(value*1000)
  def m  = Duration(s.value*60)
  def h  = Duration(m.value*60)
  def d  = Duration(h.value*24)
  def w  = Duration(d.value*7)
  override def toString = duration2Desc(value)
  def +(that:Duration) = Duration(value+that.value)
  def -(that:Duration) = Duration(value-that.value)
  def apply(d:Duration) = this + d 
}


object ByteSize {
  implicit def number2ByteSize[N](d:N)(implicit ev: N => Number) = new ByteSize(d.longValue())
  implicit def string2ByteSize(str:String) = new ByteSize(desc2Size(str))
}

case class ByteSize(value:Long) {
  def b = this
  def kb = ByteSize(value*1024)
  def mb = ByteSize(kb.value*1024)
  def gb = ByteSize(mb.value*1024)
  def tb = ByteSize(gb.value*1024)
  override def toString = size2Desc(value)
  def +(that:ByteSize) = ByteSize(value+that.value)
  def -(that:ByteSize) = ByteSize(value-that.value)
}
