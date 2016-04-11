package mock.brains.basecomponents.core.api;

import java.util.ArrayList;

import mock.brains.basecomponents.core.model.User;
import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {
    /**
     * JSONPlaceholder API
     *
     * @link http://http://jsonplaceholder.typicode.com/
     */

    @GET("users")
    Observable<ArrayList<User>> getUserList();

}
