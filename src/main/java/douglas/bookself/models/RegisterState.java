package douglas.bookself.models;

public enum RegisterState {
	PlanToStart ("Planeja começar"),
	Reading     ("Lendo"),
	OnHalt      ("Em pausa"),
	Finished    ("Terminado");

	private String displayText;

	private RegisterState(String displayText) {
		this.displayText = displayText;
	}

	public String getDisplayText() { return this.displayText; }
}
