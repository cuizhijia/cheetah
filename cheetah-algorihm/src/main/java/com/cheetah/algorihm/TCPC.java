package com.cheetah.algorihm;

import java.util.HashMap;
import java.util.Map;

public class TCPC {

    enum State {
        CLOSED{
            @Override
            State nextState(Event event) {
                if(Event.APP_PASSIVE_OPEN.equals(event)) {
                    return LISTEN;
                }
                if(Event.APP_ACTIVE_OPEN.equals(event)) {
                    return SYN_SENT;
                }
                return ERROR;
            }
        },
        LISTEN{
            @Override
            State nextState(Event event) {
                if(Event.RCV_SYN.equals(event)) {
                    return SYN_RCVD;
                }
                if(Event.APP_SEND.equals(event)) {
                    return SYN_SENT;
                }
                if(Event.APP_CLOSE.equals(event)) {
                    return CLOSED;
                }
                return ERROR;
            }
        },
        SYN_RCVD{
            @Override
            State nextState(Event event) {
                if(Event.APP_CLOSE.equals(event)) {
                    return FIN_WAIT_1;
                }
                if(Event.RCV_ACK.equals(event)) {
                    return ESTABLISHED;
                }
                return ERROR;
            }
        },
        SYN_SENT{
            @Override
            State nextState(Event event) {
                if(Event.RCV_SYN.equals(event)) {
                    return SYN_RCVD;
                }
                if(Event.RCV_SYN_ACK.equals(event)) {
                    return ESTABLISHED;
                }
                if(Event.APP_CLOSE.equals(event)) {
                    return CLOSED;
                }
                return ERROR;
            }
        },

        ESTABLISHED{
            @Override
            State nextState(Event event) {
                if(Event.APP_CLOSE.equals(event)) {
                    return FIN_WAIT_1;
                }
                if(Event.RCV_FIN.equals(event)) {
                    return CLOSE_WAIT;
                }
                return ERROR;

            }
        },

        FIN_WAIT_1{
            @Override
            State nextState(Event event) {
                return switch (event) {
                    case RCV_FIN -> CLOSING;
                    case RCV_FIN_ACK -> TIME_WAIT;
                    case RCV_ACK -> FIN_WAIT_2;
                    default -> ERROR;
                };
            }
        },
        CLOSE_WAIT{
            @Override
            State nextState(Event event) {
                if(Event.APP_CLOSE.equals(event)) {
                    return LAST_ACK;
                }
                return ERROR;
            }
        },
        LAST_ACK{
            @Override
            State nextState(Event event) {
                if(Event.RCV_ACK.equals(event)) {
                    return CLOSED;
                }
                return ERROR;
            }
        },

        FIN_WAIT_2{
            @Override
            State nextState(Event event) {
                if(Event.RCV_FIN.equals(event)) {
                    return TIME_WAIT;
                }
                return ERROR;
            }
        },
        CLOSING{
            @Override
            State nextState(Event event) {
                if(Event.RCV_ACK.equals(event)) {
                    return TIME_WAIT;
                }
                return ERROR;
            }
        },
        TIME_WAIT{
            @Override
            State nextState(Event event) {

                if(Event.APP_TIMEOUT.equals(event)) {
                    return CLOSED;
                }
                return ERROR;
            }
        },
        ERROR{
            @Override
            State nextState(Event event) {
                return ERROR;
            }
        },
        ;
        abstract State nextState(Event event);
    }

    enum Event {
        APP_PASSIVE_OPEN, APP_ACTIVE_OPEN, APP_SEND, APP_CLOSE, APP_TIMEOUT, RCV_SYN, RCV_ACK, RCV_SYN_ACK, RCV_FIN, RCV_FIN_ACK
    }

    private static final State START_STATUS = State.CLOSED;

    public static String traverseStates(String[] events) {
        State state = START_STATUS;
        for (String event : events) {
            state = state.nextState(Event.valueOf(event));
            if (state.equals(State.ERROR)) {
                break;
            }
        }
        return state.name();
    }


    final static private String START = "CLOSED", ERROR = "ERROR";

    final static private Map<String, Map<String,String>> STATES = new HashMap<>() {{
        put("CLOSED",     Map.of("APP_PASSIVE_OPEN", "LISTEN", "APP_ACTIVE_OPEN", "SYN_SENT"));
        put("LISTEN",     Map.of("RCV_SYN", "SYN_RCVD", "APP_SEND", "SYN_SENT", "APP_CLOSE", "CLOSED"));
        put("SYN_RCVD",   Map.of("APP_CLOSE", "FIN_WAIT_1", "RCV_ACK", "ESTABLISHED"));
        put("SYN_SENT",   Map.of("RCV_SYN", "SYN_RCVD", "RCV_SYN_ACK", "ESTABLISHED", "APP_CLOSE", "CLOSED"));
        put("ESTABLISHED",Map.of("APP_CLOSE", "FIN_WAIT_1", "RCV_FIN", "CLOSE_WAIT"));
        put("FIN_WAIT_1", Map.of("RCV_FIN", "CLOSING", "RCV_FIN_ACK", "TIME_WAIT", "RCV_ACK", "FIN_WAIT_2"));
        put("CLOSING",    Map.of("RCV_ACK", "TIME_WAIT"));
        put("FIN_WAIT_2", Map.of("RCV_FIN", "TIME_WAIT"));
        put("TIME_WAIT",  Map.of("APP_TIMEOUT", "CLOSED"));
        put("CLOSE_WAIT", Map.of("APP_CLOSE", "LAST_ACK"));
        put("LAST_ACK",   Map.of("RCV_ACK", "CLOSED"));
    }};

    public static String traverseStatesC(String[] events) {

        String state = START;
        for (String e: events) {
            state = STATES.get(state).get(e);
            if (state==null) return ERROR;
        }
        return state;
    }





}
