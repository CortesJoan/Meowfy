package com.example.meowtify.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meowtify.services.PlaylistService;
import com.example.meowtify.R;
import com.example.meowtify.adapters.AdapterLibraryList;
import com.example.meowtify.models.GeneralItem;
import com.example.meowtify.models.Playlist;
import com.example.meowtify.models.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaylistLibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaylistLibraryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView listaPlaylist;

    public PlaylistLibraryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlaylistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaylistLibraryFragment newInstance(String param1, String param2) {
        PlaylistLibraryFragment fragment = new PlaylistLibraryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    PlaylistService playlistService ;

    AdapterLibraryList adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_playlist_library, container, false);

        listaPlaylist = v.findViewById(R.id.lista_library);
        playlistService = new PlaylistService(v.getContext());
        playlistService.getUserPlayLists(this::IntroduceMyPlaylists,50,0);

        List<GeneralItem> items = new ArrayList<GeneralItem>(Arrays.asList(
                new GeneralItem("5tXPbKvuDsSgctH5Mlpn18", "Create playlist", Type.playlist, "https://i.scdn.co/image/0f057142f11c251f81a22ca639b7261530b280b2", null, null),
                new GeneralItem("7xsdr3YuARtJxqssk1m3Kq", "Item12", Type.playlist, "https://i.scdn.co/image/0f057142f11c251f81a22ca639b7261530b280b2", "creator12", null),
                new GeneralItem("3ForlWAUJFtzxezcS47JmB", "Item22", Type.playlist, "https://i.scdn.co/image/0f057142f11c251f81a22ca639b7261530b280b2", "creator12", null),
                new GeneralItem("6dJMlk3nncKD4y0wzuyhWr", "Item32", Type.playlist, "https://i.scdn.co/image/0f057142f11c251f81a22ca639b7261530b280b2", "creator12", null)
        ));

        adapter = new AdapterLibraryList(items, getContext());
        listaPlaylist.setAdapter(adapter);
        listaPlaylist.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }
    private void IntroduceMyPlaylists() {
        List<Playlist> itemsSongs = playlistService.getPlaylists();
        List<GeneralItem> items21 = new ArrayList<>();
        items21.add(   new GeneralItem("id", "Create playlist", Type.playlist, "https://i.scdn.co/image/0f057142f11c251f81a22ca639b7261530b280b2", null, null));
        for (Playlist p: itemsSongs
        ) {
            items21.add(p.toGeneralItem());
        }
        System.out.println("Items in param : = " + items21.toString());
        adapter.setItems(items21);
        System.out.println("The list " + adapter.toString());
    }
}