package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // Define the base URL of your Node.js API
    private static final String BASE_URL = "mongodb://localhost:27017/gest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inside onCreate or a relevant method
        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnSeeTaskList = findViewById(R.id.btnSeeTaskList);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the text from EditTexts
                EditText nomEditText = findViewById(R.id.editTextNom);
                EditText descriptionEditText = findViewById(R.id.editTextDescription);
                EditText pointsEditText = findViewById(R.id.editTextPoints);

                String nom = nomEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                int points = Integer.parseInt(pointsEditText.getText().toString());

                // Use Retrofit to send data to your Node.js API
                sendTaskData(nom,  points);
            }
        });

        btnSeeTaskList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement logic to send request to Node.js API for retrieving task list
                // Use Retrofit, AsyncTask, or any other method to perform network requests
            }
        });
    }

    private void sendTaskData(String nom, int points) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TaskApiService apiService = retrofit.create(TaskApiService.class);

        // Create a TaskRequest object with the provided data
        TaskRequest newTask = new TaskRequest(nom, points);

        // Send the task data to your Node.js API asynchronously
        Call<TaskResponse> call = apiService.addTask(newTask);
        call.enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                // Handle successful response
                if (response.isSuccessful()) {
                    // You might want to update the UI or show a success message
                } else {
                    // Log error details
                    Log.e("Retrofit", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                // Handle failure
                // You might want to show an error message
                Log.e("Retrofit", "Exception: " + t.getMessage());
            }
        });
    }
}
