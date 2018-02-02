package com.neos.trackandroll.view.adapter.sensors;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neos.trackandroll.R;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.constant.Constant;
import com.neos.trackandroll.model.sensor.TrackAndRollSensor;
import com.neos.trackandroll.utils.DataUtils;
import com.neos.trackandroll.utils.ImageUtils;
import com.neos.trackandroll.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class RunningSensorItemAdapter extends RecyclerView.Adapter<RunningSensorItemAdapter.MyViewHolder>{

    private Context context;

    private ArrayList<String> uuidPlayerList;
    HashMap<String, HashMap<String,String>> runningSessionPlayerAndSensor;

    private int totalPlayerSize;

    /**
     * the constructor of the class
     * @param context : the context
     */
    public RunningSensorItemAdapter(Context context) {
        this.context = context;

        runningSessionPlayerAndSensor = DataStore.getInstance().getRunningSessionSensorMap();
        uuidPlayerList = new ArrayList<>();
        uuidPlayerList.addAll(runningSessionPlayerAndSensor.keySet());
        totalPlayerSize = uuidPlayerList.size();
    }

    /**
     * Process called at the creation of the view
     * @param parent : the parent of the view (where the item are displayed)
     * @param viewType : the view type
     * @return : the item where the information are displayed
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_running_sensor, parent, false);
        return new MyViewHolder(itemView);
    }

    /**
     * Process called when the recycler view has to update the holder
     * @param holder : the holder of the view
     * @param position : the position in list to update
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.updatePlayerView(uuidPlayerList.get(position));

        // Player name
        holder.tvPlayerName.setText(
                String.format("%s %s",
                DataStore.getInstance().getAppConfiguration().getPlayerMap().get(uuidPlayerList.get(position)).getLastName(),
                DataStore.getInstance().getAppConfiguration().getPlayerMap().get(uuidPlayerList.get(position)).getFirstName())
        );

        // Sensor position name
        boolean containKey = runningSessionPlayerAndSensor.get(uuidPlayerList.get(position)).containsKey(TrackAndRollSensor.SENSOR_TYPE_POSITION);
        LogUtils.d(LogUtils.DEBUG_TAG,"Contain key = "+containKey);
        if(containKey) {
            TrackAndRollSensor positionSensor =
                    DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList()
                            .get(DataUtils.getPositionSensorInListById(
                                    runningSessionPlayerAndSensor
                                            .get(uuidPlayerList
                                                    .get(position))
                                            .get(TrackAndRollSensor.SENSOR_TYPE_POSITION)));
            String sensorPositionName = positionSensor.getCustomSensorId();
            LogUtils.d(LogUtils.DEBUG_TAG, "position sensor name = " + sensorPositionName);
            holder.layoutSensorPosition.setVisibility(View.VISIBLE);
            holder.tvSensorPositionName.setText(sensorPositionName);
            updatePositionSensorStateIcon(holder, positionSensor);
        }else{
            holder.layoutSensorPosition.setVisibility(View.GONE);
        }

        // Sensor heart beat name
        containKey = runningSessionPlayerAndSensor.get(uuidPlayerList.get(position)).containsKey(TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT);
        if(containKey){
            TrackAndRollSensor heartBeatSensor =
                    DataStore.getInstance().getAppConfiguration().getTrackAndRollSensorList()
                            .get(DataUtils.getHeartBeatSensorInListById(
                                    runningSessionPlayerAndSensor
                                            .get(uuidPlayerList
                                                    .get(position))
                                            .get(TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT)));
            String sensorHeartBeatName = heartBeatSensor.getCustomSensorId();
            LogUtils.d(LogUtils.DEBUG_TAG,"position sensor name = "+sensorHeartBeatName);
            holder.layoutSensorHeartBeat.setVisibility(View.VISIBLE);
            holder.tvSensorHeartBeatName.setText(sensorHeartBeatName);
            updateHeartBeatSensorStateIcon(holder, heartBeatSensor);
        }else{
            holder.layoutSensorHeartBeat.setVisibility(View.GONE);
        }
    }

    /**
     * method that update the position sensor state incon
     * @param holder : the view holder
     * @param trackAndRollSensor : the sensor
     */
    private void updatePositionSensorStateIcon(MyViewHolder holder, TrackAndRollSensor trackAndRollSensor) {
        switch(trackAndRollSensor.getSensorState()){
            case Constant.SENSOR_STATE_DISCONNECTED:
                holder.pbPositionStateConnectionProgress.setVisibility(View.INVISIBLE);
                holder.imgPositionSensorStateSrc.setVisibility(View.VISIBLE);
                holder.imgPositionSensorStateBackground.setBackgroundResource(R.drawable.circle_red);
                holder.imgPositionSensorStateSrc.setBackgroundResource(R.drawable.ic_white_cross);
                break;

            case Constant.SENSOR_STATE_CONNECTED:
                holder.pbPositionStateConnectionProgress.setVisibility(View.INVISIBLE);
                holder.imgPositionSensorStateSrc.setVisibility(View.VISIBLE);
                holder.imgPositionSensorStateBackground.setBackgroundResource(R.drawable.circle_green);
                holder.imgPositionSensorStateSrc.setBackgroundResource(R.drawable.ic_valid);
                break;

            case Constant.SENSOR_STATE_WARNING:
                holder.pbPositionStateConnectionProgress.setVisibility(View.INVISIBLE);
                holder.imgPositionSensorStateSrc.setVisibility(View.VISIBLE);
                holder.imgPositionSensorStateBackground.setBackgroundResource(R.drawable.circle_orange);
                holder.imgPositionSensorStateSrc.setBackgroundResource(R.drawable.ic_warning);
                break;

            case Constant.SENSOR_STATE_CONNECTION_PROGRESS:
                holder.pbPositionStateConnectionProgress.setVisibility(View.VISIBLE);
                holder.imgPositionSensorStateSrc.setVisibility(View.INVISIBLE);
                holder.imgPositionSensorStateBackground.setBackgroundResource(R.drawable.circle_transparent);
                break;
        }
    }

    /**
     * method that update the heartbeat sensor state incon
     * @param holder : the view holder
     * @param trackAndRollSensor : the sensor
     */
    private void updateHeartBeatSensorStateIcon(MyViewHolder holder, TrackAndRollSensor trackAndRollSensor) {
        switch(trackAndRollSensor.getSensorState()){
            case Constant.SENSOR_STATE_DISCONNECTED:
                holder.pbHeartBeatStateConnectionProgress.setVisibility(View.INVISIBLE);
                holder.imgHeartBeatSensorStateSrc.setVisibility(View.VISIBLE);
                holder.imgHeartBeatSensorStateBackground.setBackgroundResource(R.drawable.circle_red);
                holder.imgHeartBeatSensorStateSrc.setBackgroundResource(R.drawable.ic_white_cross);
                break;

            case Constant.SENSOR_STATE_CONNECTED:
                holder.pbHeartBeatStateConnectionProgress.setVisibility(View.INVISIBLE);
                holder.imgHeartBeatSensorStateSrc.setVisibility(View.VISIBLE);
                holder.imgHeartBeatSensorStateBackground.setBackgroundResource(R.drawable.circle_green);
                holder.imgHeartBeatSensorStateSrc.setBackgroundResource(R.drawable.ic_valid);
                break;

            case Constant.SENSOR_STATE_WARNING:
                holder.pbHeartBeatStateConnectionProgress.setVisibility(View.INVISIBLE);
                holder.imgHeartBeatSensorStateSrc.setVisibility(View.VISIBLE);
                holder.imgHeartBeatSensorStateBackground.setBackgroundResource(R.drawable.circle_orange);
                holder.imgHeartBeatSensorStateSrc.setBackgroundResource(R.drawable.ic_warning);
                break;

            case Constant.SENSOR_STATE_CONNECTION_PROGRESS:
                holder.pbHeartBeatStateConnectionProgress.setVisibility(View.VISIBLE);
                holder.imgHeartBeatSensorStateSrc.setVisibility(View.INVISIBLE);
                holder.imgHeartBeatSensorStateBackground.setBackgroundResource(R.drawable.circle_transparent);
                break;
        }
    }

    /**
     * method that gets the items count
     * @return the count
     */
    @Override
    public int getItemCount() {
        return totalPlayerSize;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView icPlayerPhoto;
        private View viewPlayerWithoutPicture;
        private TextView tvPlayerNumber;
        private TextView tvSensorPositionName;
        private TextView tvPlayerName;
        private TextView tvSensorHeartBeatName;

        private View layoutSensorPosition;
        private View layoutSensorHeartBeat;

        // State sensor position
        private View imgPositionSensorStateBackground;
        private ImageView imgPositionSensorStateSrc;
        private ProgressBar pbPositionStateConnectionProgress;

        // State sensor heart beat
        private View imgHeartBeatSensorStateBackground;
        private ImageView imgHeartBeatSensorStateSrc;
        private ProgressBar pbHeartBeatStateConnectionProgress;

        private SpinnerArrayAdapter spinnerPlayerAdapter;
        private SpinnerArrayAdapter spinnerHeartBeatAdapter;
        private SpinnerArrayAdapter spinnerPositionSensorAdapter;

        /**
         * the constructor of the class
         * @param view : the view
         */
        public MyViewHolder(View view) {
            super(view);

            icPlayerPhoto = (CircleImageView) view.findViewById(R.id.icMyAccountPhoto);
            viewPlayerWithoutPicture = view.findViewById(R.id.viewPlayerWithoutPicture);
            layoutSensorPosition = view.findViewById(R.id.layoutSensorPosition);
            layoutSensorHeartBeat = view.findViewById(R.id.layoutSensorHeartBeat);
            tvPlayerNumber = (TextView) view.findViewById(R.id.tvPlayerNumber);
            tvPlayerName = (TextView) view.findViewById(R.id.tvPlayerName);
            tvSensorPositionName = (TextView) view.findViewById(R.id.tvSensorPositionName);
            tvSensorHeartBeatName = (TextView) view.findViewById(R.id.tvSensorHeartBeatName);

            // Sensor Position
            imgPositionSensorStateBackground = (View) view.findViewById(R.id.imgPositionSensorStateBackground);
            imgPositionSensorStateSrc = (ImageView) view.findViewById(R.id.imgPositionSensorStateSrc);
            pbPositionStateConnectionProgress = (ProgressBar) view.findViewById(R.id.pbPositionStateConnectionProgress);
            pbPositionStateConnectionProgress.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

            // Sensor heart beat
            imgHeartBeatSensorStateBackground = (View) view.findViewById(R.id.imgHeartBeatSensorStateBackground);
            imgHeartBeatSensorStateSrc = (ImageView) view.findViewById(R.id.imgHeartBeatSensorStateSrc);
            pbHeartBeatStateConnectionProgress = (ProgressBar) view.findViewById(R.id.pbHeartBeatStateConnectionProgress);
            pbHeartBeatStateConnectionProgress.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

        }

        /**
         * method that update the player view
         * @param uuidPlayer : the uuid of the player
         */
        public void updatePlayerView(String uuidPlayer){
            ImageUtils.loadPhoto(DataStore.getInstance().getAppConfiguration().getPlayerMap()
                            .get(uuidPlayer).getPathPhoto()
                    ,icPlayerPhoto);

            if(ImageUtils.isFileImage(DataStore.getInstance().getAppConfiguration().getPlayerMap()
                    .get(uuidPlayer).getPathPhoto())) {
                viewPlayerWithoutPicture.setVisibility(View.INVISIBLE);
                icPlayerPhoto.setVisibility(View.VISIBLE);
                ImageUtils.loadPhoto(DataStore.getInstance().getAppConfiguration().getPlayerMap()
                                .get(uuidPlayer).getPathPhoto()
                        ,icPlayerPhoto);
            }else{
                viewPlayerWithoutPicture.setVisibility(View.VISIBLE);
                icPlayerPhoto.setVisibility(View.INVISIBLE);
                tvPlayerNumber.setText(String.valueOf(DataStore.getInstance().getAppConfiguration().getPlayerMap()
                        .get(uuidPlayer).getPlayerNumber()));
            }
        }
    }
}