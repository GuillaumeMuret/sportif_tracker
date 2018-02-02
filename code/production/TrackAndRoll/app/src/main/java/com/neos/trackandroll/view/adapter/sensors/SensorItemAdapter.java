package com.neos.trackandroll.view.adapter.sensors;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import fr.ganfra.materialspinner.MaterialSpinner;

public class SensorItemAdapter extends RecyclerView.Adapter<SensorItemAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<TrackAndRollSensor> positionSensorList;
    private ArrayList<TrackAndRollSensor> heartBeatSensorList;

    private ArrayList<String> playerStringList;
    private ArrayList<String> heartBeatSensorStringList;
    private ArrayList<String> positionSensorStringList;
    private int totalSensorSize;

    private HashMap<Integer, SensorItemAdapterInformation> adapterInformation;

    /**
     * the constructor of the class
     * @param context : the context
     * @param sensorList : the sensor list
     */
    public SensorItemAdapter(Context context, ArrayList<TrackAndRollSensor> sensorList) {
        this.context=context;
        adapterInformation = new HashMap<>();
        this.positionSensorList = new ArrayList<>();
        this.positionSensorStringList = new ArrayList<>();
        this.heartBeatSensorList = new ArrayList<>();
        this.heartBeatSensorStringList = new ArrayList<>();
        this.playerStringList = DataUtils.convertPlayerListToStringPlayerList(DataStore.getInstance().getPlayerList());

        this.positionSensorStringList.add(0, Constant.SENSOR_NON_ASSIGNED_NAME);
        this.heartBeatSensorStringList.add(0, Constant.SENSOR_NON_ASSIGNED_NAME);

        setSensorList(sensorList);
    }

    /**
     * method that sets the sensor list
     * @param sensorList : the sensor list
     */
    public void setSensorList(ArrayList<TrackAndRollSensor> sensorList){
        totalSensorSize = sensorList.size();
        DataUtils.convertSensorListToSensorTypeList(
                sensorList,
                this.positionSensorList,
                this.positionSensorStringList,
                this.heartBeatSensorList,
                this.heartBeatSensorStringList
        );
    }


    /**
     * Process called at the creation of the view holder (the item)
     * @param parent : the parent where the item are displayed
     * @param viewType : the view type
     * @return : the item view holder
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sensor, parent, false);
        return new MyViewHolder(itemView);
    }

    /**
     * Process called when the recycler view has to update the list view
     * @param holder : the holder where the item is displayed
     * @param position : the position in list
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.removeAllListeners();

        int selectedPlayerPositionPlusOne =
                adapterInformation.get(position) != null
                    ? adapterInformation.get(position).getSelectedPlayerPlusOne()
                    : 0;
        int selectedPositionSensorPlusOne =
                adapterInformation.get(position) != null
                        ? adapterInformation.get(position).getSelectedPositionSensorPlusOne()
                        : 0;
        int selectedHeartBeatSensorPlusOne =
                adapterInformation.get(position) != null
                        ? adapterInformation.get(position).getSelectedHeartBeatSensorPlusOne()
                        : 0;

        holder.updatePlayerView(selectedPlayerPositionPlusOne);
        holder.updatePositionSensorSpinner(position, adapterInformation, selectedPositionSensorPlusOne);
        holder.updateHeartBeatSensorSpinner(position, adapterInformation,selectedHeartBeatSensorPlusOne);
        holder.updatePlayerSpinner(position, adapterInformation,selectedPlayerPositionPlusOne);

        if(selectedPlayerPositionPlusOne>=0){
            holder.spPlayerList.setSelection(selectedPlayerPositionPlusOne);
        }

        updatePositionSensorStateIcon(holder,selectedPositionSensorPlusOne);
        updateHeartBeatSensorStateIcon(holder,selectedHeartBeatSensorPlusOne);
    }

    /**
     * method that updates the position sensor state icon
     * @param holder : the view holder
     * @param position : the position of the sensor
     */
    private void updatePositionSensorStateIcon(MyViewHolder holder, int position) {
        if(position>0){
            holder.imgPositionSensorStateBackground.setVisibility(View.VISIBLE);
            position = position-1;
            switch(positionSensorList.get(position).getSensorState()){
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
        }else{
            holder.imgPositionSensorStateBackground.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * method that updates the heart beat sensor state icon
     * @param holder : the view holder
     * @param position : the position of the sensor
     */
    private void updateHeartBeatSensorStateIcon(MyViewHolder holder, int position) {
        if(position>0){
            position = position-1;
            holder.imgHeartBeatSensorStateBackground.setVisibility(View.VISIBLE);
            switch(heartBeatSensorList.get(position).getSensorState()) {
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
        }else{
            holder.imgHeartBeatSensorStateBackground.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * method that gets the item counts
     * @return the total count
     */
    @Override
    public int getItemCount() {
        return totalSensorSize;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private MyViewHolder me;

        private CircleImageView icPlayerPhoto;
        private View viewPlayerWithoutPicture;
        private TextView tvPlayerNumber;
        private MaterialSpinner spPositionSensor;
        private MaterialSpinner spPlayerList;
        private MaterialSpinner spHeartBeatSensor;

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
            me = this;

            icPlayerPhoto = (CircleImageView) view.findViewById(R.id.icMyAccountPhoto);
            viewPlayerWithoutPicture = view.findViewById(R.id.viewPlayerWithoutPicture);
            tvPlayerNumber = (TextView) view.findViewById(R.id.tvPlayerNumber);
            spPlayerList = (MaterialSpinner) view.findViewById(R.id.spPlayerList);
            spPositionSensor = (MaterialSpinner) view.findViewById(R.id.spPositionSensor);
            spHeartBeatSensor = (MaterialSpinner) view.findViewById(R.id.spHeartBeatSensor);

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

            initSpinner();
        }

        /**
         * method that inits the spinner
         */
        private void initSpinner(){
            // Initializing player spinner
            spinnerPlayerAdapter = new SpinnerArrayAdapter(context,R.layout.spinner_item,playerStringList);
            spinnerPlayerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            spPlayerList.setAdapter(spinnerPlayerAdapter);

            // Initializing heart beat spinner
            spinnerHeartBeatAdapter = new SpinnerArrayAdapter(context,R.layout.spinner_item, heartBeatSensorStringList);
            spinnerHeartBeatAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            spHeartBeatSensor.setAdapter(spinnerHeartBeatAdapter);

            // Initializing position spinner
            spinnerPositionSensorAdapter = new SpinnerArrayAdapter(context,R.layout.spinner_item, positionSensorStringList);
            spinnerPositionSensorAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            spPositionSensor.setAdapter(spinnerPositionSensorAdapter);
        }

        /**
         * method that update the player spinner
         * @param indexInGlobalList : the global list index
         * @param adapterInformation : the sensor item adapter information hashmap
         */
        public void updatePlayerSpinner(
                final int indexInGlobalList,
                final HashMap<Integer, SensorItemAdapterInformation> adapterInformation,
                final int selectedPlayerPositionPlusOne
        ){
            spPlayerList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if(position > 0) {
                        if (adapterInformation.get(indexInGlobalList) == null) {
                            adapterInformation.put(indexInGlobalList, new SensorItemAdapterInformation());
                        } else {
                            adapterInformation.get(indexInGlobalList).setSelectedPlayerPlusOne(position);
                        }
                    }else{
                        adapterInformation.remove(indexInGlobalList);
                    }
                    updatePlayerView(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });
            spPlayerList.setSelection(selectedPlayerPositionPlusOne);
        }

        /**
         * method that update the heartbeat sensor spinner
         * @param indexInGlobalList : the global list index
         * @param adapterInformation : the sensor item adapter information hashmap
         */
        public void updateHeartBeatSensorSpinner(
                final int indexInGlobalList,
                final HashMap<Integer, SensorItemAdapterInformation> adapterInformation,
                int selectedHeartBeatSensorPlusOne
        ){
            spHeartBeatSensor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if(adapterInformation.get(indexInGlobalList)==null){
                        adapterInformation.put(indexInGlobalList, new SensorItemAdapterInformation());
                    }
                    adapterInformation.get(indexInGlobalList).setSelectedHeartBeatSensorPlusOne(position);
                    updateHeartBeatSensorStateIcon(me,position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });
            spHeartBeatSensor.setSelection(selectedHeartBeatSensorPlusOne);
        }

        /**
         * method that update the position sensor spinner
         * @param indexInGlobalList : the global list index
         * @param adapterInformation : the sensor item adapter information hashmap
         */
        public void updatePositionSensorSpinner(
                final int indexInGlobalList,
                final HashMap<Integer, SensorItemAdapterInformation> adapterInformation,
                int selectedPositionSensorPlusOne
        ){
            spPositionSensor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if(adapterInformation.get(indexInGlobalList)==null){
                        adapterInformation.put(indexInGlobalList, new SensorItemAdapterInformation());
                    }
                    adapterInformation.get(indexInGlobalList).setSelectedPositionSensorPlusOne(position);
                    updatePositionSensorStateIcon(me,position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });
            spPositionSensor.setSelection(selectedPositionSensorPlusOne);
        }

        /**
         * method that updates the player view
         */
        public void updatePlayerView(int selectedPlayerPositionPlusOne){
            if(selectedPlayerPositionPlusOne>0){
                ImageUtils.loadPhoto(DataStore.getInstance().getPlayerList()
                                .get(selectedPlayerPositionPlusOne-1).getPathPhoto()
                        ,icPlayerPhoto);

                if(ImageUtils.isFileImage(DataStore.getInstance().getPlayerList()
                        .get(selectedPlayerPositionPlusOne-1).getPathPhoto())) {
                    viewPlayerWithoutPicture.setVisibility(View.INVISIBLE);
                    icPlayerPhoto.setVisibility(View.VISIBLE);
                    ImageUtils.loadPhoto(DataStore.getInstance().getPlayerList()
                                    .get(selectedPlayerPositionPlusOne-1).getPathPhoto()
                            ,icPlayerPhoto);
                }else{
                    viewPlayerWithoutPicture.setVisibility(View.VISIBLE);
                    icPlayerPhoto.setVisibility(View.INVISIBLE);
                    tvPlayerNumber.setText(String.valueOf(DataStore.getInstance().getPlayerList()
                            .get(selectedPlayerPositionPlusOne-1).getPlayerNumber()));
                }
            }else{
                viewPlayerWithoutPicture.setVisibility(View.VISIBLE);
                icPlayerPhoto.setVisibility(View.INVISIBLE);
                tvPlayerNumber.setText("");
            }
        }

        /**
         * method that removes all listeners
         */
        public void removeAllListeners(){
            spPositionSensor.setOnItemSelectedListener(null);
            spHeartBeatSensor.setOnItemSelectedListener(null);
            spPlayerList.setOnItemSelectedListener(null);
        }
    }

    public HashMap<String, HashMap<String, String>> convertAdapterInformationToRunningSessionMap() {
        HashMap<String, HashMap<String, String>> runningSessionSensor = new HashMap<>();

        for (Integer key: adapterInformation.keySet()) {
            int selectedPlayerPosition = adapterInformation.get(key).getSelectedPlayerPlusOne() - 1;
            if(selectedPlayerPosition>=0){
                HashMap<String, String> sensorMap = new HashMap<>();
                int selectedHeartBeatSensorPositionPlusOne = adapterInformation.get(key).getSelectedHeartBeatSensorPlusOne();
                if(selectedHeartBeatSensorPositionPlusOne>0){
                    String sensorIndex = DataUtils.getSensorIdByCustomId
                            (heartBeatSensorStringList.get(selectedHeartBeatSensorPositionPlusOne));
                    LogUtils.d(LogUtils.DEBUG_TAG,"sensorIndex = "+" for "
                            +TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT+" = "+sensorIndex);
                    sensorMap.put(
                            TrackAndRollSensor.SENSOR_TYPE_HEART_BEAT,
                            sensorIndex
                    );
                }
                int selectedPositionSensorPositionPlusOne = adapterInformation.get(key).getSelectedPositionSensorPlusOne();
                if(selectedPositionSensorPositionPlusOne>0){
                    String sensorIndex = DataUtils.getSensorIdByCustomId(
                            positionSensorStringList.get(selectedPositionSensorPositionPlusOne));
                    LogUtils.d(LogUtils.DEBUG_TAG,"sensorIndex = "+" for "
                            +TrackAndRollSensor.SENSOR_TYPE_POSITION+" = "+sensorIndex);
                    sensorMap.put(
                            TrackAndRollSensor.SENSOR_TYPE_POSITION,
                            sensorIndex
                    );
                }
                runningSessionSensor.put(
                        DataStore.getInstance().getPlayerList().get(selectedPlayerPosition).getPlayerUUID(),
                        sensorMap
                );
            }
        }
        return runningSessionSensor;
    }
}