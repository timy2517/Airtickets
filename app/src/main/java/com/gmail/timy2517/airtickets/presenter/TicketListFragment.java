package com.gmail.timy2517.airtickets.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.timy2517.airtickets.R;
import com.gmail.timy2517.airtickets.model.Ticket;
import com.gmail.timy2517.airtickets.model.TicketLab;

import java.util.List;

/**
 * Created by Artem Novik on 17.6.17.
 */

public class TicketListFragment extends Fragment {

    private RecyclerView mTicketRecyclerView;
    private TicketAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ticket_list, container, false);

        mTicketRecyclerView = v.findViewById(R.id.ticket_recycler_view);
        mTicketRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    private void updateUI() {
        TicketLab ticketLab = TicketLab.get();
        List<Ticket> tickets = ticketLab.getTicketList();
        mAdapter = new TicketAdapter(tickets);
        mTicketRecyclerView.setAdapter(mAdapter);
    }

    private class TicketHolder extends RecyclerView.ViewHolder {

        private Ticket mTicket;

        private TextView mCityFrom;
        private TextView mCityTo;
        private TextView mDepartureDate;
        private TextView mArrivalDate;
        private TextView mDuration;
        private TextView mPrice;
        private TextView mWishTrans;


        public TicketHolder(View itemView) {
            super(itemView);

            mCityFrom = itemView.findViewById(R.id.city_from);
            mCityTo = itemView.findViewById(R.id.city_to);
            mDepartureDate = itemView.findViewById(R.id.dep_date);
            mArrivalDate = itemView.findViewById(R.id.arr_date);
            mDuration = itemView.findViewById(R.id.duration_str);
            mPrice = itemView.findViewById(R.id.price);
            mWishTrans = itemView.findViewById(R.id.wish_transfer);
        }

        public void bindTicket(Ticket ticket){
            mTicket = ticket;
            mCityFrom.setText(mTicket.getCityNameFrom());
            mCityTo.setText(mTicket.getCityNameTo());
            mDepartureDate.setText(mTicket.getDepDate());
            mArrivalDate.setText(mTicket.getArrDate());
            mDuration.setText(mTicket.getDurationStr());
            mPrice.setText(Integer.toString(mTicket.getPrice()));
            if (mTicket.isWithTransfer()) {
                mWishTrans.setText("С пересадкой");
            } else mWishTrans.setText("Без пересадки");
        }
    }

    private class TicketAdapter extends RecyclerView.Adapter<TicketHolder> {
        private List<Ticket> mTickets;

        public TicketAdapter(List<Ticket> tickets) {
            mTickets = tickets;
        }

        @Override
        public TicketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_ticket, parent, false);
            return new TicketHolder(view);
        }

        @Override
        public void onBindViewHolder(TicketHolder holder, int position) {
            Ticket ticket = mTickets.get(position);
            holder.bindTicket(ticket);
        }

        @Override
        public int getItemCount() {
            //Log.d("getItemCount", "count: " + mTickets.size());
            return mTickets.size();
        }
    }
}























