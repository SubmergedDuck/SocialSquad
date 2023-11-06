package use_case.search_event;

import entity.Location.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SearchEventInputData {
    final private String eventName;
    final private Location location;
    final private LocalDateTime startTime;
    final private LocalDateTime endTime;
    final private String type;


    public SearchEventInputData(String eventName, Location location, LocalDateTime startTime, LocalDateTime endTime, String type) {
        this.eventName = eventName;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }

    public SearchEventInputData(String eventName, Location location, LocalDateTime today, String type) {
        /*

        When user didn't specify a range of time to search events, will default it to within 24 hours
         */
        this.eventName = eventName;
        this.location = location;
        this.startTime = LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 0, 0);
        this.endTime = today.plusDays(1);
        this.type = type;
    }
}
