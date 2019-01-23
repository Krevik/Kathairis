package mod.krevik.kathairis.capability.hasenteredkathairis;

public class HasEnteredKathairis {

	private boolean wasInKathairisBefore;

	public boolean hasEnteredKathairis() {
		return wasInKathairisBefore;
	}

	public void setHasEnteredKathairis(boolean hasEnteredKathairis) {
		wasInKathairisBefore = hasEnteredKathairis;
	}

}
