package activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import models.entities.Tour;
import models.entities.Tournament;
import ru.chgkapp.R;

/**
 * Created by Sergey on 28.01.2015.
 */
public class TournamentInfoItem extends Fragment
{
    private ViewGroup rootView;
    private Tournament tournament;

    public static TournamentInfoItem create(Tournament tournament)
    {
        TournamentInfoItem item = new TournamentInfoItem();
        Bundle args = new Bundle();
        args.putSerializable("tournament", tournament);
        item.setArguments(args);
        return item;
    }

    public TournamentInfoItem() { }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        tournament = (Tournament) getArguments().getSerializable("tournament");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater
                .inflate(R.layout.tournament_info, container, false);

        TextView name = (TextView) rootView.findViewById(R.id.textViewTournamentName);
        TextView info = (TextView) rootView.findViewById(R.id.textViewTournamentInfo);
        TextView editors = (TextView) rootView.findViewById(R.id.textViewTournamentEditors);

        if (tournament.getTitle() != null)
            name.setText(tournament.getTitle());
        else
            name.setText("");

        if (tournament.getInfo() != null)
            info.setText(tournament.getInfo());
        else
            info.setText("");

        if (tournament.getEditors() != null)
            editors.setText(tournament.getEditors());
        else
            editors.setText("");

        return rootView;
    }
}
