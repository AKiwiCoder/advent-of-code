package twenty_fifteen

import (
	"io/ioutil"
	"strconv"
	"strings"
)

func check(err error) {
	if err != nil {
		panic(err)
	}
}

func Day02(filename string) (int, int) {
	dat, err := ioutil.ReadFile(filename)
	check(err)

	part1 := 0
	part2 := 0
	for _, line := range strings.Split(string(dat), "\n") {
		bits := strings.Split(string(line), "x")
		l, err := strconv.Atoi(bits[0])
		check(err)
		w, err := strconv.Atoi(bits[1])
		check(err)
		h, err := strconv.Atoi(bits[2])
		check(err)

		part1 += 2*l*w + 2*w*h + 2*h*l + min(l*w, min(w*h, h*l))

		side1 := l + w
		side2 := l + h
		side3 := w + h

		part2 += min(2*side1, min(2*side2, 2*side3)) + l*w*h
	}

	return part1, part2
}
