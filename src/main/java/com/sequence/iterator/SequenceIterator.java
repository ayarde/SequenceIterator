package com.sequence.iterator;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collectors;

@Data
public class SequenceIterator {
    private Collection<Iterator<Comparable>> inputs;
    private int index = 0;
    ArrayList<Comparable> comparables;

    public SequenceIterator(Collection<Iterator<Comparable>> inputs) {
        this.inputs = inputs;
    }

    public boolean hashNext(){
        ArrayList<Comparable> list = getComparableList();
        return index < list.size() && list.get(index) != null;
    }

    public Comparable next(){
        ArrayList<Comparable> list = getComparableList();
        return list.get(index++);
    }

    ArrayList<Comparable> getComparableList(){

        if(this.comparables == null){
            this.comparables = new ArrayList<>();
            this.inputs.forEach((sequence)-> sequence.forEachRemaining(this.comparables::add));
            this.comparables = (ArrayList<Comparable>) this.comparables.stream().distinct()
                    .sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        }
        return this.comparables;
    }
}
