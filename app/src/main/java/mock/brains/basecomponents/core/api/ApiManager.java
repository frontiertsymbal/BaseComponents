package mock.brains.basecomponents.core.api;

import mock.brains.basecomponents.core.model.User;
import rx.Observable;

public class ApiManager {

    private final ApiInterface apiInterface;

    public ApiManager(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    //****************************************************************************************************************************************************************************//

    public Observable<User> getUsers() {
        return apiInterface.getUserList()
                .flatMap(Observable::from);
    }
}