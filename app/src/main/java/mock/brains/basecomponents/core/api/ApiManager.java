package mock.brains.basecomponents.core.api;

import timber.log.Timber;

public class ApiManager {

    private final ApiInterface apiInterface;

    public ApiManager(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    //****************************************************************************************************************************************************************************//

    // TODO AlexTsymbal: implement methods for work with API

    //Sample method
    public void helloApi() {
        Timber.e("ApiInterface enabled = %1$s", String.valueOf(apiInterface != null));
    }
}