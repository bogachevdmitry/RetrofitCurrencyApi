import retrofit2.Call;
import retrofit2.http.GET;

public interface ExchangeRateApi {

    @GET("/latest")
    Call<CurrencyInfo> getRates();
}
