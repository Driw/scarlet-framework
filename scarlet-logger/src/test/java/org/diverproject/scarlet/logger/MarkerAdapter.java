package org.diverproject.scarlet.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Marker;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class MarkerAdapter implements Marker {

	@Getter
	private String name;
	private final List<Marker> childrens = new LinkedList<>();

	@Override
	public void add(Marker reference) {
		this.childrens.add(reference);
	}

	@Override
	public boolean remove(Marker reference) {
		return this.childrens.remove(reference);
	}

	@Override
	public boolean hasChildren() {
		return false;
	}

	@Override
	public boolean hasReferences() {
		return !this.childrens.isEmpty();
	}

	@Override
	public Iterator<Marker> iterator() {
		return this.childrens.iterator();
	}

	@Override
	public boolean contains(Marker other) {
		return this.childrens.contains(other);
	}

	@Override
	public boolean contains(String name) {
		return this.childrens.stream().anyMatch(marker -> Objects.equals(marker.getName(), name));
	}

}
