package pl.mszarlinski.spock.algo;

import static com.google.common.collect.Maps.newHashMapWithExpectedSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mszarlinski on 2016-04-28.
 */
public class ForestBuilder {

    public Forest build(final List<NodeData> nodes) {

        final Map<Integer, Node> nodesRegistry = createNodesRegistry(nodes);

        final List<Node> roots = new ArrayList<>();

        nodes.stream().forEach(node -> {
            final Node forestNode = nodesRegistry.get(node.getNodeId());

            if (isRoot(node)) {
                roots.add(forestNode);
            } else {
                final Node parentNode = nodesRegistry.get(node.getParentId());
                parentNode.getChildren().add(forestNode);
            }
        });

        return new Forest(roots);
    }

    private boolean isRoot(final NodeData node) {
        return node.getParentId() == null;
    }

    private Map<Integer, Node> createNodesRegistry(final List<NodeData> nodes) {

        final Map<Integer, Integer> parents = newHashMapWithExpectedSize(nodes.size());
        final Map<Integer, Node> registry = newHashMapWithExpectedSize(nodes.size());
        nodes.stream().forEach(nodeData -> {
            final int nodeId = nodeData.getNodeId();
            final Node node = new Node(nodeId);
            registry.put(nodeId, node);
            parents.put(nodeId, nodeData.getParentId());
        });

        assignParents(registry, parents);
        return registry;
    }

    private void assignParents(final Map<Integer, Node> registry, final Map<Integer, Integer> parents) {
        registry.forEach((nodeId, node) -> node.setParent(registry.get(parents.get(nodeId))));
    }
}
