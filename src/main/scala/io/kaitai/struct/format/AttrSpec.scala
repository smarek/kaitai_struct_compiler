package io.kaitai.struct.format

import java.util.{List => JList, Map => JMap}

import io.kaitai.struct.Utils

import collection.JavaConversions._
import com.fasterxml.jackson.annotation.JsonProperty

class AttrSpec(
  @JsonProperty("id") val id: String,
  @JsonProperty("type") val dataType: String,
  @JsonProperty("process") _process: String,
  @JsonProperty("contents") val contents: Object,
  @JsonProperty("byte_size") _byteSize: String,
  @JsonProperty("size") _size: String,
  @JsonProperty("size_eos") val sizeEos: Boolean,
  @JsonProperty("if") _ifExpr: String,
  @JsonProperty("encoding") _encoding: String,
  @JsonProperty("repeat") _repeat: String,
  @JsonProperty("repeat-expr") _repeatExpr: String,
  @JsonProperty("terminator") _terminator: String,
  @JsonProperty("consume") _consume: String,
  @JsonProperty("include") _include: String,
  @JsonProperty("eos_error") _eosError: String
) {
  val byteSize = Option(_byteSize)
  val size = Option(_size)
  val ifExpr = Option(_ifExpr)
  val encoding = Option(_encoding)
  val repeat = Option(_repeat)
  val repeatExpr = Option(_repeatExpr)
  val terminator = Utils.strToOptInt(_terminator).getOrElse(0)

  val consume = boolFromStr(_consume, true)
  val include = boolFromStr(_include, false)
  val eosError = boolFromStr(_eosError, true)

  def isArray: Boolean = repeat.isDefined

  val process = ProcessExpr.fromStr(_process)

  private def boolFromStr(s: String, byDef: Boolean): Boolean = {
    s match {
      case "true" | "yes" | "1" => true
      case "false" | "no" | "0" | "" => false
      case null => byDef
    }
  }
}

object AttrSpec {
  def create(
              id: String = null,
              dataType: String = null,
              process: String = null,
              contents: Object = null,
              byteSize: String = null,
              size: String = null,
              sizeEos: Boolean = false,
              ifExpr: String = null,
              encoding: String = null,
              repeat: String = null,
              repeatExpr: String = null,
              terminator: String = null,
              consume: String = null,
              include: String = null,
              eosError: String = null
            ): AttrSpec = {
    new AttrSpec(
      id,
      dataType,
      process,
      contents,
      byteSize,
      size,
      sizeEos,
      ifExpr,
      encoding,
      repeat,
      repeatExpr,
      terminator,
      consume,
      include,
      eosError
    )
  }
}