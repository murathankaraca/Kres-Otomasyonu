package meltem.services.data_access.concrete;

import meltem.enums.LogType;
import meltem.models.User;
import meltem.services.logging.Logger;
import meltem.services.data_access.PersistentDataService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends PersistentDataService<User> {
    public static UserRepository Instance;
    public UserRepository() {
        if(Instance == null) {
            Instance = this;
        }
    }
    @Override
    public User fetchById(int id) {
        User[] userList = new User[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM dbo.users WHERE user_id = %d;", id);
            Logger.LogDebug(query);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_password"));
                user.setUserAuth(rs.getInt("user_auth"));
                userList[0] = user;
                StringBuilder sb = new StringBuilder();
                sb.append("User by the id of ");
                sb.append(user.getUserId());
                sb.append(" was found. ");
                sb.append("Username and authorization type are: ");
                sb.append(user.getUserName());
                sb.append(", ");
                sb.append(user.getTrueAuth());
                Logger.LogDebug(sb.toString());
                userList[0] = user;
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
           // Logger.Log(LogType.Error, ex.getMessage());
            ex.printStackTrace();
        }
        // Returning the found user, or null if not found any.
        return userList[0];
    }

    public User fetchByUsername(String userName) {
        User[] userList = new User[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT TOP 1 * FROM dbo.users WHERE user_name = '%s';", userName);
            Logger.LogDebug(query);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_password"));
                user.setUserAuth(rs.getInt("user_auth"));
                userList[0] = user;
                StringBuilder sb = new StringBuilder();
                sb.append("User by the id of ");
                sb.append(user.getUserId());
                sb.append(" was found. ");
                sb.append("Username and authorization type are: ");
                sb.append(user.getUserName());
                sb.append(", ");
                sb.append(user.getTrueAuth());
                Logger.LogDebug(sb.toString());
                userList[0] = user;
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            //Logger.Log(LogType.Error, ex.getMessage());
            ex.printStackTrace();
        }
        // Returning the found user, or null if not found any.
        return userList[0];
    }

    @Override
    public List<User> fetchAll() {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_password"));
                user.setUserAuth(rs.getInt("user_auth"));
                Logger.Log(LogType.Debug, user.getUserName());
                userList.add(user);
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        return userList;
    }

    @Override
    public void Add(User user) {
        try {
            this.connect();
            String sql = "INSERT INTO users VALUES(?, ?, ?)";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getPassword());
            pst.setInt(3, user.getUserAuth());
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            this.close();
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }

    }

    @Override
    public void UpdateById(User user, int id) {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "UPDATE users SET user_name = ?, user_password = ?, user_auth = ? WHERE user_id = ?";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getPassword());
            pst.setInt(3, user.getUserAuth());
            pst.setInt(4, id);
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            //Logger.Log(LogType.Error, ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void UpdateByUsername(User user, String userName) {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "UPDATE users SET user_name = ?, user_password = ?, user_auth = ? WHERE user_name = ?";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getPassword());
            pst.setInt(3, user.getUserAuth());
            pst.setString(4, userName);
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(user.getPassword()));
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            //Logger.Log(LogType.Error,);
        }
    }

    @Override
    public void Delete(int id) {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setInt(1, id);
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }
}
