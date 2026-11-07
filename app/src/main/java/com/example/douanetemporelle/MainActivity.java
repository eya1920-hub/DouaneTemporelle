
package com.example.douanetemporelle;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nom, ann_nais, ann_dest;
    private Spinner motif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des champs
        nom = findViewById(R.id.edt_nom_er);
        ann_nais = findViewById(R.id.edt_naissance_er);
        ann_dest = findViewById(R.id.edt_destination_er);
        motif = findViewById(R.id.sp_motif_er);
        btnInspecter = findViewById(R.id.btn_inspecter_er);

        String[] motifs = {
                "🌴 Tourisme",
                "⏳ Modification du passé",
                "🏃 Fuite"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                motifs
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        ); // KIFEH BECH YBENOU LES ELEMENTS MTAA LISTE DEROULANTE

        motif.setAdapter(adapter);

        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nom.getText().toString().trim().isEmpty()
                        || ann_nais.getText().toString().trim().isEmpty()
                        || ann_dest.getText().toString().trim().isEmpty()) {

                    Toast.makeText(MainActivity.this,
                            "Veuillez remplir tous les champs",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // BECH T EVITI ENHA DAKHALI NOM FIH ARKAM
                if (!nom.getText().toString().trim().matches("[a-zA-ZÀ-ÿ ]+")) {

                    Toast.makeText(MainActivity.this,
                            "Le nom ne doit contenir que des lettres",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                // DECALARTION DES 2VAS BECH NSTOCKI FIHOM LES VALS DU USER W AAMLT TRY W CATCH BECH NDECLENCHI EXCEP AU CAS FAMECH COMPATBLITE MAA LMATLOUB
                int anneeNaissance;
                int anneeDestination;
                // UNE ANNEE  DOIT CONTENIR 4 CHIFS
                if (ann_nais.getText().toString().trim().length() != 4
                        || ann_dest.getText().toString().trim().length() != 4) {
                    Toast.makeText(MainActivity.this,
                            "L'année doit contenir 4 chiffres",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    anneeNaissance = Integer.parseInt(
                            ann_nais.getText().toString().trim());

                    anneeDestination = Integer.parseInt(
                            ann_dest.getText().toString().trim());

                } catch (NumberFormatException e) {

                    Toast.makeText(MainActivity.this,
                            "Veuillez saisir des années valides",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (anneeNaissance >= anneeDestination) {

                    Toast.makeText(MainActivity.this,
                            "L'année de destination doit être supérieure à l'année de naissance",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent i = new Intent(MainActivity.this,
                        VerificationActivity.class);
                String matricule = "0456";

                i.putExtra(creerCle(matricule, "NOM_VOYAGEUR"),
                        nom.getText().toString().trim());
                i.putExtra(creerCle(matricule, "ANNEE_NAISSANCE"),
                        anneeNaissance);
                i.putExtra(creerCle(matricule, "ANNEE_DESTINATION"),
                        anneeDestination);
                i.putExtra(creerCle(matricule, "MOTIF"),
                motif.getSelectedItem().toString());
                startActivity(i);
            }

        });
    }

    public static String creerCle(String matricule, String nomCle) {// METH LI TASNA3 L CLE A PARTIR ML  MATRICULE LENGHTH MTE3HA 4
        return matricule.substring(matricule.length() - 4) + "_" + nomCle;
    }
}
