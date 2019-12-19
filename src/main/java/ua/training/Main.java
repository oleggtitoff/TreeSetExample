package ua.training;

import ua.training.model.entity.SimpleTree;
import ua.training.model.entity.Tree;

public class Main {

    public static void main(String[] args) {
        Tree<Integer> integerTree = new SimpleTree<>();
        Tree<String> stringTree = new SimpleTree<>();

        integerTree.add(1);
        integerTree.add(13);
        integerTree.add(-21);
        integerTree.add(6);

        integerTree.get().forEach(System.out::println);

        System.out.println(integerTree.find(-21).get().getElement());
        System.out.println(integerTree.find(6).get().getElement());

        stringTree.add("fkd");
        stringTree.add("a");
        stringTree.add("abc");

        stringTree.get().forEach(System.out::println);

        System.out.println(stringTree.find("abc").get().getElement());
        System.out.println(stringTree.find("fkd").get().getElement());
    }

}
