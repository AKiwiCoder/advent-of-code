package advent.twenty_twentythree 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day05IfYouGiveASeedAFertilizer(filename : String) extends DailyProblem[Long, Long] {

  private val input = FileUtilities.readFile(filename)

  private val seeds = input.head.split(":")(1).split(" +").filter(_.nonEmpty).map(_.toLong).toList

  private def buildMap(line : String) : IndexedSeq[(Long, Long)] = {
    val nums = line.split(" ");
    val dstRangeStart = nums(0).toLong
    val srcRangeStart = nums(1).toLong
    val rangeLength = nums(2).toLong
    val result = (0L until rangeLength).map(c => (srcRangeStart + c) -> (dstRangeStart + c))
    result
  }

  case class MappingRange(src : Long, dst : Long, count : Long) {
    def isInRange(value : Long): Boolean = {
      value >= src && value < src + count
    }

    def doMap(value : Long) : Long = {
      dst + (value - src)
    }
  }

  private def buildRange(line : String) : MappingRange = {
    val nums = line.split(" ");
    val dstRangeStart = nums(0).toLong
    val srcRangeStart = nums(1).toLong
    val rangeLength = nums(2).toLong
    MappingRange(srcRangeStart, dstRangeStart, rangeLength)
  }

  def tryMapping(ranges : List[MappingRange], value : Long) : Long = {
    val matchedRange = ranges.find(_.isInRange(value))
    matchedRange match {
      case Some(m) => m.doMap(value)
      case None => value
    }
  }

  private val seedToSoilMap = input.slice(input.indexOf("seed-to-soil map:") + 1, input.indexOf("soil-to-fertilizer map:") - 1).map(buildRange)
  private val soilToFertilizerMap = input.slice(input.indexOf("soil-to-fertilizer map:") + 1, input.indexOf("fertilizer-to-water map:") - 1).map(buildRange)
  private val fertilizerToWaterMap = input.slice(input.indexOf("fertilizer-to-water map:")+1, input.indexOf("water-to-light map:")-1).map(buildRange)
  private val waterToLightMap = input.slice(input.indexOf("water-to-light map:")+1, input.indexOf("light-to-temperature map:")-1).map(buildRange)
  private val lightToTemperatureMap = input.slice(input.indexOf("light-to-temperature map:")+1, input.indexOf("temperature-to-humidity map:")-1).map(buildRange)
  private val temperatureToHumidityMap = input.slice(input.indexOf("temperature-to-humidity map:")+1, input.indexOf("humidity-to-location map:")-1).map(buildRange)
  private val humidityToLocationMap = input.drop(input.indexOf("humidity-to-location map:")+1).map(buildRange)

  private val seedToLocations =
    seeds.map(seed => tryMapping(seedToSoilMap, seed))
    .map(soil => tryMapping(soilToFertilizerMap, soil))
    .map(fertilizer => tryMapping(fertilizerToWaterMap ,fertilizer))
    .map(water => tryMapping(waterToLightMap ,water))
    .map(light => tryMapping( lightToTemperatureMap, light))
    .map(temperature => tryMapping(temperatureToHumidityMap, temperature))
    .map(location => tryMapping(humidityToLocationMap, location))

  case class SeedRange(start : Long, count : Long) {
    def process() : Long = {
      @tailrec
      def loop(index : Long, minLocation : Long) : Long = {
          if (index >= start + count) {
            minLocation
          } else {
            val soil = tryMapping(seedToSoilMap, index)
            val fertilizer = tryMapping(soilToFertilizerMap, soil)
            val water = tryMapping(fertilizerToWaterMap, fertilizer)
            val light = tryMapping(waterToLightMap, water)
            val temperature = tryMapping(lightToTemperatureMap, light)
            val humidity = tryMapping(temperatureToHumidityMap, temperature)
            val location = tryMapping(humidityToLocationMap, humidity)
            loop(index + 1, Math.min(minLocation, location))
          }
      }
      loop(start, Long.MaxValue)
    }
  }

  private val partTwoSeeds = {
    val seedBits = input.head.split(":")(1).trim().split(" +")
    (0 until seedBits.length / 2).map(c => SeedRange(seedBits(c*2).toLong, seedBits(c*2+1).toLong)).toList
  }

  override val part1Answer: Long = seedToLocations.min
  override val part2Answer: Long = partTwoSeeds.map(seedRange => seedRange.process()).min
}


