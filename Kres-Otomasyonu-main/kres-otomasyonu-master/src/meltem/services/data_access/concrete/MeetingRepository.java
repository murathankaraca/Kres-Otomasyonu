package meltem.services.data_access.concrete;

import meltem.enums.LogType;
import meltem.models.Classroom;
import meltem.models.Meeting;
import meltem.models.Student;
import meltem.models.Teacher;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeetingRepository extends PersistentDataService<Meeting> {

    public static MeetingRepository Instance;

    public MeetingRepository() {
        if(Instance == null) {
            Instance = this;
        }
    }

    @Override
    public Meeting fetchById(int id) {
        Meeting[] meetings = new Meeting[1];
        Meeting meeting = new Meeting(99, "", "", "");
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT TOP 1 * FROM meetings WHERE meeting_id = %d", id);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                meeting.setMeetingId(rs.getInt(1));
                meeting.setMeetingTitle(rs.getString(2));
                meeting.setMeetingSummary(rs.getString(4));
                meeting.setMeetingDate(rs.getString(3));
            }
            meetings[0] = meeting;
            // Bitis
            this.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return meetings[0];
    }

    public Meeting fetchByTitle(String title) {

        Meeting[] meetings = new Meeting[1];
        Meeting meeting = new Meeting(99, "", "", "");
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT TOP 1 * FROM meetings WHERE meeting_title='%s'", title);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                meeting.setMeetingId(rs.getInt(1));
                meeting.setMeetingTitle(rs.getString(2));
                meeting.setMeetingSummary(rs.getString(4));
                meeting.setMeetingDate(rs.getString(3));
            }
            meetings[0] = meeting;
            // Bitis
            this.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return meetings[0];
    }

    @Override
    public List<Meeting> fetchAll() throws SQLException {
        List<Meeting> meetingList = new ArrayList<Meeting>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM meetings";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Meeting meeting = new Meeting(-1, "", "", "");

                meeting.setMeetingId(rs.getInt(1));
                meeting.setMeetingTitle(rs.getString(2));
                meeting.setMeetingSummary(rs.getString(4));
                meeting.setMeetingDate(rs.getString(3));

                meetingList.add(meeting);
                Logger.Log(LogType.Debug, String.valueOf(meeting.getMeetingSummary()));
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        return meetingList;
    }

    @Override
    public void Add(Meeting entity)  {
        try {
            this.connect();
            String sql = "INSERT INTO meetings VALUES (?, ?, ?)";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getMeetingTitle());
            pst.setString(3, entity.getMeetingSummary());
            pst.setString(2, entity.getMeetingDate());
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i) + " IS THE CONDITION OF EXECUTING THE MEETING ADD.");
            this.close();
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    @Override
    public void UpdateById(Meeting entity, int meetingId) {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "UPDATE meetings SET meeting_title = ?, meeting_date = ?, meeting_summary = ? WHERE meeting_id = ?";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getMeetingTitle());
            pst.setString(2, entity.getMeetingDate());
            pst.setString(3, entity.getMeetingSummary());
            pst.setInt(4, entity.getMeetingId());
            int i = pst.executeUpdate();
            Logger.LogDebug("RESULTS OF THIS TEACHER UPDATE IS: " + i);
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    @Override
    public void Delete(int id) throws SQLException {

    }
}
