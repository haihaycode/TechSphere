package techsphere.Utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtils {
    // Chuyển đổi chuỗi thời gian sang timestamp
    public static long convertToTimestamp(String inputTime) {
        Instant instant = Instant.parse(inputTime);
        return instant.toEpochMilli();
    }

    // Định dạng lại thời gian theo múi giờ mong muốn
    // Định dạng lại thời gian theo múi giờ mong muốn
    public static String formatTimeWithOffset(String inputTime, int offsetHours) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime localDateTime = LocalDateTime.parse(inputTime, formatter);
        OffsetDateTime offsetDateTime = localDateTime.atOffset(ZoneOffset.ofHours(offsetHours));
        return offsetDateTime.toString();
    }
}
