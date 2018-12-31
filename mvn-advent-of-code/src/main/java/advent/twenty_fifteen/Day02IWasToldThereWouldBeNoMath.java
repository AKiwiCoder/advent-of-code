package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.twenty_fifteen.support.Parcel;
import advent.utilities.FileUtilities;

import java.util.List;


public class Day02IWasToldThereWouldBeNoMath implements DailyProblem<Integer, Integer> {

    private final int part1Answer;
    private final int part2Answer;

    private static int surfaceAreaOfSmallestSize(Parcel parcel) {
        int a = parcel.getHeight() * parcel.getWidth();
        int b = parcel.getHeight() * parcel.getLength();
        int c = parcel.getLength() * parcel.getWidth();
        return Math.min(a, Math.min(b, c));
    }

    private static int surfaceArea(Parcel parcel) {
        return 2 * parcel.getLength() * parcel.getWidth() + 2 * parcel.getWidth() * parcel.getHeight() + 2 * parcel.getHeight() * parcel.getLength();
    }

    private static int lengthToWrap(Parcel parcel) {
        int a = 2 * parcel.getHeight() + 2 * parcel.getWidth();
        int b = 2 * parcel.getHeight() + 2 * parcel.getLength();
        int c = 2 * parcel.getLength() + 2 * parcel.getWidth();
        return Math.min(a, Math.min(b, c));
    }

    private static int lengthOfBow(Parcel parcel) {
        return parcel.getWidth() * parcel.getLength() * parcel.getHeight();
    }

    public Day02IWasToldThereWouldBeNoMath(String filename) {
        List<Parcel> parcels = FileUtilities.readLines(filename, Parcel::PARSE);

        this.part1Answer = parcels.stream().map(p -> surfaceArea(p) + surfaceAreaOfSmallestSize(p)).reduce(0, (a, b) -> a + b);
        this.part2Answer = parcels.stream().map(p -> lengthToWrap(p) + lengthOfBow(p)).reduce(0, (a, b) -> a + b);;
    }

    @Override
    public Integer getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Integer getPart2Answer() {
        return part2Answer;
    }
}
