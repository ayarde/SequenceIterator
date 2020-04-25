package com.sequence.iterator;

import java.io.Console;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SequenceIteratorMain {

    public static final String NUMBER_FROM_THE_BEGINNING_OF_THE_SEQUENCE = "Enter the number from the beginning of the sequence";
    public static final String FINAL_NUMBER_OF_THE_SEQUENCE = "Enter the final number of the sequence";

    public static Integer setNumberForSequence(String message){
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();
        Pattern pattern = Pattern.compile("\\d+");
        int number = 0;
        boolean isRight = true;

        while(isRight) {
            System.out.println(message);
            String stringNumber = scanner.nextLine();
            if (pattern.matcher(stringNumber).matches()) {
                number = Integer.parseUnsignedInt(stringNumber);
                isRight = false;
                System.out.println("The number is: " + number);
            } else {
                System.out.println("The value is wrong");
            }
        }
        return number;
    }

    private static Iterator<Comparable> createSequence(int start, int end){

        List<Comparable> sequence = IntStream.rangeClosed(start, end)
                .boxed().collect(Collectors.toList());

        System.out.println(sequence);

        return sequence.iterator();
    }

    public static  void  main (String[] args){

        Integer end;
        Integer start;
        boolean createSequence = true;
        Scanner in = new Scanner(System.in);
        ArrayList <Iterator<Comparable>> input = new ArrayList<>();

        while (createSequence) {
            System.out.println("Do you want to create a numbers sequence? Press Y or N");

            if(in.nextLine().equalsIgnoreCase("y")) {
                start = setNumberForSequence(NUMBER_FROM_THE_BEGINNING_OF_THE_SEQUENCE);
                end = setNumberForSequence(FINAL_NUMBER_OF_THE_SEQUENCE);

                input.add(createSequence(start, end));

            } else {
                createSequence = false;
            }
        }

        SequenceIterator sequenceIterator = new SequenceIterator(input);
        System.out.println("The number of sequences is: " + sequenceIterator.getInputs().size());

        while(sequenceIterator.hashNext()){
            System.out.println(sequenceIterator.next());
        }
    }
}
