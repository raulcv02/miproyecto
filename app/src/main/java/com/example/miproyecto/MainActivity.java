package com.example.miproyecto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<Card> cardList;
    private LinearLayout estadisticasLayout;
    private TextView estadisticasJugadorTextView;
    private Map<String, String> estadisticasJugadores;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        estadisticasLayout = findViewById(R.id.statsLayout);

        estadisticasJugadorTextView = findViewById(R.id.estadisticasJugadorTextView);

        cardList = new ArrayList<>();
        cardList.add(new Card("Lionel Messi", R.drawable.leomessi, "Actualmente juega en el Inter de Miami"));
        cardList.add(new Card("Cristiano Ronaldo", R.drawable.cristianoronaldo, "Actualmente juega en el Al-Nassr"));
        cardList.add(new Card("Kylian Mbappé", R.drawable.mbapee, "Actualmente juega en el PSG"));
        cardList.add(new Card("Erling Haaland", R.drawable.haaland, "Actualmente juega en el Manchester City"));
        cardList.add(new Card("Vinicius Jr", R.drawable.vinicius, "Actualmente juega en el Real Madrid"));
        cardList.add(new Card("Ferran Torres", R.drawable.ferran, "Actualmente juega en el Barcelona"));

        estadisticasJugadores = new HashMap<>();
        estadisticasJugadores.put("Lionel Messi", "Goles: 750\nAsistencias: 400\nTrofeos: 30");
        estadisticasJugadores.put("Cristiano Ronaldo", "Goles: 800\nAsistencias: 200\nTrofeos: 35");
        estadisticasJugadores.put("Kylian Mbappé", "Goles: 150\nAsistencias: 50\nTrofeos: 8");
        estadisticasJugadores.put("Erling Haaland", "Goles: 100\nAsistencias: 30\nTrofeos: 5");
        estadisticasJugadores.put("Vinicius Jr", "Goles: 50\nAsistencias: 25\nTrofeos: 3");
        estadisticasJugadores.put("Ferran Torres", "Goles: 40\nAsistencias: 15\nTrofeos: 5");

        cardAdapter = new CardAdapter(cardList, this);
        recyclerView.setAdapter(cardAdapter);
    }

    public class Card {
        private String name;
        private int imageResourceId;
        private String description;

        public Card(String name, int imageResourceId, String description) {
            this.name = name;
            this.imageResourceId = imageResourceId;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public int getImageResourceId() {
            return imageResourceId;
        }

        public String getDescription() {
            return description;
        }
    }

    public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
        private List<Card> cardList;
        private Context context;

        public CardAdapter(List<Card> cardList, Context context) {
            this.cardList = cardList;
            this.context = context;
        }

        @NonNull
        @Override
        public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
            return new CardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
            Card card = cardList.get(position);
            holder.bind(card);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nombreJugador = card.getName();
                    String estadisticas = estadisticasJugadores.get(nombreJugador);

                    Log.d("ClickEvent", "onClick: Image clicked for " + nombreJugador);

                    if (estadisticasJugadorTextView != null) {
                        Log.d("ClickEvent", "onClick: Mostrando estadísticas: " + estadisticas);
                        estadisticasJugadorTextView.setText(estadisticas);
                    }

                    if (estadisticasLayout != null) {
                        Log.d("ClickEvent", "onClick: Haciendo visible estadisticasLayout");
                        estadisticasLayout.setVisibility(View.VISIBLE);
                    }

                    // Iniciar StatsActivity con información del jugador
                    Intent intent = new Intent(context, StatsActivity.class);
                    intent.putExtra(StatsActivity.EXTRA_PLAYER_NAME, nombreJugador);
                    intent.putExtra(StatsActivity.EXTRA_PLAYER_STATS, estadisticas);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return cardList.size();
        }

        public class CardViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView nameTextView;
            private TextView descriptionTextView;

            public CardViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.cardImage);
                nameTextView = itemView.findViewById(R.id.cardName);
                descriptionTextView = itemView.findViewById(R.id.cardDescription);
            }

            public void bind(Card card) {
                imageView.setImageResource(card.getImageResourceId());
                nameTextView.setText(card.getName());
                descriptionTextView.setText(card.getDescription());
            }
        }
    }
}
