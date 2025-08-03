import { createContext, useContext, useState, ReactNode } from 'react';

export interface CalendarEvent {
  id: string;
  title: string;
  description: string;
  startDate: Date;
  endDate: Date;
  tags: string[];
  recurring?: 'none' | 'daily' | 'weekly' | 'monthly';
}

interface CalendarContextType {
  events: CalendarEvent[];
  addEvent: (event: Omit<CalendarEvent, 'id'>) => void;
  updateEvent: (id: string, event: Partial<CalendarEvent>) => void;
  deleteEvent: (id: string) => void;
  getEventsForDate: (date: Date) => CalendarEvent[];
  getEventsForMonth: (year: number, month: number) => CalendarEvent[];
}

const CalendarContext = createContext<CalendarContextType | undefined>(undefined);

export function CalendarProvider({ children }: { children: ReactNode }) {
  const [events, setEvents] = useState<CalendarEvent[]>([
    // Sample events for demo
    {
      id: '1',
      title: 'Team Meeting',
      description: 'Weekly team sync',
      startDate: new Date(2024, 11, 15, 10, 0),
      endDate: new Date(2024, 11, 15, 11, 0),
      tags: ['work'],
      recurring: 'weekly',
    },
    {
      id: '2',
      title: 'Doctor Appointment',
      description: 'Annual checkup',
      startDate: new Date(2024, 11, 18, 14, 30),
      endDate: new Date(2024, 11, 18, 15, 30),
      tags: ['personal', 'health'],
      recurring: 'none',
    },
  ]);

  const addEvent = (eventData: Omit<CalendarEvent, 'id'>) => {
    const newEvent: CalendarEvent = {
      ...eventData,
      id: Date.now().toString(),
    };
    setEvents(prev => [...prev, newEvent]);
  };

  const updateEvent = (id: string, eventData: Partial<CalendarEvent>) => {
    setEvents(prev => prev.map(event => 
      event.id === id ? { ...event, ...eventData } : event
    ));
  };

  const deleteEvent = (id: string) => {
    setEvents(prev => prev.filter(event => event.id !== id));
  };

  const getEventsForDate = (date: Date): CalendarEvent[] => {
    return events.filter(event => {
      const eventDate = new Date(event.startDate);
      return (
        eventDate.getDate() === date.getDate() &&
        eventDate.getMonth() === date.getMonth() &&
        eventDate.getFullYear() === date.getFullYear()
      );
    });
  };

  const getEventsForMonth = (year: number, month: number): CalendarEvent[] => {
    return events.filter(event => {
      const eventDate = new Date(event.startDate);
      return (
        eventDate.getMonth() === month &&
        eventDate.getFullYear() === year
      );
    });
  };

  return (
    <CalendarContext.Provider value={{
      events,
      addEvent,
      updateEvent,
      deleteEvent,
      getEventsForDate,
      getEventsForMonth,
    }}>
      {children}
    </CalendarContext.Provider>
  );
}

export function useCalendar() {
  const context = useContext(CalendarContext);
  if (context === undefined) {
    throw new Error('useCalendar must be used within a CalendarProvider');
  }
  return context;
}
