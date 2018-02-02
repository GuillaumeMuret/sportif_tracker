package com.neos.trackandroll.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.neos.trackandroll.R;
import com.neos.trackandroll.model.session.PlayerSessionData;
import com.neos.trackandroll.model.session.Session;
import com.neos.trackandroll.model.session.data.PlayerPositionData;
import com.neos.trackandroll.utils.DialogUtils;
import com.neos.trackandroll.utils.FileUtils;
import com.neos.trackandroll.utils.LogUtils;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import ca.hss.heatmaplib.HeatMap;
import ca.hss.heatmaplib.HeatMapMarkerCallback;

public class DataDistanceActivity extends AbstractDataXXXActivity {

    private static final float OFFSET_ANCHOR_MASTER_X = 0.5f;
    private static final float OFFSET_ANCHOR_MASTER_Y = 0.82f;

    private static final float COEFF_X = 60; // For field of 60m of width
    private static final float COEFF_Y = 49; // For field of 30m of height

    private static final float DEFAULT_FIELD_WIDTH = 60;
    private static final float DEFAULT_FIELD_HEIGHT = 30;

    private static final float DEFAULT_INTENSITY_POINT_VALUE = 0.01f;

    private HeatMap heatMap;

    private TextView tvFieldWidth;
    private TextView tvFieldHeight;

    int fieldWidth = (int)DEFAULT_FIELD_WIDTH;
    int fieldHeight = (int) DEFAULT_FIELD_HEIGHT;

    private LinkedList<PlayerPositionData> playerPositionData = new LinkedList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_distance);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        tvFieldHeight = (TextView) findViewById(R.id.tv_field_height);
        tvFieldWidth = (TextView) findViewById(R.id.tv_field_width);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Get params from activities
        if (mapParams != null && mapParams.get(ServiceActivity.PARAM_PLAYER_KEY) != null) {
            Session session = FileUtils.loadSessionFile(
                    mapParams.get(ServiceActivity.PARAM_SESSION_NAME));
            if(session != null){
                PlayerSessionData playerSessionData = session.getPlayerSessionDataMap().get(
                        mapParams.get(ServiceActivity.PARAM_PLAYER_KEY)
                );
                if(playerSessionData != null && playerSessionData.getPlayerPositionData()!=null){
                    playerPositionData = playerSessionData.getPlayerPositionData();
                }
            }
        }

        tvFieldWidth.setText(String.valueOf(fieldWidth));
        tvFieldHeight.setText(String.valueOf(fieldHeight));

        heatMap = (HeatMap) findViewById(R.id.heat_map);
        heatMap.setMinimum(0.0);
        heatMap.setMaximum(100.0);
        heatMap.setMarkerCallback(new HeatMapMarkerCallback.CircleHeatMapMarker(0x00000000));
        heatMap.setRadius(20.0);
        Map<Float, Integer> colors = new ArrayMap<>();
        //build a color gradient in HSV from red at the center to green at the outside
        for (int i = 0; i < 21; i++) {
            float stop = ((float)i) / 20.0f;
            int color = doGradient(i * 5, 0, 100, 0xff00ff00, 0xffff0000);
            colors.put(stop, color);
        }
        heatMap.setColorStops(colors);

        addData();
    }

    /**
     * method that adds data
     */
    private void addData() {
        drawNewMap();
        heatMap.forceRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data_distance_activity_toolbar, menu);
        return true;
    }

    /**
     * method that draw a new map
     */
    @AnyThread
    private void drawNewMap() {
        heatMap.clearData();
        Random rand = new Random();



        if(playerPositionData != null){
            LogUtils.d(LogUtils.DEBUG_TAG,"playerPositionData != null");
            LogUtils.d(LogUtils.DEBUG_TAG,"playerPositionData.size() ="+playerPositionData.size());
            for (int i = 0; i < playerPositionData.size(); i++) {

                HeatMap.DataPoint point = new HeatMap.DataPoint(
                        clamp(
                                OFFSET_ANCHOR_MASTER_X + playerPositionData.get(i).getPosX() /
                                        ( COEFF_X * fieldWidth/ DEFAULT_FIELD_WIDTH )
                                , 0.0f, 1.0f
                        ),
                        clamp(
                                OFFSET_ANCHOR_MASTER_Y - playerPositionData.get(i).getPosY() /
                                        ( COEFF_Y * fieldHeight / DEFAULT_FIELD_HEIGHT )
                                , 0.0f, 1.0f
                        ),
                        clamp(DEFAULT_INTENSITY_POINT_VALUE, 0.0, 100.0)
                );
                heatMap.addData(point);
            }
        }

    }

    /**
     * method that display a point on screen
     * @param value : the value of the point
     * @param min : the min
     * @param max : the max
     */
    private float clamp(float value, float min, float max) {
        return value * (max - min) + min;
    }

    private double clamp(double value, double min, double max) {
        return value * (max - min) + min;
    }

    /**
     * method that do the gradient of the point
     * @param value : the intensity of the point
     * @param min : the min intensity of a point
     * @param max : the max intensity of a point
     * @param min_color : the min color value
     * @param max_color : the max color value
     * @return the color
     */
    private static int doGradient(double value, double min, double max, int min_color, int max_color) {
        if (value >= max) {
            return max_color;
        }
        if (value <= min) {
            return min_color;
        }
        float[] hsvmin = new float[3];
        float[] hsvmax = new float[3];
        float frac = (float)((value - min) / (max - min));
        Color.RGBToHSV(Color.red(min_color), Color.green(min_color), Color.blue(min_color), hsvmin);
        Color.RGBToHSV(Color.red(max_color), Color.green(max_color), Color.blue(max_color), hsvmax);
        float[] retval = new float[3];
        for (int i = 0; i < 3; i++) {
            retval[i] = interpolate(hsvmin[i], hsvmax[i], frac);
        }
        return Color.HSVToColor(retval);
    }

    private static float interpolate(float a, float b, float proportion) {
        return (a + ((b - a) * proportion));
    }


    @Override
    public void removePlayerData(){

    }

    public void updateHeatMap(int longueur, int larger){
        fieldWidth = longueur;
        fieldHeight = larger;
        tvFieldWidth.setText(String.valueOf(fieldWidth));
        tvFieldHeight.setText(String.valueOf(fieldHeight));
        addData();

    }

    /**
     * Process called when an item of the toolbar has been selected
     * @param item : the item selected
     * @return boolean Return false to allow normal menu processing to
     *          proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.action_delete_player_data:
                DialogUtils.displayDialogDeletePlayerData(this);
                return true;

            case R.id.action_set_field:
                DialogUtils.displayDialogSetField(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
