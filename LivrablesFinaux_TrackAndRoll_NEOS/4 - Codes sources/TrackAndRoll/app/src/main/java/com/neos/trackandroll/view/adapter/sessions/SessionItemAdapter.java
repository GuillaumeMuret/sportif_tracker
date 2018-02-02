package com.neos.trackandroll.view.adapter.sessions;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neos.trackandroll.R;
import com.neos.trackandroll.model.constant.Fonts;
import com.neos.trackandroll.model.player.Player;
import com.neos.trackandroll.model.session.Session;
import com.neos.trackandroll.utils.DateUtils;
import com.neos.trackandroll.utils.ImageUtils;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SessionItemAdapter extends RecyclerView.Adapter<SessionItemAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Player> playerList;
    private Session currentSession;
    private String filterSession;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView icPlayerPhoto;
        View viewPlayerWithoutPicture;
        TextView tvPlayerNumber;
        TextView tvPlayerFirstName;
        TextView tvPlayerLastName;
        TextView tvPlayerSpeed;
        TextView tvPlayerDistance;
        TextView tvPlayerTime;
        TextView tvPlayerHeartBeat;

        public MyViewHolder(View view) {
            super(view);
            icPlayerPhoto = (CircleImageView) view.findViewById(R.id.icMyAccountPhoto);
            viewPlayerWithoutPicture = view.findViewById(R.id.viewPlayerWithoutPicture);
            tvPlayerNumber = (TextView) view.findViewById(R.id.tvPlayerNumber);
            tvPlayerFirstName = (TextView) view.findViewById(R.id.tvAccountFirstName);
            tvPlayerLastName = (TextView) view.findViewById(R.id.tvAccountLastName);
            tvPlayerSpeed = (TextView) view.findViewById(R.id.tvPlayerSpeed);
            tvPlayerDistance = (TextView) view.findViewById(R.id.tvPlayerDistance);
            tvPlayerTime = (TextView) view.findViewById(R.id.tvPlayerTime);
            tvPlayerHeartBeat = (TextView) view.findViewById(R.id.tvPlayerHeartBeat);
        }

    }

    /**
     * method that adapts the session item
     * @param context : the context
     * @param playerList : the player list
     * @param filterSession : the filter session
     * @param session : the session
     */
    public SessionItemAdapter(Context context, ArrayList<Player> playerList,String filterSession, Session session) {
        this.context=context;
        this.setSessionPlayerList(playerList);
        this.setFilterSession(filterSession,session);
    }

    /**
     * method that set the player list
     * @param playerList : the player list
     */
    public void setSessionPlayerList(ArrayList<Player> playerList){
        this.playerList=playerList;
    }

    /**
     * method that set the filter session
     * @param filterSession : the filter session
     * @param currentSession : the current session
     */
    public void setFilterSession(String filterSession, Session currentSession){
        this.filterSession=filterSession;
        this.currentSession = currentSession;
        notifyDataSetChanged();
    }

    /**
     * method that creates the view holder
     * @param parent : the the view group
     * @param viewType : the view type
     * @return the new view holder
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_session_player, parent, false);
        return new MyViewHolder(itemView);
    }

    /**
     * method that binds the view holder
     * @param holder : the view holder
     * @param position : the position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvPlayerNumber.setTypeface(Typeface.createFromAsset(context.getAssets(), Fonts.ROBOTO_LIGHT));
        holder.tvPlayerFirstName.setTypeface(Typeface.createFromAsset(context.getAssets(), Fonts.ROBOTO_LIGHT));
        holder.tvPlayerLastName.setTypeface(Typeface.createFromAsset(context.getAssets(), Fonts.ROBOTO_LIGHT));
        holder.tvPlayerSpeed.setTypeface(Typeface.createFromAsset(context.getAssets(), Fonts.ROBOTO_LIGHT));
        holder.tvPlayerDistance.setTypeface(Typeface.createFromAsset(context.getAssets(), Fonts.ROBOTO_LIGHT));
        holder.tvPlayerTime.setTypeface(Typeface.createFromAsset(context.getAssets(), Fonts.ROBOTO_LIGHT));
        holder.tvPlayerHeartBeat.setTypeface(Typeface.createFromAsset(context.getAssets(), Fonts.ROBOTO_LIGHT));

        if(ImageUtils.isFileImage(playerList.get(position).getPathPhoto())) {
            holder.viewPlayerWithoutPicture.setVisibility(View.INVISIBLE);
            holder.icPlayerPhoto.setVisibility(View.VISIBLE);
            ImageUtils.loadPhoto(playerList.get(position).getPathPhoto(), holder.icPlayerPhoto);
        }else{
            holder.viewPlayerWithoutPicture.setVisibility(View.VISIBLE);
            holder.icPlayerPhoto.setVisibility(View.INVISIBLE);
            holder.tvPlayerNumber.setText(String.valueOf(playerList.get(position).getPlayerNumber()));
        }

        holder.tvPlayerFirstName.setText(playerList.get(position).getFirstName());
        holder.tvPlayerLastName.setText(playerList.get(position).getLastName());

        if(currentSession != null && currentSession.getPlayerSessionDataMap() != null && currentSession.getPlayerSessionDataMap().get(playerList.get(position).getPlayerUUID()) != null){
            holder.tvPlayerSpeed.setText(String.format(
                    context.getResources().getString(R.string.player_profile_format_speed),
                    currentSession.getPlayerSessionDataMap().get(playerList.get(position).getPlayerUUID()).getPlayerAverageSpeed()
            ));
            holder.tvPlayerDistance.setText(String.format(
                    context.getResources().getString(R.string.player_profile_format_distance_m),
                    currentSession.getPlayerSessionDataMap().get(playerList.get(position).getPlayerUUID()).getPlayerTotalDistance()
            ));
            holder.tvPlayerTime.setText(
                    DateUtils.convertTimeToHourMinSecond(currentSession.getPlayerSessionDataMap().get(playerList.get(position).getPlayerUUID()).getPlayerTotalSessionTimeInSec())
            );
            holder.tvPlayerHeartBeat.setText(String.format(
                    context.getResources().getString(R.string.player_profile_format_heart_beat),
                    currentSession.getPlayerSessionDataMap().get(playerList.get(position).getPlayerUUID()).getPlayerBpmMax()
            ));
        }else{
            holder.tvPlayerSpeed.setText(context.getResources().getString(R.string.player_profile_format_speed_empty));
            holder.tvPlayerDistance.setText(context.getResources().getString(R.string.player_profile_format_distance_m_empty));
            holder.tvPlayerTime.setText(context.getResources().getString(R.string.player_profile_format_time_empty));
            holder.tvPlayerHeartBeat.setText(context.getResources().getString(R.string.player_profile_format_heart_beat_empty));
        }
    }

    /**
     * method that gets the item total count
     * @return
     */
    @Override
    public int getItemCount() {
        return playerList.size();
    }

}