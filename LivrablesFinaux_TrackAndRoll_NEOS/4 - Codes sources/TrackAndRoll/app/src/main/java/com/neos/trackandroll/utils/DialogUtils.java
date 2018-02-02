package com.neos.trackandroll.utils;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.neos.trackandroll.R;
import com.neos.trackandroll.model.DataStore;
import com.neos.trackandroll.model.session.Session;
import com.neos.trackandroll.view.activity.AbstractActivity;
import com.neos.trackandroll.view.activity.AbstractDataXXXActivity;
import com.neos.trackandroll.view.activity.DataDistanceActivity;
import com.neos.trackandroll.view.activity.PlayersActivity;

import java.util.Calendar;

public class DialogUtils {

    /**
     * Method that draws a dialog to ask if sure to remove the selected players
     * @param playersActivity : the players activity
     */
    public static void displayDialogSureToRemoveSelectedPlayers(final PlayersActivity playersActivity) {
        new MaterialDialog.Builder(playersActivity)
                .title(R.string.dialog_delete_selected_players_title)
                .content(R.string.dialog_delete_selected_players_content)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .negativeText(R.string.dialog_cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .positiveText(R.string.dialog_confirm)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        playersActivity.removeSelectedPlayers();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Method that draws a dialog to ask if sure to quit the app
     * @param abstractActivity : the activity
     */
    public static void displayDialogSureToQuitApp(final AbstractActivity abstractActivity) {
        new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_quit_app_title)
                .content(R.string.dialog_quit_app_content)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .negativeText(R.string.dialog_cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .positiveText(R.string.dialog_confirm)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        abstractActivity.quitApplication();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Method that draws a dialog to ask if sure to remove a player
     * @param abstractActivity : the activity
     */
    public static void displayDialogSureToRemovePlayer(final AbstractActivity abstractActivity) {
        new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_delete_player_title)
                .content(R.string.dialog_delete_player_content)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .negativeText(R.string.dialog_cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .positiveText(R.string.dialog_confirm)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        abstractActivity.removePlayer();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Method that draws a dialog to ask if sure to export the sesion in CSV
     * @param abstractActivity : the activity
     * @param session : the session
     */
    public static void displayDialogSureToExportSession(final AbstractActivity abstractActivity, final Session session) {
        new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_export_csv_title)
                .content(R.string.dialog_export_csv_content)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .negativeText(R.string.dialog_cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .positiveText(R.string.dialog_confirm)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        abstractActivity.displaySaveInProgress();
                        abstractActivity.generateCsvSessionFile(session);
                    }
                })
                .show();
    }

    /**
     * Method that draws a dialog to rename the session
     * @param abstractActivity : the activity
     * @param filterName : the filter name
     */
    public static void displayDialogSetNameSession(final AbstractActivity abstractActivity, String filterName) {

        MaterialDialog dialog = new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_set_name_session_title)
                .customView(R.layout.dialog_set_name_session_content, false)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .negativeText(R.string.dialog_cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .positiveText(R.string.dialog_confirm)
                .build();

        final EditText etSetSessionName = (EditText) dialog.findViewById(R.id.etSetSessionName);
        etSetSessionName.setText(filterName);

        dialog.getBuilder().onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if (!etSetSessionName.getText().toString().isEmpty()
                        && !DataStore.getInstance().getAppConfiguration().getSessionNameList().contains(etSetSessionName.getText().toString())) {
                    abstractActivity.setSessionName(etSetSessionName.getText().toString());
                }
                dialog.dismiss();
            }
        }).show();
    }


    public static void displayDialogSetField(final DataDistanceActivity dataDistanceActivity) {

        MaterialDialog dialog = new MaterialDialog.Builder(dataDistanceActivity)
                .title(R.string.dialog_set_field_title)
                .customView(R.layout.dialog_set_field_content, false)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .negativeText(R.string.dialog_cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .positiveText(R.string.dialog_confirm)
                .build();

        final EditText etFieldLongueur = (EditText) dialog.findViewById(R.id.etSetLongueur);
        final EditText etFieldLarger = (EditText) dialog.findViewById(R.id.etSetLarger);

        dialog.getBuilder().onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if (!etFieldLongueur.getText().toString().isEmpty()
                        && !etFieldLarger.getText().toString().isEmpty()) {
                    dataDistanceActivity.updateHeatMap(
                            Integer.valueOf(etFieldLongueur.getText().toString()),
                            Integer.valueOf(etFieldLarger.getText().toString())
                    );
                }
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * Method that draws a dialog to say that saving is in process
     * @param abstractActivity : the activity
     * @return the dialog
     */
    public static MaterialDialog displayDialogSavingInProgress(final AbstractActivity abstractActivity) {

        MaterialDialog materialDialog = new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_saving_session_title)
                .customView(R.layout.dialog_saving_session_content, false)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)
                .build();

        ProgressBar pcSavingAuth = (ProgressBar) materialDialog.findViewById(R.id.pcSavingSession);
        pcSavingAuth.getIndeterminateDrawable().setColorFilter(abstractActivity.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        materialDialog.show();
        return materialDialog;
    }

    /**
     * Method that draws a dialog to name the running session
     * @param abstractActivity : the activity
     * @param endInstant : the date and hour of the ending moment
     */
    public static void displayDialogNameRunningSession(final AbstractActivity abstractActivity, final Calendar endInstant) {

        MaterialDialog dialog = new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_name_running_session_title)
                .customView(R.layout.dialog_name_running_session_content, false)
                .cancelable(false)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .positiveText(R.string.dialog_save)
                .build();

        final EditText etSetSessionName = (EditText) dialog.findViewById(R.id.etSetSessionName);

        etSetSessionName.setText(DataUtils.getDefaultSessionName(endInstant));

        dialog.getBuilder().onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if (!etSetSessionName.getText().toString().isEmpty()
                        && !DataStore.getInstance().getAppConfiguration().getSessionNameList().contains(etSetSessionName.getText().toString())) {
                    abstractActivity.saveAndAddNewSession(etSetSessionName.getText().toString());
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                    Toast.makeText(abstractActivity, abstractActivity.getResources().getString(R.string.error_session_name), Toast.LENGTH_SHORT).show();
                    DialogUtils.displayDialogNameRunningSession(abstractActivity, endInstant);
                }
            }
        }).show();
    }

    /**
     * Method that draws a dialog if sure to delete the session
     * @param abstractActivity : the activity
     */
    public static void displayDialogDeleteSession(final AbstractActivity abstractActivity) {
        new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_delete_session_title)
                .content(R.string.dialog_delete_session_content)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .negativeText(R.string.dialog_cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .positiveText(R.string.dialog_confirm)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        abstractActivity.removeSession();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Method that draws a dialog to ask if sure to delete the player datas
     * @param abstractActivity : the activity
     */
    public static void displayDialogDeletePlayerData(final AbstractActivity abstractActivity) {
        new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_delete_player_data_title)
                .content(R.string.dialog_delete_player_data_content)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .negativeText(R.string.dialog_cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .positiveText(R.string.dialog_confirm)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        abstractActivity.removePlayerSession();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Method that draws a dialog to warns that it is impossible to remove the global session
     * @param abstractActivity the activity
     */
    public static void displayDialogCannotRemoveGlobalSession(final AbstractActivity abstractActivity) {
        new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_cannot_delete_session_title)
                .content(R.string.dialog_cannot_delete_session_content)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .positiveText(R.string.dialog_ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Method that draws a dialog to says that the global session cannot be customed
     * @param abstractActivity the acctivity
     */
    public static void displayDialogCannotCustomGlobalSession(final AbstractActivity abstractActivity) {
        new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_cannot_custom_session_title)
                .content(R.string.dialog_cannot_custom_session_content)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .positiveText(R.string.dialog_ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Method that draws a dialog to ask if sure to delete the running session
     * @param abstractActivity the activity
     */
    public static void displayDialogDeleteRunningSession(final AbstractActivity abstractActivity) {
        new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_delete_running_session_title)
                .content(R.string.dialog_delete_running_session_content)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .positiveText(R.string.dialog_confirm)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        abstractActivity.deleteRunningSession();
                    }
                })

                .negativeText(R.string.dialog_cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Method that draws a dialog to ask what to do in the end of the session
     * @param abstractActivity the activity
     */
    public static void displayDialogEndSession(final AbstractActivity abstractActivity) {
        new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_end_session_title)
                .content(R.string.dialog_end_session_content)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .negativeText(R.string.dialog_cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .neutralText(R.string.dialog_delete)
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        DialogUtils.displayDialogDeleteRunningSession(abstractActivity);
                    }
                })

                .positiveText(R.string.dialog_save)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        abstractActivity.stopRunningSessionTimeForSaving();
                    }
                })
                .show();
    }

    /**
     * Method that draws a dialog to if sure to remove the player data
     * @param abstractDataXXXActivity the activity
     */
    public static void displayDialogSureToRemovePlayerData(final AbstractDataXXXActivity abstractDataXXXActivity) {
        new MaterialDialog.Builder(abstractDataXXXActivity)
                .title(R.string.dialog_delete_player_data_title)
                .content(R.string.dialog_delete_player_data_content)
                .cancelable(true)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .negativeText(R.string.dialog_cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .positiveText(R.string.dialog_confirm)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        abstractDataXXXActivity.removePlayerData();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Method that draws a dialog to says that target is reconnecting
     * @param abstractActivity the activity
     */
    public static MaterialDialog displayDialogTargetReconnecting(final AbstractActivity abstractActivity) {

        MaterialDialog materialDialog = new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_target_reconnecting_title)
                .customView(R.layout.dialog_target_reconnecting_content, false)
                .cancelable(false)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .negativeText(R.string.dialog_session)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        abstractActivity.displaySessionActivity();
                        dialog.dismiss();
                    }
                })
                .positiveText(R.string.dialog_help)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        abstractActivity.startHelpActivity();
                        dialog.dismiss();
                    }
                })
                .build();

        ProgressBar pcSavingAuth = (ProgressBar) materialDialog.findViewById(R.id.pcSavingSession);
        pcSavingAuth.getIndeterminateDrawable().setColorFilter(abstractActivity.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        materialDialog.show();
        return materialDialog;
    }

    /**
     * Method that draws a dialog to alert permission are not granted
     * @param abstractActivity the activity
     */
    public static void displayDialogAlertPermission(final AbstractActivity abstractActivity){
        new MaterialDialog.Builder(abstractActivity)
                .title(R.string.dialog_alert_permission_title)
                .content(R.string.dialog_alert_permission_content)
                .cancelable(false)
                .backgroundColorRes(R.color.colorDialogBackground)
                .contentColor(Color.WHITE)
                .titleColorRes(R.color.colorDialogTitle)
                .positiveColorRes(R.color.colorDialogButton)
                .neutralColorRes(R.color.colorDialogButton)
                .negativeColorRes(R.color.colorDialogButton)

                .positiveText(android.R.string.ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", abstractActivity.getPackageName(), null);
                        intent.setData(uri);
                        abstractActivity.startActivityForResult(intent, PermissionUtils.MY_PERMISSIONS_REQUEST_STORAGE);
                    }
                })
                .show();
    }
}
