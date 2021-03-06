package sayan.example.com.gsonsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import sayan.example.com.gsonsample.pojos.PhoneNumber;
import sayan.example.com.gsonsample.pojos.UserAddress;
import sayan.example.com.gsonsample.pojos.UserDetails;

public class MainActivity extends AppCompatActivity {

    private Gson gsonObject;
    //private final String JSON_STRING = "{\"age\":32,\"firstName\":\"John\",\"gender\":\"Male\",\"lastName\":\"Smith\"}";
    private final String JSON_STRING= "{\"firstName\":\"John\",\"lastName\":\"Smith\",\"gender\":\"man\",\"age\":32,\"address\":{\"streetAddress\":\"21 2nd Street\",\"city\":\"New York\",\"state\":\"NY\",\"postalCode\":\"10021\"},\"phoneNumbers\":[{\"type\":\"home\",\"number\":\"212 555-1234\"},{\"type\":\"fax\",\"number\":\"646 555-4567\"}]}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gsonObject = new Gson();
        UserDetails pojo = gsonObject.fromJson(JSON_STRING, new TypeToken<UserDetails>(){}.getType());

        JsonObject addressJsonObject = pojo.getAddress();
        UserAddress address= gsonObject.fromJson(addressJsonObject.toString(), new TypeToken<UserAddress>(){}.getType());

        JsonArray phoneNumbersJsonArray = pojo.getPhoneNumbers();
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        ArrayList<JsonObject> phoneNumberJsonObjects = gsonObject.fromJson(phoneNumbersJsonArray, new TypeToken<ArrayList<JsonObject>>(){}.getType());
        for (JsonObject phoneNumberJsonObject :
                phoneNumberJsonObjects) {
            PhoneNumber phoneNo= gsonObject.fromJson(phoneNumberJsonObject.toString(), new TypeToken<PhoneNumber>(){}.getType());
            phoneNumbers.add(phoneNo);
        }

        String result = gsonObject.toJson(pojo);
        String adressResult = gsonObject.toJson(address);
        String phoneNoResult1 = gsonObject.toJson(phoneNumbers.get(0));
        String phoneNoResult2 = gsonObject.toJson(phoneNumbers.get(1));

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(result);
        textView.append("\n");
        textView.append("\n");
        textView.append(adressResult);
        textView.append("\n");
        textView.append("\n");
        textView.append(phoneNoResult1);
        textView.append("\n");
        textView.append("\n");
        textView.append(phoneNoResult2);
//        textView.append(phoneNoResult);
    }
}
