#include <iostream>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <math.h>

template <class T> int assertEquals(T expected, T actual) {
  printf("%d == %d", expected, actual);
  if (expected == actual) {
    printf("  Passed\n");
    return 0;
  }
		      
  printf("  FAILED\n");
  return -1;
}

int calculateFuelRequired(int mass) {
  return round(mass / 3) - 2;
}

int calculateFuelRequiredAccumulating(int mass) {
  int total = 0;
  for (int temp = calculateFuelRequired(mass); temp > 0; temp=calculateFuelRequired(temp)) {
    total += temp;
  }
  return total;
}

int main(int argv, char **argc) {
  FILE *file = fopen("data/twenty_nineteen/Day01-TheTyrannyOfTheRocketEquation-input.txt", "r");

  char buffer[1000];

  int part1 = 0;
  int part2 = 0;
  while (fgets(buffer, 1000, file) != NULL) {
    int moduleMass = atoi(buffer);

    part1 += calculateFuelRequired(moduleMass);
    part2 += calculateFuelRequiredAccumulating(moduleMass);
  }

  
  int rc = 0;
  printf("2019 Day 1\n");  
  printf("  Part 1: ");
  rc += assertEquals(part1, 3233481);
  printf("  Part 2: ");
  rc += assertEquals(part2, 4847351);
  
  return rc;
}
