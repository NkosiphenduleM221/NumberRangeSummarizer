package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NumberRangeSummarizer implements numberrangesummarizerr.NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        String[] parts = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String part : parts) {
            try {
                numbers.add(Integer.parseInt(part.trim()));
            } catch (NumberFormatException e) {
                // ignore non-numeric values
            }
        }
        return numbers;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        List<Integer> sorted = new ArrayList<>(input);
        Collections.sort(sorted);
        List<String> ranges = new ArrayList<>();
        int start = sorted.get(0);
        int end = start;
        for (int i = 1; i < sorted.size(); i++) {
            int num = sorted.get(i);
            if (num == end + 1) {
                end = num;
            } else {
                if (start == end) {
                    ranges.add(Integer.toString(start));
                } else {
                    ranges.add(start + "-" + end);
                }
                start = num;
                end = num;
            }
        }
        // handle the last range
        if (start == end) {
            ranges.add(Integer.toString(start));
        } else {
            ranges.add(start + "-" + end);
        }
        return String.join(", ", ranges);
    }
}

