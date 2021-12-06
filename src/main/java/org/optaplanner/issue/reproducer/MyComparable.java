package org.optaplanner.issue.reproducer;

public class MyComparable<T extends Comparable<T>> implements Comparable<MyComparable<T>> {

    final T reference;

    public MyComparable(T reference) {
        this.reference = reference;
    }

    @Override
    public int compareTo(MyComparable<T> other) {
        return reference.compareTo(other.reference);
    }

    public boolean equals(Object other) {
        if (other instanceof MyComparable) {
            return compareTo((MyComparable<T>) other) == 0;
        }
        return false;
    }
}
