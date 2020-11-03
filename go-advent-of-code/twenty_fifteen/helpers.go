package twenty_fifteen

import "testing"

func assertEquals(t  *testing.T, expected int, actual int) {
	if expected != actual {
		t.Errorf("Assert failed incorrect, got: %d, want: %d.", actual, expected)
	}
}


func min(lhs int, rhs int) int {
	if lhs < rhs {
		return lhs
	}
	return rhs
}