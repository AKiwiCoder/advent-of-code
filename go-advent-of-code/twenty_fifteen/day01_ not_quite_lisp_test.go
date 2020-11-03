package twenty_fifteen

import "testing"

func TestDay01_Real(t *testing.T) {
	part1,part2 := Day01("../data/twenty_fifteen/Day01-NotQuiteLisp-input.txt")

	assertEquals(t, 74, part1)
	assertEquals(t, 1795, part2 )
}
