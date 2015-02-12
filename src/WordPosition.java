

public class WordPosition {
	public static final WordPosition UNDEFINED=new WordPosition(-1, -1);
	public int start;
	public int end;
	
	public WordPosition(int start, int end){
		this.start=start;
		this.end=end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + start;
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
		WordPosition other = (WordPosition) obj;
		if (end != other.end)
			return false;
		if (start != other.start)
			return false;
		return true;
	}
	
	
}
