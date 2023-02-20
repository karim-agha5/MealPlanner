package com.example.mealplanner.data.repositories;

import com.example.mealplanner.data.api.responses.MealsNetworkCallBack;
import com.example.mealplanner.data.datasource.meals.impl.MealsForSpecificAreaRemoteRepo;


public class MealsForAreaRepository {

    private MealsForSpecificAreaRemoteRepo remoteRepo;

    private final String TAG = "Exception";

    public MealsForAreaRepository() {
        remoteRepo = new MealsForSpecificAreaRemoteRepo();
    }


    public void getAllMeals(String country,  MealsNetworkCallBack mealsNetworkCallBack){
        remoteRepo.getAllMeals(country, mealsNetworkCallBack);

//        MutableLiveData<DataLayerResponse<ArrayList<Meal>>> response =
//                new MutableLiveData<>();
//
//        DataLayerResponse<ArrayList<Meal>> dataLayerResponse = new DataLayerResponse<>();
//
//        Observable<AreaListResponse> areaMealsResponseObservable =
//                mealsForSpecificAreaService.getAllMeals();
//        areaMealsResponseObservable.subscribe(
//                e -> {
//                    dataLayerResponse.setStatus(Status.SUCCESS);
//                    dataLayerResponse.setWrappedResponse(e.getMeal());
//                    response.postValue(dataLayerResponse);
//                    Log.i(TAG, "getAllMeals: "+e.getMeal().get(0).getName());
//                },
//                error -> {
//                    dataLayerResponse.setStatus(Status.FAILURE);
//                    dataLayerResponse.setMessage("Unable to retrieve meals for you.");
//                    response.postValue(dataLayerResponse);
//                }
//        );
//
//        return response;
    }
}
