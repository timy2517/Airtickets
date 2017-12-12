package com.gmail.timy2517.airtickets.network;


import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;

import com.gmail.timy2517.airtickets.R;
import com.gmail.timy2517.airtickets.model.Ticket;
import com.gmail.timy2517.airtickets.model.TicketLab;
import com.gmail.timy2517.airtickets.presenter.Sorter;
import com.gmail.timy2517.airtickets.presenter.TicketListFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by Artem Novik on 17.6.17.
 */

public class LoadData extends AsyncTask<Void, Void, Void> {

    private List<URL> mRequestList;
    private String mResultString;
    private List<String> mResultList;
    private HttpURLConnection connection;
    private RequestParams mRequestParams;
    FragmentManager manager;
    TicketLab mTicketLab;

    public LoadData(RequestParams requestParams, FragmentManager fm) throws MalformedURLException {
        mRequestParams = requestParams;
        manager = fm;
    }

    public void loadTickets() throws IOException {

        mRequestList = new RequestMaker(mRequestParams).getRequestList();

        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("user10", "GdshqwKle".toCharArray());
            }
        });

        mResultList = new ArrayList<>();

        for (int i = 0; i < mRequestList.size(); i++) {
            try {
                connection = (HttpURLConnection) mRequestList.get(i).openConnection();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                InputStream in = connection.getInputStream();
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new IOException(connection.getResponseMessage());
                }
                int bytesRead;
                byte[] buffer = new byte[1024];
                while ((bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
                out.close();
                mResultList.add(new String(out.toByteArray()));

            } finally {
                connection.disconnect();
            }
        }
    }


    private void loadDataToTicketLab(List<String> dataList) {
        Gson gson = new GsonBuilder().create();

        Type collectionType = new TypeToken<Collection<Ticket>>() {
        }.getType();
        mTicketLab = TicketLab.get();
        if (mTicketLab.getTicketList() != null) {
            mTicketLab.getTicketList().clear();
        }
        for (String data : dataList) {
            mTicketLab.addTicketList((List<Ticket>) gson.fromJson(data, collectionType));
        }


        if (!mRequestParams.isWithTransfer()) {
            List<Ticket> mTicketList = mTicketLab.getTicketList();
            for (int i = mTicketList.size()-1; i >= 0; i--) {
                if (mTicketList.get(i).isWithTransfer()) {
                    mTicketLab.getTicketList().remove(i);
                }
            }
        }
        new Sorter().sort(mTicketLab, mRequestParams.getSort());
    }

    public String getData() {
        return mResultString;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            loadTickets();
            loadDataToTicketLab(mResultList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        manager.beginTransaction()
                .replace(R.id.fragment_container, new TicketListFragment())
                .addToBackStack(null)
                .commit();
    }


}
