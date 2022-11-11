package meltem.view_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import meltem.models.Meeting;
import meltem.services.logging.Logger;

public class MeetingViewModel {
    public Meeting meeting;

    public ObservableValue<SimpleIntegerProperty> meetingId;
    public ObservableValue<SimpleStringProperty> meetingTitle;
    public ObservableValue<SimpleStringProperty> meetingSummary;
    public ObservableValue<SimpleStringProperty> meetingDate;


    public MeetingViewModel (
            int id,
            String name,
            String summary,
            String date
    ) {
        try {
            this.meetingId = (ObservableValue) new SimpleIntegerProperty(id);
            this.meetingTitle = (ObservableValue) new SimpleStringProperty(name);
            this.meetingSummary = (ObservableValue) new SimpleStringProperty(summary);
            this.meetingDate = (ObservableValue) new SimpleStringProperty(date);

            this.meeting = new Meeting(id, name, summary, date);
        }
        catch(NullPointerException ex) {
            Logger.LogError(ex.toString());
        }
    }

    public MeetingViewModel(
            Meeting meeting
    ) {
        try {

            this.meeting = meeting;

            this.meetingId = (ObservableValue) new SimpleIntegerProperty(meeting.getMeetingId());
            this.meetingTitle = (ObservableValue) new SimpleStringProperty(meeting.getMeetingTitle());
            this.meetingSummary = (ObservableValue) new SimpleStringProperty(meeting.getMeetingSummary());
            this.meetingDate = (ObservableValue) new SimpleStringProperty(meeting.getMeetingDate());
        }
        catch(NullPointerException ex) {
            Logger.LogError(ex.toString());
        }
    }

}
