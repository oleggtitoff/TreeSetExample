package ua.training.model.entity;

import java.util.LinkedList;
import java.util.List;

class SimpleTree<E> {
    private Leaf<E> root;
    private List<E> list;
    private int size = 0;

    public SimpleTree() {
        list = new LinkedList<>();
        root = new Leaf<>(null);
    }

    class Leaf<E> implements Comparable<E> {
        private Leaf<E> right;
        private Leaf<E> left;
        private E element;

        public Leaf(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        @Override
        public int compareTo(Object obj) {
            Leaf<E> node = (Leaf<E>) obj;

            return this.hashCode() - node.hashCode();
        }

        @Override
        public int hashCode() {
            return 31 * 17 + element.hashCode();
        }
    }
}
