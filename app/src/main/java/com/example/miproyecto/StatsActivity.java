package com.example.miproyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StatsActivity extends AppCompatActivity {
    public static final String EXTRA_PLAYER_NAME = "player_name";
    public static final String EXTRA_PLAYER_STATS = "player_stats";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Configurar el OnClickListener para el botón
        Button btnBackToMain = findViewById(R.id.btnsalir);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para volver a la MainActivity
                Intent intent = new Intent(StatsActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Opcional: finalizar la actividad actual
            }
        });

        // Obtener referencias a las vistas
        TextView playerNameTextView = findViewById(R.id.playerNameTextView);
        TextView playerStatsTextView = findViewById(R.id.playerStatsTextView);

        // Verificar si hay datos extra en el Intent
        if (getIntent().getExtras() != null) {
            String playerName = getIntent().getStringExtra(EXTRA_PLAYER_NAME);
            String playerStats = getIntent().getStringExtra(EXTRA_PLAYER_STATS);

            // Configurar el texto en las vistas
            playerNameTextView.setText(playerName);
            playerStatsTextView.setText(playerStats);
        }
    }
}
