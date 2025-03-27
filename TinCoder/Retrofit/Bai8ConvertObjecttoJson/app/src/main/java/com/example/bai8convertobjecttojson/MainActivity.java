package com.example.bai8convertobjecttojson;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bai8convertobjecttojson.model.Address;
import com.example.bai8convertobjecttojson.model.Job;
import com.example.bai8convertobjecttojson.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = new User();
        user.setId(1);
        user.setName("Nguyen Van A");
        user.setAddress(new Address(1, "Ha Noi"));
        Job job1 = new Job("Developer");
        Job job2 = new Job("Tester");
        List<Job> jobs = new ArrayList<>(List.of(job1, job2));
        user.setJobs(jobs);

        Log.d("TAG", user.toJson());
    }

    private String getStringJson(User user) {
        String res;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", user.getId());
            jsonObject.put("name", user.getName());

            JSONObject address = new JSONObject();
            address.put(
                    "id",
                    user.getAddress()
                        .getId()
            );
            address.put(
                    "location",
                    user.getAddress()
                        .getLocation()
            );
            jsonObject.put("address", address);

            JSONArray jsonArray = new JSONArray();
            for (Job job : user.getJobs()) {
                JSONObject jobJson = new JSONObject();
                jobJson.put("jobName", job.getJobName());
                jsonArray.put(jobJson);
            }

            jsonObject.put("jobs", jsonArray);

            res = jsonObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return res;
    }
}