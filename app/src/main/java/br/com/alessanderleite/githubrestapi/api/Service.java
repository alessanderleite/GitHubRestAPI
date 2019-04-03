package br.com.alessanderleite.githubrestapi.api;

import br.com.alessanderleite.githubrestapi.model.ItemResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("/search/users?q=language:java+location:brazil")
    Call<ItemResponse> getItens();
}
