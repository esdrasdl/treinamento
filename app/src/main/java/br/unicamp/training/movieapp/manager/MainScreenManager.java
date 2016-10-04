package br.unicamp.training.movieapp.manager;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class MainScreenManager {

    public void getSimpleJson(InternetCallback callback) {
        String url = "http://api.themoviedb.org/3/movie/popular?api_key=87a901020f496977f9d6d508c5d186ec&language=pt-BR";
        new PopularMovieAsyncTask(callback).execute(url);
    }

    public interface InternetCallback {
        void onSuccess(String result);
    }

    private class PopularMovieAsyncTask extends AsyncTask<String, Object, String> {

        InternetCallback listener;

        public PopularMovieAsyncTask(InternetCallback callback) {
            listener = callback;
        }

        @Override
        protected String doInBackground(String... params) {
            Reader reader = null;
            try {
                URL url = new URL(params[0]);
                reader = new InputStreamReader(url.openStream());
                return readInputStreamReader(reader);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            listener.onSuccess(s);
        }
    }

    private String readInputStreamReader(Reader inputStream) {
        BufferedReader r = new BufferedReader(inputStream);
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return total.toString();
    }

}
