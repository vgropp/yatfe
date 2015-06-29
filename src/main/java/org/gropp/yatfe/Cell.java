package org.gropp.yatfe;

import java.util.Random;

public class Cell {

	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public void newValue() {
		int ran = new Random().nextInt(10);
		if (ran < 9) {
			value = 2;
		} else {
			value = 4;
		}
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
