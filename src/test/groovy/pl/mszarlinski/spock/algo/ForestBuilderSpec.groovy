package pl.mszarlinski.spock.algo

import spock.lang.Specification
import spock.lang.Subject

/**
 * @author mszarlinski on 2016-04-28.
 */
class ForestBuilderSpec extends Specification {

    @Subject
    def forestBuilder = new ForestBuilder()

    def 'Should build a tree for one node'() {
        given:
            List<NodeData> nodes = NodeDataFactory.createFromList([null]);
        when:
            Forest forest = forestBuilder.build(nodes);
        then:
            forest.roots.size() == 1

            Node root = forest.roots[0]
            root.id == 0
            root.parent == null
            root.children.isEmpty()
    }

    def 'Should build a tree for a list'() {
        given:
            List<NodeData> nodes = NodeDataFactory.createFromList([2, 4, 1, null, 3]); // 3 <- 4 <- 1 <- 2 <- 0
        when:
            Forest forest = forestBuilder.build(nodes);
        then:
            forest.roots.size() == 1

            Node root = forest.roots[0]
            root.id == 3
            root.parent == null
            root.children.size() == 1
    }

    def 'Should build a forest containg many trees'() {
        given:
            List<NodeData> nodes = NodeDataFactory.createFromList([null, 0, 0, 2, null, 4, 4, 4, null]);
        when:
            Forest forest = forestBuilder.build(nodes);
        then:
            forest.roots.size() == 3

            Node firstRoot = forest.roots[0]
            firstRoot.id == 0
            firstRoot.children.collect { n -> n.id } containsAll([1, 2])
            firstRoot.children.collect { n -> n.parent.id } containsAll(0)
            firstRoot.children.collect { n -> n.children.size() } containsAll([0, 1])

            Node secondRoot = forest.roots[1]
            secondRoot.id == 4
            secondRoot.children.collect { n -> n.id } containsAll([5, 6, 7])
            secondRoot.children.collect { n -> n.parent.id } containsAll(4)
            secondRoot.children.collect { n -> n.children.size() } containsAll([0, 0, 0])

            Node thirdRoot = forest.roots[2]
            thirdRoot.id == 8
            thirdRoot.children.isEmpty()
    }
}
