package twenty_fifteen

import (
	"io/ioutil"
)

func Day01(filename string) (int, int) {
	dat, err := ioutil.ReadFile(filename)
	if err != nil {
		panic(err)
	}

	floor := 0
	firstBasementIndex := -1
	for i, c := range dat {
		if c == '(' {
			floor++
		}
		if c == ')' {
			floor--
		}
		if floor == -1 && firstBasementIndex == -1 {
			firstBasementIndex = i + 1
		}
	}

	return floor, firstBasementIndex
}
