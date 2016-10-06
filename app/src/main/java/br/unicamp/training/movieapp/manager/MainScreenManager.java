package br.unicamp.training.movieapp.manager;

import br.unicamp.training.movieapp.model.Page;
import br.unicamp.training.movieapp.services.Service;
import br.unicamp.training.movieapp.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenManager {

    public void getList(final SmartCallback smartCallback) {
        Call<Page> call = Service.getPopularMovieAPI().getPage(Constants.API_KEY, 2);
        call.enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                if (response.isSuccessful()) {
                    smartCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public interface SmartCallback {
        void onSuccess(Page result);
    }

}
