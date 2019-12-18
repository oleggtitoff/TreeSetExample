package ua.training.model.entity;

import java.util.List;
import java.util.Optional;

public interface Tree<E> extends Iterable<E> {
    boolean add(E e);
    List<E> get();
    int size();
    Optional<SimpleTree<E>.Leaf<E>> find(E e);
}
