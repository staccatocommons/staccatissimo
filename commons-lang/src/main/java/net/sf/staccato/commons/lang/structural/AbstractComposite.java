/*
This program is free software; you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation; version 3 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.lang.structural;

import java.util.Arrays;
import java.util.List;

public class AbstractComposite<NodeType> {

	private final List<NodeType> nodes;

	public AbstractComposite(NodeType... nodes) {
		this(Arrays.asList(nodes));
	}

	public AbstractComposite(List<NodeType> nodes) {
		this.nodes = nodes;
	}

	protected List<NodeType> getNodes() {
		return nodes;
	}

}
