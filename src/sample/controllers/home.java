package sample.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sample.model.Attempt;
import sample.model.AttemptKind;

import java.awt.event.ActionEvent;

/**
 * Created by Ken on 9/12/2015.
 */
public class Home {

    @FXML
    private VBox container;

    @FXML
    private Label title;

    private Attempt mCurrentAttempt;
    private StringProperty mTimerText;
    private Timeline mTimeline;


    public Home() {
        mTimerText = new SimpleStringProperty();
        setTimerText(0);
    }

    public void setTimerText(String timerText) {
        this.mTimerText.set(timerText);
    }

    public void setTimerText(int remainingSeconds) {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        setTimerText(String.format("%02d:%02d", minutes, seconds));
    }

    public String getTimerText() {
        return mTimerText.get();
    }

    public StringProperty timerTextProperty() {
        return mTimerText;
    }

    private void prepareAttempt(AttemptKind kind) {
        clearAttemptStyles();
        mCurrentAttempt = new Attempt("", kind);
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(mCurrentAttempt.getRemainingSeconds());
        // TODO:kwa This is creating multiple timelines. We need to fix this!
        mTimeline = new Timeline();
        mTimeline.setCycleCount(kind.getTotalSeconds());
        mTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            mCurrentAttempt.tick();
            setTimerText(mCurrentAttempt.getRemainingSeconds());
        }));
    }

    public void playTimer() {
        mTimeline.play();
    }

    public void pauseTimer() {
        mTimeline.pause();
    }

    private void addAttemptStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyles() {
        for (AttemptKind kind : AttemptKind.values()) {
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }

    public void DEBUG(javafx.event.ActionEvent actionEvent) {
        System.out.println("DEBUGGING THE CODE");
    }


    public void handleRestart(javafx.event.ActionEvent actionEvent) {
        prepareAttempt(AttemptKind.FOCUS);
        playTimer();
    }
}
