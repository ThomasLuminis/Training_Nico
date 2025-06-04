package nl.test;

import java.util.Comparator;
import java.util.List;

public class Node {

	private List<Node> reachableNodes;

	public boolean canReach(Node target) {
		if (this == target) {
			return true;
		}
		if (reachableNodes == null || reachableNodes.isEmpty()) {
			return false;
		}

		if (reachableNodes.contains(target)) {
			return true;
		}

		return reachableNodes.stream()
												 .anyMatch(item -> item.canReach(target));
	}

	public int smallestHopsTo(Node target) {
		if (this == target) {
			return 0;
		}
		if (reachableNodes == null || reachableNodes.isEmpty()) {
			return 0;
		}

		if (reachableNodes.contains(target)) {
			return 1;
		}

		return 1 + reachableNodes.stream()
														 .map(n -> n.smallestHopsTo(target))
														 .min(Comparator.naturalOrder())
														 .orElse(0);
	}

	public List<Node> getReachableNodes() {
		return reachableNodes;
	}

	public void setReachableNodes(List<Node> reachableNodes) {
		this.reachableNodes = reachableNodes;
	}
}
