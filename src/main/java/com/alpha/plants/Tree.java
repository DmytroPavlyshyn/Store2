package com.alpha.plants;

import com.alpha.enums.Growing;

import java.time.LocalDateTime;
import java.util.Objects;

public class Tree extends Plant {
    String treeType;

    public Tree(int startingPrice, boolean isNative, double stemLength, Growing growing, String treeType) {
        super(startingPrice, isNative, stemLength, growing);
        this.treeType = treeType;
    }

    public String getTreeType() {
        return treeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tree)) return false;
        if (!super.equals(o)) return false;
        Tree tree = (Tree) o;
        return Objects.equals(getTreeType(), tree.getTreeType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTreeType());
    }

    @Override
    public String toString() {
        return treeType + "{"
                + super.toString() +
                "\n}\n";
    }
}
