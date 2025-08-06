package com.cheetah.algorihm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TCP {
    /*
     * CLOSED: APP_PASSIVE_OPEN -> LISTEN
     * CLOSED: APP_ACTIVE_OPEN  -> SYN_SENT
     * LISTEN: RCV_SYN          -> SYN_RCVD
     * LISTEN: APP_SEND         -> SYN_SENT
     * LISTEN: APP_CLOSE        -> CLOSED
     * SYN_RCVD: APP_CLOSE      -> FIN_WAIT_1
     * SYN_RCVD: RCV_ACK        -> ESTABLISHED
     * SYN_SENT: RCV_SYN        -> SYN_RCVD
     * SYN_SENT: RCV_SYN_ACK    -> ESTABLISHED
     * SYN_SENT: APP_CLOSE      -> CLOSED
     * ESTABLISHED: APP_CLOSE   -> FIN_WAIT_1
     * ESTABLISHED: RCV_FIN     -> CLOSE_WAIT
     * FIN_WAIT_1: RCV_FIN      -> CLOSING
     * FIN_WAIT_1: RCV_FIN_ACK  -> TIME_WAIT
     * FIN_WAIT_1: RCV_ACK      -> FIN_WAIT_2
     * CLOSING: RCV_ACK         -> TIME_WAIT
     * FIN_WAIT_2: RCV_FIN      -> TIME_WAIT
     * TIME_WAIT: APP_TIMEOUT   -> CLOSED
     * CLOSE_WAIT: APP_CLOSE    -> LAST_ACK
     * LAST_ACK: RCV_ACK        -> CLOSED
     */
    public static void main(String[] args) {

        System.out.println(traverseStates(new String[]{"APP_ACTIVE_OPEN", "RCV_SYN_ACK", "RCV_FIN"}));
        System.out.println(traverseStates(new String[]{"APP_PASSIVE_OPEN", "RCV_SYN", "RCV_ACK"}));
        System.out.println(traverseStates(new String[]{"APP_ACTIVE_OPEN", "RCV_SYN_ACK", "RCV_FIN", "APP_CLOSE"}));
        System.out.println(traverseStates(new String[]{"APP_ACTIVE_OPEN"}));
        System.out.println(traverseStates(new String[]{"APP_PASSIVE_OPEN", "RCV_SYN", "RCV_ACK", "APP_CLOSE", "APP_SEND"}));

        System.out.println("------------------");
        System.out.println(TCPC.traverseStates(new String[]{"APP_ACTIVE_OPEN", "RCV_SYN_ACK", "RCV_FIN"}));
        System.out.println(TCPC.traverseStates(new String[]{"APP_PASSIVE_OPEN", "RCV_SYN", "RCV_ACK"}));
        System.out.println(TCPC.traverseStates(new String[]{"APP_ACTIVE_OPEN", "RCV_SYN_ACK", "RCV_FIN", "APP_CLOSE"}));
        System.out.println(TCPC.traverseStates(new String[]{"APP_ACTIVE_OPEN"}));
        System.out.println(TCPC.traverseStates(new String[]{"APP_PASSIVE_OPEN", "RCV_SYN", "RCV_ACK", "APP_CLOSE", "APP_SEND"}));



    }

    private static final Map<String, String> CLOSED = Map.of("APP_PASSIVE_OPEN", "LISTEN","APP_ACTIVE_OPEN","SYN_SENT");
    private static final Map<String, String> LISTEN = Map.of("RCV_SYN", "SYN_RCVD","APP_SEND","SYN_SENT","APP_CLOSE","CLOSED");
    private static final Map<String, String> SYN_RCVD = Map.of("APP_CLOSE", "FIN_WAIT_1","RCV_ACK","ESTABLISHED");
    private static final Map<String, String> SYN_SENT = Map.of("RCV_SYN", "SYN_RCVD","RCV_SYN_ACK","ESTABLISHED","APP_CLOSE","CLOSED");
    private static final Map<String, String> ESTABLISHED = Map.of("APP_CLOSE", "FIN_WAIT_1","RCV_FIN","CLOSE_WAIT");
    private static final Map<String, String> FIN_WAIT_1 = Map.of("RCV_FIN", "CLOSING","RCV_FIN_ACK","TIME_WAIT","RCV_ACK","FIN_WAIT_2");
    private static final Map<String, String> CLOSING = Map.of("RCV_ACK", "TIME_WAIT");
    private static final Map<String, String> FIN_WAIT_2 = Map.of("RCV_FIN", "TIME_WAIT");
    private static final Map<String, String> TIME_WAIT = Map.of("APP_TIMEOUT", "CLOSED");
    private static final Map<String, String> CLOSE_WAIT = Map.of("APP_CLOSE", "LAST_ACK");
    private static final Map<String, String> LAST_ACK = Map.of("RCV_ACK", "CLOSED");
    private static final Map<String,Map<String, String>> STATUS = new HashMap<>();

    static {
        STATUS.put("CLOSED",CLOSED);
        STATUS.put("LISTEN",LISTEN);
        STATUS.put("SYN_RCVD",SYN_RCVD);
        STATUS.put("SYN_SENT",SYN_SENT);
        STATUS.put("ESTABLISHED",ESTABLISHED);
        STATUS.put("FIN_WAIT_1",FIN_WAIT_1);
        STATUS.put("CLOSING",CLOSING);
        STATUS.put("FIN_WAIT_2",FIN_WAIT_2);
        STATUS.put("TIME_WAIT",TIME_WAIT);
        STATUS.put("CLOSE_WAIT",CLOSE_WAIT);
        STATUS.put("LAST_ACK", LAST_ACK);
    }

    public static String traverseStates(String[] events) {
        String nextStatus = "CLOSED";
        for (String event : events) {
            nextStatus = STATUS.get(nextStatus).get(event);
            if (Objects.isNull(nextStatus)) {
                return "ERROR";
            }
        }
        return nextStatus;
    }






    public enum Event {
        APP_PASSIVE_OPEN, APP_ACTIVE_OPEN, APP_SEND, APP_CLOSE, APP_TIMEOUT, RCV_SYN, RCV_ACK, RCV_SYN_ACK, RCV_FIN, RCV_FIN_ACK
    }

    public static abstract class State {
        public abstract State consume(Event event);

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }

    public static class ERROR extends State {
        @Override
        public State consume(Event event) {
            return new ERROR();
        }
    }

    public static class CLOSED extends State {
        @Override
        public State consume(Event event) {
            switch (event) {
                case APP_ACTIVE_OPEN:
                    return new SYN_SENT();
                case APP_PASSIVE_OPEN:
                    return new LISTEN();
            }
            return new ERROR();
        }
    }

    public static class LISTEN extends State {
        @Override
        public State consume(Event event) {
            switch (event) {
                case RCV_SYN:
                    return new SYN_RCVD();
                case APP_SEND:
                    return new SYN_SENT();
                case APP_CLOSE:
                    return new CLOSED();
            }
            return new ERROR();
        }
    }

    public static class SYN_RCVD extends State {
        @Override
        public State consume(Event event) {
            switch (event) {
                case APP_CLOSE:
                    return new FIN_WAIT_1();
                case RCV_ACK:
                    return new ESTABLISHED();
            }
            return new ERROR();
        }
    }

    public static class SYN_SENT extends State {
        @Override
        public State consume(Event event) {
            switch (event) {
                case RCV_SYN:
                    return new SYN_RCVD();
                case RCV_SYN_ACK:
                    return new ESTABLISHED();
                case APP_CLOSE:
                    return new CLOSED();
            }
            return new ERROR();
        }
    }

    public static class FIN_WAIT_1 extends State {
        @Override
        public State consume(Event event) {
            switch (event) {
                case RCV_FIN:
                    return new CLOSING();
                case RCV_FIN_ACK:
                    return new TIME_WAIT();
                case RCV_ACK:
                    return new FIN_WAIT_2();
            }
            return new ERROR();
        }
    }

    public static class ESTABLISHED extends State {
        @Override
        public State consume(Event event) {
            switch (event) {
                case APP_CLOSE:
                    return new FIN_WAIT_1();
                case RCV_FIN:
                    return new CLOSE_WAIT();
            }
            return new ERROR();
        }
    }

    public static class CLOSING extends State {
        @Override
        public State consume(Event event) {
            if (event == Event.RCV_ACK) return new TIME_WAIT();
            return new ERROR();
        }
    }

    public static class FIN_WAIT_2 extends State {
        @Override
        public State consume(Event event) {
            if (event == Event.RCV_FIN) return new TIME_WAIT();
            return new ERROR();
        }
    }

    public static class TIME_WAIT extends State {
        @Override
        public State consume(Event event) {
            if (event == Event.APP_TIMEOUT) return new CLOSED();
            return new ERROR();
        }
    }

    public static class CLOSE_WAIT extends State {
        @Override
        public State consume(Event event) {
            if (event == Event.APP_CLOSE) return new LAST_ACK();
            return new ERROR();
        }
    }

    public static class LAST_ACK extends State {
        @Override
        public State consume(Event event) {
            if (event == Event.RCV_ACK) return new CLOSED();
            return new ERROR();
        }
    }

    public static String traverseStatesC(String[] strings) {
        State state = new CLOSED();
        for (String eventstr : strings) {
            state = state.consume(Event.valueOf(eventstr));
            if (state instanceof ERROR) break;
        }
        return state.toString();
    }




}
