package com.gullele.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Given two lists words, find those words in the second list that start with
 * any word in the first list.
 *
 * @version 1.0.0
 *
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
public class WordLists {
    public static void main(String args[]) {
		//@todo unit-test
        Processor processor = new Processor();
        List<String> first = Arrays.asList("one", "two", "three", "beg", "semantic", "semaphore", "same");
        List<String> second = Arrays.asList("threemusk", "zy", "semantic anti", "beggining", "oner");

        List<String> curated = processor.curatedWords(first, second);

        curated.forEach(x -> System.out.println(x));
    }
}

/**
 * Supporting class for the algorithm
 *
 */
class Processor {
    /**
     * First sort both, the best you can do is, at the cost of space, put them
     * to TreeSet, it will automatically sort those. Then pick a value from the
     * second list, compare it with the first value in the first list, if you
     * found the match, then add it the list to be returned and move to the next
     * element in the list pick the second element from the list and compare it
     * with still the the first element in the list if no match, then move to
     * the next element in the first list..
     * 
     * @param first
     * @param second
     * @return
     */
    public List<String> curatedWords(List<String> first, List<String> second) {

        /*
         * Using TreeSet here for the auto order. The hashset has complexity of
         * O(n) but it can't hold the sort, it is unpredictable. The TreeSet on
         * the other hand, will be using tree and it takes O(logn) for
         * add/search
         */
        // clean the first list with unique words only
        Set<String> unique = new TreeSet<String>();
        first.forEach(x -> unique.add(x)); // O(n) + O(logn)

        Set<String> uniqueSecond = new TreeSet<String>();
        second.forEach(x -> uniqueSecond.add(x)); // O(n) + O(logn)

        /*
         * Iterators are working forward only?? and hence it might be easier to
         * work with the list. Better way?
         */
        List<String> firstSorted = new ArrayList<>(unique); // O(n)
        firstSorted.forEach(x -> System.out.println(x));

        List<String> secondSorted = new ArrayList<>(uniqueSecond); // O(n)
        secondSorted.forEach(x -> System.out.println(x));

        List<String> curated = new ArrayList<>();

        // now both are ordered.
        int firstSize = 0; // unique.size();
        int secondSize = 0; // uniqueSecond.size();
        // the while will be taking roughly O(m+n)
        while (firstSize < firstSorted.size() && secondSize < secondSorted.size()) {
            /*
             * Check if the word in the second list starts with the the word in
             * the first list. Since they are sorted, each list will be examined
             * only once.
             */
            String firstWord = firstSorted.get(firstSize);
            String secondWord = secondSorted.get(secondSize);
            if (secondWord.startsWith(firstWord)) {
                curated.add(secondSorted.get(secondSize));
                secondSize++;
                continue;
            }

			/*
			 * Only move the first pointer when second word is higher than the first. Since both are sorted,
			 * if the second is higher, there is no point to the current value on the first list.
			 */
            if (secondWord.compareTo(firstWord) > 0) {
                firstSize++;
            } else {
                secondSize++;
            }
        }

        return curated;
    }
}
