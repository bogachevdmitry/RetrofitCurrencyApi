import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main {
    public static void main(String[] args) {

        final String BASE_URL = "https://api.exchangeratesapi.io";
        Gson gson = new Gson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExchangeRateApi service = retrofit.create(ExchangeRateApi.class);

        Call<CurrencyInfo> callSync = service.getRates();

        try {
            String response = gson.toJson(callSync.execute().body());
            CurrencyInfo currencyInfo = gson.fromJson(response, CurrencyInfo.class);
            String info = String.format("Value of the %s on %s date across the following currencies:",
                    currencyInfo.getBase(),
                    currencyInfo.getDate());
            System.out.println(info);
            currencyInfo.getRates().forEach((key, value) -> System.out.printf("%s: %s\n", key, value));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
