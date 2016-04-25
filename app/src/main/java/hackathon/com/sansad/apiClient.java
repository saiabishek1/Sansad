package hackathon.com.sansad;

import hackathon.com.sansad.models.api.AddReview.AddReviewModel;
import hackathon.com.sansad.models.api.EditDetailsModel;
import hackathon.com.sansad.models.api.GCMModel;
import hackathon.com.sansad.models.api.LoginModel;
import hackathon.com.sansad.models.api.SignupModel;
import hackathon.com.sansad.models.api.UserInfoModel;
import hackathon.com.sansad.models.api.getReviews.GetReviewsModel;
import hackathon.com.sansad.models.chat.PushResponse;
import hackathon.com.sansad.models.images.ImageDownloadCallback;
import hackathon.com.sansad.models.images.ImageUploadCallback;
import hackathon.com.sansad.models.mp.MpResponse;
import hackathon.com.sansad.models.places.PlacesModel;
import hackathon.com.sansad.models.tags.TagsModel;
import hackathon.com.sansad.models.user.ChangePasswordModel;
import hackathon.com.sansad.models.user.ResetPasswordModel;
import hackathon.com.sansad.models.userids.UserIdsModel;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

/**
 * Created by utk994 on 23-Apr-16.
 */
public class apiClient {
    private static final String USER_URL = "http://webloomsolutions.com/ieencmanipal.in/api2/";
    private static APIInterface apiInterface = null;

    public static APIInterface getAPI() {
        if (apiInterface == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(USER_URL)
                    .build();
            apiInterface = restAdapter.create(APIInterface.class);
        }
        return apiInterface;
    }

    public interface APIInterface {

        @POST("/?method=login&key=leuk12&secret=gammayz")
        void userLogin(@Query("username") String username, @Query("password") String password,
                       Callback<LoginModel> loginCallback);


        @POST("/?method=logout&key=leuk12&secret=gammayz")
        void userLogout(@Query("sessionid") String sessionid, @Query("token") String token,
                        Callback<SignupModel> signupCallback);


        @POST("/?method=signup&key=leuk12&secret=gammayz")
        void userSignup(@Query("name") String name, @Query("username") String username, @Query("email") String email,
                         @Query("gender") String gender,
                        @Query("password") String password, @Query("profile_img") String prifile_pic, Callback<SignupModel> signupCallback);

        @POST("/?method=changePassword&key=leuk12&secret=gammayz")
        void userChangePassword(@Query("sessionid") String sessionid, @Query("token") String token,
                                @Query("password") String password, @Query("newpassword") String newpassword,
                                Callback<ChangePasswordModel> changePasswordModelCallback);

        @POST("/?method=resetPassword&key=leuk12&secret=gammayz")
        void userResetPassword(@Query("username") String username, @Query("email") String email,
                               Callback<ResetPasswordModel> resetPasswordModelCallback);


        @POST("/?method=getUserInfo&key=leuk12&secret=gammayz")
        void getUserInfoFromId(@Query("sessionid") String sessionid, @Query("token") String token, @Query("user") String id,
                               Callback<UserInfoModel> userInfoModelCallback);

        @POST("/?method=getUserInfo&key=leuk12&secret=gammayz")
        void getCurrentUserInfo(@Query("sessionid") String sessionid, @Query("token") String token, Callback<UserInfoModel> userInfoModelCallback);

        @POST("/?method=editUserDetails&key=leuk12&secret=gammayz")
        void editUserDetails(@Query("sessionid") String sessionid, @Query("token") String token, @Query("name") String name,
                             @Query("email") String email,
                             @Query("phone") String phone, @Query("location") String location,
                             @Query("gender") String gender, @Query("image") String image, Callback<EditDetailsModel> editDetailsModelCallback);


        @POST("/?method=getPlaces&key=leuk12&secret=gammayz")
        void getPlaces(@Query("sessionid") String sessionid, @Query("token") String token, @Query("orderby") String orderby,
                       Callback<PlacesModel> placesCallback);


        @POST("/?method=updateGCMRegID&key=leuk12&secret=gammayz")
        void updateGCMRegID(@Query("sessionid") String sessionid, @Query("token") String token, @Query("gcmRegID") String gcmRegID,
                            Callback<GCMModel> loginCallback);

        @POST("/?method=getUserMessages&key=leuk12&secret=gammayz")
        void getUserMessages(@Query("sessionid") String sessionid, @Query("token") String token, Callback<UserIdsModel> ids);

        @POST("/?method=getAllUsers&key=leuk12&secret=gammayz")
        void getAllUsers(@Query("sessionid") String sessionid, @Query("token") String token, Callback<UserIdsModel> ids);


        @POST("/?method=getReviews&key=leuk12&secret=gammayz")
        void getReviews(@Query("sessionid") String sessionid, @Query("token") String token, @Query("place_id") String place_id,
                        Callback<GetReviewsModel> getReviewsModelCallback);

        @POST("/?method=addReview&key=leuk12&secret=gammayz")
        void addReview(@Query("sessionid") String sessionid, @Query("token") String token, @Query("place_id") String place_id,
                       @Query("review") String review, @Query("rating") String rating, Callback<AddReviewModel> addReviewModelCallback);



        @POST("/?method=updateViewCount&key=leuk12&secret=gammayz")
        void updateViewCount(@Query("sessionid") String sessionid, @Query("token") String token, @Query("place_id") String placeId,
                             Callback<AddReviewModel> updateViewsCallback);


        @POST("/?method=sendPush&key=leuk12&secret=gammayz")
        void sendPush(@Query("sessionid") String sessionid, @Query("token") String token, @Query("message") String message,
                      @Query("user") String receiver, Callback<PushResponse> callback);



        @GET("/?method=getPlacesLazy&key=leuk12&secret=gammayz")
        void getPlacesLazy(@Query("sessionid") String sessionid, @Query("token") String token,
                           @Query("start") String start, Callback<PlacesModel> placesModelCallback);

        @GET("/?method=getGallery&key=leuk12&secret=gammayz")
        void getGallery(@Query("sessionid") String sessionid, @Query("token") String token,
                        @Query("place_id") String start, Callback<ImageDownloadCallback> imageDownloadCallback);

        @Multipart
        @POST("/?method=imageUpload&key=leuk12&secret=gammayz")
        void imageUpload(@Query("sessionid") String sessionid, @Query("token") String token,
                         @Part("image") TypedFile image, @Query("place_id") String place_id, Callback<ImageUploadCallback> response);


        @POST("/?method=updateNewsHits&key=leuk12&secret=gammayz")
        void updateNewsHits(@Query("sessionid") String sessionid, @Query("token") String token, @Query("news_id") String news_id
                , Callback<AddReviewModel> callback);


        @POST("/?method=getMp&key=leuk12&secret=gammayz")
        void getMp(@Query("sessionid") String sessionid, @Query("token") String token
                , Callback<MpResponse> callback);

        @POST("/?method=getTags&key=leuk12&secret=gammayz")
        void getTags(@Query("sessionid") String sessionid, @Query("token") String token
                , Callback<TagsModel> callback);

    }
}


