public class Card {
    private int pin;
    private String cardName;
    private long cardNumber;
    private int id;
    private Boolean inService;

    public Card(int pin, String cardName, int id) {
        this.pin = pin;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.id = id;
        this.inService = true;
    }


    public Boolean getInService() {
        return inService;
    }

    public void setInService(Boolean inService) {
        this.inService = inService;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
