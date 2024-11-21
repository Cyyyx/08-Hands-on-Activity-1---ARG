//Cyrus Ramirez BSIT-307
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Monday extends Weekday {

    public static void main(String[] args) {
        Monday monday = new Monday();
        monday.startAlarmProcess();
    }

    private void startAlarmProcess() {
        try (Scanner cyrus = new Scanner(System.in)) {
            boolean alarmSet = false;

            while (!alarmSet) {
                String timeInput = promptForTime(cyrus);
                setAlarm(timeInput);
                alarmSet = showAlarm();
            }
        }
    }

    private String promptForTime(Scanner scanner) {
        System.out.print("Enter the time for the alarm (HH:MM): ");
        return scanner.nextLine();
    }
}

interface Alarm {
    void setAlarm(String time);
    boolean showAlarm();
}

class Weekday implements Alarm {
    protected String time;

    @Override
    public void setAlarm(String time) {
        this.time = time;
    }

    @Override
    public boolean showAlarm() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        try {
            LocalTime alarmTime = LocalTime.parse(time, formatter);
            LocalTime currentTime = LocalTime.now();

            displayAlarmMessage(alarmTime, currentTime);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please enter the time in HH:MM format.");
            return false;
        }
    }

    private void displayAlarmMessage(LocalTime alarmTime, LocalTime currentTime) {
        if (alarmTime.isAfter(currentTime)) {
            System.out.println("I'll wake you up later!");
        } else {
            System.out.println("Alarm is set for tomorrow!");
        }
    }
}
