package com.example.iste.egim;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tvAcilar,tvDegerler,tvKutu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor s=sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(this,s,100);

        tvAcilar=findViewById(R.id.tvAcilar);
        tvDegerler=findViewById(R.id.tvDegerler);
        tvKutu=findViewById(R.id.tvKutu);
    }

    float eskiX=0;
    float eskiY=0;
    float eskiZ=0;
    float hassasiyet=0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x=event.values[0];
        float y=event.values[1];
        float z=event.values[2];

        if(Math.abs(x-eskiX)<hassasiyet && Math.abs(y-eskiY)<hassasiyet
                                        && Math.abs(z-eskiZ)<hassasiyet)
            return;

        eskiX=x; eskiY=y; eskiZ=z;

        tvDegerler.setText("x: "+x+" - y: "+y+" - z: " + z);

        float aci1=(float)(180.0f*Math.atan(x/y)/Math.PI);
        float aci2=(float)(180.0f*Math.atan(y/z)/Math.PI);
        float aci3=(float)(180.0f*Math.atan(z/x)/Math.PI);

        tvAcilar.setText("x: "+aci1+" - y: " + aci2 + " - z: " + aci3);

        tvKutu.setRotation(aci1);
        tvKutu.setRotationX(aci2);
        tvKutu.setRotationY(aci3);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
