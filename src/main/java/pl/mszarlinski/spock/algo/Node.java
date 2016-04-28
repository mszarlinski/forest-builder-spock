package pl.mszarlinski.spock.algo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.MoreObjects;

/**
 * @author mszarlinski on 2016-04-28.
 */
@Data
public class Node {
    private final int id;

    private Node parent;

    private List<Node> children = new ArrayList<>();

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("parent", parent)
            .toString();
    }
}
