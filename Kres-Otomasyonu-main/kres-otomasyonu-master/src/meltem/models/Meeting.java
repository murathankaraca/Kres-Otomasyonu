package meltem.models;

public class Meeting {
    private int meetingId;
    private String meetingTitle;
    private String meetingSummary;
    private String meetingDate;

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public String getMeetingSummary() {
        return meetingSummary;
    }

    public void setMeetingSummary(String meetingSummary) {
        this.meetingSummary = meetingSummary;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public Meeting(int id, String title, String summary, String date) {
        this.meetingId = id;
        this.meetingTitle = title;
        this.meetingSummary = summary;
        this.meetingDate = date;
    }

    public Meeting() {
        this.meetingId = -1;
        this.meetingTitle = "";
        this.meetingSummary = "";
        this.meetingDate = "";
    }
}
