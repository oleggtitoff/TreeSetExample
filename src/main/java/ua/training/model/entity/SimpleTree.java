package ua.training.model.entity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class SimpleTree<E> implements Tree<E> {
    private Leaf<E> root;
    private List<E> list;
    private int size = 0;

    public SimpleTree() {
        list = new LinkedList<>();
        root = new Leaf<>(null);
    }

    @Override
    public boolean add(E e) {
        if (size == 0) {
            return initRootLeaf(e);
        }

        Leaf<E> newNode = new Leaf<>(e);
        Leaf<E> lastNode = search(root, newNode);
        int compare = lastNode.compareTo(newNode);

        if (compare == 0) {
            return false;
        } else if (compare < 0) {
            lastNode.right = newNode;
        } else {
            lastNode.left = newNode;
        }

        size++;
        return true;
    }

    private boolean initRootLeaf(final E e) {
        root.element = e;
        size++;
        return true;
    }

    private Leaf<E> search(final Leaf<E> oldLeaf, final Leaf<E> target) {
        int compare = oldLeaf.compareTo(target);

        if (compare < 0 && oldLeaf.right != null) {
            return search(oldLeaf.right, target);
        } else if (compare > 0 && oldLeaf.left != null) {
            return search(oldLeaf.left, target);
        } else if (compare == 0) {
            return target;
        }

        return oldLeaf;
    }

    @Override
    public List<E> get() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Leaf find(E e) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
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
