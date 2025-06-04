package nl.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class NodeTest {

	Node nodeA, nodeB, nodeC, nodeD, nodeE;

	@BeforeEach
	void setUp() {
		nodeA = new Node();
		nodeB = new Node();
		nodeC = new Node();
		nodeD = new Node();
		nodeE = new Node();

		nodeA.setReachableNodes(List.of(nodeB));
		nodeB.setReachableNodes(List.of(nodeC, nodeD));
		nodeC.setReachableNodes(List.of(nodeD));
		nodeD.setReachableNodes(List.of(nodeE));
	}

	@Test
	void can_A_Reach_B() {
		assertTrue(nodeA.canReach(nodeB));
	}

	@ParameterizedTest
	@MethodSource("nodes")
	void testAllNodes(Node source, Node target) {
		assertTrue(source.canReach(target));
	}

	@Test
	void smallestHopsToB() {
		assertThat(nodeA.smallestHopsTo(nodeB)).isEqualTo(1);
	}

	@Test
	void smallestHopsToItself() {
		assertThat(nodeA.smallestHopsTo(nodeA)).isZero();
	}

	@Test
	void smallestHopsToC() {
		assertThat(nodeA.smallestHopsTo(nodeC)).isEqualTo(2);
	}

	@Test
	void smallestHopsToE() {
		assertThat(nodeA.smallestHopsTo(nodeE)).isEqualTo(3);
	}

	@Test
	void smallestHopsFromEToA() {
		assertThat(nodeE.smallestHopsTo(nodeA)).isEqualTo(0);
	}

	private static Stream<Arguments> nodes() {
		Node nodeA = new Node();
		Node nodeB = new Node();
		Node nodeC = new Node();
		Node nodeD = new Node();
		Node nodeE = new Node();

		nodeA.setReachableNodes(List.of(nodeB));
		nodeB.setReachableNodes(List.of(nodeC, nodeD));
		nodeC.setReachableNodes(List.of(nodeD));
		nodeD.setReachableNodes(List.of(nodeE));

		return Stream.of(
						Arguments.of(nodeA, nodeB),
						Arguments.of(nodeB, nodeC),
						Arguments.of(nodeB, nodeD),
						Arguments.of(nodeD, nodeE),
						Arguments.of(nodeA, nodeE)
		);
	}


}