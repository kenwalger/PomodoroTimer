package sample.model;

/**
 * Created by Ken on 9/12/2015.
 */
public enum AttemptKind {
    FOCUS(25 * 60, "Focus time"),
    BREAK(5 * 60, "Break time");

    private int mTotalSeconds;
    private String mDisplayName;

    AttemptKind(int totalSeconds, String displayName) {
        mTotalSeconds = totalSeconds;
        mDisplayName = displayName;
    }

    public int getTotalSeconds() {
        return mTotalSeconds;
    }

    public String getDisplayName() {
        return null;
    }
}
