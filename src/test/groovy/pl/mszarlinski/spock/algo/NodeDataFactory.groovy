package pl.mszarlinski.spock.algo
/**
 * @author mszarlinski on 2016-04-28.
 */
class NodeDataFactory {

    static List<NodeData> createFromList(final List<Integer> parents) {

        List<NodeData> nodes = new ArrayList<>(parents.size());

        for (int index = 0; index < parents.size(); index++) {
            nodes.add(new NodeData(nodeId: index, parentId: parents[index]));
        }

        return nodes;
    }
}
