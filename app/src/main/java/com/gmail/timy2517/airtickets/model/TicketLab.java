package com.gmail.timy2517.airtickets.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artem Novik on 17.6.17.
 */

public class TicketLab {

    private static TicketLab sTicketLab;

    public static TicketLab get(){
        if (sTicketLab == null){
            sTicketLab = new TicketLab();
        }
        return sTicketLab;
    }

    private TicketLab(){}

    private List<Ticket> mTicketList;

    public List<Ticket> getTicketList() {
        return mTicketList;
    }

    public void addTicketList(List<Ticket> ticketList) {
        if (mTicketList == null){
            mTicketList = new LinkedList<>(ticketList);

        } else {
            mTicketList.addAll(ticketList);
        }
    }

}
