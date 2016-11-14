package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.CalendarEvent;
import model.Event;
import model.Status;
import utils.converter.DatatypeConverter;
import utils.db.Query;

public class CalendarEventService {
	
	public static ArrayList<CalendarEvent> getAllEvents(){
		ArrayList<CalendarEvent> result = new ArrayList<>();
		CalendarEvent event = null;
		
		String query = "select *"
				+ " from"
				+ " " + CalendarEvent.TABLE_EVENT
				+ " inner join " + CalendarEvent.TABLE_EVENTDATE
				+ " on "+CalendarEvent.TABLE_EVENT+"."+CalendarEvent.COL_EVENTID+" = "+CalendarEvent.TABLE_EVENTDATE+"."+CalendarEvent.COL_EVENTID
				+ " ORDER BY "+CalendarEvent.COL_POSTACTDEADLINE+";";
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query);
			
			while(r.next()) {
				event = new CalendarEvent();
				event.setEverything(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME), 
						r.getDate(CalendarEvent.COL_DATE).toString(), 
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						"#333");
				
				result.add(event);
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
		return result;
	}
	
	public static ArrayList<CalendarEvent> getEventsByOrg(String orgcode){
		ArrayList<CalendarEvent> result = new ArrayList<>();
		ArrayList<Object> input = new ArrayList<>();
		CalendarEvent event = null;
		
		String query = "select *"
				+ " from"
				+ " " + CalendarEvent.TABLE_EVENT
				+ " inner join " + CalendarEvent.TABLE_EVENTDATE
				+ " on "+CalendarEvent.TABLE_EVENT+"."+CalendarEvent.COL_EVENTID+" = "+CalendarEvent.TABLE_EVENTDATE+"."+CalendarEvent.COL_EVENTID
				+ " where "+CalendarEvent.COL_ORGCODE+"= ? " 
				+ " ORDER BY "+CalendarEvent.COL_POSTACTDEADLINE+";";
		
		input.add(orgcode);
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			while(r.next()) {
				event = new CalendarEvent();
				event.setEverything(
						r.getInt(CalendarEvent.COL_EVENTID), 
						r.getString(CalendarEvent.COL_EVENTNAME), 
						r.getDate(CalendarEvent.COL_DATE).toString(), 
						r.getDate(CalendarEvent.COL_POSTACTDEADLINE).toString(), 
						"#333");
				
				result.add(event);
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
		return result;
	}
}
