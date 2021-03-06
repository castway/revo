package com.tomankiewicz.rafal.revolut_statement_handler;

/*
 * Helper class for holding data corresponding to two columns in a table.
 */

class TwoColumnHolder {

	private String first;
	private String second;
	
	TwoColumnHolder(String first, String second) {

		this.first = first;
		this.second = second;
	}
	
	String getFirstColumn() {
		return first;
	}
	
	String getSecondColumn() {
		return second;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TwoColumnHolder other = (TwoColumnHolder) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TwoColumnHolder [first=" + first + ", second=" + second + "]";
	}
	
	
}
