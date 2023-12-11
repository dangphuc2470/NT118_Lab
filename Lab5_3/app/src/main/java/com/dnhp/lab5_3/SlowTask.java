package com.dnhp.lab5_3;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class SlowTask extends AsyncTask<String, Long, Void>
{
    private ProgressDialog pdWaiting;
    private Context context;
    private Long startTime;
    private TextView tvStatus;

    public SlowTask(Context context, TextView tvStatus) {
        this.context = context;
        this.tvStatus = tvStatus;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // Use UI thread here
        pdWaiting = new ProgressDialog(context);
        startTime = System.currentTimeMillis();
        tvStatus.setText("Start time: " + startTime);
        pdWaiting.setMessage(context.getString(R.string.please_wait));
        pdWaiting.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        // Doing SLOW job
        try {
            for (Long i = 0L; i < 3L; i++) {
                Thread.sleep(2000);
                publishProgress(i);
            }
        } catch (Exception e) {
            Log.e("SlowJob", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);

        tvStatus.append("\nWorking ... " + values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        // Use UI thread here
        if (pdWaiting.isShowing()) {
            pdWaiting.dismiss();
        }

        // Show done message
        tvStatus.append("\nEnd Time: " + System.currentTimeMillis());
        tvStatus.append("\nDone!");
    }

}
