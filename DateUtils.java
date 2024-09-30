import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DateUtils {
  
  public static boolean isExpired(LocalDate expiryDate){
    return expiryDate.isBefore(LocalDate.now());
  }

  public static int daysUntilExpiryDate(LocalDate expiryDate){
    return (int) ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
  }

  public static String formatDate(LocalDate date, String format){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return date.format(formatter);
  }

  public static LocalDate parseDate(String dateString, String format){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return LocalDate.parse(dateString, formatter);
  }

  public static LocalDate getCurrentDate(){
    return LocalDate.now();
  }

  public static boolean isDateValid(String dateString, String format){
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate.parse(dateString, formatter);
        return true;
    } catch (DateTimeParseException e) {
        return false;
    }
  }
  
}
