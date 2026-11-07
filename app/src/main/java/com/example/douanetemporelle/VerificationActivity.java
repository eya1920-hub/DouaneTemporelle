package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    // LES COMPOSANTS MTE3I ATHOUMA KL AADA
    private TextView tvNom, tvAge, tvMotif;
    private Button btnApprouver, btnRefuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // HOUNI RBATT LES COMPOSANTS MTAA L ACTV LOULA B THENIA
        tvNom = findViewById(R.id.tv_nom_er);
        tvAge = findViewById(R.id.tv_age_er);
        tvMotif = findViewById(R.id.tv_motif_er);
        btnApprouver = findViewById(R.id.btn_approuver_er);
        btnRefuser = findViewById(R.id.btn_refuser_er);

        // recu ll data li dakhltha mloul
        Intent i = getIntent();
        String matricule = "0456"; // M MATRUCULE LI STA3MLTHA FEL MainActivity(4DERNIERS CHIFF ML CIN MTE3I)

        String nom = i.getStringExtra(
                MainActivity.creerCle(matricule, "NOM_VOYAGEUR"));
        int anneeNaissance = i.getIntExtra(
                MainActivity.creerCle(matricule, "ANNEE_NAISSANCE"), 0);
        int anneeDestination = i.getIntExtra(
                MainActivity.creerCle(matricule, "ANNEE_DESTINATION"), 0);
        String motif = i.getStringExtra(
                MainActivity.creerCle(matricule, "MOTIF"));

        // calcul d age
        int ageTemporel = anneeDestination - anneeNaissance;

        // affichage des donnees saisies
        tvNom.setText("👤 Nom : " + nom);
        tvAge.setText("⏳ Âge temporel  : " + ageTemporel + " ans");
        tvMotif.setText("🎯 Motif :\n" + motif);
// TEST LEMZ SI LMOTIF est Modification du passé WEL AGE EST PREMIER NDEACTIVIW BOUTON APPROUVER
        if (motif != null && motif.contains("Modification du passé")
                && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
            btnApprouver.setAlpha(0.5f);
        }
    }

    // methode pour verifier si un nombre est premier ELLE BASEE SUR CETTE  REGLE "si un nombre possède un diviseur supérieur à sa racine carrée, il possède forcément aussi un diviseur inférieur à sa racine carrée."
    private boolean estPremier(int n) {
        if (n < 2) {
            return false;
        }
        for (int j = 2; j * j <= n; j++) {
            if (n % j == 0) {
                return false;
            }
        }
        return true;
    }
}