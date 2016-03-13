package lovera.linha.comum;
//substituir por enuns
public enum Subset {
	
	S1(1),
	S2(2),
	S3(3);

	private int set;
	
	private Subset(int set) {
		this.set = set;
	}
	
	public int getSet() {
		return this.set;
	}
}
