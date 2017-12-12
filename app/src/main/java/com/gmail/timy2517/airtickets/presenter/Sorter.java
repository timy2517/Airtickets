package com.gmail.timy2517.airtickets.presenter;

import android.util.Log;

import com.gmail.timy2517.airtickets.model.Ticket;
import com.gmail.timy2517.airtickets.model.TicketLab;
import com.gmail.timy2517.airtickets.network.RequestParams;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Artem Novik on 21.6.17.
 */

public class Sorter {
    final double durationWeight = 0.4;
    final double priceWeight = 0.4;
    double transferWeight = 0.2;
    double maxDuration = 0;
    double maxPrice = 0;
    double maxMinuteCoast = 0;
    double ticketMinuteCoast;
    double t1MinuteCoast;


    public void sort(TicketLab ticketLab, String sortMethod) {
        if (sortMethod.equals(RequestParams.SORTING_BY_DURATION)) {
            sortByDuration(ticketLab);
        } else if (sortMethod.equals(RequestParams.SORTING_BY_PRICE)) {
            sortByPrice(ticketLab);
        } else {
            choiceOfTheBest(ticketLab);
        }

    }

    private void sortByDuration(TicketLab ticketLab) {
        Collections.sort(ticketLab.getTicketList(), new Comparator<Ticket>() {
            @Override
            public int compare(Ticket ticket, Ticket t1) {
                return Integer.signum(ticket.getDuration() - t1.getDuration());
            }
        });
    }

    private void sortByPrice(TicketLab ticketLab) {
        Collections.sort(ticketLab.getTicketList(), new Comparator<Ticket>() {
            @Override
            public int compare(Ticket ticket, Ticket t1) {
                return Integer.signum(ticket.getPrice() - t1.getPrice());
            }
        });
    }

    private void choiceOfTheBest(TicketLab ticketLab) {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        for (Ticket ticket : ticketLab.getTicketList()) {
            if (ticket.getDuration() > maxDuration) {
                maxDuration = ticket.getDuration();
            }
            if (ticket.getPrice() > maxPrice) {
                maxPrice = ticket.getPrice();
            }
            if ((double)ticket.getPrice()/(double)ticket.getDuration() > maxMinuteCoast){
                maxMinuteCoast = (double)ticket.getPrice()/(double)ticket.getDuration();
            }
        }

        Collections.sort(ticketLab.getTicketList(), new Comparator<Ticket>() {
            @Override
            public int compare(Ticket ticket, Ticket t1) {

                double mTicketDuration = ticket.getDuration();
                double mT1Duration = t1.getDuration();
                double mTicketPrice = ticket.getPrice();
                double mT1Price = t1.getPrice();

                ticketMinuteCoast = mTicketPrice / mTicketDuration ;
                t1MinuteCoast = mT1Price / mT1Duration;

                double ticketWeight =mTicketDuration/maxDuration + mTicketPrice/maxPrice;
                double t1Weight =mT1Duration/maxDuration + mT1Price/maxPrice;

                Log.d("SORTER", "вес: " + String.format("%.4f", ticketWeight) +" время: " + String.format("%.4f", mTicketDuration)
                        +" цена: " + String.format("%.4f", mTicketPrice) +" цена минуты: " + String.format("%.4f", ticketMinuteCoast)
                        + " вес времени: " + String.format("%.4f", mTicketDuration/maxDuration) +" вес цены: " + String.format("%.4f", mTicketPrice/maxPrice)
                        +"   вес цены минуты: " + String.format("%.4f", ticketMinuteCoast/maxMinuteCoast));

                if(ticketWeight > t1Weight){
                    return 1;
                } else if (ticketWeight < t1Weight){
                    return -1;
                } else return 0;
            }
        });

    }
}
