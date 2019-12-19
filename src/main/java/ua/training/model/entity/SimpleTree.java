package ua.training.model.entity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

        newNode.parent = lastNode;
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
        return size;
    }

    @Override
    public Optional<Leaf<E>> find(E e) {
        Leaf<E> result = search(root, new Leaf<>(e));

        if (e.equals(result.element)) {
            return Optional.of(result);
        }
        return Optional.empty();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int count = 0;
            Iterator<Leaf<E>> iterator = new TreeIterator<>(root);

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public E next() {
                count++;
                return iterator.next().element;
            }
        };
    }

    private class TreeIterator<E> implements Iterator<Leaf<E>> {
        private Leaf<E> next;

        private TreeIterator(Leaf<E> root) {
            next = root;
            goToLeftmost();
        }

        private void goToLeftmost() {
            while (next.left != null) {
                next = next.left;
            }
        }

        @Override
        public boolean hasNext() {
            return next != null && next.element != null;
        }

        @Override
        public Leaf<E> next() {
            Leaf<E> leaf = next;

            if (next.right != null) {
                goRight();
            } else {
                goUp();
            }
            return leaf;
        }

        private void goRight() {
            next = next.right;
            while (next.left != null) {
                next = next.left;
            }
        }

        private void goUp() {
            while (true) {
                if (next.parent == null) {
                    next = null;
                    return;
                } else if (next.parent.left == next) {
                    next = next.parent;
                    return;
                }
                next = next.parent;
            }
        }
    }

    class Leaf<E> implements Comparable<E> {
        private Leaf<E> parent;
        private Leaf<E> right;
        private Leaf<E> left;
        private E element;

        Leaf(E element) {
            this.element = element;
        }

        @Override
        public int compareTo(Object obj) {
            Leaf<E> node = (Leaf<E>) obj;

            return this.hashCode() - node.hashCode();
        }
    }
}
