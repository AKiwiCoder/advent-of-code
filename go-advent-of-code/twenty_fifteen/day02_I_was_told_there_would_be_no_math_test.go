package twenty_fifteen

import "testing"

func TestDay02_Real(t *testing.T) {
	part1,part2 := Day02("../data/twenty_fifteen/Day02-IWasToldThereWouldBeNoMath-input.txt")

	assertEquals(t, 1606483, part1)
	assertEquals(t, 3842356, part2 )
}

