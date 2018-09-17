package com.example.gabri.afinal;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    Spinner spinner;
    ListView listView;
    ArrayAdapter<Events> adapter;
    ArrayList<Events> events_array;
    Control control;
    AlertDialog info;
    Events events1;

    TextView idPROD,idTYPE,idDATE,idTIME,idCOMMENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        control = new Control(this);

        listView = (ListView)findViewById(R.id.ListEvent);





        //construção do spinner
        spinner = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adp;
        adp = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_item);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);
        search();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                search();
                change();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        change();
    }
    private void click(int i){ //sla
        events1 = events_array.get(i);
        //Toast.makeText(this, events1.Comment.toString(), Toast.LENGTH_SHORT).show();
        showAlert(events1);
    } //sla
    public void search(){
        events_array = control.selectEvents(spinner.getSelectedItem().toString());
    } //pesquisa os itens
    public void change(){
        if(events_array.size() > 0){
            adapter = new ArrayAdapter<Events>(Search.this, android.R.layout.simple_expandable_list_item_1, events_array);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    click(position);

                }
            });
            listView.setAdapter(adapter);
        }

    } //Load listview
    public void showAlert(Events events){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("ID:"+events.ID+"\n"+"Comment: " + events.Comment.trim() + "\n" + "Time:" + events.Time);

        info = builder.create();
        info.show();

    } //Iniciar Alerta
    public void closeAlert(View view){
        info.dismiss();
    } //Fechar Alerta
}
