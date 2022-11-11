package meltem.models;

public class User {
    private int userId;
    private String userName;
    private String password;
    private int userAuth;

    public User(int userId, String userName, String password, int userType) {
        this.setUserId(userId);
        this.setUserName(userName);
        this.setPassword(password);
        this.setUserAuth(userType);
    }
    public User(){}

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserAuth() {
        return this.userAuth;
    }

    public void setUserAuth(int userAuth) {
        this.userAuth = userAuth;
    }

    public String getTrueAuth() {
        switch(this.getUserAuth()) {
            case 1:
                return "Yonetici";
            case 2:
                return "Sinif Ogretmeni";
            case 3:
                return "Brans Ders Ogretmeni";
            default:
                return "Yetkisiz";
        }
    }
}
