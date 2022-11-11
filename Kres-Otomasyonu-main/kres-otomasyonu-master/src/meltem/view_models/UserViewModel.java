package meltem.view_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import meltem.models.User;
import meltem.services.logging.Logger;
import sun.java2d.pipe.SpanShapeRenderer;

public class UserViewModel{
    public User user;

    public ObservableValue<SimpleIntegerProperty> userId;
    public ObservableValue<SimpleStringProperty> userName;
    public ObservableValue<SimpleStringProperty> userPassword;
    public ObservableValue<SimpleIntegerProperty> userAuth;
    public ObservableValue<SimpleStringProperty> userTrueAuth;

    public UserViewModel (
            int id,
            String username,
            String pw,
            int userAuth
    ) {
        try {
            this.user = new User(id, username, pw, (short) userAuth);

            this.userId = (ObservableValue) new SimpleIntegerProperty(id);
            this.userName = (ObservableValue) new SimpleStringProperty(username);
            this.userPassword = (ObservableValue) new SimpleStringProperty(pw);
            this.userAuth = (ObservableValue) new SimpleIntegerProperty(userAuth);
            this.userTrueAuth = (ObservableValue) new SimpleStringProperty(user.getTrueAuth());
            Logger.LogDebug(this.user.getUserName());
        }
        catch(NullPointerException ex) {
            Logger.LogError(ex.toString());
        }
    }

}
