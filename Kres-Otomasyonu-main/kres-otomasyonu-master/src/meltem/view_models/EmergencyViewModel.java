package meltem.view_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import meltem.models.EmergencyNumber;
import meltem.services.logging.Logger;

public class EmergencyViewModel {
    public EmergencyNumber emergencyNumber;

    public ObservableValue<SimpleIntegerProperty> numberId;
    public ObservableValue<SimpleStringProperty> numberDescription;
    public ObservableValue<SimpleStringProperty> number;

    public EmergencyViewModel (
            int id,
            String desc,
            String number
    ) {
        try {
            this.numberId = (ObservableValue) new SimpleIntegerProperty(id);
            this.numberDescription = (ObservableValue) new SimpleStringProperty(desc);
            this.number = (ObservableValue) new SimpleStringProperty(desc);
            this.emergencyNumber = new EmergencyNumber(id, desc, number);
            Logger.LogDebug(this.emergencyNumber.numberDescription);
        }
        catch(NullPointerException ex) {
            Logger.LogError(ex.toString());
        }
    }

}
