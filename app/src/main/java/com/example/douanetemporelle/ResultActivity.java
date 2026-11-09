package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    // nafs l matricule li staamltou fel activites lokhrin
    private String matricule = "0456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // rabtt les composants b l ids mta3hom
        View root = findViewById(R.id.main);
        ImageView imgVerdict = findViewById(R.id.img_verdict_er);
        TextView tvMsg = findViewById(R.id.tv_msg_er);
        TextView tvSousMsg = findViewById(R.id.tv_sous_message_er);
        ImageView imgAvion = findViewById(R.id.img_avion_er);
        Button btnNouveau = findViewById(R.id.btn_nouveau_er);

        // na9ra l verdict w esm l voyageur men l intent (nafs l cles)
        Intent i = getIntent();
        boolean approuve = i.getBooleanExtra(
                MainActivity.creerCle(matricule, "VERDICT"), false);
        String nom = i.getStringExtra(
                MainActivity.creerCle(matricule, "NOM_VOYAGEUR"));

        if (approuve) {
            // cas approuve : fond vert + check + message de bienvenue
            root.setBackgroundColor(Color.parseColor("#98E0B7"));
            imgVerdict.setImageResource(R.drawable.ic_check_er);
            imgAvion.setImageResource(R.drawable.avion_er);
            tvMsg.setText("Bon voyage " + nom + " !");
            tvMsg.setTextColor(Color.parseColor("#1E7A45"));
            tvSousMsg.setText("Profitez de votre voyage temporel ✨");
        } else {
            // cas refuse : fond rouge + croix + bouclier + message d arrestation
            root.setBackgroundColor(Color.parseColor("#F29A9A"));
            imgVerdict.setImageResource(R.drawable.ic_croix_er);
            imgAvion.setImageResource(R.drawable.bouclier_er);
            tvMsg.setText(nom + " est en état d'arrestation temporelle");
            tvMsg.setTextColor(Color.parseColor("#8B1A1A"));
            tvSousMsg.setText("Paradoxe détecté 🚨");
        }

        // bouton nouveau voyageur : narj3ou lel MainActivity w nsakrou  les activites kol li halthom
        btnNouveau.setOnClickListener(v -> {
            Intent retour = new Intent(ResultActivity.this, MainActivity.class);
            retour.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(retour);
        });
    }
}