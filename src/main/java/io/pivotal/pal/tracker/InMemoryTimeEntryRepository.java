package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository  implements TimeEntryRepository{
    private TimeEntry timeEntry;
    private HashMap<Long, TimeEntry> timeEntryMap = new HashMap<>();
    private long currentId = 1L;
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;
                TimeEntry newTimeEntry = new TimeEntry(id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        timeEntryMap.put(id,newTimeEntry);
        return newTimeEntry;
    }

    public TimeEntry find(long timeEntryId) {
        return timeEntryMap.get(timeEntryId);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryMap.values());
    }

    public void delete(long id) {
        timeEntryMap.remove(id);
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (find(id) == null) return null;
        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        timeEntryMap.replace(id, updatedEntry);
        return updatedEntry;
    }
}
