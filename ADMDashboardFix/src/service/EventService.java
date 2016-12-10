package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import model.Event;
import model.EventType;
import model.Status;
import model.datetime.SimpleDate;
import utils.converter.DatatypeConverter;
import utils.db.Query;

public class EventService {

	public static ArrayList<Event> getUpcomingDeadlines(String orgcode, int minDays, int maxDays) {
		System.out.println("[METHOD] getUpcomingDeadlines " + maxDays);
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME + " WHERE " + Event.COL_POSTACTDEADLINE + " > curdate()"
				+ "	AND " + Event.COL_POSTACTDEADLINE + " < date_add(CURDATE(), INTERVAL ? DAY)" + "	AND "
				+ Event.COL_POSTACTDEADLINE + " > date_add(CURDATE(), INTERVAL ? DAY)" + " AND " + Event.COL_ORGCODE
				+ " = ? " + " AND " + Event.COL_POSTACTSTATUS + " = ? " + " ORDER BY " + Event.COL_POSTACTDEADLINE;

		input.add(maxDays);
		input.add(minDays);
		input.add(orgcode);
		input.add(Status.PENDING);
		Query q = Query.getInstance();
		ResultSet r = null;
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				event.setPostact_deadline(DatatypeConverter.toCalendar(r.getDate(Event.COL_POSTACTDEADLINE)));
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return events;
	}

	public static ArrayList<String> convertDates(ArrayList<Event> events) {
		System.out.println("[METHOD] convertDates");
		ArrayList<String> dates = new ArrayList<String>();
		
		SimpleDate simpleDate = null;
		Calendar c = null;
		int year, month, day;
		
		for(int i = 0; i < events.size(); i ++) {
			c = events.get(i).getPostact_deadline();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
			simpleDate = new SimpleDate(year, month, day);			
			
			dates.add(simpleDate.toString());
		}
		
		
		return dates;
	}

	public static ArrayList<Event> getOverdueDeadlines(String orgcode) {
		System.out.println("[METHOD] getOverdueDeadlines");
		ArrayList<Event> overdueEvents = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME + " WHERE " + Event.COL_POSTACTDEADLINE + " < curdate()"
				+ " AND " + Event.COL_ORGCODE + " = ? " + " AND " + Event.COL_POSTACTSTATUS + " = ? " + " ORDER BY "
				+ Event.COL_POSTACTDEADLINE;

		input.add(orgcode);
		input.add(Status.PENDING);

		Query q = Query.getInstance();
		ResultSet r = null;
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				event.setPostact_deadline(DatatypeConverter.toCalendar(r.getDate(Event.COL_POSTACTDEADLINE)));
				overdueEvents.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/*
		 * System.out.println("RETURN"); for(int i = 0 ; i< events.size(); i++)
		 * System.out.println(events.get(i).getEventname());
		 */
		return overdueEvents;
	}

	public static ArrayList<Event> getOtherDeadlines(String orgcode) {
		System.out.println("[METHOD] getOtherDeadlines ");
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		String query = "SELECT * FROM " + Event.TABLE_NAME + " WHERE " + Event.COL_POSTACTDEADLINE + " > curdate()"
				+ "	AND " + Event.COL_POSTACTDEADLINE + " > date_add(CURDATE(), INTERVAL 14 DAY)" + " AND "
				+ Event.COL_ORGCODE + " = ? " + " AND " + Event.COL_POSTACTSTATUS + " = ? " + " ORDER BY "
				+ Event.COL_POSTACTDEADLINE;

		input.add(orgcode);
		input.add(Status.PENDING);
		Query q = Query.getInstance();
		ResultSet r = null;
		try {
			r = q.runQuery(query, input);
			while (r.next()) {
				event = new Event();
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventdesc(r.getString(Event.COL_EVENTDESC));
				event.setPostact_status(Status.PENDING);
				event.setPostact_deadline(DatatypeConverter.toCalendar(r.getDate(Event.COL_POSTACTDEADLINE)));
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return events;
	}
	
	public static ArrayList<Event> searchEvents(String searchString) {
		System.out.println("[METHOD] searchEvents " + searchString);
		
		ArrayList<Event> eventList = new ArrayList<Event>();
		ArrayList<Object> input = new ArrayList<Object>();
		Event event = null;
		
		String query = "SELECT *"
				+ " FROM " + Event.TABLE_NAME + " NATURAL JOIN " + EventType.TABLE_NAME
				+ " WHERE LOWER(" + Event.COL_EVENTNAME + ") LIKE LOWER(?) OR "
				+ " LOWER(" + Event.COL_EVENTDESC + ") LIKE LOWER(?) OR "
				+ " LOWER(" + Event.COL_ORGCODE + ") LIKE LOWER(?)";
		
		input.add("%" + searchString + "%");
		input.add("%" + searchString + "%");
		input.add("%" + searchString + "%");
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			while(r.next()) {
				event = new Event();
				
				event.setEventID(r.getInt(Event.COL_EVENTID));
				event.setOrgcode(r.getString(Event.COL_ORGCODE));
				event.setEventname(r.getString(Event.COL_EVENTNAME));
				event.setEventtype(r.getString(EventType.COL_EVENTTYPE));
				event.setPostact_status(DatatypeConverter.toStatus(r.getString(Event.COL_POSTACTSTATUS)));
				event.setPostact_deadline(DatatypeConverter.toCalendar(r.getDate(Event.COL_POSTACTDEADLINE)));
				
				eventList.add(event);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return eventList;
	}

}