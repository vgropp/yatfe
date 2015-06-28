package org.gropp.yatfe;

public class Cell {

	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public boolean isEmpty() {
		return value == null;
	}

	public boolean merge(Cell other) {
		if (other != null
				&& value != null
				&& !other.isEmpty()) {
			if (value.equals(other.getValue())) {
				value *= 2;
				other.setValue(null);
				return true;
			}
		}
		return false;
	}

}
