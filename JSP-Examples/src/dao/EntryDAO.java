package dao;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import databeans.Entry;

public class EntryDAO {
    private static final Entry[] ALL_ENTRIES = initEntries();
    

    public Entry read(int id) {
        for (Entry e : ALL_ENTRIES) {
            if (e.getId() == id) {
                return e;
            }
        }
        
        return null;
    }

    public Entry[] lookupByStartOfLastName(String start) {
        List<Entry> matches = new ArrayList<>();
        for (Entry e : ALL_ENTRIES) {
            if (e.getLastName().startsWith(start)) {
                matches.add(e);
            }
        }
        
        Collections.sort(matches,
                (Entry e1, Entry e2) -> e1.getLastName().compareToIgnoreCase(e2.getLastName())); 

        return matches.toArray(new Entry[matches.size()]);
    }
    
    private static Entry[] initEntries() {
        List<Entry> entries = new ArrayList<>();
        
        Entry e = new Entry();
        e.setId(entries.size() + 1);
        e.setLastName("Bush");
        e.setFirstNames("George & Barbara");
        e.setAddress("9 West Oak Lane South");
        e.setCity("Houston");
        e.setState("TX");
        e.setZip("77056");
        entries.add(e);
        
        e = new Entry();
        e.setId(entries.size() + 1);
        e.setLastName("Bush");
        e.setFirstNames("George & Laura");
        e.setAddress("10141 Daria Place");
        e.setCity("Dallas");
        e.setState("TX");
        e.setZip("75229");
        entries.add(e);

        e = new Entry();
        e.setId(entries.size() + 1);
        e.setLastName("Obama");
        e.setFirstNames("Barack & Michelle");
        e.setAddress("2446 Belmont Rd NW");
        e.setCity("Washington");
        e.setState("DC");
        e.setZip("20008");
        entries.add(e);

        e = new Entry();
        e.setId(entries.size() + 1);
        e.setWorkPhone("202-456-1414");
        e.setLastName("Trump");
        e.setFirstNames("Donald & Melania");
        e.setAddress("1600 Pennsylvania Ave");
        e.setCity("Washington");
        e.setState("DC");
        e.setZip("20500");
        entries.add(e);
        
        return entries.toArray(new Entry[entries.size()]);
    }
}
