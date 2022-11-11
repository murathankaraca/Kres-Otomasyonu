package meltem.models;

public class EmergencyNumber {
    public int numberId;
    public String numberDescription;
    public String number;
    public EmergencyNumber(int id, String desc, String number) {
        this.numberId = id;
        this.numberDescription = desc;
        this.number = number;
    }
}
